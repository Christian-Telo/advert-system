package com.web.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.model.Advert;
import com.web.app.model.NewUser;
import com.web.app.model.Roles;
import com.web.app.model.User;
import com.web.app.service.AdvertService;
import com.web.app.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommonController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AdvertService advertService;
	
	@GetMapping("/")
	public ModelAndView loginPage() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("user", new User());
		modelandview.setViewName("login");
		return modelandview;
	}
	
	@GetMapping("/signup")
	public ModelAndView signupPage() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("newUser", new NewUser());
		modelandview.setViewName("signup");
		return modelandview;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashboardPage() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("adverts", advertService.allAdvert());
		modelandview.setViewName("dashboard");
		return modelandview;
	}
	
	@GetMapping("/index")
	public ModelAndView indexPage() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("index");
		return modelandview;
	}
	
	@GetMapping("/about")
	public ModelAndView aboutPage() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("about");
		return modelandview;
	}
	
	@GetMapping("/advert/new")
	public ModelAndView newAdvertPage() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("addAdvert");
		modelandview.addObject("advert", new Advert());
		return modelandview;
	}
	
	@GetMapping("/advert/delete/{id}")
	public String delete(@PathVariable int id) {
		advertService.delete(id);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/user/new")
	public String addUser(NewUser newUser) {
		userService.createUser(newUser);
		return "redirect:/";
	}
	
	@PostMapping("/user/login")
	public String Login(User user, HttpSession session, Model model) {
		user = userService.logUser(user);
		if(user.getRole().equals(Roles.ADMIN)) {
			return "redirect:/dashboard";
		}else {
			return "redirect:/index";
		}
	}
	
	@PostMapping("/advert/new")
	public String Login(Advert advert) throws IOException {
		advertService.createAdvert(advert);
		return "redirect:/dashboard";
	}
}
