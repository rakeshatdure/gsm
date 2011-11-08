package POJO;

public class Day {
	private int dayId;
	private int time;

	public Day() {
	}
	
	public Day(int dayId, int time) {
		super();
		this.dayId = dayId;
		this.time = time;
	}
	public Day(int time){
		this.time = time;
	}

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getDayId() {
		return dayId;
	}
	public void setDayId(int dayId) {
		this.dayId = dayId;
	}

}
