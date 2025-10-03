<!DOCTYPE html>
<html>
<head>
  <title>START PAGE</title>
</head>
<body>
<h4>Hello World!</h4>

<%@ page import="model.*" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="util.HibernateUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>

<br>
<br>

<a href="new-match">New Match Page</a>
<a href="match-score">Match Score Page</a>



<% try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {

    hibernateSession .beginTransaction();


    Match match = hibernateSession.find(Match.class, 3);
    Player player = match.getWinner();
%>

WINNER:          <p><%= player.getName()%></p>
<p><%= player.getId()%></p>

<%       hibernateSession.getTransaction().commit();
} catch (Exception e) {
    System.err.println("Error during testing:");
    e.printStackTrace();}
%>


</body>
</html>