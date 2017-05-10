package ch.zhaw.iwi.alcoholtester.service.work;

import static spark.Spark.get;

import com.google.common.primitives.Longs;
import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.alcoholtester.model.task.Task;
import ch.zhaw.iwi.alcoholtester.service.AbstractCrudRestService;
import ch.zhaw.iwi.alcoholtester.service.task.TaskDatabaseService;

public class WorkRestService extends AbstractCrudRestService<WorkDatabaseService, Long> {
	
	@Inject
	TaskDatabaseService taskDatabaseService;
	
	@Inject
	public WorkRestService(Injector injector) {
		super(injector, WorkDatabaseService.class);
	}
	
	@Override
	protected void initGet() {
		super.initGet();
		
		get("services/task/:taskKey/work", (req, res) -> {
			Long taskKey = Longs.tryParse(req.params("taskKey"));
			Task project = taskDatabaseService.read(taskKey);
			return getCrudDatabaseService().createPathList(project.getWorks());
		}, getJsonTransformer());
	}
		
}
