<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ToDo</title>
	<style>
		<%@include file="/css/task-list.css"%>
	</style>
	<!--  <link href="${pageContext.request.contextPath}/css/task-list.css" rel="stylesheet" type="text/css" /> -->
</head>
<body>
	<header class="header1">
		<div id="title-el">
        	<h1>My ToDo List</h1>
        	<!-- <h3><a href="<%=request.getContextPath()%>/list">Tasks</a></h3>
        	<button onclick="location.href = '<%=request.getContextPath()%>/list'">Task list</button> -->
    	</div>
	</header>
	
	<div class="content">
	
    <div class="body-el">

		<h3>Tasks In-Progress :</h3>
		
    	<table class="task-table">
			<thead>
				<tr>
					<th>Task Name</th>
					<th>Deadline</th>
					<th>Priority</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${inProgrssTasks}" var="task">
					<tr id='task-row'>
						<td><c:out value="${task.getName()}"/></td>
			
						<td id='deadline'><c:out value="${task.getDate()}"/></td>
					
						<td id='priority'><c:out value="${task.getPriority()}"/></td>
						
						<td id='actions'>
							<input class='edit-btn' type='button' onclick='location.href="edit?id=<c:out value='${task.getId()}'/>"' value="&#128394;&#65039;"/>
							&nbsp;
							<input class='delete-btn' type='button' onclick='location.href="delete?id=<c:out value='${task.getId()}'/>"' value="&#128465;&#65039;"/>
							&nbsp;
							<input class='complete-btn' type='button' onclick='location.href="complete?id=<c:out value='${task.getId()}'/>"' value="&#x2705;"/>
							<!-- <a href="edit?id=<c:out value='${task.getId()}'/>">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="delete?id=<c:out value='${task.getId()}'/>">Delete</a> -->
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </div>
    
    <div class="body-complete">

		<br>
		<h3>Tasks Completed :</h3>
		
    	<table class="task-table">
			<thead>
				<tr>
					<th>Task Name</th>
					<th>Deadline</th>
					<th>Priority</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${completedTasks}" var="task">
					<tr id='task-row'>
						<td><c:out value="${task.getName()}"/></td>
			
						<td id='deadline'><c:out value="${task.getDate()}"/></td>
					
						<td id='priority'><c:out value="${task.getPriority()}"/></td>
						
						<td id='actions'>
							<!--<input id='edit-btn' type='button' onclick='location.href="edit?id=<c:out value='${task.getId()}'/>"' value="Edit"/>
							&nbsp;  -->
							<input id='delete-btn' type='button' onclick='location.href="delete?id=<c:out value='${task.getId()}'/>"' value="&#128465;&#65039;"/>
							<!--&nbsp;
							<input id='complete-btn' type='button' onclick='location.href="complete?id=<c:out value='${task.getId()}'/>"' value="&#x1F5F9;"/>
							-->
							
							<!-- <a href="edit?id=<c:out value='${task.getId()}'/>">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="delete?id=<c:out value='${task.getId()}'/>">Delete</a> -->
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </div>
    
    <div>
			<!-- <a href="<%=request.getContextPath()%>/new">New Task</a> -->
			<button id='add-btn' onclick="location.href = '<%=request.getContextPath()%>/new'">New Task</button>
	</div>
	
	</div>
</body>
</html>