package com.jiwon.tour.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiwon.tour.vo.PlanParticipant;
import com.jiwon.tour.vo.PlanRequireArticle;
import com.jiwon.tour.vo.PlanSchdule;
import com.jiwon.tour.vo.PlanStayPlace;
import com.jiwon.tour.vo.PlanTitle;
import com.jiwon.tour.vo.PlanTransCost;

public interface PlanMapper {
	
	public int insertPlan(PlanTitle pt);
	
	public PlanTitle selectPlan(@Param("pIdx") int pIdx);
	
	public List<PlanTitle> selectPlans();
	
	public int insertPlanSchdule(PlanSchdule planSchdule);
	
	public List<PlanSchdule> selectPlanSchdules(@Param("ptIdx") int ptIdx);
	
	public int deletePlanSchedule(@Param("psIdx") int ivPsIdx);
	
	public int insertPlanTransCost(PlanTransCost planTransCost);
	
	public List<PlanTransCost> selectPlanTransCosts(@Param("ptIdx") int ptIdx);
	
	public int deletePlanTransCost(@Param("ptcIdx") int ivPtcIdx);
	
	public int insertPlanStayPlace(PlanStayPlace planStayPlace);
	
	public List<PlanStayPlace> getPlanStayPlaces(@Param("ptIdx") int ptIdx);
	
	public int deletePlanStayPlace(@Param("pspIdx") int ivPspIdx);
	
	public int insertPlanRequireArticle(PlanRequireArticle ra);
	
	public List<PlanRequireArticle> selectPlanRequireArticles(@Param("ptIdx") int ptIdx);
	
	public int deletePlanRequireArticle(@Param("praIdx") int ivPraIdx);
	
	public int insertPlanParticipant(PlanParticipant pp);
	
	public int deletePlanParticipant(@Param("mid") String mid);
	
	public PlanParticipant selectPlanParticipant(PlanParticipant pp);
	
	public List<PlanParticipant> selectPlanParticipants(int ptIdx);
}
