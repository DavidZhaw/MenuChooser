package ch.zhaw.iwi.alcoholtester.model.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.alcoholtester.model.project.AbstractEntity;
import ch.zhaw.iwi.alcoholtester.model.project.Project;
import ch.zhaw.iwi.alcoholtester.model.task.Task;
import ch.zhaw.iwi.alcoholtester.server.json.ExcludeFromJson;

@Entity
public class Person extends AbstractEntity implements Comparable<Person> {

	private String lastName;

	private String firstName;
	
	private String email;
	
	@ManyToMany
	@ExcludeFromJson
	@JoinTable(name = "Person_Projects", inverseJoinColumns = @JoinColumn(name = "project_key"))
	private Set<Project> projects = new HashSet<>();

	@OneToMany(mappedBy = "person")
	private List<Task> tasks = new ArrayList<>();
	
	@OneToMany(mappedBy = "person")
	private List<MonthlyWork> monthlyWorks = new ArrayList<>();

	public String getLastName() {
		return lastName;
	}
	
	public Set<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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

	@Column(length = COMMENT_FIELD_LENGTH)
	private String comments;

	@Override
	public int compareTo(Person o) {
		int nameComparison = (this.getLastName() + this.getFirstName()).compareTo(o.getLastName() + o.getFirstName());
		if (nameComparison != 0) {
			return nameComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

}
