package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
	
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
	
	public int addRoom(Room room) throws SQLException
	{
		Connection con= getConnection();
		PreparedStatement st= con.prepareStatement("INSERT INTO ROOM VALUES(?,?,?,?,?,?)");
		st.setInt(1, room.getRoomNo());		
		st.setString(2, room.getRoomType());
		st.setString(3, room.getBedType());
		st.setDouble(4, room.getTariff());
		st.setBoolean(5, room.getStatus());
		st.setString(6, room.getGuest());
		int no= st.executeUpdate();
		con.close();
		return no;
	}
	public List<Integer> getAllRoomsNo() throws SQLException
	{
		//SELECT * FROM ROOM;
		Connection con= getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT ROOMNO FROM ROOM");
		List<Integer> nos=new ArrayList<Integer>();
		while(rs.next())
		{
			nos.add(rs.getInt(1));
		}
		return nos;
	}
	public List<Integer> getAvailableRooms() throws SQLException
	{
		//SELECT * FROM ROOM WHERE STATUS=TRUE;
		Connection con= getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT ROOMNO FROM ROOM WHERE STATUS=TRUE");
		List<Integer> nos=new ArrayList<Integer>();
		while(rs.next())
		{
			nos.add(rs.getInt(1));
		}
		return nos;
	}
	public ArrayList<Room> getAllRooms() throws SQLException{
		Connection con= getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT * FROM ROOM");
		ArrayList<Room> rooms= new ArrayList<Room>();
		int n=0;
		while(rs.next()){
			Room room= new Room(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getBoolean(5),rs.getString(6));
			rooms.add(room);
			n++;
		}
		if(n==0) System.out.println("oops!! No room in our hotel..");
		return rooms;
		
	}
	public Room getRoomById(int roomNo) throws SQLException
	{
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ROOM WHERE ROOMNO="+roomNo);
		Room rm = null;
		if(rs.next()){
			rm=new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5),rs.getString(6));
		}
		return rm;
	}
	public List<Room> getRoomsByStatus(boolean status) throws SQLException
	{
		Connection con= getConnection();
		PreparedStatement st= con.prepareStatement("SELECT * FROM ROOM WHERE status=?");
		st.setBoolean(1, status);
		ResultSet rs=st.executeQuery();
		ArrayList<Room> rooms= new ArrayList<>();
		int n=0;
		while(rs.next())
		{
			Room room= new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), rs.getString(6));
			rooms.add(room);
			n++;
		}
		if(n==0) System.out.println("No room is available..");
		return rooms;
	}
	public void removeRoom(int roomNo) throws SQLException{
		Connection con= getConnection();
		PreparedStatement st= con.prepareStatement("DELETE FROM ROOM WHERE ROOMNO = ?");
		st.setInt(1, roomNo);	
		st.executeUpdate();
	    con.close();
	}
	public void displayAllRooms() throws SQLException{
		Connection con= getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT * FROM ROOM");
		while(rs.next()){
			System.out.println("Room [ "+rs.getInt(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getDouble(4)+", "+rs.getBoolean(5)+", "+rs.getString(6)+" ]");
		}
	}
	public void displayAllRooms(boolean status) throws SQLException{
		RoomDAO rdao= new RoomDAO();
		List<Room> rooms= rdao.getRoomsByStatus(status);
		for(Room room : rooms){
			System.out.println(room);
		}
	}

	//Room number, type, bed type, tariff, availability.
	public static void main(String[] args) throws SQLException {
		RoomDAO rdao= new RoomDAO();
/*		for(int i=208;i<214;i++){
			Room room= new Room(i,"NON-AC","DOUBLE",900.00);
			int no=rdao.addRoom(room);
			System.out.println(no+" row(s) affected");
		}*/
		//System.out.println(rdao.getRoomById(102));
/*		for(Integer rn : rdao.getAllRoomNos()){
			//System.out.println(rn);
			System.out.println(rdao.getRoomById(rn));
		}*/
		rdao.removeRoom(100);

		rdao.displayAllRooms();
		//rdao.displayAllRooms(false);s()){
			//System.out.println(rn);
	}

}
