package it.beije.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.model.Order;
import it.beije.model.OrderItem;
import it.beije.repository.OrderItemRepository;
import it.beije.repository.OrderRepository;
import it.beije.repository.ProductRepository;
@Service
public class OrderItemService {
@Autowired
private OrderItemRepository orderItemRepository;
@Autowired
private ProductRepository productRepository;
@Autowired
private OrderRepository orderRepository;

public Order addOrderItem(Integer idUser, int idProduct, int quantity) {
	Order ordine=orderRepository.findByUserIDAndState(idUser, "open");
	if(ordine==null)
		{
		 ordine= new Order();
		 ordine.setState("open");
		 ordine.setUserID(idUser);
		 ordine.setAmount(0);
		 ordine=orderRepository.save(ordine);
	
 		}
	System.out.println(ordine.getAmount());
OrderItem temp= orderItemRepository.findByOrderIDAndProductID(ordine.getId(), idProduct);
			if(temp==null) {
				temp=new OrderItem();
				temp.setOrderID(ordine.getId());
				temp.setProductID(idProduct);
				temp.setQuantity(quantity);
				temp.setAmount(productRepository.findById(idProduct).get().getPrice()*quantity);
				ordine.setAmount(ordine.getAmount()+temp.getAmount());
				ordine=orderRepository.save(ordine);
				orderItemRepository.save(temp);
				System.out.println(ordine.getAmount());
			return ordine;
			}

			temp.setQuantity(quantity);
			temp.setAmount(productRepository.findById(idProduct).get().getPrice()*quantity);
			orderItemRepository.save(temp);
			ordine.setAmount(ordine.getAmount()+temp.getAmount());
			ordine=orderRepository.save(ordine);
			System.out.println(ordine.getAmount());
			return ordine;
}
public List<OrderItem> findByOrderID(int orderID) {
	return orderItemRepository.findByOrderID(orderID);
}
public void deleteOrderItemByID(int orderID) {
	 orderItemRepository.deleteById(orderID);
}
}

