<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="couse" type="blackboard.data.course.Course"--%>
<%--@elvariable id="users" type="java.util.List"--%>

<html>
<body>
<pre>
<h1> List of Courses in Blackboard</h1>
<br/>
<c:forEach items="${courses}" var="c">
	<c:out value= "${c.displayTitle}" /><br />
</c:forEach>

<h1>List of Courses and Users</h1>
<c:forEach items="${courseEnrollmentMap}" var="course">
	Course Id = ${course.key}
	<br />
	Users = 
		<c:forEach items="${course.value}" var="user" varStatus="loop">
			${user} <br /> ${!loop.last ? '' : '' }
		</c:forEach> <br>
	</c:forEach>
		
</pre>
</body>
</html>