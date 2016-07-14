package com.jiwon.tour.service;

import java.util.List;

import com.jiwon.tour.vo.PlanSchdule;
import com.jiwon.tour.vo.PlanTitle;

public interface PlanService {

	int regPlan(PlanTitle pt);

	PlanTitle getPlan(int parseInt);

	List<PlanTitle> getPlans();

	int regPlanSchdule(List<PlanSchdule> pslist);

	List<PlanSchdule> getPlanSchedules(int ptIdx);

	int removePlanSchdule(int ivPsIdx);

}
