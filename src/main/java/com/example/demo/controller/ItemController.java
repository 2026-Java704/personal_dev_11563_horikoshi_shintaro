package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.example.demo.model.Account;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class ItemController {

	private final Account account;
	private final UserRepository userRepository;
	private final GenreRepository genreRepository;
	private final ItemRepository itemRepository;

	public ItemController(
			Account account,
			UserRepository userRepository,
			GenreRepository genreRepository,
			ItemRepository itemRepository) {
		this.account = account;
		this.userRepository = userRepository;
		this.genreRepository = genreRepository;
		this.itemRepository = itemRepository;
	}

	// 項目一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(defaultValue = "") Integer genreId,
			Model model) {

		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findAll();
		model.addAttribute("genres", genreList);

		// 商品一覧情報の取得
		List<Item> itemList = null;

		if (genreId != null) {
			itemList = itemRepository.findByUserIdAndGenreId(account.getUserId(), genreId);

		} else {
			itemList = itemRepository.findByUserId(account.getUserId());
		}

		model.addAttribute("items", itemList);
		model.addAttribute("account", account);

		//今月の収支を計算
		int allIncomeAndExpensesInThisMonth = 0;
		for (Item item : itemList) {
			allIncomeAndExpensesInThisMonth += item.getPriceWithSign();
		}
		model.addAttribute("allIncomeAndExpensesInThisMonth", allIncomeAndExpensesInThisMonth);

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
	public String store(
			@RequestParam(defaultValue = "") String itemName,
			@RequestParam Integer genreId,
			@RequestParam(defaultValue = "") Integer price,
			@RequestParam(defaultValue = "") LocalDate addDate,
			@RequestParam(defaultValue = "") String comment,
			Model model) {

		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findAll();
		model.addAttribute("genres", genreList);

		List<String> errors = new ArrayList<String>();

		if (price == null) {
			errors.add("金額を入力してください");
		} else {
			if (price < 0) {
				errors.add("金額を正の値にしてください");
			}
		}

		if (errors.size() > 0) {
			model.addAttribute("itemName", itemName);
			model.addAttribute("genreId", genreId);
			model.addAttribute("price", price);
			model.addAttribute("addDate", addDate);
			model.addAttribute("comment", comment);

			model.addAttribute("errors", errors);
			return "addItem";
		}

		if (itemName.length() <= 0) {
			itemName = "無題";
		}

		if (addDate == null) {
			addDate = LocalDate.now();
		}

		//itemsテーブルをIDで検索
		User user = account.getUser();
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
			@RequestParam(defaultValue = "") String itemName,
			@RequestParam Integer genreId,
			@RequestParam(defaultValue = "") Integer price,
			@RequestParam(defaultValue = "") LocalDate addDate,
			@RequestParam(defaultValue = "") String comment,
			Model model) {

		//itemsテーブルをIDで検索
		Item targetItem = itemRepository.findById(id).get();
		model.addAttribute("item", targetItem);

		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findAll();
		model.addAttribute("genres", genreList);

		List<String> errors = new ArrayList<String>();

		if (price == null) {
			errors.add("金額を入力してください");
		} else {
			if (price < 0) {
				errors.add("金額を正の値にしてください");
			}
		}

		if (errors.size() > 0) {
			model.addAttribute("item", targetItem);
			model.addAttribute("errors", errors);
			return "editItem";
		}

		if (itemName.length() <= 0) {
			itemName = "無題";
		}

		if (addDate == null) {
			addDate = LocalDate.now();
		}

		//itemsテーブルをIDで検索
		Item item = itemRepository.findById(id).get();
		User user = account.getUser();
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
