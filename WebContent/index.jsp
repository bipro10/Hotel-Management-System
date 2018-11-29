<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	if(session.getAttribute("userid")==null){
		response.sendRedirect("login.jsp");
	}
%>

   Welcome <FONT COLOR=GREEN> ${userid }</FONT> <br>
   
   <div class=menu>
   		<a href="GuestForm.jsp">Add Guest</a>
   		<a href="RoomForm.jsp">Room Status</a>
   </div>
   
   <form action="LogoutServlet">
   		<input type="submit" value="logout">
   </form>
</body>
</html>