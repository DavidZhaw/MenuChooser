package ch.zhaw.iwi.alcoholtester.service.task;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.List;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.alcoholtester.model.person.Person;
import ch.zhaw.iwi.alcoholtester.model.project.Project;
import ch.zhaw.iwi.alcoholtester.model.task.Task;
import ch.zhaw.iwi.alcoholtester.service.AbstractCrudRestService;
import ch.zhaw.iwi.alcoholtester.service.DateUtility;
import ch.zhaw.iwi.alcoholtester.service.PathListEntry;
import ch.zhaw.iwi.alcoholtester.service.person.PersonDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.project.ProjectDatabaseService;

public class TaskRestService extends AbstractCrudRestService<TaskDatabaseService, Long> {

	@Inject
	PersonDatabaseService personDatabaseService;

	@Inject
	ProjectDatabaseService projectDatabaseService;
	
	@Inject
	public TaskRestService(Injector injector) {
		super(injector, TaskDatabaseService.class);
	}
	
	@Override
	protected void initGet() {
		super.initGet();
		
		get("services/project/:projectKey/task", (req, res) -> {
			Long projectKey = Longs.tryParse(req.params("projectKey"));
			Project project = projectDatabaseService.read(projectKey);
			List<PathListEntry<Long>> result = new ArrayList<>();
			for (Task task : project.getTasks()) {
				PathListEntry<Long> entry = new PathListEntry<>();
				getCrudDatabaseService().createPathListEntry(task, entry);
				entry.getDetails().add(DateUtility.formatDate(task.getEvtBegin()));
				entry.getDetails().add(task.getPerson().getLastName());
				result.add(entry);
			}
			return result;
		}, getJsonTransformer());
		
		get("services/person/:personKey/task", (req, res) -> {
			Long personKey = Longs.tryParse(req.params("personKey"));
			Person person = personDatabaseService.read(personKey);
			List<PathListEntry<Long>> result = new ArrayList<>();
			for (Task task : person.getTasks()) {
				PathListEntry<Long> entry = new PathListEntry<>();
				getCrudDatabaseService().createPathListEntry(task, entry);
				entry.getDetails().add(DateUtility.formatDate(task.getEvtBegin()));
				entry.getDetails().add(task.getProject().getName());
				result.add(entry);
			}
			return result;
		}, getJsonTransformer());
	}
	
}
