package vlocity.project.plans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Plan plan = new Main().createPlan();
		System.out.println("Plan: " + plan.getPlan());

		System.out.println("Task Size: " + plan.getTasks().size());
		for (Task task : plan.getTasks()) {
			System.out.println("\tTask: " + task.getTaskName());
			System.out.println("\t\tDate: " + task.getStartDate() + " to " + task.getEndDate());

			if (task.getDependency() != null) {// Iterate dependencies
				System.out.println("\t\tTask Dependencies");
				System.out.print("\t\t");
				for (Task taskDep : task.getDependency()) {
					System.out.print(taskDep.getTaskName() + " ");
				}
			}

		}
	}

	private Plan createPlan() {
		boolean loopCreateTask = true;
		System.out.print("Enter Plan Name: ");
		Scanner scanner = new Scanner(System.in);
		String planName = scanner.nextLine();
		ArrayList<Task> tasks = new ArrayList<Task>();
		tasks.add(createTask());
		Plan plan = new Plan(planName, tasks);

		do {
			System.out.print("Create Task?(YES/NO): ");
			String isCreate = scanner.nextLine();
			if ("YES".equalsIgnoreCase(isCreate)) {
				Task task = createTask();
				if (task != null) {
					plan.getTasks().add(task);
				}
				createDependecies(plan, task);
			} else {
				loopCreateTask = false;
			}
			loopCreateTask = "NO".equalsIgnoreCase(isCreate) ? false : true;
		} while (loopCreateTask);

		return plan;
	}

	private Task createTask() {
		boolean validStrDate = false, validEndDate = false;
		String strStartDate = "", strEndDate = "";

		System.out.print("Enter Task Name: ");
		Scanner scanner = new Scanner(System.in);
		String taskName = scanner.nextLine();

		while (!validStrDate) {
			System.out.print("Enter Start Date(yyyy-MM-dd): ");
			strStartDate = scanner.nextLine();
			validStrDate = Util.validDate(strStartDate);
		}
		while (!validEndDate) {
			System.out.print("Enter End Date(yyyy-MM-dd): ");
			strEndDate = scanner.nextLine();
			validEndDate = Util.validDate(strEndDate);
		}

		Date startDate = Util.getDate(strStartDate);
		Date endDate = Util.getDate(strEndDate);

		if (!Util.validDuration(startDate, endDate)) {
			System.out.println("Invalid Date Duration");
			return null;
		}

		return new Task(taskName, false, startDate, endDate);
	}

	private void createDependecies(Plan plan, Task task) {
		if (plan.getTasks().size() < 1) // When no available task can depend
			return;
		System.out.print("Wan't to add dependecies?(yes/no): ");
		Scanner scanner = new Scanner(System.in);
		boolean hasDependency = "yes".equalsIgnoreCase(scanner.nextLine()) ? true : false;
		if (hasDependency) {
			loadAvailableDependecies(plan.getTasks(), task);
			System.out.print("Type task name to depend: ");
			attachDependency(plan.getTasks(), task, scanner.nextLine());
		}
		return;
	}

	private void loadAvailableDependecies(ArrayList<Task> availableTasks, Task task) {
		System.out.println("\tCurrent Task: " + task.getTaskName());
		System.out.println("\t\tDate: " + task.getStartDate() + " to " + task.getEndDate());
		for (Task availableTask : availableTasks) {
			if (task != availableTask) {
				System.out.println("\tTask: " + availableTask.getTaskName());
				System.out.println("\t\tDate: " + availableTask.getStartDate() + " to " + availableTask.getEndDate());
			}
		}
	}

	private void attachDependency(List<Task> tasks, Task task, String taskName) {
		boolean validTaskNameInput = false;
		for (Task availableTask : tasks) {
			if (availableTask.getTaskName().equalsIgnoreCase(taskName) && availableTask != task) {
				validTaskNameInput = true;
				if (validDependecies(task, availableTask)) {
					if (task.getDependency() == null) {
						ArrayList<Task> addDepTasks = new ArrayList<>();
						addDepTasks.add(availableTask);
						task.setDependency(addDepTasks);
					} else {
						task.getDependency().add(availableTask);
					}
				} else {
					adjustTaskDate(task, availableTask);
				}
			}
		}
		if(validTaskNameInput){
			System.out.println("Sorry that task is not an OPTION!");
		}
	}

	private boolean validDependecies(Task dependent, Task taskNeed) {
		System.out.println();
		return dependent.getStartDate().after(taskNeed.getEndDate());
	}

	private void adjustTaskDate(Task task, Task taskNeed) {
		boolean loopAdjustDate = true;

		while (loopAdjustDate) {
			System.out.print("Adjust Current Task Date!()(yes/no): ");
			Scanner scanner = new Scanner(System.in);
			boolean isAdjustDate = "yes".equalsIgnoreCase(scanner.nextLine()) ? true : false;
			if (isAdjustDate) {
				boolean validStrDate = false, validEndDate = false;
				String strStartDate = "", strEndDate = "";

				while (!validStrDate) {
					System.out.print("Enter Start Date(yyyy-MM-dd): ");
					strStartDate = scanner.nextLine();
					validStrDate = Util.validDate(strStartDate);
				}
				while (!validEndDate) {
					System.out.print("Enter End Date(yyyy-MM-dd): ");
					strEndDate = scanner.nextLine();
					validEndDate = Util.validDate(strEndDate);
				}

				Date startDate = Util.getDate(strStartDate);
				Date endDate = Util.getDate(strEndDate);
				task.setStartDate(startDate);
				task.setEndDate(endDate);
				if (Util.validDuration(startDate, endDate)) {
					if (validDependecies(task, taskNeed)) {
						loopAdjustDate = false;
						if (task.getDependency() == null) {
							ArrayList<Task> addDepTasks = new ArrayList<>();
							addDepTasks.add(taskNeed);
							task.setDependency(addDepTasks);
						} else {
							task.getDependency().add(taskNeed);
						}
					}
				} else {
					System.out.println("Invalid Date Duration");
				}
			}else{
				loopAdjustDate = false;
			}
		}
	}
}
