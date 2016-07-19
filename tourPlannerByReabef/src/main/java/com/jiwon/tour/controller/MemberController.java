package com.jiwon.tour.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiwon.tour.service.MemberService;
import com.jiwon.tour.vo.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	private Logger log = Logger.getLogger(MemberController.class);
	
	private String contextPath = "tourplannerbyreabef-env.ap-northeast-2.elasticbeanstalk.com";
	
	@Autowired
	private MemberService memberService; 
	
	@RequestMapping(value="idcheck", method=RequestMethod.POST)
	@ResponseBody
	private String idCheck(Model model, String mid){
		Member m=null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", mid);
		try{
		log.debug("체크");
		m = (Member) memberService.getMember(map);
		}catch(Exception e){
			log.info(e.getMessage());
		}
		
		/*log.info("체크"+m==null);*/
		
		if(m==null){
			return "notExist";
		}else{
			return "exist";
		}
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	private String join(RedirectAttributes attr, HttpServletRequest request, Member m){
		String referer = request.getHeader("referer");
		String contextPath=request.getContextPath();
		String referView = referer.split(contextPath)[1];
		log.debug("이름 : " +m.getName());
		int iv = memberService.joinMember(m);
		if(iv>0){
			attr.addAttribute("message", "회원가입에 성공하였습니다.");
		}else{
			attr.addAttribute("message", "회원가입에 실패하였습니다.");
		}
		return "redirect:"+referView;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	private String login(HttpServletRequest request, RedirectAttributes attr, Member m){
		String referer = request.getHeader("referer");
		String contextPath=request.getContextPath();
		String referView = referer.split(contextPath)[1];
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("mid", m.getMid());
		map.put("pwd", m.getPwd());
		
		m = memberService.loginMember(map);
		
		if(m != null){
			request.getSession().setAttribute("mid", m.getMid());
		}else{
			attr.addFlashAttribute("message", "로그인에 실패하였습니다.");
		}
		return "redirect:"+referView;
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	private String logout(HttpServletRequest request){
		String referer = request.getHeader("referer");
		String contextPath=request.getContextPath();
		String referView = referer.split(contextPath)[1];
		
		request.getSession().removeAttribute("mid");
		log.debug("이전 페이지 : "+referView);
		return "redirect:"+referView;
	}
}

