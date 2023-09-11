package implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import model.Task;

public class TaskImplementation implements TaskInterface{
	private String jdbcURL = "jdbc:mysql://localhost:3306/task_log";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Database#6526";

	private static final String CREATE_TASK = "insert into task (taskName, deadline, priority, status) values" + "(?,?,?,?)";
	private static final String READ_ALL_TASK = "select * from task";
	private static final String INPROGRESS_TASK = "select * from task where status = 0";
	private static final String ALL_COMPLETED_TASK = "select * from task where status = 1";
	private static final String READ_TASK_ByID = "select taskName,deadline,priority,status from task where id=?";
	private static final String UPDATE_TASK = "update task set taskName = ?, deadline = ?, priority = ? where id = ?";
	private static final String DELETE_TASK = "delete from task where id = ?";
	private static final String COMPLETE_TASK = "update task set status = 1 where id = ?";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	//Add task
	public void createTask(Task task) throws SQLException {
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TASK)){
			preparedStatement.setString(1, task.getName());
			preparedStatement.setString(2, task.getDate());
			preparedStatement.setString(3, task.getPriority());
			preparedStatement.setBoolean(4, task.getStatus());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Update Task
	public boolean updateTask(Task task) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TASK)){
			statement.setString(1, task.getName());
			statement.setString(2, task.getDate());
			statement.setString(3, task.getPriority());
			statement.setInt(4, task.getId());
			
			rowUpdated = statement.executeUpdate()>0;
		}
		return rowUpdated;
	}
	
	//Complete the task 
//	public boolean completeTask(Task task) throws SQLException {
//		boolean taskCompleted;
//		try(Connection connection = getConnection();
//				PreparedStatement statement = connection.prepareStatement(COMPLETE_TASK)){
//			statement.setInt(1, task.getId());
//			
//			taskCompleted = statement.executeUpdate()>0;
//		}
//		return taskCompleted;
//	}
	
	//Complete the task by id
	public boolean completeTask(int id) throws SQLException {
		boolean taskCompleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(COMPLETE_TASK)){
			statement.setInt(1, id);
			
			taskCompleted = statement.executeUpdate()>0;
		}
		return taskCompleted;
	}
	
	//Select user by id
	public Task selctTask(int id) {
		Task task = null;
	
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(READ_TASK_ByID);){
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();  
		
			while(rs.next()) {
				String taskName = rs.getString("taskName");
				String deadline = rs.getString("deadline");
				String priority = rs.getString("priority");
				Boolean status = rs.getBoolean("status");
				task = new Task(taskName, deadline, priority, status, id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	//Select all tasks
	public List<Task> selectAllTasks() {
		List<Task> taskList = new ArrayList<Task>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_TASK);){
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String taskName = rs.getString("taskName");
				String deadline = rs.getString("deadline");
				String priority = rs.getString("priority");
				Boolean status = rs.getBoolean("status");
				taskList.add(new Task(taskName, deadline, priority, status, id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskList;
	}
	
	//Select In-progress tasks
	public List<Task> selectInProgressTasks() {
		List<Task> inProgressTaskList = new ArrayList<Task>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INPROGRESS_TASK);){
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String taskName = rs.getString("taskName");
				String deadline = rs.getString("deadline");
				String priority = rs.getString("priority");
				Boolean status = rs.getBoolean("status");
				inProgressTaskList.add(new Task(taskName, deadline, priority, status, id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inProgressTaskList;
	}
	
	//Select Completed tasks
	public List<Task> selectCompletedTasks() {
		List<Task> completedTaskList = new ArrayList<Task>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ALL_COMPLETED_TASK);){
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String taskName = rs.getString("taskName");
				String deadline = rs.getString("deadline");
				String priority = rs.getString("priority");
				Boolean status = rs.getBoolean("status");
				completedTaskList.add(new Task(taskName, deadline, priority, status, id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return completedTaskList;
	}
	
	//delete task
	public boolean deletetask(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TASK);){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate()>0;
		}
		return rowDeleted;
	}
}
