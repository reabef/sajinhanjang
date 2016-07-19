<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="PlanDetailSection"
	class="container content-section text-center">
	<div class="row">
		<div class="col-lg-12 planDiv">
			<h2>TOURPLAN 계획서</h2>
			<hr>
				<table class="table table-bordered tableTop">
					<thead>
						<tr>
							<th>개최자</th>
							<td>${pt.holder}</td>
							<td id="pIdx" style="display: none;">${pt.pIdx}</td>
							<td rowspan="2">기간</td>
							<td rowspan="2"><span id="spanSdate">${pt.sdate}</span> ~ <span
								id="spanEdate">${pt.edate}</span></td>
						</tr>
						<tr>
							<th>인원</th>
							<td>${pt.numPeople}명</td>
						</tr>
						<tr>
							<th>여행 구분</th>
							<td colspan="3">${pt.pType}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Plan명</th>
							<td colspan="3">${pt.pName}</td>
						</tr>
						<tr>
							<th>전체 이동 경로</th>
							<td colspan="3"></td>
						</tr>
						<tr>
							<th>참여자</th>
							<td colspan="3"><span id="participantList">[참여자 목록 보기]</span></td>
						</tr>
						<tr>
							<th>비고</th>
							<td colspan="3"></td>
						</tr>
					</tbody>
				</table>
				<c:if test="${!empty sessionScope.mid}">
					<ul style="list-style-type: none;">
						<li style="float: right; display: inline;">
							<button type="button" class="btn btn-default btn-lg" id="addPPButton" onclick="regPlanParticipant();">
								<span id="addPlanParticipant" class="network-name">참여하기</span>
							</button>
						</li>
					</ul>
					<ul style="list-style-type: none;">
						<li style="float: right; display: inline;">
							<button type="button" class="btn btn-default btn-lg" id="subPPButton" onclick="removePlanParticipant();" style="display: none;">
								<span id="addPlanParticipant" class="network-name">참여취소</span>
							</button>
						</li>
					</ul>
				</c:if>
				<form action="${pageContext.request.contextPath}/plan/${pt.pIdx}/PlanSchedule" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">상세 일정</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="PlanSchedule">
					<thead>
						<tr>
							<th class="noVisible psIdx"></th>
							<th class="psDate">날짜</th>
							<th class="psPlace">장소</th>
							<th class="psTrans">교통편</th>
							<th class="pstime">시각</th>
							<th class="psSchedule">일정</th>
							<th class="psRemark" style="width: 10%;">비고</th>
						</tr>
					</thead>
					<tbody class="DBlist">
						<c:forEach items="${ps}" var="ps" varStatus="status">
							<tr>
								<td class="noVisible psIdx" id="psIdx"><c:out value="${ps.psIdx }"/></td>
								<td class="psDate"><c:out value="제${ps.psDate }일차"/></td>
								<td class="psPlace"><c:out value="${ps.psPlace }"/></td>
								<td class="psTrans"><c:out value="${ps.psTrans }"/></td>
								<td class="pstime"><c:out value="${ps.pstime }"/></td>
								<td class="psSchedule"><c:out value="${ps.psSchedule }"/></td>
								<td class="psRemark"><c:out value="${ps.psRemark }"/>
								<c:if test="${ppBoolean==true }">
								<span class="fa fa-times-circle-o removeButton removeSchedule" id="removeSchedule" onclick="event.cancelBubble=true;"></span>
								</c:if>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
					<tbody id="inputlist">
					</tbody>
				</table>
				<c:if test="${ppBoolean==true }">
				<div
					style="overflow: auto; padding-top: 0.5em; padding-bottom: 1em;">
					<!-- <span id="submitPlanDetail" class="spanButton">[등록]</span><span id="addPlanDetail" class="spanButton">[입력창 추가]</span> -->
					<ul style="list-style-type: none;">
						<li style="float: right; display: inline;">
							<button type="submit" id="detailSubmit" class="btn btn-default btn-lg submitButton"  style="display: none;">
								<span id="submitPlanDetail" class="network-name" >등록</span>
							</button>
						</li>

						<li style="float: right; display: inline;">
							<button type="button" class="btn btn-default btn-lg">
								<span id="addPlanDetail" class="network-name">입력창 추가</span>
							</button>
						</li>

					</ul>
				</div>
				</c:if>
				</form>
				<form action="${pageContext.request.contextPath}/plan/${pt.pIdx}/PlanTransCost" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">교통비 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="PlanTransCost">
					<thead>
						<tr>
							<th class="noVisible ptcIdx"></th>
							<th class="psDate">이동 날짜</th>
							<th class="startPlace">출발 지역</th>
							<th class="startTime">출발 시간</th>
							<th class="endPlace">도착 지역</th>
							<th class="endTime">도착 시간</th>
							<th class="psTrans">방법</th>
							<th class="ptcCost">비용(인당)</th>
							<th class="ptcRemark">비고</th>
						</tr>
					</thead>
					<tbody class="DBlist">
						<c:set var="sumTransCost" value="0"></c:set>
						<c:forEach items="${ptc}" var="ptc" varStatus="status">
							<tr>
								<td class="noVisible ptcIdx" id="ptcIdx"><c:out value="${ptc.ptcIdx }"/></td>
								<td class="psDate"><c:out value="제${ptc.psDate }일차"/></td>
								<td class="startPlace"><c:out value="${ptc.startPlace }"/></td>
								<td class="startTime"><c:out value="${ptc.startTime }"/></td>
								<td class="endPlace"><c:out value="${ptc.endPlace }"/></td>
								<td class="endTime"><c:out value="${ptc.endTime }"/></td>
								<td class="psTrans"><c:out value="${ptc.psTrans }"/></td>
								<td class="ptcCost"><c:out value="${ptc.ptcCost }원"/></td>
								<td class="ptcRemark"><c:out value="${ptc.ptcRemark }"/>
								<c:if test="${!empty ptc.ptcCost}">
								<c:set var="sumTransCost" value="${sumTransCost+ptc.ptcCost}"></c:set>
								</c:if>
								<c:if test="${ppBoolean==true }">
								<span class="fa fa-times-circle-o removeButton removeTransCost" id="removeTransCost" onclick="event.cancelBubble=true;"></span>
								</c:if>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
					<tbody id="inputlist">
					</tbody>
					<tbody id="sumTransCost">
						<tr>
							<td colspan="3">비용 합계</td>
							<td colspan="6"><c:out value="총 ${sumTransCost}원"/></td>
						</tr>
					</tbody>
				</table>
				<c:if test="${ppBoolean==true }">
				<div
					style="overflow: auto; padding-top: 0.5em; padding-bottom: 1em;">
					<!-- <span id="submitPlanDetail" class="spanButton">[등록]</span><span id="addPlanDetail" class="spanButton">[입력창 추가]</span> -->
					<ul style="list-style-type: none;">
						<li style="float: right; display: inline;">
							<button type="submit" id="transCostSubmit" class="btn btn-default btn-lg submitButton" style="display: none;">
								<span id="" class="network-name">등록</span>
							</button>
						</li>
						<li style="float: right; display: inline;">
							<button type="button" class="btn btn-default btn-lg">
								<span id="addTransCost" class="network-name">입력창 추가</span>
							</button>
						</li>
					</ul>
				</div>
				</c:if>
				</form>
				<form action="${pageContext.request.contextPath}/plan/${pt.pIdx}/PlanFoodCost" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">식비 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="PlanFoodCost">
					<thead>
						<tr>
							<th class="noVisible pfcIdx"></th>
							<th class="psDate">구매 날짜</th>
							<th class="pfcTime">구매 시간</th>
							<th class="pfcContent">구매 내역</th>
							<th class="pfcCost">비용</th>
							<th class="pfcRemark">비고</th>
						</tr>
					</thead>
					<tbody class="DBlist">
						<c:set var="sumFoodCost" value="0"></c:set>
						<c:forEach items="${pfc}" var="pfc" varStatus="status">
							<tr>
								<td class="noVisible pfcIdx" id="pfcIdx"><c:out value="${pfc.pfcIdx }"/></td>
								<td class="psDate"><c:out value="제${pfc.psDate }일차"/></td>
								<td class="pfcTime"><c:out value="${pfc.pfcTime }"/></td>
								<td class="pfcContent"><c:out value="${pfc.pfcContent }"/></td>
								<td class="pfcCost"><c:out value="${pfc.pfcCost }원"/></td>
								<td class="pfcRemark"><c:out value="${pfc.pfcRemark }"/>
								<c:if test="${!empty pfc.pfcCost}">
								<c:set var="sumFoodCost" value="${sumFoodCost+pfc.pfcCost}"></c:set>
								</c:if>
								<c:if test="${ppBoolean==true }">
								<span class="fa fa-times-circle-o removeButton removeFoodCost" id="removeFoodCost" onclick="event.cancelBubble=true;"></span>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tbody id="inputlist">
					</tbody>
					<tbody id="sumFoodCost">
						<tr>
							<td colspan="3">비용 합계</td>
							<td colspan="4"><c:out value="총 ${sumFoodCost}원"/></td>
						</tr>
					</tbody>
				</table>
				<c:if test="${ppBoolean==true }">
				<div
					style="overflow: auto; padding-top: 0.5em; padding-bottom: 1em;">
					<!-- <span id="submitPlanDetail" class="spanButton">[등록]</span><span id="addPlanDetail" class="spanButton">[입력창 추가]</span> -->
					<ul style="list-style-type: none;">
						<li style="float: right; display: inline;">
							<button type="submit" id="foodCostSubmit" class="btn btn-default btn-lg submitButton" style="">
								<span id="foodCostSubmit" class="network-name">등록</span>
							</button>
						</li>
						<li style="float: right; display: inline;">
							<button type="button" class="btn btn-default btn-lg">
								<span id="addFoodCost" class="network-name">입력창 추가</span>
							</button>
						</li>
					</ul>
				</div>
				</c:if>
				</form>
				<form action="${pageContext.request.contextPath}/plan/${pt.pIdx}/PlanStayPlace" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">숙박 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="PlanStayPlace">
					<thead>
						<tr>
							<th class="noVisible pspIdx"></th>
							<th class="psDate">숙박 날짜</th>
							<th class="pspPlace">숙박 지역</th>
							<th class="pspStartTime">입실 시간</th>
							<th class="pspEndTime">퇴실 시간</th>
							<th class="pspName">숙박지 이름</th>
							<th class="pspCost">비용(1박)</th>
							<th class="pspRemark">비고</th>
						</tr>
					</thead>
					<tbody class="DBlist">
						<c:set var="sumStayCost" value="0"></c:set>
						<c:forEach items="${psp}" var="psp" varStatus="status">
							<tr>
								<td class="noVisible pspIdx" id="pspIdx"><c:out value="${psp.pspIdx }"/></td>
								<td class="psDate"><c:out value="제${psp.psDate }일차"/></td>
								<td class="pspPlace"><c:out value="${psp.pspPlace }"/></td>
								<td class="pspStartTime"><c:out value="${psp.pspStartTime }"/></td>
								<td class="pspEndTime"><c:out value="${psp.pspEndTime }"/></td>
								<td class="pspName"><c:out value="${psp.pspName }"/></td>
								<td class="pspCost"><c:out value="${psp.pspCost }원"/></td>
								<td class="pspRemark"><c:out value="${psp.pspRemark }"/>
								<c:if test="${!empty psp.pspCost}">
								<c:set var="sumStayCost" value="${sumStayCost+ psp.pspCost}"></c:set>
								</c:if>
								<c:if test="${ppBoolean==true }">
								<span class="fa fa-times-circle-o removeButton removeStayPlace" id="removeStayPlace" onclick="event.cancelBubble=true;"></span>
								</c:if>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
					<tbody id="inputlist">
					</tbody>
					<tbody id="sumStayCost">
						<tr>
							<td colspan="3">비용 합계</td>
							<td colspan="4"><c:out value="총 ${sumStayCost}원"/></td>
						</tr>
					</tbody>
				</table>
				<c:if test="${ppBoolean==true }">
				<div
					style="overflow: auto; padding-top: 0.5em; padding-bottom: 1em;">
					<!-- <span id="submitPlanDetail" class="spanButton">[등록]</span><span id="addPlanDetail" class="spanButton">[입력창 추가]</span> -->
					<ul style="list-style-type: none;">
						<li style="float: right; display: inline;">
							<button type="submit" id="stayPlaceSubmit" class="btn btn-default btn-lg submitButton" style="display: none;">
								<span id="" class="network-name">등록</span>
							</button>
						</li>
						<li style="float: right; display: inline;">
							<button type="button" class="btn btn-default btn-lg">
								<span id="addStayPlace" class="network-name">입력창 추가</span>
							</button>
						</li>
					</ul>
				</div>
				</c:if>
				</form>
				<form action="${pageContext.request.contextPath}/plan/${pt.pIdx}/PlanRequireArticle" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">필요 물품 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle">
					<thead>
						<tr>
							<th class="noVisible praIdx"></th>
							<th>작성자</th>
							<th>물품 내역</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody class="DBlist">
						<c:forEach items="${pra}" var="pra" varStatus="status">
							<tr>
								<td class="noVisible praIdx" id="praIdx"><c:out value="${pra.praIdx }"/></td>
								<td id="mid" onclick="event.cancelBubble=true;"><c:out value="${pra.mid }"/></td>
								<td id="praRequireArticle"><c:out value="${pra.praRequireArticle }"/></td>
								<td id="praRemark"><c:out value="${pra.praRemark }"/>
								<c:if test="${ppBoolean==true }">
								<span class="fa fa-times-circle-o removeButton removeRequireArticle" id="removeRequireArticle" onclick="event.cancelBubble=true;"></span>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${ppBoolean==true }">
					<tbody id="inputlist">
						<tr>
							<td class="noVisible praIdx">
							</td>
							<td id="midTd">
								<input class="form-control detailInput" type="text" name="mid" value="${sessionScope.mid}" required="required" readonly="readonly" style="background-color: gray;">
							</td>
							<td id="praRequireArticleTd">
								<input class="form-control detailInput" type="text" name="praRequireArticle" placeholder="물품을 입력해주세요">
							</td>
							<td id="praRemarkTd">
								<input class="form-control detailInput" type="text" name="praRemark" placeholder="비고">
							</td>
						</tr>
					</tbody>
					</c:if>
				</table>
				<c:if test="${!empty sessionScope.mid}">
				<ul style="list-style-type: none;">
					<li style="float: right; display: inline;">
						<button type="submit" id="stayPlaceSubmit" class="btn btn-default btn-lg submitButton">
							<span id="" class="network-name">등록</span>
						</button>
					</li>
				</ul>
				</c:if>
				</form>
				<h2 class="subTitle">장소별 갈만한 곳 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle">
					<thead>
						
					</thead>
				</table>
				
		</div>
	</div>
</section>