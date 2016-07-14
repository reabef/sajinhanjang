package com.jiwon.tour.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiwon.tour.config.MyBatisSupport;
import com.jiwon.tour.config.MyBatisTransactionManager;
import com.jiwon.tour.dao.PlanDAO;
import com.jiwon.tour.vo.PlanSchdule;
import com.jiwon.tour.vo.PlanTitle;

import twitter4j.Logger;

@Service("PlanService")
public class PlanServiceImpl extends MyBatisSupport implements PlanService {
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

	@Override
	public List<PlanTitle> getPlans() {
		// TODO Auto-generated method stub
		List<PlanTitle> pts = null;
		
		pts = planDAO.getPlans();
		
		return pts;
	}

	@Override
	public int regPlanSchdule(List<PlanSchdule> pslist) {
		// TODO Auto-generated method stub
		MyBatisTransactionManager transaction = getTransactionManager();
		int iv=0;
		try{
			transaction.start();
			for (PlanSchdule planSchdule : pslist) {
				iv = planDAO.regPlanSchdule(planSchdule);
			}
			transaction.commit();
		}catch(Exception e){
			
		}finally {
			transaction.end();
		}
		return iv;
	}

	@Override
	public List<PlanSchdule> getPlanSchedules(int ptIdx) {
		// TODO Auto-generated method stub
		return planDAO.getPlanSchedules(ptIdx);
	}

	@Override
	public int removePlanSchdule(int ivPsIdx) {
		// TODO Auto-generated method stub
		return planDAO.removePlanSchedule(ivPsIdx);
	}

}
