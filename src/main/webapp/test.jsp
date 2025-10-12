<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12.10.2025
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>
<%
    String degreeParam = request.getParameter("degree");
    request.setAttribute("degreeVar", degreeParam);
%>
</p>

<c:choose>
    <c:when test="${degreeVar == 'twenty' }"> it's ok</c:when>
    <c:when test="${degreeVar == 'five' }"> it's cold</c:when>
    <c:otherwise>its my temp</c:otherwise>
</c:choose>





</body>
</html>



<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>First Jsp</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Testing JSP</h1>--%>
<%--<p>--%>
<%--    <%String name = request.getParameter("name");%>--%>
<%--    <%="Hello baby " + name%>--%>
<%--</p>--%>
<%--</body>--%>
<%--</html>--%>