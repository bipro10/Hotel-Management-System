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
    <form>
	<TABLE>
	<TR><TD>Room No: </TD>
	
	<TD><select name=roomNo>
		<%
		RoomDAO rrdao=new RoomDAO();
		try
		{
			List<Integer> rooms= rrdao.getAvailableRooms();
			for(Integer rn:rooms){
		%>
          		<option value="<%=rn%>"><%=rn%></option>
		<%
     		}
		}catch(Exception e){    out.print(e);}
		%>
	</select></TD>
		<TR><TD>Guest Name</TD>	<TD><input type=text name=guestName /></TD></TR>
 		<TR><TD>Gender</TD>
 			<TD><select name=gender>
        	<option name=male value=MALE> Male </option>
        	<option name=famale value=FAMALE> Female </option>
        	<option name=other value=OTHER> Other </option>
        	</select>
        	</TD>
        </TR>
		<TR><TD>Status: </TD>
        	<TD><input type="radio" name=status value="single"/>Single
        		<input type="radio" name=status value="married"/>Married</TD>
        </TR>		
        <tr><td>City</TD>	<TD><input type=text name=city /></TD></TR>
		<tr><td>Country</TD>	<TD><input type=text name=country /></TD></TR>
		<tr><td>Phone</TD>	<TD><input type=text name=phone /></TD></TR>
		<tr><td>NightStay</TD>	<TD><input type=text name=nightsStay /></TD></TR>
		
		<tr>	<TD></TD><TD><input type=submit value=AddGuest /></TD></TR>
	</TABLE>
    </form>
 <%
 GuestDAO gdao=new GuestDAO();
		
//write a code to add guest
	String guestName, gender, status, city, country, phone, checkIn, checkOut;
	int room, nightsStay;
	
if(request.getParameter("guestName")!=null)
{
	guestName=request.getParameter("guestName");
	gender=request.getParameter("gender");
	status=request.getParameter("status");
	city=request.getParameter("city");
	country=request.getParameter("country");
	phone=request.getParameter("phone");
	
	room=Integer.parseInt(request.getParameter("roomNo"));
	nightsStay= Integer.parseInt(request.getParameter("nightsStay"));
	
	Guest guest=new Guest(guestName, gender, status, city, country, phone, room, nightsStay);
	
	try{
		gdao.addGuest(guest);
		out.print("Guest Successfully added..");
	}catch(Exception ex)
	{
		out.println(ex.getMessage());
	}
}
//get all rooms
try{
	RoomDAO rdao= new RoomDAO();
	List<Guest> guests= gdao.getAllGuests();
}
catch(Exception ex){
	out.println(ex.getMessage());
}
//display all guest
try{
	List<Guest> guests= gdao.getAllGuests();
	out.print("<TABLE BORDER =1>");

	out.print("<THEAD><TH>Room No</TH><TH>Guest Name</TH><TH>Gender</TH><TH>Status</TH><TH>City</TH><TH>Country</TH><TH>Phone</TH><TH>NightsStay</TH><TH>CheckIn</TH><TH>CheckOut</TH></THEAD>");
	
	for(Guest guest : guests)
	{
			out.print("<TR>");
			out.println("<TD>"+guest.getSelectedRoom()+"</TD><TD>"+guest.getGuestName()+"</TD><TD>"+guest.getGender()+"</TD><TD>"+guest.getStatus()+"</TD><TD>"+guest.getCity()+"</TD><TD>"+guest.getCountry()+"</TD><TD>"+guest.getPhone()+"</TD><TD>"+guest.getNightsStay()+"</TD><TD>"+guest.getCheckIn()+"</TD><TD>"+guest.getCheckOut()+"</TD>");
			out.print("</TR>");
	}
	out.print("</TABLE>");
}catch(Exception ex){
	out.println(ex.getMessage());
}
//*/
 %>
</body>
</html>











