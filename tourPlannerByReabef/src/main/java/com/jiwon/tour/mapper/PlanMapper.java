package com.jiwon.tour.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiwon.tour.vo.PlanSchdule;
import com.jiwon.tour.vo.PlanTitle;

public interface PlanMapper {
	
	public int insertPlan(PlanTitle pt);
	
	public PlanTitle selectPlan(@Param("pIdx") int pIdx);
	
	public List<PlanTitle> selectPlans();
	
	public int insertPlanSchdule(PlanSchdule planSchdule);
	
	public List<PlanSchdule> selectPlanSchdules(@Param("ptIdx") int ptIdx);
	
	public int deletePlanSchedule(@Param("psIdx") int ivPsIdx);
}
