package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

	/**固有ID**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**項目の名前**/
	@Column(name = "item_name")
	private String itemName;

	/**外部キーユーザーID**/
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**外部キージャンルID**/
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genre genre;

	/**値段**/
	@Column(name = "price")
	private Integer price;

	/**日付**/
	@Column(name = "add_date")
	private LocalDate addDate;

	/**備考**/
	@Column(name = "comment")
	private String comment;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public LocalDate getAddDate() {
		return addDate;
	}

	public void setAddDate(LocalDate addDate) {
		this.addDate = addDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public Item() {

	}

	public Item(String itemName, User user, Genre genre, Integer price, LocalDate addDate, String comment) {
		this.itemName = itemName;
		this.user = user;
		this.genre = genre;
		this.price = price;
		this.addDate = addDate;
		this.comment = comment;
	}

	/**情報を変えます**/
	public void changeInfomations(String itemName, User user, Genre genre, Integer price, LocalDate addDate,
			String comment) {
		this.itemName = itemName;
		this.user = user;
		this.genre = genre;
		this.price = price;
		this.addDate = addDate;
		this.comment = comment;
	}

	/**符号付きの収支を返します**/
	public int getPriceWithSign() {
		if (genre.getIsIncome()) {
			return price;
		} else {
			return -price;
		}
	}

}
