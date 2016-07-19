package com.jiwon.tour.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jiwon.tour.config.MyBatisSupport;
import com.jiwon.tour.config.MyBatisTransactionManager;
import com.jiwon.tour.dao.PlanDAO;
import com.jiwon.tour.vo.EditData;
import com.jiwon.tour.vo.PlanFoodCost;
import com.jiwon.tour.vo.PlanParticipant;
import com.jiwon.tour.vo.PlanRequireArticle;
import com.jiwon.tour.vo.PlanSchedule;
import com.jiwon.tour.vo.PlanStayPlace;
import com.jiwon.tour.vo.PlanTitle;
import com.jiwon.tour.vo.PlanTransCost;

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

	/*@Override
	public int regPlanSchdule(List<PlanSchedule> pslist, String tableName) {
		// TODO Auto-generated method stub
		MyBatisTransactionManager transaction = getTransactionManager();
		int iv=0;
		try{
			transaction.start();
			for (PlanSchedule planSchdule : pslist) {
				iv = planDAO.regPlanVariables(planSchdule, tableName);
			}
			transaction.commit();
		}catch(Exception e){
			
		}finally {
			transaction.end();
		}
		return iv;
	}*/

	@Override
	public List<PlanSchedule> getPlanSchedules(int ptIdx) {
		// TODO Auto-generated method stub
		return planDAO.getPlanSchedules(ptIdx);
	}

	/*@Override
	public int removePlanSchdule(int ivPsIdx) {
		// TODO Auto-generated method stub
		return planDAO.removePlanSchedule(ivPsIdx);
	}*/

	/*@Override
	public int regPlanTransCost(List<PlanTransCost> ptclist, String tableName) {
		// TODO Auto-generated method stub
		MyBatisTransactionManager transaction = getTransactionManager();
		int iv = 0;
		try{
			transaction.start();
			for (Object object : ptclist) {
				iv = planDAO.regPlanVariables(object, tableName);
			}
			transaction.commit();
		}catch(Exception e){
			iv = 0;
		}finally{
			transaction.end();
		}
		
		return iv;
	}*/

	@Override
	public List<PlanTransCost> getPlanTransCosts(int ptIdx) {
		// TODO Auto-generated method stub
		return planDAO.getPlanTransCosts(ptIdx);
	}

	/*@Override
	public int removePlanTransCost(int ivPtcIdx) {
		// TODO Auto-generated method stub
		return planDAO.removePlanTransCost(ivPtcIdx);
	}*/

	/*@Override
	public int regPlanStayPlace(List<PlanStayPlace> psplist) {
		// TODO Auto-generated method stub
		MyBatisTransactionManager transaction = getTransactionManager();
		int iv = 0;
		try{
			transaction.start();
			for (PlanStayPlace planStayPlace : psplist) {
				iv = planDAO.regPlanStayPlace(planStayPlace);
			}
			transaction.commit();
		}catch(Exception e){
			iv = 0;
		}finally{
			transaction.end();
		}
		
		return iv;
	}*/

	@Override
	public List<PlanStayPlace> getPlanStayPlaces(int ptIdx) {
		// TODO Auto-generated method stub
		return planDAO.getPlanStayPlaces(ptIdx);
	}

	/*@Override
	public int removePlanStayPlace(int ivPspIdx) {
		// TODO Auto-generated method stub
		return planDAO.removePlanStayPlace(ivPspIdx);
	}*/

	/*@Override
	public int regPlanRequireArticle(PlanRequireArticle ra) {
		// TODO Auto-generated method stub
		return planDAO.regPlanRequireArticle(ra);
	}*/

	@Override
	public List<PlanRequireArticle> getPlanRequireArticle(int ptIdx) {
		// TODO Auto-generated method stub
		return planDAO.getPlanRequireArticles(ptIdx);
	}

	/*@Override
	public int removePlanRequireArticle(int ivPraIdx) {
		// TODO Auto-generated method stub
		return planDAO.removePlanRequireArticle(ivPraIdx);
	}*/

	@Override
	public int planParticipantReg(PlanParticipant pp) {
		// TODO Auto-generated method stub
		return planDAO.regPlanParticipant(pp);
	}
	@Override
	public int planParticipantRemove(String mid) {
		// TODO Auto-generated method stub
		return planDAO.removePlanParticipant(mid);
	}
	@Override
	public PlanParticipant getPlanParticipant(PlanParticipant pp) {
		// TODO Auto-generated method stub
		return planDAO.getPlanParticipant(pp);
	}
	@Override
	public String getPlanParticipants(int ptIdx) {
		// TODO Auto-generated method stub
		List<PlanParticipant> pp = planDAO.getPlanParticipants(ptIdx);
		
		Gson gson = new Gson();
		String pps="";
		try{
			pps = gson.toJson(pp);
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		return pps;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int regPlanVariables(List list, String tableName) {
		// TODO Auto-generated method stub
		MyBatisTransactionManager transaction = getTransactionManager();
		int iv = 0;
		try{
			transaction.start();
			for (Object object : list) {
				iv = planDAO.regPlanVariables(object, tableName);
			}
			transaction.commit();
		}catch(Exception e){
			iv = 0;
		}finally{
			transaction.end();
		}
		
		return iv;
	}

	@Override
	public int regPlanVariable(Object object, String tableName) {
		// TODO Auto-generated method stub
		return planDAO.regPlanVariables(object, tableName);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List getPlanVariables(int ptIdx, String tableName) {
		// TODO Auto-generated method stub
		return planDAO.getPlanVariables(ptIdx, tableName);
	}
	
	@Override
	public int removePlanVariable(int ivIdx, String tableName) {
		// TODO Auto-generated method stub
		return planDAO.removePlanVariable(ivIdx, tableName);
	}
	
	@Override
	public int editPlanVariable(EditData ed) {
		// TODO Auto-generated method stub
		return planDAO.editPlanVariable(ed);
	}
}
