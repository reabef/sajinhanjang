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

import com.jiwon.tour.service.PlanService;
import com.jiwon.tour.vo.MultiPlanSchedule;
import com.jiwon.tour.vo.PlanSchdule;
import com.jiwon.tour.vo.PlanTitle;

@Controller
@RequestMapping("/plan/")
public class PlanController {
	private Logger log = Logger.getLogger(MemberController.class);

	@Autowired
	private PlanService planService;
	
	@RequestMapping(value="planBook", method=RequestMethod.GET)
	public String planList(Model model){
		List<PlanTitle> pts = planService.getPlans();
		
		if(pts.size()>0){
			model.addAttribute("pts",pts);
		}else{
			log.debug("pts의 크기는 0");
		}
		return "plan/planBook";
	}
	
	@RequestMapping(value="planReg", method=RequestMethod.GET)
	public String planReg(HttpServletRequest request){
		String mid = (String) request.getSession().getAttribute("mid");
		if(mid==null){
			//홈으로
			return "redirect:../";
		}else{
			return "plan/planReg";
		}
	}
	
	@RequestMapping(value="planReg", method=RequestMethod.POST)
	public String planReg(HttpServletRequest request, PlanTitle pt, RedirectAttributes attr){
		int iv = 0;
		pt.setHolder((String) request.getSession().getAttribute("mid"));
		iv = planService.regPlan(pt);
		if(iv>0){
			return "redirect:../plan/"+pt.getpIdx();
		}else{
			attr.addFlashAttribute("message","Plan 등록에 실패하였습니다.");
			return "plan/planReg";
		}
	}
	
	@RequestMapping(value="{pIdx}")
	public String planDetail(@PathVariable String pIdx, Model model){
		int ptIdx = Integer.parseInt(pIdx);
		PlanTitle pt = planService.getPlan(ptIdx);
		
		List<PlanSchdule> ps = planService.getPlanSchedules(ptIdx);
		model.addAttribute("pt", pt);
		
		model.addAttribute("ps", ps);
		return "plan/planDetail";
	}
	
	@RequestMapping(value="{pIdx}", method=RequestMethod.POST)
	public String planDetailReg(@PathVariable String pIdx, @ModelAttribute MultiPlanSchedule planschedules){
		List<PlanSchdule> pslist = planschedules.getPlanSchedules();
		int iv = planService.regPlanSchdule(pslist);
		return "redirect:../plan/"+pIdx;
	}
	
	@RequestMapping(value="remove",method=RequestMethod.POST)
	@ResponseBody
	public String planScheduleRemove(String psIdx){
		int ivPsIdx = Integer.parseInt(psIdx);
		int iv = planService.removePlanSchdule(ivPsIdx);
		if(iv>0){
			return "success";
		}else{
			return "fail";
		}
	}
}
