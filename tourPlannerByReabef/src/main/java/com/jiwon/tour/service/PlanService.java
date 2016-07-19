package com.jiwon.tour.service;

import java.util.List;

import com.jiwon.tour.vo.EditData;
import com.jiwon.tour.vo.PlanFoodCost;
import com.jiwon.tour.vo.PlanParticipant;
import com.jiwon.tour.vo.PlanRequireArticle;
import com.jiwon.tour.vo.PlanSchedule;
import com.jiwon.tour.vo.PlanStayPlace;
import com.jiwon.tour.vo.PlanTitle;
import com.jiwon.tour.vo.PlanTransCost;

public interface PlanService {

	int regPlan(PlanTitle pt);

	PlanTitle getPlan(int parseInt);

	List<PlanTitle> getPlans();

	/*int regPlanSchdule(List<PlanSchedule> pslist, String tableName);*/

	List<PlanSchedule> getPlanSchedules(int ptIdx);

	/*int removePlanSchdule(int ivPsIdx);*/

	/*int regPlanTransCost(List<PlanTransCost> ptclist, String tableName);*/

	List<PlanTransCost> getPlanTransCosts(int ptIdx);

	/*int removePlanTransCost(int ivPtcIdx);*/

	/*int regPlanStayPlace(List<PlanStayPlace> psplist);*/

	List<PlanStayPlace> getPlanStayPlaces(int ptIdx);

	/*int removePlanStayPlace(int ivPspIdx);*/

	/*int regPlanRequireArticle(PlanRequireArticle ra);*/

	List<PlanRequireArticle> getPlanRequireArticle(int ptIdx);

	/*int removePlanRequireArticle(int ivPraIdx);*/

	int planParticipantReg(PlanParticipant pp);

	int planParticipantRemove(String mid);

	PlanParticipant getPlanParticipant(PlanParticipant pp);

	String getPlanParticipants(int ptIdx);

	@SuppressWarnings("rawtypes")
	int regPlanVariables(List list, String tableName);

	int regPlanVariable(Object object, String tableName);

	@SuppressWarnings("rawtypes")
	List getPlanVariables(int ptIdx, String tableName);

	int removePlanVariable(int ivIdx, String tableName);

	int editPlanVariable(EditData ed);

}
