package it.beije.api;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import it.beije.model.*;

import it.beije.service.*;


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
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public List<Product> index(HttpServletRequest request, Model model) {
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




