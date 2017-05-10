package ch.zhaw.iwi.alcoholtester.server;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ch.zhaw.iwi.alcoholtester.Main;
import ch.zhaw.iwi.alcoholtester.model.person.MonthlyWork;
import ch.zhaw.iwi.alcoholtester.model.person.Person;
import ch.zhaw.iwi.alcoholtester.model.project.Project;
import ch.zhaw.iwi.alcoholtester.model.task.Task;
import ch.zhaw.iwi.alcoholtester.model.work.Work;
import ch.zhaw.iwi.alcoholtester.service.AbstractDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.DateUtility;
import ch.zhaw.iwi.alcoholtester.service.person.MonthlyWorkDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.person.PersonDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.project.ProjectDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.task.TaskDatabaseService;
import ch.zhaw.iwi.alcoholtester.service.work.WorkDatabaseService;

public class TestDataService extends AbstractDatabaseService {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	@Inject
	private ProjectDatabaseService projectDatabaseService;

	@Inject
	private TaskDatabaseService taskDatabaseService;

	@Inject
	private PersonDatabaseService personDatabaseService;
	
	@Inject
	private WorkDatabaseService workDatabaseService;
	
	@Inject
	private MonthlyWorkDatabaseService monthlyWorkDatabaseService;
	
	@SuppressWarnings("unused")
	@Transactional
	public void create() {
		if (!projectDatabaseService.list().isEmpty()) {
			// test data already created
			return;
		}
		
		Person adrian = createDemoPerson("Adrian", "Moser", "mosa@zhaw.ch");
		Person alex = createDemoPerson("Alexandre", "de Spindler", "desa@zhaw.ch");
		Person david = createDemoPerson("David", "Grünert", "grud@zhaw.ch");
		Person max = createDemoPerson("Max", "Meisterhans", "meix@zhaw.ch");
		Person moritz = createDemoPerson("Moritz", "Schneider", "scmo@zhaw.ch");
		Person john = createDemoPerson("John", "Brush", "brsh@zhaw.ch");
		Person philipp = createDemoPerson("Philipp", "Stalder", "stlr@zhaw.ch");
		Person sebastian = createDemoPerson("Sebastian", "Stephan", "stpn@zhaw.ch");

		Project expact = createDemoProject("ExpAct", DateUtility.parseDate("01012016", "ddMMyyyy"), DateUtility.parseDate("31122016", "ddMMyyyy"), john, adrian, alex);
		Project faircare = createDemoProject("FairCare", DateUtility.parseDate("01012016", "ddMMyyyy"), DateUtility.parseDate("31122017", "ddMMyyyy"), john, max, alex);
		Project usz = createDemoProject("USZ Indoor Tracking", DateUtility.parseDate("01022017", "ddMMyyyy"), DateUtility.parseDate("31032017", "ddMMyyyy"), adrian, philipp);
		Project versionierung = createDemoProject("KTI Versionierung", DateUtility.parseDate("01012015", "ddMMyyyy"), DateUtility.parseDate("31122017", "ddMMyyyy"), david, max);
		
		Task task1 = createDemoTask("Testlauf ZHAW", usz, adrian, DateUtility.parseDate("01022017", "ddMMyyyy"), DateUtility.parseDate("31032017", "ddMMyyyy"), null, 200d);
		Task task2 = createDemoTask("Testlauf USZ", usz, philipp, DateUtility.parseDate("01032017", "ddMMyyyy"), DateUtility.parseDate("31032017", "ddMMyyyy"), null, 100d);
		Task task3 = createDemoTask("GUI", versionierung, max, DateUtility.parseDate("01012016", "ddMMyyyy"), DateUtility.parseDate("01012018", "ddMMyyyy"), null, 20.5d);
		Task task4 = createDemoTask("GUI", faircare, max, DateUtility.parseDate("01012016", "ddMMyyyy"), DateUtility.parseDate("01012018", "ddMMyyyy"), null, 50d);
		Task task5 = createDemoTask("Implementierung", versionierung, david, DateUtility.parseDate("01012016", "ddMMyyyy"), DateUtility.parseDate("01012018", "ddMMyyyy"), DateUtility.parseDate("01012017", "ddMMyyyy"), 20d);
				
		Work work1 = createDemoWork("Arbeit1", DateUtility.parseDate("01022017", "ddMMyyyy"), task1, 115d);
		Work work2 = createDemoWork("Arbeit2", DateUtility.parseDate("01022017", "ddMMyyyy"), task1, 10d);
		Work work3 = createDemoWork("Arbeit3", DateUtility.parseDate("01032017", "ddMMyyyy"), task1, 20d);
		
		MonthlyWork monthlyWork1 = createDemoMonthlyWork(adrian, 2, 2017, 100d);
		MonthlyWork monthlyWork2 = createDemoMonthlyWork(adrian, 3, 2017, 100d);
		MonthlyWork monthlyWork3 = createDemoMonthlyWork(adrian, 4, 2017, 100d);
		MonthlyWork monthlyWork4 = createDemoMonthlyWork(adrian, 5, 2017, 120d);
		MonthlyWork monthlyWork5 = createDemoMonthlyWork(adrian, 6, 2017, 150d);
		
		logger.info("test data created");
	}
	
	private Project createDemoProject(String name, Date evtBegin, Date evtEnd, Person... persons) {
		Project project = new Project();
		project.setName(name);
		project.setEvtBegin(evtBegin);
		project.setEvtEnd(evtEnd);
		project.getPersons().addAll(Arrays.asList(persons));
		projectDatabaseService.create(project);
		for (Person person : persons) {
			person.getProjects().add(project);
		}
		return project;
	}
	
	private Task createDemoTask(String name, Project project, Person person, Date evtBegin, Date evtEnd, Date evtCompleted, Double planned) {
		Task task = new Task();
		task.setName(name);
		task.setEvtBegin(evtBegin);
		task.setEvtEnd(evtEnd);
		task.setEvtCompleted(evtCompleted);
		task.setProject(project);
		task.setPerson(person);
		task.setPlanned(planned);
		taskDatabaseService.create(task);
		return task;
	}

	private Person createDemoPerson(String firstName, String lastName, String email) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(email);
		personDatabaseService.create(person);
		return person;
	}
	
	private Work createDemoWork(String name, Date date, Task task, Double duration) {
		Work work = new Work();
		work.setName(name);
		work.setEvtDate(date);
		work.setTask(task);
		work.setDuration(duration);
		workDatabaseService.create(work);
		return work;
	}
	
	private MonthlyWork createDemoMonthlyWork(Person person, Integer month, Integer year, Double duration) {
		MonthlyWork monthlyWork = new MonthlyWork();
		monthlyWork.setPerson(person);
		monthlyWork.setMonth(month);
		monthlyWork.setYear(year);
		monthlyWork.setDuration(100d);
		monthlyWorkDatabaseService.create(monthlyWork);
		return monthlyWork;
	}
	
	
}