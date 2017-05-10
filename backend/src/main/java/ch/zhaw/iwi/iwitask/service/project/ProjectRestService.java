package ch.zhaw.iwi.iwitask.service.project;

import static spark.Spark.get;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.iwitask.model.person.Person;
import ch.zhaw.iwi.iwitask.service.AbstractCrudRestService;
import ch.zhaw.iwi.iwitask.service.person.PersonDatabaseService;

public class ProjectRestService extends AbstractCrudRestService<ProjectDatabaseService, Long> {

	@Inject
	private PersonDatabaseService personDatabaseService;
	
	@Inject
	public ProjectRestService(Injector injector) {
		super(injector, ProjectDatabaseService.class);
	}
	
	@Override
	protected void initGet() {
		super.initGet();
		
		get("services/person/:personKey/project", (req, res) -> {
			Long personKey = Longs.tryParse(req.params("personKey"));
			if (personKey == null) {
				return getCrudDatabaseService().createPathList(getCrudDatabaseService().list());
			}
			Person person = personDatabaseService.read(personKey);
			return getCrudDatabaseService().createPathList(person.getProjects());
		}, getJsonTransformer());
	}
	
}
