package ch.zhaw.iwi.alcoholtester.service.person;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.alcoholtester.model.person.Person;
import ch.zhaw.iwi.alcoholtester.model.person.Person_;
import ch.zhaw.iwi.alcoholtester.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.PathListEntry;

public class PersonDatabaseService extends AbstractCrudDatabaseService<Person, Long> {

	@Override
	public Class<Person> getEntityClass() {
		return Person.class;
	}

	@Override
	public void createPathListEntry(Person entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getLastName() + " " + entity.getFirstName());
		entry.getDetails().add(entity.getEmail());
	}

	@Override
	protected void orderBy(Root<Person> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(Person_.lastName)));
		orderList.add(getCriteriaBuilder().asc(root.get(Person_.firstName)));
	}
	

}