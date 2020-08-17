package hotelReservation.domain;

import javax.persistence.*;

@Entity
@Table(name="Rooms")
public class Room {
	
	@Id
	private long roomID;
	private long hotelID;
	private String type;
	private int price;
	private String imageURL;
	private String imageURL_2;
	
	public Room() {}
	
	public Room(long roomID, long hotelID, String type, int price, String imageURL, String imageURL_2) {
		super();
		this.roomID = roomID;
		this.hotelID = hotelID;
		this.type = type;
		this.price = price;
		this.imageURL = imageURL;
		this.imageURL_2 = imageURL_2;
	}
	
	public long getRoomID() {
		return roomID;
	}

	public void setRoomID(long roomID) {
		this.roomID = roomID;
	}

	public long getHotelID() {
		return hotelID;
	}

	public void setHotelID(long hotelID) {
		this.hotelID = hotelID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getImageURL_2() {
		return imageURL_2;
	}

	public void setImageURL_2(String imageURL_2) {
		this.imageURL_2 = imageURL_2;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (hotelID != other.hotelID)
			return false;
		if (imageURL == null) {
			if (other.imageURL != null)
				return false;
		} else if (!imageURL.equals(other.imageURL))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (roomID != other.roomID)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
