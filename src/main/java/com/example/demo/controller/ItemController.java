package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@RequestMapping("/item/index")
	public String index(Model model) {
		List<Item> items = itemService.serachAll();
		model.addAttribute("items",items);
		return "html/index";
	}
	
	// 詳細ページ
	@RequestMapping("/item/show/{id}")
	public String show(@PathVariable int id,Model model) {
		Item item = itemService.fimdById(id);
		model.addAttribute("id",item);
		return "html/show";
		
	}
}
