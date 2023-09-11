package model;

public class Task {
	private String taskName; 
	private String deadline;
	private boolean status;
	private String priority;
	private int id;
	
	public Task(String name, String date, String priority, boolean status, int id) {
		this.taskName = name;
		this.deadline = date;
		this.priority = priority;
		this.status = status;
		this.id = id;
	}
	
	public Task(String name, String date, String priority, boolean status) {
		this.taskName = name;
		this.deadline = date;
		this.priority = priority;
		this.status = status;
	}
	
	public Task(boolean status, int id) {
		this.status = status;
		this.id = id;
	}
	
//	public Task() {
//		this.name = "Task";
//		this.date = "Deadline";
//		this.status = false;
//		this.priority = "low"
//		this.id = "0";
//	}

	public String getName() {
		return taskName;
	}

	public void setName(String name) {
		this.taskName = name;
	}

	public String getDate() {
		return deadline;
	}

	public void setDate(String date) {
		this.deadline = date;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
//	public String toString(){
//		String task = "\n Task name : " + name + "\n Deadline : " + date + "\n Priority : "+ priority +"\n";
//		return task; 
//	}
}
