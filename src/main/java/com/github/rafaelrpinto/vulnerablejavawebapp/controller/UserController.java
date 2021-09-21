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


}
