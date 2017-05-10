package ch.zhaw.iwi.alcoholtester.service.project;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.alcoholtester.model.project.Project;
import ch.zhaw.iwi.alcoholtester.model.project.Project_;
import ch.zhaw.iwi.alcoholtester.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.ColorUtility;
import ch.zhaw.iwi.alcoholtester.service.DateUtility;
import ch.zhaw.iwi.alcoholtester.service.PathListEntry;

public class ProjectDatabaseService extends AbstractCrudDatabaseService<Project, Long> {

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public void createPathListEntry(Project entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		entry.getDetails().add(DateUtility.formatDate(entity.getEvtBegin()) + " - " + DateUtility.formatDate(entity.getEvtEnd()));

		if (!DateUtility.checkDateOrder(new Date(), entity.getEvtEnd())) {
			entry.setColor(ColorUtility.inactive);
		}
	}

	@Override
	protected void orderBy(Root<Project> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(Project_.name)));
	}

}