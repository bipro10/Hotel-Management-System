package model;

public class Room {
	//Room number, type, bed type, tariff, status, guest.
	int roomNo;
	String roomType;
	String bedType;
	double tariff;
	boolean status; //avaialble=true, occupied= false.
	String guestName;
	
	public Room(){};
	
	public Room(int roomNo, String roomType, String bedType, double tariff,
				boolean status, String guestName){
		this.roomNo= roomNo;
		this.roomType= roomType;
		this.bedType= bedType;
		this.tariff= tariff;
		this.status= status;
		this.guestName= guestName;
	}
	
	public Room(int roomNo, String roomType, String bedType, double tariff) {
		super();
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.bedType = bedType;
		this.tariff = tariff;
	}

	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public double getTariff() {
		return tariff;
	}
	public void setTariff(double tariff) {
		this.tariff = tariff;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	//Add/Remove Guest in/from this room
	public String getGuest(){
		return guestName;
	}
	public void addGuest(String guestName){
		this.guestName= guestName;
		this.status= false;
	}
	public void removeGuest(){
		this.guestName=null;
		this.status= true;
	}
	
	@Override
	public String toString() {
		String s="";
	    if(this.status==true) s="Available";
	    else s="Booked";
		return "Room [room=" + roomNo + ", "+ roomType + ", " + bedType + ", "
				+ tariff + "$, " + s + ", "+guestName+"]";
	}
}
