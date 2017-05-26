<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        
        <c:forEach items="${queueList}" var="queue">     
        <c:out value="${queue.id}"/>
        </c:forEach>
        <p><a href="<c:url value="/admin/onButtonClick/add_queue"/>">Добавить очередь</a></p>

    </body>
</html>
