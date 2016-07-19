package com.jiwon.tour.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiwon.tour.vo.EditData;
import com.jiwon.tour.vo.PlanFoodCost;
import com.jiwon.tour.vo.PlanParticipant;
import com.jiwon.tour.vo.PlanRequireArticle;
import com.jiwon.tour.vo.PlanSchedule;
import com.jiwon.tour.vo.PlanStayPlace;
import com.jiwon.tour.vo.PlanTitle;
import com.jiwon.tour.vo.PlanTransCost;

import twitter4j.Logger;

@Repository("planDAO")
public class PlanDAO extends AbstractDAO{
	Logger log = Logger.getLogger(MemberDAO.class);

	public int regPlan(PlanTitle pt) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insertPlan", pt);
	}

	public PlanTitle getPlan(int pIdx) {
		// TODO Auto-generated method stub
		return (PlanTitle) selectOne("com.jiwon.tour.mapper.PlanMapper.selectPlan", pIdx);
	}

	@SuppressWarnings("unchecked")
	public List<PlanTitle> getPlans() {
		// TODO Auto-generated method stub
		return (List<PlanTitle>) selectList("com.jiwon.tour.mapper.PlanMapper.selectPlans");
	}

	public int regPlanSchdule(PlanSchedule planSchedule) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insertPlanSchedule", planSchedule);
	}

	@SuppressWarnings("unchecked")
	public List<PlanSchedule> getPlanSchedules(int ptIdx) {
		// TODO Auto-generated method stub
		return (List<PlanSchedule>)selectList("com.jiwon.tour.mapper.PlanMapper.selectPlanSchedules", ptIdx);
	}

	public int removePlanSchedule(int ivPsIdx) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.jiwon.tour.mapper.PlanMapper.deletePlanSchedule", ivPsIdx);
	}

	public int regPlanTransCost(PlanTransCost planTransCost) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insertPlanTransCost", planTransCost);
	}

	@SuppressWarnings("unchecked")
	public List<PlanTransCost> getPlanTransCosts(int ptIdx) {
		// TODO Auto-generated method stub
		return (List<PlanTransCost>)selectList("com.jiwon.tour.mapper.PlanMapper.selectPlanTransCosts", ptIdx);
	}

	public int removePlanTransCost(int ivPtcIdx) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.jiwon.tour.mapper.PlanMapper.deletePlanTransCost", ivPtcIdx);
	}

	public int regPlanStayPlace(PlanStayPlace planStayPlace) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insertPlanStayPlace", planStayPlace);
	}

	@SuppressWarnings("unchecked")
	public List<PlanStayPlace> getPlanStayPlaces(int ptIdx) {
		// TODO Auto-generated method stub
		return (List<PlanStayPlace>)selectList("com.jiwon.tour.mapper.PlanMapper.selectPlanStayPlaces", ptIdx);
	}

	public int removePlanStayPlace(int ivPspIdx) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.jiwon.tour.mapper.PlanMapper.deletePlanStayPlace", ivPspIdx);
	}

	public int regPlanRequireArticle(PlanRequireArticle ra) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insertPlanRequireArticle", ra);
	}

	@SuppressWarnings("unchecked")
	public List<PlanRequireArticle> getPlanRequireArticles(int ptIdx) {
		// TODO Auto-generated method stub
		return (List<PlanRequireArticle>)selectList("com.jiwon.tour.mapper.PlanMapper.selectPlanRequireArticles", ptIdx);
	}

	public int removePlanRequireArticle(int ivPraIdx) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.jiwon.tour.mapper.PlanMapper.deletePlanRequireArticle", ivPraIdx);
	}

	public int regPlanParticipant(PlanParticipant pp) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insertPlanParticipant", pp);
	}

	public int removePlanParticipant(String mid) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.jiwon.tour.mapper.PlanMapper.deletePlanParticipant", mid);
	}

	public PlanParticipant getPlanParticipant(PlanParticipant pp) {
		// TODO Auto-generated method stub
		return (PlanParticipant) selectOne("com.jiwon.tour.mapper.PlanMapper.selectPlanParticipant", pp);
	}

	@SuppressWarnings("unchecked")
	public List<PlanParticipant> getPlanParticipants(int ptIdx) {
		// TODO Auto-generated method stub
		return (List<PlanParticipant>) selectList("com.jiwon.tour.mapper.PlanMapper.selectPlanParticipants", ptIdx);
	}

	public int regPlanVariables(Object object, String tableName) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insert"+tableName, object);
	}

	@SuppressWarnings("rawtypes")
	public List getPlanVariables(int ptIdx, String tableName) {
		// TODO Auto-generated method stub
		return selectList("com.jiwon.tour.mapper.PlanMapper.select"+tableName+"s", ptIdx);
	}

	public int removePlanVariable(int ivIdx, String tableName) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.jiwon.tour.mapper.PlanMapper.delete"+tableName, ivIdx);
	}

	public int editPlanVariable(EditData ed) {
		// TODO Auto-generated method stub
		return (Integer) update("com.jiwon.tour.mapper.PlanMapper.updateVariable", ed);
	}
}
