package com.jiwon.tour.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiwon.tour.vo.PlanSchdule;
import com.jiwon.tour.vo.PlanTitle;

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

	public int regPlanSchdule(PlanSchdule planSchdule) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.PlanMapper.insertPlanSchdule", planSchdule);
	}

	@SuppressWarnings("unchecked")
	public List<PlanSchdule> getPlanSchedules(int ptIdx) {
		// TODO Auto-generated method stub
		return (List<PlanSchdule>)selectList("com.jiwon.tour.mapper.PlanMapper.selectPlanSchdules", ptIdx);
	}

	public int removePlanSchedule(int ivPsIdx) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.jiwon.tour.mapper.PlanMapper.deletePlanSchedule", ivPsIdx);
	}
}
