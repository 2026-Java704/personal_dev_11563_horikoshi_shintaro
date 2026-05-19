package com.example.demo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

	//	private final HttpSession session;
	//	private final Account account;
	//
	//	public AccountController(HttpSession session, Account account) {
	//		this.session = session;
	//		this.account = account;
	//	}
	//
	//	// 会員登録画面を表示
	//	@GetMapping("/account")
	//	public String create() {
	//
	//		return "accountForm";
	//	}

	//	// 会員登録を実行
	//	@PostMapping("/account")
	//	public String store(
	//			@RequestParam(defaultValue = "") String name,
	//			@RequestParam(defaultValue = "") String address,
	//			@RequestParam(defaultValue = "") String tel,
	//			@RequestParam(defaultValue = "") String email,
	//			@RequestParam(defaultValue = "") String password,
	//			Model model) {
	//
	//		List<String> errors = new ArrayList<String>();
	//
	//		if (name.length() <= 0) {
	//			errors.add("名前は必須です");
	//		}
	//
	//		if (address.length() <= 0) {
	//			errors.add("住所は必須です");
	//		}
	//
	//		if (tel.length() <= 0) {
	//			errors.add("電話番号は必須です");
	//		}
	//
	//		if (email.length() <= 0) {
	//			errors.add("メールアドレスは必須です");
	//		}
	//
	//		Customer customer = customerRepository.findByEmail(email);
	//		if (customer != null) {
	//			errors.add("登録済みのメールアドレスです");
	//		}
	//
	//		if (password.length() <= 0) {
	//			errors.add("パスワードは必須です");
	//		}
	//
	//		if (errors.size() > 0) {
	//			model.addAttribute("name", name);
	//			model.addAttribute("address", address);
	//			model.addAttribute("tel", tel);
	//			model.addAttribute("email", email);
	//
	//			model.addAttribute("errors", errors);
	//			return "accountForm";
	//		}
	//
	//		Customer newCustomer = new Customer(name, address, tel, email, password);
	//		customerRepository.save(newCustomer);
	//
	//		return "redirect:/login";
	//	}
	//
	//	// ログイン画面を表示
	//	@GetMapping({ "/", "/login", "/logout" })
	//	public String index() {
	//		// セッション情報を全てクリアする
	//		session.invalidate();
	//
	//		return "login";
	//	}
	//
	//	// ログインを実行
	//	@PostMapping("/login")
	//	public String login(
	//			@RequestParam String email,
	//			@RequestParam String password,
	//			Model model) {
	//
	//		Customer targetCustomer = customerRepository.findByEmailAndPassword(email, password);
	//		if (targetCustomer == null) {
	//
	//			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
	//			return "login";
	//		}
	//
	//		//		 セッション管理されたアカウント情報に名前をセット
	//		account.setId(targetCustomer.getId());
	//		account.setName(targetCustomer.getName());
	//		account.setAddress(targetCustomer.getAddress());
	//		account.setTel(targetCustomer.getTel());
	//		account.setEmail(targetCustomer.getEmail());
	//
	//		// 「/items」へのリダイレクト
	//		return "redirect:/items";
	//	}
}
