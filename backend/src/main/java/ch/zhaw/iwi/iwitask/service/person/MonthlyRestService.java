package ch.zhaw.iwi.iwitask.service.person;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.iwitask.service.AbstractCrudRestService;

public class MonthlyRestService extends AbstractCrudRestService<MonthlyWorkDatabaseService, Long> {
	
	@Inject
	public MonthlyRestService(Injector injector) {
		super(injector, MonthlyWorkDatabaseService.class);
	}
	
}
