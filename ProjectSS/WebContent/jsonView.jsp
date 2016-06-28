<%@ page import="com.google.gson.Gson"%>
<%@ page import="il.ac.hit.finalproject.*"%>
<%@ page import="java.util.List"%>

<%
Gson gson = new Gson();
out.println(gson.toJson(request.getAttribute("Object")));
%>
