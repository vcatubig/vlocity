package vlocity.project.plans;

import java.util.ArrayList;
import java.util.Date;

public class Task {
	private String taskName;
	private boolean hasDepTask;
	private Date startDate = new Date();
	private Date endDate = new Date();
	private ArrayList<Task> dependency; 
	
	public Task(String taskName, boolean hasDepTask, Date startDate, Date endDate) {
		super();
		this.taskName = taskName;
		this.hasDepTask = hasDepTask;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public boolean isHasDepTask() {
		return hasDepTask;
	}
	public void setHasDepTask(boolean hasDepTask) {
		this.hasDepTask = hasDepTask;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Task> getDependency() {
		return dependency;
	}

	public void setDependency(ArrayList<Task> dependency) {
		this.dependency = dependency;
	}
}
