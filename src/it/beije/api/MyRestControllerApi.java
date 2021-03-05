package it.beije.api;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.beije.model.Order;
import it.beije.model.Product;
import it.beije.model.Users;
import it.beije.service.OrderItemService;
import it.beije.service.OrderService;
import it.beije.service.ProductService;
import it.beije.service.UserService;


//@Controller
@RestController
@RequestMapping("/api")
public class MyRestControllerApi {
@Autowired	
private UserService userService;	
@Autowired
private OrderItemService orderItemService;
@Autowired
private ProductService productService;
@Autowired
private OrderService orderService;
	
	@RequestMapping(value = {"/prodotti"}, method = RequestMethod.GET)
	public List<Product> listaprodotti(HttpServletRequest request, Model model) {
		List<Product> prodotti=productService.returnProducts();
		return prodotti;
	}
	
	@RequestMapping(value = {"/order/{id}"}, method = RequestMethod.GET)
	public Order ordineAperto(@PathVariable int id) {
if(userService.esiste(id))
{
	return orderService.findByUserIDAndState(id, "open");
	
}
return null;
	}
	
	@RequestMapping(value = {"/orders/{id}"}, method = RequestMethod.GET)
	public List<Order> listaordini(@PathVariable int id) {
if(userService.esiste(id))
{
	return orderService.findByUserID(id);
	
}
return null;
	}
	
//	@PostMapping("/users/add")
//public Users insertUser(@RequestBody Users utente) {
//		
//		if(utente.getId().equals(utente))
//			Users u=userService.save(utente);
//		return utente;
//	}
//	
	@GetMapping("/users/all")
	public List<Users> allUser() {
		return userService.findAll();
	}
}




