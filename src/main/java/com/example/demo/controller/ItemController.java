package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository itemrepositoy;
	
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("items",itemrepositoy.findAll());
		return "index";
	}
}
