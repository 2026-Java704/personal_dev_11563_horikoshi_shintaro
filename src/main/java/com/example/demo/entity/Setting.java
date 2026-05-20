package com.example.demo.entity;

public class Setting {

	/**全ての項目を表示するかどうか**/
	private Boolean showAll = true;

	/**表示する項目ID**/
	private Integer showGenreId = null;

	public Setting(Boolean showAll, Integer showGenreId) {
		this.showAll = showAll;
		this.showGenreId = showGenreId;
	}

	public Boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(Boolean showAll) {
		this.showAll = showAll;
	}

	public Integer getShowGenreId() {
		return showGenreId;
	}

	public void setShowGenreId(Integer showGenreId) {
		this.showGenreId = showGenreId;
	}

}
