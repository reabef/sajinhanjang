package com.jiwon.tour.vo;

public class EditData {
	private int idx;
	private String idxName;
	private String column;
	private String tableName;
	private String content;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getIdxName() {
		return idxName;
	}
	public void setIdxName(String idxName) {
		this.idxName = idxName;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
