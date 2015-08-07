<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="couse" type="blackboard.data.course.Course"--%>
<%--@elvariable id="users" type="java.util.List"--%>

<html>
<body>
<pre>
Courses:
<br/>
<c:forEach items="${courses}" var="course">
	<c:out value= "${course.displayTitle}" /><br />
</c:forEach>

Users:<br/>
<c:forEach items="${users}" var="element">
    <c:out value="${element.userName}"/><br/>
</c:forEach>
</pre>
</body>
</html>