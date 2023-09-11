package implementation;

import java.sql.SQLException;
import java.util.List;

import model.Task;

public interface TaskInterface {
	public void createTask(Task task) throws SQLException;
	public boolean updateTask(Task task) throws SQLException;
	public boolean completeTask(int id) throws SQLException;
	public Task selctTask(int id);
	public List<Task> selectAllTasks();
	public List<Task> selectInProgressTasks();
	public List<Task> selectCompletedTasks();
	public boolean deletetask(int id) throws SQLException;
}
