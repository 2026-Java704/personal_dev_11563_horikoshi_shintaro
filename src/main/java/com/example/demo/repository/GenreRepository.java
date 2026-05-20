package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

	//収入か支出かを選んで取得
	List<Genre> findByIsIncome(boolean bool);
}
