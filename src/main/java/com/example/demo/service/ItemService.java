package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Item;
import com.example.demo.itemFrom.ItemFrom;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemService {
	
	
	@Autowired
	ItemRepository itemRepository;
	
	
	public List<Item> serachAll(){
		return itemRepository.findAll();
	}
	
	public Item fimdById(int id) {
		return itemRepository.getReferenceById(id);
		
	}
	
	public void save(ItemFrom from) {
		Item item = new Item();
		item.setName(from.getName());
		item.setPrice(from.getPrice());
		item.setText(from.getText());
		itemRepository.save(item);
		return;
	}

}
