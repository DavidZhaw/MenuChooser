package ch.zhaw.iwi.alcoholtester.service.task;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.alcoholtester.model.task.Task;
import ch.zhaw.iwi.alcoholtester.model.task.Task_;
import ch.zhaw.iwi.alcoholtester.model.work.Work;
import ch.zhaw.iwi.alcoholtester.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.ColorUtility;
import ch.zhaw.iwi.alcoholtester.service.PathListEntry;

public class TaskDatabaseService extends AbstractCrudDatabaseService<Task, Long> {

	@Override
	public Class<Task> getEntityClass() {
		return Task.class;
	}

	@Override
	public void createPathListEntry(Task entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());

		// calculate work done
		double done = 0d;
		for (Work work : entity.getWorks()) {
			done += work.getDuration();
		}

		entry.getDetails().add(String.format("%01.1f", done) + "/" + String.format("%01.1f", entity.getPlanned()) + "h");
		
		if (entity.getEvtCompleted() != null) {
			entry.setColor(ColorUtility.inactive);
		}
	}

	@Override
	protected void orderBy(Root<Task> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(Task_.evtBegin)));
	}

}