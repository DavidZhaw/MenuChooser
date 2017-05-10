package ch.zhaw.iwi.indoortracking.service.badge;

import ch.zhaw.iwi.alcoholtester.model.project.Project;
import ch.zhaw.iwi.alcoholtester.service.project.ProjectDatabaseService;
import ch.zhaw.iwi.indoortracking.service.AbstractCrudDatabaseServiceTest;

public class BadgeDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<Project, Long> {
	
	@Override
	protected Class<Project> getEntity() {
		return Project.class;
	}
	
	@Override
	protected Class<ProjectDatabaseService> getService() {
		return ProjectDatabaseService.class;
	}

}
