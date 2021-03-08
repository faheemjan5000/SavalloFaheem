package it.beije.api;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.beije.model.Order;
import it.beije.model.OrderItem;
import it.beije.model.Product;
import it.beije.model.Users;
import it.beije.service.OrderItemService;
import it.beije.service.OrderService;
import it.beije.service.ProductService;
import it.beije.service.UserService;


//@Controller
@CrossOrigin(origins="http://localhost:3000")
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

	@GetMapping("/prodotti")
	public List<Product> listaprodotti(HttpServletRequest request, Model model) {
		List<Product> prodotti=productService.returnProducts();
		return prodotti;
	}
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/session/user")
	public Users utenteSession(HttpServletRequest request, HttpSession session) {
		System.out.println("sono in sessiione provo a vedere l'utente"+(Users)(session.getAttribute("users")));

		Users c= (Users)request.getSession().getAttribute("users");
		System.out.println("sono in sessiion "+c);
		if(c!=null)
			return c;
		else 
			return new Users();
	}
	  @CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/login/user")
	public Users loginSession( @RequestBody Users utente,HttpServletRequest request) {
		Users inDB=userService.findByEmail(utente.getEmail());

		if(inDB!=null)
			if(inDB.getPassword().equals(utente.getPassword())) {
				System.out.println("sono nel login registro l'utente "+inDB.getSecondName());
				request.getSession().setAttribute("users", inDB);
				System.out.println("sono nel login registro l'utente "+	request.getSession().getAttribute("users"));

				return inDB;
			}
		System.out.println("sono nel login registro null "+null);

		return null;
	}

	@GetMapping("/logout/user")
	public Users logout(HttpSession session) {
		session.invalidate();
		return null;
	}


	@RequestMapping(value = {"/order/{id}"}, method = RequestMethod.GET)
	public Order ordineAperto(@PathVariable int id) {
		if(userService.esiste(id))
		{
			return orderService.findByUserIDAndState(id, "open");

		}
		return null;
	}


	@GetMapping("/prodotto/{id}")
	public Product prodottoID(@PathVariable Integer id) {
		if(productService.returnProduct(id)!= null)
			return productService.returnProduct(id);
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

	@PostMapping("/users/add")
	public Users insertUser(@RequestBody Users utente) {

		if(userService.addUser(utente.getEmail(), utente.getFirstNname(), utente.getSecondName(), utente.getPassword())) {
			return userService.findByEmail(utente.getEmail());
		}
		return utente;
	}


	@GetMapping("/users/all")
	public List<Users> allUser() {
		return userService.findAll();
	}


	@RequestMapping(value = {"/carrello"}, method = RequestMethod.POST)
	public List<OrderItem>  carrello(int id,int quan, HttpSession session) {
		Users utente=(Users)session.getAttribute("users");
		Order confermato=orderItemService.addOrderItem(utente.getId(), id, quan);
		if(confermato!=null) {
			List<OrderItem> carrello= orderItemService.findByOrderID(confermato.getId());
			session.setAttribute("carrello", carrello);
			return carrello; 
		}
		else {
			session.setAttribute("errorAcquisto", "ERRORE");	
			return null;
		}
	}


	@PostMapping("/acquista")
	public List<Order> acquista( Model model ,HttpSession session ) {
		Users utente=(Users)session.getAttribute("users");
		Order c= orderService.findByUserIDAndState(utente.getId(), "open");
		orderService.changeStateClose(c);
		List<Order> ordini= orderService.findByUserID(utente.getId());
		model.addAttribute("ordini", ordini);
		return ordini;
	}
	
	@GetMapping("/ordini")
	public  List<Order> ordini( Model model ,HttpSession session ) {
		Users utente=(Users)session.getAttribute("users");
		List<Order> ordini= orderService.findByUserID(utente.getId());
		model.addAttribute("ordini", ordini);
		return ordini;
	}

	@GetMapping("/carrello")
	public List<OrderItem> carrello( Model model ,HttpSession session ) {
		Users utente=(Users)session.getAttribute("users");
		Order c= orderService.findByUserIDAndState(utente.getId(), "open");
		List<OrderItem> carrello= orderItemService.findByOrderID(c.getId());
		model.addAttribute("carrello", carrello);
		return carrello; 

	}
}




