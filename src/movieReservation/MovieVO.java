package movieReservation;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieVO implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 941773534705942234L;
	String place;
	String title;
	int day;
	String[] time;
//   String time;
	double price;
	ArrayList<Integer> seatNum;

//   MovieVO(String place, String title, int day, String time){
//      this.place = place;
//      this.title = title;
//      this.day = day;
//      this.time = time;
//      //this.seatNum = seatNum;
//   }

	@Override
	public String toString() {
		return "MovieVO [place=" + place + ", title=" + title + ", day=" + day + ", time=" + time + ", seatNum="
				+ seatNum + "]";
	}

	public MovieVO(String place, String title, int day, String[] time, double price, ArrayList<Integer> seatNum) {
		super();
		this.place = place;
		this.title = title;
		this.day = day;
		this.time = time;
		this.price = price;
		this.seatNum = seatNum;
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

//   public String getTime() {
//      return time;
//   }
//
//   public void setTime(String time) {
//      this.time = time;
//   }

	public ArrayList<Integer> getSeatNum() {
		return seatNum;
	}

	public String[] getTime() {
		return time;
	}

	public void setTime(String[] time) {
		this.time = time;
	}

	public void setSeatNum(ArrayList<Integer> seatNum) {
		this.seatNum = seatNum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}