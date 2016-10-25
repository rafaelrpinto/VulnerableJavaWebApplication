package com.github.rafaelrpinto.vulnerablejavawebapp.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.rafaelrpinto.vulnerablejavawebapp.model.User;
import com.github.rafaelrpinto.vulnerablejavawebapp.repository.UserRepository;

/**
 * 
 * Controller for requests related to users.
 * 
 * @author Rafael
 *
 */
@Controller
public class UserController {

	private static final Logger LOGGER = Logger.getLogger( UserController.class.getName() );
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	String showLogin(HttpSession session) {
		LOGGER.info("Logging out...");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String showLoginForm(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	String processLoginForm(@RequestParam("login") String login, @RequestParam("password") String password, Model model, HttpSession session) {
		LOGGER.info("Processing login for " + login);
		User user = this.userRepository.authenticate(login, password);
		if (user != null) {
			LOGGER.info("Login successful!");
			session.setAttribute("sessionUser", user);
			return "redirect:/";
		} else {
			LOGGER.info("Invalid credentials!");
			model.addAttribute("invalidCredentials", true);
			return showLoginForm(model);
		}
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	String showSignUpForm(Model model) {
		return "signUp";
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	String processSignUpForm(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, 
					Model model, HttpSession session) {
		LOGGER.info("Processing sign up form!");
		User user = this.userRepository.find(email);
		if (user == null) {
			user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			
			this.userRepository.insert(user);
			session.setAttribute("sessionUser", user);
			
			LOGGER.info("Sugn up successful!");
			
			return "forward:/";
		} else {
			LOGGER.info("E-mail already exist: " + email);
			model.addAttribute("emailExists", true);
			return showSignUpForm(model);
		}
	}
}
