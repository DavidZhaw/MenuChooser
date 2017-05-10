package ch.zhaw.iwi.indoortracking.service.badge;

import ch.zhaw.iwi.indoortracking.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.iwitask.model.project.Project;
import ch.zhaw.iwi.iwitask.service.project.ProjectRestService;

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
