package ch.zhaw.iwi.iwitask.model.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ch.zhaw.iwi.iwitask.model.person.Person;
import ch.zhaw.iwi.iwitask.model.project.AbstractEntity;
import ch.zhaw.iwi.iwitask.model.project.Project;
import ch.zhaw.iwi.iwitask.model.work.Work;

@Entity
public class Task extends AbstractEntity implements Comparable<Task> {

	private String name;
	
	private Date evtBegin;
	
	private Date evtEnd;
	
	private Date evtCompleted;
	
	private Double planned;

	@Column(length = COMMENT_FIELD_LENGTH)
	private String comments;
	
	@ManyToOne
	private Person person;
	
	@ManyToOne
	private Project project;
	
	@OneToMany(mappedBy = "task")
	private List<Work> works = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public Date getEvtCompleted() {
		return evtCompleted;
	}

	public void setEvtCompleted(Date evtCompleted) {
		this.evtCompleted = evtCompleted;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public Double getPlanned() {
		return planned;
	}
	
	public void setPlanned(Double planned) {
		this.planned = planned;
	}
	
	public List<Work> getWorks() {
		return works;
	}
	
	public void setWorks(List<Work> works) {
		this.works = works;
	}
	
	@Override
	public int compareTo(Task o) {
		int nameComparison = this.getName().compareTo(o.getName());
		if (nameComparison != 0) {
			return nameComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

}
