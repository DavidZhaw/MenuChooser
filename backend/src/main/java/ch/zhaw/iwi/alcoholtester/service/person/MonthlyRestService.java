package ch.zhaw.iwi.alcoholtester.service.person;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.alcoholtester.service.AbstractCrudRestService;

public class MonthlyRestService extends AbstractCrudRestService<MonthlyWorkDatabaseService, Long> {
	
	@Inject
	public MonthlyRestService(Injector injector) {
		super(injector, MonthlyWorkDatabaseService.class);
	}
	
}
