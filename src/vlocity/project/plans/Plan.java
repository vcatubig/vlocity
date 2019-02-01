package vlocity.project.plans;

import java.util.ArrayList;

public class Plan {
	private String plan;
	private ArrayList<Task> tasks;
	
	public Plan(String plan) {
		super();
		this.plan = plan;
	}
	
	public Plan(String plan, ArrayList<Task> tasks) {
		super();
		this.plan = plan;
		this.tasks = tasks;
	}
	
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
}
