package com.example.demo.entity;

import java.time.LocalDate;

public class Setting {

	/**全ての項目を表示するかどうか**/
	private Boolean showAll = true;

	/**表示する項目ID**/
	private Integer showGenreId = null;

	/**表示する月**/
	private LocalDate showingDate = null;

	/**選択している日付**/
	private LocalDate selectedDate = null;

	public Setting(Boolean showAll, Integer showGenreId, LocalDate showingDate, LocalDate selectedDate) {
		this.showAll = showAll;
		this.showGenreId = showGenreId;
		this.showingDate = showingDate;
		this.selectedDate = selectedDate;
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

	public LocalDate getShowingDate() {
		return showingDate;
	}

	public void setShowingDate(LocalDate showingDate) {
		this.showingDate = showingDate;
	}

	public LocalDate getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(LocalDate selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Boolean getShowAll() {
		return showAll;
	}

}
