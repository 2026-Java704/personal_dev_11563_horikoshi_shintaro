package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	/**genreIdとUserIdから項目を特定します**/
	List<Item> findByUserIdAndGenreId(Integer userId, Integer genreId);

	/**UserIdから項目を特定します**/
	List<Item> findByUserId(Integer userId);
}
