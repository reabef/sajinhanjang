package com.jiwon.tour.mapper;

import org.apache.ibatis.annotations.Param;

import com.jiwon.tour.vo.PlanTitle;

public interface PlanMapper {
	
	public int insertPlan(PlanTitle pt);
	
	public PlanTitle selectPlan(@Param("pIdx") int pIdx);
}
