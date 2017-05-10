package ch.zhaw.iwi.iwitask.model.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.iwitask.model.project.AbstractEntity;

@Entity
public class MonthlyWork extends AbstractEntity implements Comparable<MonthlyWork> {

	private Integer month;
	
	private Integer year;
	
	private Double duration;
	
	@ManyToOne
	private Person person;
	
	@Column(length = COMMENT_FIELD_LENGTH)
	private String comments;
	
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int compareTo(MonthlyWork o) {
		int nameComparison = (this.getYear()).compareTo(o.getYear());
		if (nameComparison != 0) {
			int nameComparison2 = this.getMonth().compareTo(o.getMonth());
			if (nameComparison2 != 0) {
				return nameComparison2;
			}
		}
		return this.getKey().compareTo(o.getKey());
	}

}
