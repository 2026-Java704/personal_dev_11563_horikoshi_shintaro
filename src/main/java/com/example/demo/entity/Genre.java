package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {

	/**固有ID**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**ジャンルの名前**/
	@Column(name = "genre_name")
	private String genreName;

	/**収入かどうか**/
	@Column(name = "is_income")
	private Boolean isIncome;

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Boolean getIsIncome() {
		return isIncome;
	}

	public void setIsIncome(Boolean isIncome) {
		this.isIncome = isIncome;
	}

	public Integer getId() {
		return id;
	}

	public Genre() {

	}

	public Genre(String genreName, Boolean isIncome) {
		super();
		this.genreName = genreName;
		this.isIncome = isIncome;
	}

	/**収入の場合は"+",支出の場合は"-"を返します**/
	public String getSign() {
		if (isIncome)
			return "+";
		return "-";
	}

}
