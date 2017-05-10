package ch.zhaw.iwi.iwitask.service.person;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.iwitask.model.person.Person;
import ch.zhaw.iwi.iwitask.model.project.Project;
import ch.zhaw.iwi.iwitask.service.AbstractCrudRestService;
import ch.zhaw.iwi.iwitask.service.project.ProjectDatabaseService;

public class PersonRestService extends AbstractCrudRestService<PersonDatabaseService, Long> {

	@Inject
	private ProjectDatabaseService projectDatabaseService;
	
	@Inject
	public PersonRestService(Injector injector) {
		super(injector, PersonDatabaseService.class);
	}
	
	@Override
	protected void initGet() {
		super.initGet();
		
		get("services/project/:projectKey/person", (req, res) -> {
			Long projectKey = Longs.tryParse(req.params("projectKey"));
			if (projectKey == null) {
				return getCrudDatabaseService().createPathList(getCrudDatabaseService().list());
			}
			Project project = projectDatabaseService.read(projectKey);
			return getCrudDatabaseService().createPathList(project.getPersons());
		}, getJsonTransformer());
		
		get("services/project/:projectKey/person/:personKey", (req, res) -> {
			Long personKey = Long.parseLong(req.params("personKey"));
			Long projectKey = Long.parseLong(req.params("projectKey"));
			ProjectPersonView view = new ProjectPersonView();
			view.personKey = personKey;
			view.projectKey = projectKey;
			return view;
		}, getJsonTransformer());
	}
	
	@Override
	protected void initPost() {
		super.initPost();
		
		post("services/project/:projectKey/person", (req, res) -> {
			ProjectPersonView view = fromJson(req.body(), ProjectPersonView.class);
			Project project = projectDatabaseService.read(view.projectKey);
			Person person = getCrudDatabaseService().read(view.personKey);
			project.getPersons().add(person);
			person.getProjects().add(project);
			return projectDatabaseService.update(project) && getCrudDatabaseService().update(person);
		}, getJsonTransformer());
	}
	
	@Override
	protected void initPut() {
		super.initPut();
		
		put("services/project/:projectKey/person", (req, res) -> {
			ProjectPersonView view = fromJson(req.body(), ProjectPersonView.class);
			Project project = projectDatabaseService.read(view.projectKey);
			Person person = getCrudDatabaseService().read(view.personKey);
			project.getPersons().add(person);
			return projectDatabaseService.update(project);
		}, getJsonTransformer());
		
		put("services/project/:projectKey/person/:personKey", (req, res) -> {
			// nop
			return true;
		}, getJsonTransformer());
	}
	
	@Override
	protected void initDelete() {
		super.initDelete();
		
		delete("services/project/:projectKey/person/:personKey", (req, res) -> {
			Long projectKey = Long.parseLong(req.params("projectKey"));
			Long personKey = Long.parseLong(req.params("personKey"));
			Project project = projectDatabaseService.read(projectKey);
			Person person = getCrudDatabaseService().read(personKey);
			project.getPersons().remove(person);
			person.getProjects().remove(project);
			return projectDatabaseService.update(project) && getCrudDatabaseService().update(person);
		}, getJsonTransformer());		
	}
	
	private class ProjectPersonView {

		private Long projectKey;
		private Long personKey;
		
	}
	
}
