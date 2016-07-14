package com.jiwon.tour.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiwon.tour.dao.MemberDAO;
import com.jiwon.tour.vo.Member;

import twitter4j.Logger;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	private Logger log = Logger.getLogger(MemberServiceImpl.class);
	
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	@Override
	public Member getMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memberDAO.selectMember(map);
	}

	@Override
	public int joinMember(Member m) {
		// TODO Auto-generated method stub
		return memberDAO.insertMember(m);
	}

	@Override
	public Member loginMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Member m = null;
		m = memberDAO.selectMember(map);
		
		if(m==null||!m.getPwd().equals(map.get("pwd"))){
			return null;
		}else{
			
			return m;
		}
	}
	
}
