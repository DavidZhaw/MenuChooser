package ch.zhaw.iwi.indoortracking.service.badge;

import ch.zhaw.iwi.indoortracking.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.iwitask.model.project.Project;
import ch.zhaw.iwi.iwitask.service.project.ProjectDatabaseService;

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
