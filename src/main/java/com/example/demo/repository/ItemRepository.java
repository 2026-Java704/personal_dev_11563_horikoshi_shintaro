package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	/**genreIdとUserIdから項目を特定し返します**/
	List<Item> findByUserIdAndGenreId(Integer userId, Integer genreId);

	/**UserIdから項目を特定し返します**/
	List<Item> findByUserId(Integer userId);

	//	/**GenreのisIncomeから項目を特定します**/
	List<Item> findByUserIdAndGenreIsIncome(Integer userId, boolean bool);

	//	/**特定の期間の項目を返します**/
	List<Item> findByUserIdAndAddDateBetween(Integer userId, LocalDate day1, LocalDate day2);
}
