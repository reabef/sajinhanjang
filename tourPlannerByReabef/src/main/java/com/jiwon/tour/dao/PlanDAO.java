package com.jiwon.tour.dao;

import org.springframework.stereotype.Repository;

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
}
