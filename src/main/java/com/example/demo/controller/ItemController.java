package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Genre;
import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class ItemController {

	private final UserRepository userRepository;
	private final GenreRepository genreRepository;
	private final ItemRepository itemRepository;

	public ItemController(
			UserRepository userRepository,
			GenreRepository genreRepository,
			ItemRepository itemRepository) {
		this.userRepository = userRepository;
		this.genreRepository = genreRepository;
		this.itemRepository = itemRepository;
	}

	// 項目一覧表示
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

	// 新規登録画面の表示
	@GetMapping("/items/add")
	public String add(Model model) {
		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findAll();
		model.addAttribute("genres", genreList);

		return "addItem";
	}

	// 新規登録処理
	@PostMapping("/items/add")
	public String store(@PathVariable Integer id,
			@RequestParam String itemName,
			@RequestParam Integer userId,
			@RequestParam Integer genreId,
			@RequestParam Integer price,
			@RequestParam LocalDate addDate,
			@RequestParam(required = false) String comment,
			Model model) {

		//itemsテーブルをIDで検索
		User user = userRepository.findById(userId).get();
		Genre genre = genreRepository.findById(genreId).get();

		// Itemオブジェクトの生成
		Item item = new Item(itemName, user, genre, price, addDate, comment);
		// itemsテーブルへの反映（INSERT）
		itemRepository.save(item);

		// 「/items」にGETでリクエストし直す（リダイレクト）
		return "redirect:/items";
	}

	// 更新画面表示
	@GetMapping("/items/{id}/edit")
	public String edit(@PathVariable Integer id, Model model) {

		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findAll();
		model.addAttribute("genres", genreList);

		// 全ユーザー一覧を取得
		List<User> userList = userRepository.findAll();
		model.addAttribute("users", userList);

		//itemsテーブルをIDで検索
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);

		return "editItem";
	}

	// 更新処理
	@PostMapping("/items/{id}/edit")
	public String edit(@PathVariable Integer id,
			@RequestParam String itemName,
			@RequestParam Integer userId,
			@RequestParam Integer genreId,
			@RequestParam Integer price,
			@RequestParam LocalDate addDate,
			@RequestParam(required = false) String comment,
			Model model) {

		//itemsテーブルをIDで検索
		Item item = itemRepository.findById(id).get();
		User user = userRepository.findById(userId).get();
		Genre genre = genreRepository.findById(genreId).get();
		item.changeInfomations(itemName, user, genre, price, addDate, comment);

		itemRepository.save(item);

		return "redirect:/items";
	}

	// 削除処理
	@PostMapping("/items/{id}/delete")
	public String delete(@PathVariable Integer id) {

		//itemsテーブルをIDで検索
		itemRepository.deleteById(id);

		return "redirect:/items";
	}
}
