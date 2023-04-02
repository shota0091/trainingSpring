package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ItemRepository itemRepository;
	
	List<Item> basketList = new ArrayList<Item>();
	// 買い物かご
	@RequestMapping("/item/itemBox")
	public String box() {
		session.setAttribute("baskes",basketList);
		return "html/itemBox";
	}

	// 買い物籠追加
	@RequestMapping("/item/itemBox/add")
	public String boxAdd(@ModelAttribute Item item) {
		basketList.add(item);
		return "redirect:/item/itemBox";
	}
	
	// 買い物籠追加
	@RequestMapping("/item/itemBox/remove/{id}")
	public String remove(@PathVariable int id) {
		basketList.remove(id);
		return "redirect:/item/itemBox";
	}
}
