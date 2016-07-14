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
import com.jiwon.tour.vo.MultiPlanStayPlace;
import com.jiwon.tour.vo.MultiPlanTransCost;
import com.jiwon.tour.vo.PlanParticipant;
import com.jiwon.tour.vo.PlanRequireArticle;
import com.jiwon.tour.vo.PlanSchdule;
import com.jiwon.tour.vo.PlanStayPlace;
import com.jiwon.tour.vo.PlanTitle;
import com.jiwon.tour.vo.PlanTransCost;

@Controller
@RequestMapping("/plan/")
public class PlanController {
	private Logger log = Logger.getLogger(MemberController.class);

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

	@RequestMapping(value = "{pIdx}", method = RequestMethod.GET)
	public String planDetail(HttpServletRequest request, @PathVariable String pIdx, Model model) {
		int ptIdx = Integer.parseInt(pIdx);
		PlanTitle pt = planService.getPlan(ptIdx);

		List<PlanSchdule> ps = planService.getPlanSchedules(ptIdx);

		List<PlanTransCost> ptc = planService.getPlanTransCosts(ptIdx);

		List<PlanStayPlace> psp = planService.getPlanStayPlaces(ptIdx);

		List<PlanRequireArticle> pra = planService.getPlanRequireArticle(ptIdx);
		
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
		
		
		return "plan/planDetail";
	}

	@RequestMapping(value = "{pIdx}", method = RequestMethod.POST)
	public String planDetailReg(@PathVariable String pIdx, @ModelAttribute MultiPlanSchedule planschedules) {
		List<PlanSchdule> pslist = planschedules.getPlanSchedules();
		int iv = planService.regPlanSchdule(pslist);
		return "redirect:../plan/" + pIdx;
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public String planScheduleRemove(String psIdx, String ptcIdx, String pspIdx, String praIdx) {
		int iv = 0;
		if (psIdx != null) {
			int ivPsIdx = Integer.parseInt(psIdx);
			iv = planService.removePlanSchdule(ivPsIdx);
		} else if (ptcIdx != null) {
			int ivPtcIdx = Integer.parseInt(ptcIdx);
			iv = planService.removePlanTransCost(ivPtcIdx);
		} else if (pspIdx != null) {
			int ivPspIdx = Integer.parseInt(pspIdx);
			iv = planService.removePlanStayPlace(ivPspIdx);
		} else if (praIdx != null) {
			int ivPraIdx = Integer.parseInt(praIdx);
			iv = planService.removePlanRequireArticle(ivPraIdx);
		}
		if (iv > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "planTransCostReg", method = RequestMethod.POST)
	public String planTransCostReg(RedirectAttributes attr, @ModelAttribute MultiPlanTransCost planTransCosts) {
		List<PlanTransCost> ptclist = planTransCosts.getPlanTransCosts();
		int ptIdx = ptclist.get(0).getPtIdx();
		int iv = planService.regPlanTransCost(ptclist);
		if (iv > 0) {
			attr.addFlashAttribute("message", "교통비 등록에 성공하였습니다.");
		} else {
			attr.addFlashAttribute("message", "교통비 등록에 실패하였습니다.");
		}
		return "redirect:../plan/" + ptIdx;
	}

	@RequestMapping(value = "planStayPlaceReg", method = RequestMethod.POST)
	public String planStayPlaceReg(RedirectAttributes attr, @ModelAttribute MultiPlanStayPlace planStayPlaces) {
		List<PlanStayPlace> psplist = planStayPlaces.getPlanStayPlaces();
		int ptIdx = psplist.get(0).getPtIdx();
		int iv = planService.regPlanStayPlace(psplist);
		if (iv > 0) {
			attr.addFlashAttribute("message", "숙박 등록에 성공하였습니다.");
		} else {
			attr.addFlashAttribute("message", "숙박 등록에 실패하였습니다.");
		}
		return "redirect:../plan/" + ptIdx;
	}

	@RequestMapping(value = "planRequireArticleReg", method = RequestMethod.POST)
	public String planRequireArticleReg(RedirectAttributes attr, PlanRequireArticle ra) {

		int iv = 0;

		iv = planService.regPlanRequireArticle(ra);
		if (iv > 0) {
			attr.addFlashAttribute("message", "필요 물품 등록에 성공하였습니다.");
		} else {
			attr.addFlashAttribute("message", "필요 물품 등록에 실패하였습니다.");
		}
		return "redirect:../plan/" + ra.getPtIdx();
	}

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
}
