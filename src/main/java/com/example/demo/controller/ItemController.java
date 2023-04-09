package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Item;
import com.example.demo.itemFrom.ItemFrom;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ItemRepository itemRepository;
	
	List<Item> basketList = new ArrayList<Item>();
	
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
		model.addAttribute("item",item);
		return "html/show";
		
	}

	// 新規投稿ページ
	@RequestMapping("/item/newItemInput")
	public String newItem() {
		return "html/new";
	}
	
	// 新規投稿ページ
	@RequestMapping("/item/create")
	public String create(ItemFrom from) {
		itemService.save(from);
		return "redirect:/item/index";
	}



}
