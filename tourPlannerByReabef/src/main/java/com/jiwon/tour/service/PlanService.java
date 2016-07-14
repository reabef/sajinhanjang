package com.jiwon.tour.service;

import com.jiwon.tour.vo.PlanTitle;

public interface PlanService {

	int regPlan(PlanTitle pt);

	PlanTitle getPlan(int parseInt);

}
