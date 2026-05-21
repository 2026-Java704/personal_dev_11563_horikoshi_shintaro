package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

	//収入か支出かを選んで取得
	List<Genre> findByUserIdAndIsIncome(Integer userId, boolean bool);

	// SELECT * FROM genres WHERE user_id = ?
	List<Genre> findByUserId(Integer userId);
}
