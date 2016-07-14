package com.jiwon.tour.service;

import java.util.Map;

import com.jiwon.tour.vo.Member;

public interface MemberService {
	Member getMember(Map<String, Object> map);

	int joinMember(Member m);

	Member loginMember(Map<String, Object> map);

}
