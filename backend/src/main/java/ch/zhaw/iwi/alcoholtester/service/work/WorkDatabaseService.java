package ch.zhaw.iwi.alcoholtester.service.work;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.alcoholtester.model.work.Work;
import ch.zhaw.iwi.alcoholtester.model.work.Work_;
import ch.zhaw.iwi.alcoholtester.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.PathListEntry;

public class WorkDatabaseService extends AbstractCrudDatabaseService<Work, Long> {

	@Override
	public Class<Work> getEntityClass() {
		return Work.class;
	}

	@Override
	public void createPathListEntry(Work entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		entry.getDetails().add(String.format("%01.1f", entity.getDuration())+"h");
	}

	@Override
	protected void orderBy(Root<Work> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(Work_.evtDate)));
	}

}