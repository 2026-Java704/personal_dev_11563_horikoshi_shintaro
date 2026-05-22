package com.example.demo.controller;

import java.awt.Color;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// testPage表示
	@GetMapping("/test")
	public String test() {
		return "testPage";
	}

	// 項目一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(defaultValue = "") Integer genreId,
			Model model) {

		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findByUserId(account.getUserId());
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

		String sign = "";
		if (allIncomeAndExpensesInThisMonth > 1) {
			sign += "+";
		}
		String allIncomeAndExpensesInThisMonthText = sign + String.format("%1$,3d", allIncomeAndExpensesInThisMonth);

		model.addAttribute("allIncomeAndExpensesInThisMonth", allIncomeAndExpensesInThisMonthText);

		return "items";
	}

	// 新規登録画面の表示
	@GetMapping("/items/add")
	public String add(
			@RequestParam(required = false) LocalDate selectedDate,
			Model model) {
		// 全ジャンル一覧を取得
		List<Genre> genreList = genreRepository.findByUserId(account.getUserId());
		model.addAttribute("genres", genreList);

		if (selectedDate != null) {
			model.addAttribute("addDate", selectedDate);
		}

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
		List<Genre> genreList = genreRepository.findByUserId(account.getUserId());
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
		List<Genre> genreList = genreRepository.findByUserId(account.getUserId());
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
		List<Genre> genreList = genreRepository.findByUserId(account.getUserId());
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

	// カレンダー画面の表示
	@GetMapping("/calendar")
	public String showCalendor(
			@RequestParam(required = false) Integer id,
			@RequestParam(required = false) Integer genreId,
			@RequestParam(required = false) Boolean showAll,
			@RequestParam(required = false) LocalDate selectedDate,
			@RequestParam(required = false) LocalDate showingDate,

			@RequestParam(required = false) String addGenre_name,
			@RequestParam(required = false) Boolean addGenre_isIncome,
			@RequestParam(required = false) String addGenre_color,

			@RequestParam(required = false) Integer editGenre_id,
			@RequestParam(required = false) String editGenre_name,
			@RequestParam(required = false) Boolean editGenre_isIncome,
			@RequestParam(required = false) String editGenre_color,

			@RequestParam(required = false) Integer deleteGenre_id,

			@RequestParam(required = false) String addItem_name,
			@RequestParam(required = false) Integer addItem_genreId,
			@RequestParam(required = false) Integer addItem_price,
			@RequestParam(required = false) LocalDate addItem_addDate,
			@RequestParam(required = false) String addItem_comment,

			@RequestParam(required = false) Integer editItem_id,
			@RequestParam(required = false) String editItem_name,
			@RequestParam(required = false) Integer editItem_genreId,
			@RequestParam(required = false) Integer editItem_price,
			@RequestParam(required = false) LocalDate editItem_addDate,
			@RequestParam(required = false) String editItem_comment,

			@RequestParam(required = false) Integer deleteItem_id,
			Model model) {

		if (showAll != null)
			account.setSettingIsShowAll(showAll);
		if (genreId != null)
			account.setSettingShowGenreId(genreId);

		if (showingDate != null)
			account.setSettingShowingDate(showingDate);
		if (selectedDate != null)
			account.setSettingSelectedDate(selectedDate);

		if (account.getSettingIsShowAll() != null) {
			if (account.getSettingIsShowAll().booleanValue()) {
				account.setSettingShowGenreId(null);
			}
		}

		//項目追加処理
		if (addItem_name != null && addItem_name.length() > 0 && addItem_genreId != null && addItem_price != null
				&& addItem_addDate != null) {

			Genre genre = genreRepository.findById(addItem_genreId).get();

			Item item = new Item(addItem_name, account.getUser(), genre, addItem_price, addItem_addDate,
					addItem_comment);

			itemRepository.save(item);

		}

		//項目編集処理
		if (id != null) {
			Item item = itemRepository.findById(id).get();
			model.addAttribute("selectedItem", item);
		}
		if (editItem_name != null && editItem_name.length() > 0 && editItem_genreId != null && editItem_price != null
				&& editItem_addDate != null) {

			Genre genre = genreRepository.findById(editItem_genreId).get();

			Item item = itemRepository.findById(editItem_id).get();

			item.changeInfomations(editItem_name, account.getUser(), genre, editItem_price, editItem_addDate,
					editItem_comment);

			itemRepository.save(item);
		}

		//項目削除処理
		if (deleteItem_id != null) {
			itemRepository.deleteById(deleteItem_id);
		}

		//ジャンル追加処理
		if (addGenre_name != null && addGenre_name.length() > 0) {
			boolean isIncome = addGenre_isIncome == null ? false : true;
			String hex = addGenre_color.replace("%", "#");

			Genre genre = new Genre(addGenre_name, isIncome, hex, account.getUser());
			genreRepository.save(genre);
		}

		if (account.getSettingShowGenreId() != null) {
			Genre selectedGenre = genreRepository.findById(account.getSettingShowGenreId()).get();
			model.addAttribute("selectedGenre", selectedGenre);
		}

		//ジャンル編集処理
		if (editGenre_id != null && editGenre_name != null && editGenre_name.length() > 0) {
			boolean isIncome = editGenre_isIncome == null ? false : true;

			Genre genre = genreRepository.findById(editGenre_id).get();
			genre.setGenreName(editGenre_name);

			String hex = editGenre_color.replace("%", "#");
			genre.setColorHex(hex);
			genre.setIsIncome(isIncome);

			genreRepository.save(genre);
		}

		//ジャンル削除処理
		if (deleteGenre_id != null) {
			account.setSettingShowGenreId(null);
			genreRepository.deleteById(deleteGenre_id);
		}

		//		 収入ジャンル一覧を取得
		List<Genre> genre_incomeList = genreRepository.findByUserIdAndIsIncome(account.getUserId(), true);
		model.addAttribute("genres_income", genre_incomeList);

		// 支出ジャンル一覧を取得
		List<Genre> genre_outcomeList = genreRepository.findByUserIdAndIsIncome(account.getUserId(), false);
		model.addAttribute("genres_outcome", genre_outcomeList);

		List<Genre> genreList = genreRepository.findByUserId(account.getUserId());
		model.addAttribute("genres", genreList);

		//表示するアイテムを取得し送信
		List<Item> itemList = itemRepository.findByUserId(account.getUserId());

		//イベント登録
		List<Map<String, Object>> events = new ArrayList<>();
		for (Item item : itemList) {
			Map<String, Object> event = new HashMap<>();
			event.put("id", item.getId()); // データベースのPK（数値型でも文字列型でも可）
			event.put("title", item.getPriceWithSignString());
			event.put("start", item.getAddDate());

			//色を決める
			String backGroundColor = item.getGenre().getColorHex();
			Color color = item.getGenre().getColor();
			double luma = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());

			String textColor = "#FFFFFFFF";
			if (luma >= 128) {
				textColor = "#000000FF";
			}

			//ShowAllではないかつジャンルが指定されていて、違っているものは、色を薄くする
			if (!account.getSettingIsShowAll()) {
				if (account.getSettingShowGenreId() != null) {
					if (account.getSettingShowGenreId() != item.getGenre().getId()) {
						Color fadedColor = new Color(
								color.getRed(),
								color.getGreen(),
								color.getBlue(),
								60);

						// #RRGGBBAA 形式の文字列に変換して永続化用フィールドにセット
						backGroundColor = String.format("#%02X%02X%02X%02X",
								fadedColor.getRed(),
								fadedColor.getGreen(),
								fadedColor.getBlue(),
								fadedColor.getAlpha());

						//文字カラーは黒くした方が見やすいので黒くする
						textColor = "#00000060";
					}
				}
			}
			//showAllでも、現在表示されている月以外は薄くする
			else if (account.getSettingShowingDate() != null) {
				int showingMonth = account.getSettingShowingDate().getMonthValue();

				if (showingMonth != item.getAddDate().getMonthValue()) {
					Color fadedColor = new Color(
							color.getRed(),
							color.getGreen(),
							color.getBlue(),
							60);

					// #RRGGBBAA 形式の文字列に変換して永続化用フィールドにセット
					backGroundColor = String.format("#%02X%02X%02X%02X",
							fadedColor.getRed(),
							fadedColor.getGreen(),
							fadedColor.getBlue(),
							fadedColor.getAlpha());

					textColor = "#00000060";
				}
			}

			event.put("color", backGroundColor);
			event.put("textColor", textColor);
			events.add(event);
		}
		model.addAttribute("calendarEvents", events);

		//現在選択中のアイテムを送信
		if (id != null) {
			Item item = itemRepository.findById(id).get();
			model.addAttribute("selectedItem", item);
		}

		//現在選択中の日付を送信
		if (account.getSettingSelectedDate() != null) {
			model.addAttribute("selectedDate", account.getSettingSelectedDate());
		}

		//現在見ている月を送信
		LocalDate targetDate = account.getSettingShowingDate();
		model.addAttribute("showingDate", targetDate);
		model.addAttribute("initialDate", targetDate);

		//本月の収支を計算
		//最初の日と最後の日
		LocalDate firstDay = targetDate.withDayOfMonth(1);
		LocalDate lastDay = targetDate.with(TemporalAdjusters.lastDayOfMonth());
		//その月の項目を全て取得
		List<Item> itemListInMonth = itemRepository.findByUserIdAndAddDateBetween(account.getUserId(), firstDay,
				lastDay);

		//今月の収支を計算
		int allIncomeAndExpensesInThisMonth = 0;
		for (Item item : itemListInMonth) {
			allIncomeAndExpensesInThisMonth += item.getPriceWithSign();
		}
		String sign = "";
		if (allIncomeAndExpensesInThisMonth > 1) {
			sign += "+";
		}
		String allIncomeAndExpensesInThisMonthText = sign + String.format("%1$,3d", allIncomeAndExpensesInThisMonth);

		model.addAttribute("allIncomeAndExpensesInThisMonth", allIncomeAndExpensesInThisMonthText);
		model.addAttribute("targetMonth", targetDate.getMonthValue());

		//全体の収支を計算
		int allIncomeAndExpenses = 0;
		for (Item item : itemList) {
			allIncomeAndExpenses += item.getPriceWithSign();
		}
		String sign2 = "";
		if (allIncomeAndExpenses > 1) {
			sign2 += "+";
		}
		String allIncomeAndExpensesText = sign2 + String.format("%1$,3d", allIncomeAndExpenses);

		model.addAttribute("allIncomeAndExpenses", allIncomeAndExpensesText);

		return "calendarView";
	}
}
