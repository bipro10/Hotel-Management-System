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
		<TR><TD>Room No</TD>	<TD><input type=text name=roomNo /></TD></TR>
		<TR><TD>Room Type: </TD>
        <TD><input type="radio" name=roomType value="AC"/>AC
        <input type="radio" name=roomType value="NON-AC"/>NON-AC</TD>
        </TR>
        <TR><TD>Bed Type: </TD>
        <TD><input type="radio" name=bedType value="SINGLE"/>SINGLE
        <input type="radio" name=bedType value="DOUBLE"/>DOUBLE</TD>
        </TR>
		<TR><td>Tariff</TD>	<TD><input type=text name=tariff /></TD></TR>
		<TR><TD></TD> <TD><input type=submit value=AddRoom /></TD></TR>
	</TABLE>
</form>

<%
 RoomDAO rdao=new RoomDAO();
		
//write a code to add room
	int roomNo;
	String roomType, bedType;
	double tariff;
	
if(request.getParameter("roomNo")!=null)
{	
	roomNo=Integer.parseInt(request.getParameter("roomNo"));
	roomType=request.getParameter("roomType");
	bedType=request.getParameter("bedType");
	tariff=Double.parseDouble(request.getParameter("tariff"));
	
	Room room=new Room(roomNo, roomType, bedType, tariff);
	
	try{
		rdao.addRoom(room);
		out.print("Room Successfully added..");
	}catch(Exception ex)
	{
		out.println(ex.getMessage());
	}
}
%>

<br/><br/>Room No:
<form>
	<select name=roomno>
		<%
		RoomDAO rrdao=new RoomDAO();
		try
		{
			List<Integer> rooms= rrdao.getAllRoomsNo();
			for(Integer rn:rooms){
		%>
          		<option value="<%=rn%>"><%=rn%></option>
		<%
     		}
		}catch(Exception e){    out.print(e);}
		%>
	</select>
	<input type=submit value=RemoveRoom />
</form>
<%
//RoomDAO rmdao= new RoomDAO();
int r;
if(request.getParameter("roomno")!=null){
	r= Integer.parseInt(request.getParameter("roomno"));
	rdao.removeRoom(r);
	out.print("Room "+r+"  removed Successfully..");
}
%>

<%
//display all room
try{
	List<Room> rooms= rdao.getAllRooms();
	out.print("<TABLE BORDER =1>");

	out.print("<THEAD><TH>Room No</TH><TH>Room Type</TH><TH>Bed Type</TH><TH>Tariff</TH><TH>Status</TH><TH>Guest</TH>");
	for(Room room : rooms){
		String status;
		status= room.getStatus() ? "Booked" : "Available";
			out.print("<TR>");
			out.println("<TD>"+room.getRoomNo()+"</TD><TD>"+room.getRoomType()+"</TD><TD>"+room.getBedType()+"</TD><TD>"+room.getTariff()+"</TD><TD>"+status+"</TD><TD>"+room.getGuest()+"</TD>");
			out.print("</TR>");
	}
	out.print("</TABLE>");
}catch(Exception ex){
	out.println(ex.getMessage());
}
//get all rooms
try{
	//
}
catch(Exception ex){
	out.println(ex.getMessage());
}
%>
</body>
</html>