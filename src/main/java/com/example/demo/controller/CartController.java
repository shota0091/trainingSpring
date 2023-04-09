package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	ArrayList<Integer> purchaseList = new ArrayList<>();
	LinkedHashSet<Item> basketList = new LinkedHashSet<>();
	// 買い物かご
	@RequestMapping("/item/itemBox")
	public String box(CartFrom from) {
		session.setAttribute("baskes",basketList);
		session.setAttribute("purchase",purchaseList);
		return "html/itemBox";
	}

	// 買い物籠追加
	@RequestMapping("/item/itemBox/add")
	public String boxAdd(@ModelAttribute Item item,CartFrom from,Model model) {
		basketList.add(item);
		if(basketList.size() == (purchaseList.size() + 1)) {
			purchaseList.add(from.getPurchaseQuantity());
		}else {
			ArrayList<Item> list = new ArrayList<Item>(basketList);
			int i = list.indexOf(item);
			purchaseList.set(i,purchaseList.get(i) + from.getPurchaseQuantity());
			if(item.getQuantity() < purchaseList.get(i)) {
				model.addAttribute("caveat","商品より数が多いです。");
				purchaseList.set(i,purchaseList.get(i) - from.getPurchaseQuantity());
				return "forward:/item/itemBox";
			}
		}
		return "redirect:/item/itemBox";
	}

	// 買い物籠削除
	@RequestMapping("/item/itemBox/remove/{id}")
	public String remove(@ModelAttribute Item item,@PathVariable int id) {
		basketList.remove(item);
		purchaseList.remove(id);
		return "redirect:/item/itemBox";
	}
}
