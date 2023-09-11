package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import implementation.TaskImplementation;
import implementation.TaskInterface;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaskInterface taskImpl;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
        this.taskImpl = new TaskImplementation();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		
		switch (action) {
		case "/new":
            showNewForm(request, response);
            break;
        case "/insert":
            try {
				insertTask(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case "/delete":
            try {
				deleteTask(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case "/edit":
            try {
				showEditForm(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case "/update":
            try {
				updateTask(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case "/complete":
        	try {
				completeTask(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        default:
            try {
				listTask(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
		}
		
	}
	

	private void listTask(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		List <Task> listTasks = taskImpl.selectAllTasks();
		List <Task> inProgressTasks = taskImpl.selectInProgressTasks();
		List <Task> completedTasks = taskImpl.selectCompletedTasks();
		request.setAttribute("listTasks", listTasks);
		request.setAttribute("inProgrssTasks", inProgressTasks);
		request.setAttribute("completedTasks", completedTasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
		dispatcher.forward(request, response);
	}

	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Task existingTask = taskImpl.selctTask(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
		request.setAttribute("task", existingTask);
		dispatcher.forward(request, response);
	 }
	
	private void insertTask(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		String name = request.getParameter("taskName");
		String deadline = request.getParameter("deadline");
		String priority = request.getParameter("priority");
		Boolean status = false;
		Task newTask = new Task(name, deadline, priority, status);
		taskImpl.createTask(newTask);
		response.sendRedirect("list");
	}
	
	 private void deleteTask(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		taskImpl.deletetask(id);
		response.sendRedirect("list");
	 }
	 
	 private void updateTask(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("taskName");
		String deadline = request.getParameter("deadline");
		String priority = request.getParameter("priority");
		Boolean status = Boolean.parseBoolean(request.getParameter("status"));
		Task task = new Task(name, deadline, priority, status, id);
		taskImpl.updateTask(task);
		response.sendRedirect("list");
	 }
	 
//	 private void completeTask(HttpServletRequest request, HttpServletResponse response) 
//			 throws SQLException, IOException {
//		 int id = Integer.parseInt(request.getParameter("id"));
//		 Boolean status = Boolean.parseBoolean(request.getParameter("status"));
//		 Task task = new Task(status, id);
//		 taskImpl.completeTask(task);
//		 response.sendRedirect("list");
//	}
	 
	 private void completeTask(HttpServletRequest request, HttpServletResponse response) 
			 throws SQLException, IOException {
		 int id = Integer.parseInt(request.getParameter("id"));
		 taskImpl.completeTask(id);
//		 response.sendRedirect("list");
	}
	 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
