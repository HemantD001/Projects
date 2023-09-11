<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ToDo</title>
	<style>
		<%@include file="/css/task-form.css"%>
	</style>
</head>
<body>
	<header>
		<div id="title-el">
        	<h1>My ToDo List</h1>
        	<!-- <h3><a href="<%=request.getContextPath()%>/list">Tasks</a></h3>
        	<button onclick="location.href = '<%=request.getContextPath()%>/list'">Task list</button> -->
    	</div>
	</header>
	
	
	<div id='body'>
		<c:if test="${task !=null}">
			<form id='form-el' action="update" method="post">
		</c:if>
		<c:if test="${task == null}">
			<form id='form-el' action="insert" method="post">
		</c:if>
		
		<caption>
			<h2>
				<c:if test="${task != null}">
					Edit Task
				</c:if>
				<c:if test="${task == null}">
					New Task
				</c:if>
			</h2>
		</caption>
		
		<c:if test="${task != null}">
			<input type="hidden" name="id" value="<c:out value='${task.getId()}'/>" />
		</c:if>
		<div class="inputEl">
			<h4>Name : </h4>
			<input class="name" type="text" name="taskName" value="<c:out value='${task.getName()}'/>">
		</div>
		<div class="inputEl">
			<h4>Deadline : </h4>
			<input class="deadline" type="date" name="deadline" value="<c:out value='${task.getDate()}'/>">
		</div>
		<!-- <div>
			<legend>Priority</legend>
			<input type="text" name="priority" value="<c:out value='${task.getPriority()}'/>">
			
			
			
			<div>
			<label>Priority</label>
			<input type="radio" id="low" class="priority" name="priority" value="<c:out value='low'/>" >
            <label for="low">Low</label>
            <input type="radio" id="medium" class="priority" name="priority" value="<c:out value='medium'/>">
            <label for="medium">Medium</label>
            <input type="radio" id="high" class="priority" name="priority" value="<c:out value='high'/>">
            <label for="high">High</label>
		</div>
		</div> -->
		<div class="inputEl">
			<h4>Priority : </h4>
			<input type="radio" id="low" class="priority" name="priority" value='low' <c:if test="${task.getPriority()=='low'}">checked</c:if> >
            <label for="low">Low</label>
            <input type="radio" id="medium" class="priority" name="priority" value='medium' <c:if test="${task.getPriority()=='medium'}">checked</c:if>>
            <label for="medium">Medium</label>
            <input type="radio" id="high" class="priority" name="priority" value='high' <c:if test="${task.getPriority()=='high'}">checked</c:if>>
            <label for="high">High</label>
		</div>
		<button id='save-btn' type="submit">Save</button>
		<input id='close-btn' type='button' onclick="location.href = '<%=request.getContextPath()%>/list'" value="Close">
		</form>
	</div>
</body>
</html>