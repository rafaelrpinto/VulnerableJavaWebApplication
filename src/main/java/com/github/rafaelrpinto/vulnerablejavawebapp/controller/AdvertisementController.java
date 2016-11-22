package com.github.rafaelrpinto.vulnerablejavawebapp.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.rafaelrpinto.vulnerablejavawebapp.model.Advertisement;
import com.github.rafaelrpinto.vulnerablejavawebapp.model.User;
import com.github.rafaelrpinto.vulnerablejavawebapp.repository.AdvertisementRepository;

/**
 * 
 * Controller responsible for requests related to Advertisements.
 * 
 * @author Rafael
 *
 */
@Controller
public class AdvertisementController {

	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	@RequestMapping(value = "/")
	String home(Model model) {
		model.addAttribute("ads", this.advertisementRepository.findAll());
		return "home";
	}
	
	@RequestMapping(value = "/search")
	String search(@RequestParam("keyword") String keyword,  Model model) {
		model.addAttribute("ads", this.advertisementRepository.findByKeyword(keyword));
		model.addAttribute("keyword", keyword);
		return "home";
	}
	
	@RequestMapping(value = "/secure/newAd", method=RequestMethod.GET)
	String showNewAdForm(Model model) {
		return "newAdForm";
	}
	
	@RequestMapping(value = "/secure/newAd", method=RequestMethod.POST)
	String processNewAdForm(@RequestParam("title") String title, @RequestParam("text") String text, HttpSession session) {
		
		User user = (User) session.getAttribute("sessionUser");
		
		Advertisement ad = new Advertisement();
		ad.setTitle(title);
		ad.setText(text);
		ad.setCreateDate(new Date());
		ad.setUser(user);
		
		this.advertisementRepository.insert(ad);
		
		return "redirect:/";
	}
}
