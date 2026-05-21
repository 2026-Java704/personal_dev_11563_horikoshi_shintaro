package com.example.demo.entity;

import java.awt.Color;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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

	/**色**/
	@Column(name = "color", length = 9)
	private String colorHex;

	/**外部ユーザーID**/
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;

	public Genre() {

	}

	public Genre(String genreName, Boolean isIncome, String colorHex, User userId) {
		this.genreName = genreName;
		this.isIncome = isIncome;
		this.colorHex = colorHex;
		this.userId = userId;
	}

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

	/**Color型の色を返します**/
	@Transient
	public Color getColor() {
		if (this.colorHex == null || this.colorHex.isEmpty()) {
			return null;
		}

		String hex = this.colorHex.startsWith("#") ? this.colorHex.substring(1) : this.colorHex;

		if (hex.length() == 8) {
			int r = Integer.parseInt(hex.substring(0, 2), 16);
			int g = Integer.parseInt(hex.substring(2, 4), 16);
			int b = Integer.parseInt(hex.substring(4, 6), 16);
			int a = Integer.parseInt(hex.substring(6, 8), 16);
			return new Color(r, g, b, a);
		} else if (hex.length() == 6) {
			int r = Integer.parseInt(hex.substring(0, 2), 16);
			int g = Integer.parseInt(hex.substring(2, 4), 16);
			int b = Integer.parseInt(hex.substring(4, 6), 16);
			return new Color(r, g, b, 255); // 透明度がない場合は不透明
		}

		throw new IllegalArgumentException("Invalid color hex format: " + this.colorHex);
	}

	/**Color型の色を引数として、セットします**/
	@Transient
	public void setColor(Color color) {
		if (color == null) {
			this.colorHex = null;
			return;
		}

		// #RRGGBBAA 形式の文字列に変換して永続化用フィールドにセット
		this.colorHex = String.format("#%02X%02X%02X%02X",
				color.getRed(),
				color.getGreen(),
				color.getBlue(),
				color.getAlpha());
	}

	public String getColorHex() {
		return colorHex;
	}

	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}

	public Genre(String genreName, Boolean isIncome) {
		super();
		this.genreName = genreName;
		this.isIncome = isIncome;
	}

	/**収入の場合は"+",支出の場合は"-"を返します**/
	@Transient
	public String getSign() {
		if (isIncome)
			return "+";
		return "-";
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

}
