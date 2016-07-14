package com.jiwon.tour.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiwon.tour.service.PlanService;
import com.jiwon.tour.vo.PlanTitle;

@Controller
@RequestMapping("/plan/")
public class PlanController {
	private Logger log = Logger.getLogger(MemberController.class);

	@Autowired
	private PlanService planService;
	
	@RequestMapping(value="planBook", method=RequestMethod.GET)
	public String planList(){
		
		return "plan/planBook";
	}
	
	@RequestMapping(value="planReg", method=RequestMethod.GET)
	public String planReg(){
		
		return "plan/planReg";
	}
	
	@RequestMapping(value="planReg", method=RequestMethod.POST)
	public String planReg(PlanTitle pt, RedirectAttributes attr){
		int iv = 0;
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
		PlanTitle pt = planService.getPlan(Integer.parseInt(pIdx));
		
		model.addAttribute("pt", pt);
		return "plan/planDetail";
	}
}
