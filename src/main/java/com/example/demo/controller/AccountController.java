package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Setting;
import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class AccountController {

	private final HttpSession session;
	private final Account account;
	private final UserRepository userRepository;

	public AccountController(UserRepository userRepository, HttpSession session,
			Account account) {
		this.userRepository = userRepository;
		this.session = session;
		this.account = account;
	}

	// 会員登録画面を表示
	@GetMapping("/register")
	public String create() {

		return "accountForm";
	}

	// 会員登録を実行
	@PostMapping("/register")
	public String store(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String email,
			@RequestParam(defaultValue = "") String password,
			@RequestParam(defaultValue = "") String passwordConfirm,
			Model model) {

		List<String> errors = new ArrayList<String>();

		if (name.length() <= 0) {
			errors.add("名前は必須です");
		}

		if (email.length() <= 0) {
			errors.add("メールアドレスは必須です");
		}

		User user = userRepository.findByEmail(email);
		if (user != null) {
			errors.add("登録済みのメールアドレスです");
		}

		if (password.length() <= 0) {
			errors.add("パスワードは必須です");
		}

		if (!password.equals(passwordConfirm)) {
			errors.add("パスワードが間違っています");
		}

		if (errors.size() > 0) {
			model.addAttribute("name", name);
			model.addAttribute("email", email);

			model.addAttribute("errors", errors);
			return "accountForm";
		}

		User newUser = new User(name, email, password);
		userRepository.save(newUser);

		return "redirect:/login";
	}

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		// セッション情報を全てクリアする
		session.invalidate();

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {

		User targetUser = userRepository.findByEmailAndPassword(email, password);
		if (targetUser != null) {
			account.setUser(targetUser);
			account.setSetting(new Setting(true, null, LocalDate.now(), LocalDate.now()));
			// 「/items」へのリダイレクト
			return "redirect:/calendar";

		} else {

			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
	}
}
