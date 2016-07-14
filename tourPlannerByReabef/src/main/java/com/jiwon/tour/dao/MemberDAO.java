package com.jiwon.tour.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jiwon.tour.vo.Member;

import twitter4j.Logger;

@Repository("memberDAO")
public class MemberDAO extends AbstractDAO{
	Logger log = Logger.getLogger(MemberDAO.class);
	public Member selectMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Member) selectOne("com.jiwon.tour.mapper.MemberMapper.selectMember", map);
	}
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return (Integer) insert("com.jiwon.tour.mapper.MemberMapper.insertMember", m);
	}
}