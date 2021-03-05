package it.beije.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.model.*;
import it.beije.service.*;




@Controller
public class EcommerceController {
	
@Autowired
	private UserService userService;
	
@Autowired
private OrderItemService orderItemService;
@Autowired
private ProductService productService;
@Autowired
private OrderService orderService;
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		List<Product> prodotti=productService.returnProducts();
		model.addAttribute("prodotti", prodotti);
		return "index1";
	}
	@RequestMapping(value = {"logout"}, method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model, Locale locale, HttpSession session) {
	session.invalidate();
		return index( request,  model);
	}
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String login(@RequestParam String email,@RequestParam String password,HttpServletRequest request, Model model,HttpSession session) {
		System.out.println(email);
		System.out.println(password);
		Users utente= userService.findByEmail(email);
		System.out.println(utente.getFirstNname());
		boolean succes=utente.getPassword().equals(password)? true:false;
			 if(succes) {
////				Users users= JPAManager.returnUser(request.getParameter("email"));
//			session.setAttribute("users", users);
//			session.setAttribute("login", "ok");
//			session.setAttribute("session", true);
			}else {
				session.setAttribute("login", "error");
			 session.setAttribute("session", false);
			}
			 
			 return index( request,  model);

			}
	
	@RequestMapping(value = {"/prodotto"}, method = RequestMethod.GET)
	public String prodotto(@RequestParam int id, HttpServletRequest request, Model model, HttpSession session) {
Product prodotto= productService.returnProduct(id);
System.out.println("id prodotto passato "+id+"nome prodotto "+prodotto.getName());
model.addAttribute("prodotto", prodotto);
		return "prodotto";
			}
	
	
	@RequestMapping(value = {"/registrazione"}, method = RequestMethod.GET)
	public String registrazione(HttpServletRequest request, Model model, Locale locale) {
	
		return "registrazione";

			}
	
	@RequestMapping(value = {"/registrazione"}, method = RequestMethod.POST)
	public String registrazione(@RequestParam String email, @RequestParam String password,@RequestParam String name,@RequestParam String surname,HttpServletRequest request, Model model, HttpSession session) {
		boolean successo=userService.addUser(email, name, surname, password);
		if(successo)
		return index( request,  model);
		else {
			session.setAttribute("registrazione", false);
		return "registrazione";

			}
		
	}
	@RequestMapping(value = {"/carrello"}, method = RequestMethod.POST)
	public String carrello(int id,int quan,HttpServletRequest request, Model model, Locale locale, HttpSession session) {
		Users utente=(Users)session.getAttribute("users");
		Order confermato=orderItemService.addOrderItem(utente.getId(), id, quan);
		if(confermato!=null) {
			List<OrderItem> carrello= orderItemService.findByOrderID(confermato.getId());
			model.addAttribute("carrello", carrello);
			return "carrello"; 
		}
		else {
			model.addAttribute("errorAcquisto", "ERRORE");	
			return "prodotto";
		}


			}
	
//	@RequestMapping(value = {"/carrello"}, method = RequestMethod.GET)
//	public String carrello(HttpServletRequest request, Model model, HttpSession session) {
//		Users utente=(Users)session.getAttribute("users");
//			ArrayList<OrderItem> carrello= JPAManager.ritornoCarrello(utente.getId());
//			model.addAttribute("carrello", carrello);
//			session.setAttribute("carrello", carrello);
//			return "carrello"; 
//		
//	}
	

	@PostMapping("/acquista")
	public String acquista( Model model ,HttpSession session ) {
		Users utente=(Users)session.getAttribute("users");
	Order c= orderService.findByUserIDAndState(utente.getId(), "open");
			orderService.changeStateClose(c);
	List<Order> ordini= orderService.findByUserID(utente.getId());
	model.addAttribute("ordini", ordini);
		return "ordini";
	}
			
	@GetMapping("/ordini")
	public String ordini( Model model ,HttpSession session ) {
		Users utente=(Users)session.getAttribute("users");
	List<Order> ordini= orderService.findByUserID(utente.getId());
	model.addAttribute("ordini", ordini);
		return "ordini";
	}
			
	@GetMapping("/carrello")
	public String carrello( Model model ,HttpSession session ) {
		Users utente=(Users)session.getAttribute("users");
		Order c= orderService.findByUserIDAndState(utente.getId(), "open");
		List<OrderItem> carrello= orderItemService.findByOrderID(c.getId());
		model.addAttribute("carrello", carrello);
		return "carrello"; 

	}
			
}
	
	
	
	

