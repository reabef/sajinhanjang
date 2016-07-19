package com.jiwon.tour.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.jiwon.tour.service.PlanService;
import com.jiwon.tour.vo.MultiPlanFoodCost;
import com.jiwon.tour.vo.MultiPlanSchedule;
import com.jiwon.tour.vo.MultiPlanStayPlace;
import com.jiwon.tour.vo.MultiPlanTransCost;
import com.jiwon.tour.vo.PlanFoodCost;
import com.jiwon.tour.vo.PlanParticipant;
import com.jiwon.tour.vo.PlanRequireArticle;
import com.jiwon.tour.vo.PlanSchedule;
import com.jiwon.tour.vo.PlanStayPlace;
import com.jiwon.tour.vo.PlanTitle;
import com.jiwon.tour.vo.PlanTransCost;
import com.jiwon.tour.vo.EditData;

@Controller
@RequestMapping("/plan")
public class PlanController {
	private Logger log = Logger.getLogger(PlanController.class);

	@Autowired
	private PlanService planService;

	@RequestMapping(value = "planBook", method = RequestMethod.GET)
	public String planList(Model model) {
		List<PlanTitle> pts = planService.getPlans();

		if (pts.size() > 0) {
			model.addAttribute("pts", pts);
		} else {
			log.debug("pts의 크기는 0");
		}
		return "plan/planBook";
	}

	@RequestMapping(value = "planReg", method = RequestMethod.GET)
	public String planReg(HttpServletRequest request) {
		String mid = (String) request.getSession().getAttribute("mid");
		if (mid == null) {
			// 홈으로
			return "redirect:../";
		} else {
			return "plan/planReg";
		}
	}

