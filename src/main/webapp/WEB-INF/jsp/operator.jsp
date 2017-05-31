<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OPERATOR</title>
    </head>

    <body>
        <p>Очередь: ${queue.name}</p>
        
        <c:set var = "head" scope = "session" value = "${queue.head}"/>
        <c:if test="${head !=null}">
            <p><a href="<c:url value="/operator/onButtonClick/${queue.id}/callNext"/>">Следующий талон №${queue.head.number}</a></p>
        </c:if>
        <c:out value="${queue.queueInfo}"/>
        
        
        
    </body>
</html>
