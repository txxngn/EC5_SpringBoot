package ca.sheridancollege.ngquocth.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.ngquocth.beans.Burger;
import ca.sheridancollege.ngquocth.database.BurgerDatabase;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BurgerController {

	@GetMapping ("/")
	public String root() {
		return "home.html";
	}
	
	
	@GetMapping ("/add")
	public String goAdd() {
		return "addBurger.html";
	}
	
	
	@GetMapping ("/addBurger")
	public String addBurger(@RequestParam String name, 
			@RequestParam(required=false,defaultValue="false") boolean vegetarian, 
			@RequestParam String toppings, 
			@RequestParam double price){
		
		Burger b = new Burger (name, vegetarian,toppings, price);
		
		BurgerDatabase.burgerList.add(b);
		
		return "addBurger.html";
		
	}
	
	
	@GetMapping("/view")
	public void showBurger(HttpServletResponse response) throws IOException { 
		String bu = "";
		bu +="<ul>";
		
		for (Burger b : BurgerDatabase.burgerList) {
			bu += b + "</br>";
		}
		
		bu +="</ul>";
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>" + 
				"<h1>View Burger List</h1>" +
				bu +
				"</body></html>");

				
		
	}	
	
	
}
