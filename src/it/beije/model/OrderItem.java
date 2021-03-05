package it.beije.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="order_id")
	private int orderID;
	@Column(name="product_id")
	private int productID;
	@Column(name="quantity")
	private int quantity;
	@Column(name="amount")
	private double amount;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public int getOrderID() {
	return orderID;
}
public void setOrderID(int orderID) {
	this.orderID = orderID;
}
public int getProductID() {
	return productID;
}
public void setProductID(int productID) {
	this.productID = productID;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}


}
