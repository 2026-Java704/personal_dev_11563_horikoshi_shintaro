package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	/**メールアドレスからユーザーを特定します**/
	User findByEmail(String email);

	/**メールアドレスとパスワードからユーザーを特定します**/
	User findByEmailAndPassword(String email, String password);
}
