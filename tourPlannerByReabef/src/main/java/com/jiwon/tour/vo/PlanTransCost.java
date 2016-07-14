package com.jiwon.tour.vo;

public class PlanTransCost {
	private int ptcIdx;
	private int ptIdx;
	private int psDate;
	private String startPlace;
	private String startTime;
	private String endPlace;
	private String endTime;
	private String psTrans;
	private int ptcCost;
	private String ptcRemark;
	
	public int getPsDate() {
		return psDate;
	}
	public void setPsDate(int psDate) {
		this.psDate = psDate;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndPlace() {
		return endPlace;
	}
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPsTrans() {
		return psTrans;
	}
	public void setPsTrans(String psTrans) {
		this.psTrans = psTrans;
	}
	public int getPtcIdx() {
		return ptcIdx;
	}
	public void setPtcIdx(int ptcIdx) {
		this.ptcIdx = ptcIdx;
	}
	public int getPtIdx() {
		return ptIdx;
	}
	public void setPtIdx(int ptIdx) {
		this.ptIdx = ptIdx;
	}
	public int getPtcCost() {
		return ptcCost;
	}
	public void setPtcCost(int ptcCost) {
		this.ptcCost = ptcCost;
	}
	public String getPtcRemark() {
		return ptcRemark;
	}
	public void setPtcRemark(String ptcRemark) {
		this.ptcRemark = ptcRemark;
	}
}
