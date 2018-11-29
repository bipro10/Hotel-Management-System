<%@page import="model.*" %>
<%@page import="java.util.List"%>
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
<table>
<tr><td><a href="GuestForm.jsp">Add New Guest</a></td><td> </td>
<td><a href="guests.jsp">Guest Lists</a></td><td> </td>
<td><a href="#">Archived Guest</a></td></tr>
</table>
<br/><br/>

<%
//display all guest

GuestDAO gdao= new GuestDAO();
try{
	List<Guest> guests= gdao.getAllGuests();
	out.print("<TABLE BORDER =1>");

	out.print("<THEAD><TH>Guest Name</TH><TH>Gender</TH><TH>Status</TH><TH>City</TH><TH>Country</TH><TH>Phone</TH><TH>Room No</TH><TH>NightsStay</TH><TH>CheckIn</TH><TH>CheckOut</TH></THEAD>");
	
	for(Guest guest : guests)
	{
			out.print("<TR>");
			out.println("<TD>"+guest.getGuestName()+"</TD><TD>"+guest.getGender()+"</TD><TD>"+guest.getStatus()+"</TD><TD>"+guest.getCity()+"</TD><TD>"+guest.getCountry()+"</TD><TD>"+guest.getPhone()+"</TD><TD>"+guest.getSelectedRoom()+"</TD><TD>"+guest.getNightsStay()+"</TD><TD>"+guest.getCheckIn()+"</TD><TD>"+guest.getCheckOut()+"</TD>");
			out.print("</TR>");
	}
	out.print("</TABLE>");
}catch(Exception ex){
	out.println(ex.getMessage());
}

%>
<br/>
<div id="demo"></div>
</body>
</html>