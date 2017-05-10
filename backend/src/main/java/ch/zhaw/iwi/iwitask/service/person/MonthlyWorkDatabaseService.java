package ch.zhaw.iwi.iwitask.service.person;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;

import ch.zhaw.iwi.iwitask.model.person.MonthlyWork;
import ch.zhaw.iwi.iwitask.model.person.MonthlyWork_;
import ch.zhaw.iwi.iwitask.model.task.Task;
import ch.zhaw.iwi.iwitask.model.work.Work;
import ch.zhaw.iwi.iwitask.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.iwitask.service.ColorUtility;
import ch.zhaw.iwi.iwitask.service.PathListEntry;
import ch.zhaw.iwi.iwitask.service.task.TaskDatabaseService;
import ch.zhaw.iwi.iwitask.service.work.WorkDatabaseService;

public class MonthlyWorkDatabaseService extends AbstractCrudDatabaseService<MonthlyWork, Long> {

	@Inject
	WorkDatabaseService workDatabaseService;

	@Inject
	TaskDatabaseService taskDatabaseService;
	
	@Override
	public Class<MonthlyWork> getEntityClass() {
		return MonthlyWork.class;
	}

	@Override
	public void createPathListEntry(MonthlyWork entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getMonth() + "/" + entity.getYear() + " (" + String.format("%01.1f", entity.getDuration()) + "h)");

		// calculate work planned
		Calendar cal = Calendar.getInstance();
		double workPlanned = 0d;
		for (Task task : taskDatabaseService.list()) {
			if (task.getPerson().equals(entity.getPerson())) {
				cal.setTime(task.getEvtBegin());
				int beginYear = cal.get(Calendar.YEAR);
				int beginMonth = cal.get(Calendar.MONTH) + 1; // starts with 0
				int beginDay = cal.get(Calendar.DAY_OF_MONTH);
				
				cal.setTime(task.getEvtEnd());
				int endYear = cal.get(Calendar.YEAR);
				int endMonth = cal.get(Calendar.MONTH) + 1; // starts with 0
				int endDay = cal.get(Calendar.DAY_OF_MONTH);

				if (beginYear <= entity.getYear() && endYear >= entity.getYear()) {
					if (beginMonth <= entity.getMonth() && endMonth >= entity.getMonth()) {
						// task includes this month
						Calendar cal2 = new GregorianCalendar(entity.getYear(), entity.getMonth() - 1, 1);
						int daysCovered = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);
						int daysOfTask = (int)( (task.getEvtEnd().getTime() - task.getEvtBegin().getTime()) / (1000 * 60 * 60 * 24)) + 1;
						
						if (endYear == entity.getYear() && endMonth == entity.getMonth()) {
							daysCovered = daysCovered - (daysCovered - endDay);
						}
						if (beginYear == entity.getYear() && beginMonth == entity.getMonth()) {
							daysCovered = daysCovered - beginDay + 1;
						}
						workPlanned = (double) daysCovered / (double) daysOfTask * task.getPlanned();
					}
				}
			}
		}
		entry.getDetails().add("Planned: " + String.format("%01.1f", workPlanned) + "h");
		
		// calculate work done
		double workDone = 0d;
		for (Work work : workDatabaseService.list()) {
			if (work.getTask().getPerson().equals(entity.getPerson())) {
				cal.setTime(work.getEvtDate());
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1; // starts with 0
				if (year == entity.getYear() && month == entity.getMonth()) {
					workDone += work.getDuration();
				}
			}
		}

		entry.getDetails().add("Worked: " + String.format("%01.1f", workDone) + "h");
		
		if (workPlanned > entity.getDuration()) {
			entry.setColor(ColorUtility.exceeded);
		}
		if (workDone > entity.getDuration()) {
			entry.setColor(ColorUtility.overtime);
		}
	}

	@Override
	protected void orderBy(Root<MonthlyWork> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(MonthlyWork_.year)));
		orderList.add(getCriteriaBuilder().asc(root.get(MonthlyWork_.month)));
	}

}