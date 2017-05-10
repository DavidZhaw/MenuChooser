package ch.zhaw.iwi.alcoholtester.model.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.alcoholtester.model.person.Person;
import ch.zhaw.iwi.alcoholtester.model.task.Task;

@Entity
public class Project extends AbstractEntity implements Comparable<Project> {

	private String name;
	
	private Date evtBegin;
	
	private Date evtEnd;
	
	@ManyToMany(mappedBy = "projects")
	private Set<Person> persons = new HashSet<>();
	
	@OneToMany(mappedBy = "project")
	private List<Task> tasks = new ArrayList<>();

	@Column(length = COMMENT_FIELD_LENGTH)
	private String comments;
	
	public Set<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEvtBegin() {
		return evtBegin;
	}

	public void setEvtBegin(Date evtBegin) {
		this.evtBegin = evtBegin;
	}

	public Date getEvtEnd() {
		return evtEnd;
	}

	public void setEvtEnd(Date evtEnd) {
		this.evtEnd = evtEnd;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int compareTo(Project o) {
		int nameComparison = this.getName().compareTo(o.getName());
		if (nameComparison != 0) {
			return nameComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

}
