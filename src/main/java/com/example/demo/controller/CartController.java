package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Item;
import com.example.demo.itemFrom.CartFrom;
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
	
	ArrayList<Item> basketList = new ArrayList<>();
	// 買い物かご
	@RequestMapping("/item/itemBox")
	public String box(CartFrom from) {
		session.setAttribute("baskes",basketList);
		return "html/itemBox";
	}

	// 買い物籠追加
	@RequestMapping("/item/itemBox/add")
	public String boxAdd(@ModelAttribute Item item,CartFrom from) {
		item.setQuantity(from.getPurchaseQuantity());
		int id1 = basketList.lastIndexOf(item);
		
		System.out.println(id1);
		if(basketList.isEmpty()) {
			basketList.add(item);
		}else if(id1 < 0) {
			if(basketList.contains(item) == false) {
				basketList.add(item);
				System.out.println(222123);
			}else {
				System.out.println(123);
			}
			
		}else if(id1 > 0){
			int i = basketList.get(id1).getId();
			if(i == item.getId()) {
				System.out.println(456);
			}else {
				System.out.println(789);
				basketList.add(item);
			}
			
			
		}
			
//			System.out.println(basketList);
		return "redirect:/item/itemBox";
	}
	
	// 買い物籠削除
	@RequestMapping("/item/itemBox/remove/{id}")
	public String remove(@PathVariable int id) {
		basketList.remove(id);
		return "redirect:/item/itemBox";
	}
}
