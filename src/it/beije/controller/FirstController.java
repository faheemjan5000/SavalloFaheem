package it.beije.controller;
//package it.beije.ananke.controller;
//
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import it.beije.ananke.model.Contatto;
//import it.beije.ananke.repository.ContattoRepository;
//import it.beije.ananke.service.RubricaService;
//
//
//@Controller
//public class FirstController {
//	//	@Autowired
////	private ContattoRepository contattoRepository;
//	@Autowired
//	private RubricaService rubricaService;
//
//	
//
////	@RequestMapping(value = {"/", "index", "pippopluto"}, method = RequestMethod.GET)
////	public String index(HttpServletRequest request, Model model, Locale locale) {
////		System.out.println("sono nella index..." + request.getRequestURL() + " - locale " + locale.getDisplayCountry());
////		
////		String saluto = "Buongiorno";
////		model.addAttribute("saluto", saluto);
////		
////		return "index";
////	}
//	
//	@RequestMapping(value = "/hello", method = RequestMethod.GET)
//	public String hello() {
//		System.out.println("sono nella hello...");
//		return "hello";
//	}
//
//	@RequestMapping(value = "/form", method = RequestMethod.GET)
//	public String form() {
//		System.out.println("get form...");
//		return "form";
//	}
//
//	@RequestMapping(value = "/form", method = RequestMethod.POST)
//	public String form(@RequestParam(required = false) String nome, @RequestParam String cognome, HttpServletRequest request, Model model) {
//		System.out.println("post form...");
//		
//		//String nome = request.getParameter("nome");
//		//String cognome = request.getParameter("cognome");
//		
//		model.addAttribute("nome", nome);
//		model.addAttribute("cognome", cognome);
//		
//		return "dati";
//	}
//
//	@RequestMapping(value = "/contatto/{id}", method = RequestMethod.GET)
//	public String getContatto(@PathVariable Integer id, Model model) {
//		System.out.println("getContatto id : " + id);
//		
//		//... SELECT * from Contatto where id = {id}
//		
//		model.addAttribute("nome", "Pippo");
//		model.addAttribute("cognome", "Pluto");
//		
//		return "dati";
//	}
//
//	@RequestMapping(value = "/contatto", method = RequestMethod.GET)//contatto?id=5
//	public String getContatto(@RequestParam int id, Model model) {
//		System.out.println("getContatto id parametro : " + id);
//		
//		//... SELECT * from Contatto where id = {id}
//		
//		model.addAttribute("nome", "Pippo");
//		model.addAttribute("cognome", "Pluto");
//		
//		return "dati";
//	}
//	
//	
//	@RequestMapping(value = "/contatto", method = RequestMethod.POST)
//	public String postContatto(Contatto c, Model model) {
//		System.out.println("postContatto : " + c);
//		
//
//		try {
//			rubricaService.checkAndSave(c);
//			
//		} catch (Exception e) {
//			model.addAttribute("errore", e.getMessage());
//			return "dati"; 
//		}
//
//
//		model.addAttribute("contatto", c);
//		
//		return "datiContatto"; 
//	}
//
//}
