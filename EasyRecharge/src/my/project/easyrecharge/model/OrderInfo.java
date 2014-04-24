package my.project.easyrecharge.model;

/**
 * Order info,send to server as json string through alipay server
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class OrderInfo {

	private String SchoolID;

	private String ApartID;

	private String RoomNum;

	private int Price;

	public String getSchoolID() {
		return SchoolID;
	}

	public void setSchoolID(String schoolID) {
		SchoolID = schoolID;
	}

	public String getApartID() {
		return ApartID;
	}

	public void setApartID(String apartID) {
		ApartID = apartID;
	}

	public String getRoomNum() {
		return RoomNum;
	}

	public void setRoomNum(String roomNum) {
		RoomNum = roomNum;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

}
