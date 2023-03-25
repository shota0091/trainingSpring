package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		List<Item> items = itemService.serachAll();
		model.addAttribute("items",items);
		return "index";
	}
}
