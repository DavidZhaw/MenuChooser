package ch.zhaw.iwi.alcoholtester.model.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.alcoholtester.model.project.AbstractEntity;
import ch.zhaw.iwi.alcoholtester.model.task.Task;

@Entity
public class Work extends AbstractEntity implements Comparable<Work> {

	private String name;
	
	private Date evtDate;
		
	private Double duration;
	
	@ManyToOne
	private Task task;

	@Column(length = COMMENT_FIELD_LENGTH)
	private String comments;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEvtDate() {
		return evtDate;
	}

	public void setEvtDate(Date evtDate) {
		this.evtDate = evtDate;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int compareTo(Work o) {
		int nameComparison = this.getEvtDate().compareTo(o.getEvtDate());
		if (nameComparison != 0) {
			return nameComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

}
