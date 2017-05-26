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
        <p><a href="<c:url value="/terminal/onButtonClick/${queue.id}"/>">Очередь ${queue.id}</a>
        <c:out value="${queue.queueInfo}"/>
        
        </c:forEach>
        
    </body>
</html>
