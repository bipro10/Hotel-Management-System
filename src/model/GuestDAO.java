package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GuestDAO {
	//Connect to mysql database
	private Connection getConnection() throws SQLException
	{
		String url="jdbc:mysql://localhost:3306/hotelmanagement";
		String uid="root";
		String pwd="";
		//Register the Driver Class
		new com.mysql.jdbc.Driver();
		//Create the Connection
		return DriverManager.getConnection(url,uid, pwd);
	}
	
	public int addGuest(Guest guest) throws SQLException
	{
		Connection con= getConnection();
		PreparedStatement pst= con.prepareStatement("INSERT INTO GUEST VALUES(?,?,?,?,?,?,?,?,?,?)");
		pst.setString(1, guest.getGuestName());		
		pst.setString(2, guest.getGender());
		pst.setString(3, guest.getStatus());
		pst.setString(4, guest.getCity());
		pst.setString(5, guest.getCountry());
		pst.setString(6, guest.getPhone());
		pst.setInt(7, guest.getSelectedRoom());
		pst.setInt(8, guest.getNightsStay());
		pst.setString(9, guest.getCheckIn());
		pst.setString(10,guest.getCheckOut());
		
		int no= pst.executeUpdate();
/*		RoomDAO rdao= new RoomDAO();
		Room room= rdao.getRoomById(guest.getSelectedRoom());
		room.addGuest(guest.getGuestName());*/
		PreparedStatement pst2= con.prepareStatement("UPDATE ROOM SET GUEST=? , STATUS=? WHERE ROOMNO=?");
		pst2.setString(1, guest.getGuestName());
		pst2.setBoolean(2, true);
		pst2.setInt(3, guest.getSelectedRoom());
		pst2.executeUpdate();
		
		System.out.println(guest.getGuestName()+" Successfully added..");
		con.close();
		return no;
	}
	
	public void removeGuest(int roomNo) throws SQLException{
		Connection con= getConnection();
		PreparedStatement pst= con.prepareStatement("DELETE FROM GUEST WHERE ROOMNO=?");
		pst.setInt(1, roomNo);
		pst.executeUpdate();
		pst= con.prepareStatement("UPDATE ROOM SET GUEST=null , STATUS=true WHERE ROOMNO="+roomNo);
		pst.executeUpdate();
		con.close();
        System.out.println("Guest Archived Successfully..");
	}
	
	public ArrayList<String> getAllGuestsName() throws SQLException
	{
		Connection con= getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT GUESTNAME FROM GUEST");
		ArrayList<String> guests=new ArrayList<String>();
		while(rs.next()){
			guests.add(rs.getString(1));
		}
		return guests;
	}
	
	public Guest findGuestByName(String name) throws SQLException{
		Connection con= getConnection();
		PreparedStatement pst= con.prepareStatement("SELECT * FROM GUEST WHERE GUESTNAME=?");
		pst.setString(1,name);
		ResultSet rs= pst.executeQuery();
		Guest guest = null;
		if(rs.next()){
			guest=new Guest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
		}
		return guest;	
	}
	
	public Guest findGuestByRoomNo(int roomNo) throws SQLException{
		Connection con= getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT * FROM GUEST WHERE ROOMNO="+roomNo);
		Guest guest = null;
		if(rs.next()){
			guest=new Guest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
		}
		return guest;
		
	}
	
	public ArrayList<Guest> getAllGuests() throws SQLException{
		Connection con= getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT * FROM GUEST");
		ArrayList<Guest> guests= new ArrayList<Guest>();
		int n=0;
		while(rs.next()){
			Guest guest= new Guest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),rs.getInt(8));
			guests.add(guest);
			n++;
		}
		if(n==0) System.out.println("oops!! No Guest in our hotel..");
		return guests;
		
	}
	
	public void displayAllGuests() throws SQLException{
		ArrayList<Guest> guests= new GuestDAO().getAllGuests();
		for(Guest guest : guests){
			System.out.println(guest);
		}
	}
	
	public void displayGuestsName() throws SQLException{
		ArrayList<String> guests= new GuestDAO().getAllGuestsName();
		for(String guest : guests){
			System.out.println(guest);
		}
	}

	public static void main(String args[]) throws SQLException{
		GuestDAO gdao= new GuestDAO();
		//name, gender, status, city, country, phone, roomNo, nightsStay.
		//Guest guest= new Guest("Suman","MALE","Married","Dhaka","Bangladesh","+8801923801678",112,7);
		//gdao.addGuest(guest);
		//System.out.println(no+" row(s) affected");
		
		//String name= gdao.findGuestByname("Biprodas").toString();
		//System.out.println(gdao.findGuestByRoomNo(207));
		//ArrayList<Guest> guests= gdao.getAllGuests();
		gdao.displayAllGuests();
		//gdao.removeGuest(201);
		//gdao.displayGuestsName();
		
	}
}
