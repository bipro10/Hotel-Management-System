package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Guest {
	//Guest Name, gender, status,  city, country, phone, roomNo, nights, checkIn, checkOut.
	String guestName;
	String gender;
	String status;
	String city;
	String country;
	String phone;
	int room;
	int nightsStay;
	String checkIn;
	String checkOut;
	Calendar calendar;
	//SimpleDateFormat sdtf= new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
	SimpleDateFormat sdf= new SimpleDateFormat("dd-MMM-yyyy");
	//SimpleDateFormat sdf= new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
	
	public Guest(){}

	public Guest(String guestName, String gender, String status, String city, String country, 
			String phone, int room, int nightsStay) {
		super();
		this.guestName = guestName;
		this.gender = gender;
		this.status = status;
		this.city = city;
		this.country= country;
		this.phone= phone;
		this.room= room;
		this.nightsStay= nightsStay;
		calendar= Calendar.getInstance();
		checkIn= sdf.format(calendar.getTime());
		calendar.add(Calendar.DATE, nightsStay);
		checkOut= sdf.format(calendar.getTime());
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSelectedRoom() {
		return room;
	}

	public void setSelectedRoom(int room) {
		this.room = room;
	}

	public int getNightsStay() {
		return nightsStay;
	}

	public void setNightsStay(int nightsStay) {
		this.nightsStay = nightsStay;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut= checkOut;
		//this.checkOut= sdf.format(checkOut.getTime());
	}
	
	@Override
	public String toString() {
		return "Guest [ " + room + ", " + guestName + ", "+ gender + ", " + city + ", "
				+ country + ", " + checkIn +  ", " + checkOut + "]";
	}
	
	
}
