package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abc.entity.MyUser;
import com.abc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/git")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
	@GetMapping("/contactus")
	public String contactPage() {
		return "contact";
	}
	
	@GetMapping("/product")
	public String productPage() {
		return "product";
	}
	
	@GetMapping("/admin")
	public String admindashboardPage() {
		return "admindashboard";
	}
	
	@GetMapping("/super")
	public String superadmindashboardPage() {
		return "superdashboard";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("myuser",new MyUser());
		return "signup";
	}
	
	@PostMapping("/save")
	public String registerPage(@ModelAttribute MyUser myUser,Model model,RedirectAttributes attributes) {
		MyUser saveUser = service.saveUser(myUser);
		
		if(saveUser!=null) {
			attributes.addFlashAttribute("success","User registered successfully");
		}
		
		return "redirect:/signup";
	}
}