	@RequestMapping(value = "planReg", method = RequestMethod.POST)
	public String planReg(HttpServletRequest request, PlanTitle pt, RedirectAttributes attr) {
		int iv = 0;
		String mid = (String) request.getSession().getAttribute("mid");
		pt.setHolder(mid);
		iv = planService.regPlan(pt);
		if (iv > 0) {
			PlanParticipant pp = new PlanParticipant();
			pp.setMid(mid);
			pp.setPpType("개최자");
			pp.setPtIdx(pt.getpIdx());
			iv = planService.planParticipantReg(pp);
			if (iv > 0) {
				return "redirect:../plan/" + pt.getpIdx();
			} else {
				attr.addFlashAttribute("message", "Plan 등록에 실패하였습니다.");
				return "plan/planReg";
			}
		} else {
			attr.addFlashAttribute("message", "Plan 등록에 실패하였습니다.");
			return "plan/planReg";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{pIdx}", method = RequestMethod.GET)
	public String planDetail(HttpServletRequest request, @PathVariable String pIdx, Model model) {
		int ptIdx = Integer.parseInt(pIdx);
		String tableName="";
		PlanTitle pt = planService.getPlan(ptIdx);

		List<PlanSchedule> ps = planService.getPlanSchedules(ptIdx);

		List<PlanTransCost> ptc = planService.getPlanTransCosts(ptIdx);

		List<PlanStayPlace> psp = planService.getPlanStayPlaces(ptIdx);

		List<PlanRequireArticle> pra = planService.getPlanRequireArticle(ptIdx);
		
		tableName="PlanFoodCost";
		List<PlanFoodCost> pfc = planService.getPlanVariables(ptIdx, tableName);
		
		//참여자 체크 (참여자만 삭제 / 추가)
		
		String mid = (String) request.getSession().getAttribute("mid");
		
		PlanParticipant pp = new PlanParticipant();
		
		pp.setMid(mid);
		
		pp.setPtIdx(Integer.parseInt(pIdx));
		
		pp = planService.getPlanParticipant(pp);
		
		if(pp==null){
			model.addAttribute("ppBoolean", false);
		}else{
			model.addAttribute("ppBoolean", true);
		}
		
		model.addAttribute("pt", pt);

		model.addAttribute("ps", ps);

		model.addAttribute("ptc", ptc);

		model.addAttribute("psp", psp);

		model.addAttribute("pra", pra);
		
		model.addAttribute("pfc", pfc);
		
		return "plan/planDetail";
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "{ptIdx}/{tableName}", method = RequestMethod.POST)
	public String planDetailReg(@PathVariable String ptIdx, RedirectAttributes attr,@PathVariable String tableName, @ModelAttribute MultiPlanSchedule planschedules, 
								@ModelAttribute MultiPlanTransCost planTransCosts, @ModelAttribute MultiPlanStayPlace planStayPlaces, PlanRequireArticle requireArticle, 
								@ModelAttribute MultiPlanFoodCost planFoodCosts) {
		int iv=0;
		List list=null;
		
		//multiVO의 경우
		if(tableName.equals("PlanSchedule")){
			list = planschedules.getPlanSchedules();
		}else if(tableName.equals("PlanTransCost")){
			list = planTransCosts.getPlanTransCosts();
		}else if(tableName.equals("PlanStayPlace")){
			list = planStayPlaces.getPlanStayPlaces();
		}else  if(tableName.equals("PlanFoodCost")){
			list = planFoodCosts.getPlanFoodCosts();
		}
		
		//service 호출 && singleVO의 경우
		if(list!=null){
			iv = planService.regPlanVariables(list, tableName);
		}else if(tableName.equals("PlanRequireArticle")){
			iv = planService.regPlanVariable(requireArticle, tableName);
		}
		
		if(tableName.equals("PlanSchedule")){
			if (iv > 0) {
				attr.addFlashAttribute("message", "상세 일정 등록에 성공하였습니다.");
			} else {
				attr.addFlashAttribute("message", "상세 일정 등록에 실패하였습니다.");
			}
		}else if(tableName.equals("PlanTransCost")){
			if (iv > 0) {
				attr.addFlashAttribute("message", "교통비 등록에 성공하였습니다.");
			} else {
				attr.addFlashAttribute("message", "교통비 등록에 실패하였습니다.");
			}
		}else if(tableName.equals("PlanStayPlace")){
			if (iv > 0) {
				attr.addFlashAttribute("message", "숙박 등록에 성공하였습니다.");
			} else {
				attr.addFlashAttribute("message", "숙박 등록에 실패하였습니다.");
			}
		}else if(tableName.equals("PlanRequireArticle")){
			if (iv > 0) {
				attr.addFlashAttribute("message", "필요 물품 등록에 성공하였습니다.");
			} else {
				attr.addFlashAttribute("message", "필요 물품 등록에 실패하였습니다.");
			}
		}else if(tableName.equals("PlanStayPlace")){
			if (iv > 0) {
				attr.addFlashAttribute("message", "필요 물품 등록에 성공하였습니다.");
			} else {
				attr.addFlashAttribute("message", "필요 물품 등록에 실패하였습니다.");
			}
		}
		
		return "redirect:../../plan/" + ptIdx;
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public String planScheduleRemove(String psIdx, String ptcIdx, String pspIdx, String praIdx, String pfcIdx) {
		int iv = 0;
		int ivIdx = 0;
		String tableName = "";
		if (psIdx != null) {
			ivIdx = Integer.parseInt(psIdx);
			tableName = "PlanSchedule";
			//iv = planService.removePlanSchdule(ivIdx);
		} else if (ptcIdx != null) {
			ivIdx = Integer.parseInt(ptcIdx);
			tableName = "PlanTransCost";
			//iv = planService.removePlanTransCost(ivIdx);
		} else if (pspIdx != null) {
			ivIdx = Integer.parseInt(pspIdx);
			tableName = "PlanStayPlace";
			//iv = planService.removePlanStayPlace(ivIdx);
		} else if (praIdx != null) {
			ivIdx = Integer.parseInt(praIdx);
			tableName = "PlanRequireArticle";
			//iv = planService.removePlanRequireArticle(ivIdx);
		} else if (pfcIdx != null) {
			ivIdx = Integer.parseInt(pfcIdx);
			tableName = "PlanFoodCost";
		}
		iv = planService.removePlanVariable(ivIdx, tableName);
		if (iv > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	/*@RequestMapping(value = "planTransCostReg", method = RequestMethod.POST)
	public String planTransCostReg(RedirectAttributes attr, @ModelAttribute MultiPlanTransCost planTransCosts) {
		int iv=0;
		int ptIdx=7;
		if(planTransCosts!=null&&planTransCosts.getPlanTransCosts().size()>0){
			List<PlanTransCost> ptclist = planTransCosts.getPlanTransCosts();
			ptIdx = ptclist.get(0).getPtIdx();
			iv = planService.regPlanTransCost(ptclist);
		}else{
			log.info("널이거나 사이즈가 0");
		}
		if (iv > 0) {
			attr.addFlashAttribute("message", "교통비 등록에 성공하였습니다.");
		} else {
			attr.addFlashAttribute("message", "교통비 등록에 실패하였습니다.");
		}
		return "redirect:../plan/" + ptIdx;
	}*/

	/*@RequestMapping(value = "planStayPlaceReg", method = RequestMethod.POST)
	public String planStayPlaceReg(RedirectAttributes attr, @ModelAttribute MultiPlanStayPlace planStayPlaces) {
		int iv=0;
		int ptIdx=7;
		if(planStayPlaces!=null&&planStayPlaces.getPlanStayPlaces().size()>0){
			List<PlanStayPlace> psplist = planStayPlaces.getPlanStayPlaces();
			ptIdx = psplist.get(0).getPtIdx();
			iv = planService.regPlanStayPlace(psplist);
		}else{
			log.info("널이거나 사이즈가 0");
		}
		if (iv > 0) {
			attr.addFlashAttribute("message", "숙박 등록에 성공하였습니다.");
		} else {
			attr.addFlashAttribute("message", "숙박 등록에 실패하였습니다.");
		}
		return "redirect:../plan/" + ptIdx;
	}*/

	/*@RequestMapping(value = "planRequireArticleReg", method = RequestMethod.POST)
	public String planRequireArticleReg(RedirectAttributes attr, PlanRequireArticle ra) {

		int iv = 0;
		int ptIdx = 7;
		if (ra!=null&&ra.getPtIdx()!=0){
			iv = planService.regPlanRequireArticle(ra);
		}else{
			log.info("널이래요||0이래요");
		}
		
		
		if (iv > 0) {
			attr.addFlashAttribute("message", "필요 물품 등록에 성공하였습니다.");
		} else {
			attr.addFlashAttribute("message", "필요 물품 등록에 실패하였습니다.");
		}
		return "redirect:../plan/" + ptIdx;
	}*/

	@RequestMapping(value = "planParticipantReg", method = RequestMethod.POST)
	@ResponseBody
	public String planParticipantReg(HttpServletRequest request, String ptIdx) {
		int iv = 0;
		String mid = (String) request.getSession().getAttribute("mid");
		if (mid != null) {
			PlanParticipant pp = new PlanParticipant();
			pp.setMid(mid);
			pp.setPtIdx(Integer.parseInt(ptIdx));
			pp.setPpType("참여자");
			iv = planService.planParticipantReg(pp);
		}
		if (iv > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "planParticipantRemove", method = RequestMethod.POST)
	@ResponseBody
	public String planParticipantRemove(HttpServletRequest request) {
		int iv = 0;

		String mid = (String) request.getSession().getAttribute("mid");
		if (mid != null) {
			iv = planService.planParticipantRemove(mid);
		}

		if (iv > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "planParticipantGet", method = RequestMethod.POST)
	@ResponseBody
	public String planParticipantGet(String ptIdx, HttpServletRequest request) {
		PlanParticipant pp = new PlanParticipant();
		String mid = (String) request.getSession().getAttribute("mid");
		pp.setMid(mid);
		pp.setPtIdx(Integer.parseInt(ptIdx));

		pp = planService.getPlanParticipant(pp);
		if (pp != null) {
			return "exist";
		} else {
			return "notExist";
		}
	}

	@RequestMapping(value = "planParticipantGets", method = RequestMethod.POST)
	@ResponseBody
	public String planParticipantGets(String ptIdx) {
		String pps = planService.getPlanParticipants(Integer.parseInt(ptIdx));
		log.debug(pps);
		return pps;
	}
	
	@RequestMapping(value = "editPlan", method = RequestMethod.POST)
	@ResponseBody
	public String editPlan(String editData) {
		int iv=0;
		Gson gson = new Gson();
		EditData ed = gson.fromJson(editData, com.jiwon.tour.vo.EditData.class);
		log.info("데이터 리시브 : "+ed.getContent());
		log.info("데이터 리시브 : "+ed.getColumn());
		log.info("데이터 리시브 : "+ed.getIdxName());
		log.info("데이터 리시브 : "+ed.getTableName());
		log.info("데이터 리시브 : "+ed.getIdx());
		ed.setTableName(ed.getTableName().toUpperCase());
		iv = planService.editPlanVariable(ed);
		log.info("서비스 끝");
		if (iv > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
