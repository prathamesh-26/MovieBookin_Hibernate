package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Meal {

	@Id
	private int id;
	private String name;
	private String descp;
	private int price;
	

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", descp=" + descp + ", price=" + price + "\n";
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

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
