package com.jiwon.tour.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiwon.tour.dao.PlanDAO;
import com.jiwon.tour.vo.PlanTitle;

import twitter4j.Logger;

@Service("PlanService")
public class PlanServiceImpl implements PlanService {
	private Logger log = Logger.getLogger(MemberServiceImpl.class);
	
	@Resource(name="planDAO")
	private PlanDAO planDAO;

	@Override
	public int regPlan(PlanTitle pt) {
		// TODO Auto-generated method stub
		return planDAO.regPlan(pt);
	}

	@Override
	public PlanTitle getPlan(int pIdx) {
		// TODO Auto-generated method stub
		return (PlanTitle) planDAO.getPlan(pIdx);
	}
}
