package ch.zhaw.iwi.indoortracking.service.badge;

import ch.zhaw.iwi.alcoholtester.model.project.Project;
import ch.zhaw.iwi.alcoholtester.service.project.ProjectRestService;
import ch.zhaw.iwi.indoortracking.service.AbstractCrudRestServiceTest;

public class BadgeRestServiceTest extends AbstractCrudRestServiceTest<Project, Long> {

	@Override
	protected Class<?> getService() {
		return ProjectRestService.class;
	}

	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}
	
}
