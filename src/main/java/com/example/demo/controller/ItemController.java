package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Genre;
import com.example.demo.entity.Item;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	private final GenreRepository genreRepository;
	private final ItemRepository itemRepository;

	public ItemController(
			GenreRepository genreRepository,
			ItemRepository itemRepository) {
		this.genreRepository = genreRepository;
		this.itemRepository = itemRepository;
	}

	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			Model model) {

		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findAll();
		model.addAttribute("genres", genreList);

		// 商品一覧情報の取得
		List<Item> itemList = null;
		itemList = itemRepository.findAll();
		model.addAttribute("items", itemList);

		return "items";
	}
}
