package domain.model;

import java.util.Date;

public class Ticket implements IHaveId {

	private int id;
	private String name;
	private String date;
	private String location;
	private int price;
	private int quantity;

	public Ticket(Integer id, String name, String date, String location, Integer price, Integer quantity) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.location = location;
		this.price = price;
		this.quantity = quantity;
	}

	public Ticket() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}