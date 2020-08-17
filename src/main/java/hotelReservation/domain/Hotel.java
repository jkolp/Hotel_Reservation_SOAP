package hotelReservation.domain;

import javax.persistence.*;

@Entity
@Table(name="hotels")
public class Hotel {
	
	@Id
	private long hotelID;
	private String name;
	private String city;
	private String address;
	private String description;
	private float rating;
	private String imageURL;
	
	public Hotel (){}
	
	public Hotel(long hotelID, String name, String city, String address, String description,
			float rating, String imageURL) {
		super();
		this.hotelID = hotelID;
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.rating = rating;
		this.imageURL = imageURL;
	}
	public long getHotelID() {
		return hotelID;
	}
	public void setHotelID(long hotelID) {
		this.hotelID = hotelID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hotelID != other.hotelID)
			return false;
		if (imageURL == null) {
			if (other.imageURL != null)
				return false;
		} else if (!imageURL.equals(other.imageURL))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(rating) != Float.floatToIntBits(other.rating))
			return false;
		return true;
	}

}
