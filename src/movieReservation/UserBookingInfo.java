package movieReservation;

import java.io.Serializable;
import java.util.ArrayList;

public class UserBookingInfo implements Serializable{
	
	
	@Override
	public String toString() {
		return "UserBookingInfo [id=" + id + ", name=" + name + ", place=" + place + ", title=" + title + ", day=" + day
				+ ", time=" + time + ", price=" + price + ", seatNum=" + seatNum + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String place;
	private String title;
	private int day;
	private String time;
	private double price;
	ArrayList<Integer> seatNum;
	
	
	public UserBookingInfo(String id, String name, String place, String title, int day, String time, double price,
			ArrayList<Integer> seatNum) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.title = title;
		this.day = day;
		this.time = time;
		this.price = price;
		this.seatNum = seatNum;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Integer> getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(ArrayList<Integer> seatNum) {
		this.seatNum = seatNum;
	}

}
