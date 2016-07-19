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
				<form action="${pageContext.request.contextPath}/plan/${pt.pIdx}" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">상세 일정</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="detailSchedule">
					<thead>
						<tr>
							<th class="noVisible psIdx"></th>
							<th>날짜</th>
							<th>장소</th>
							<th>교통편</th>
							<th>시각</th>
							<th>일정</th>
							<th style="width: 10%;">비고</th>
						</tr>
					</thead>
					<tbody id="DBlist">
						<c:forEach items="${ps}" var="ps" varStatus="status">
							<tr>
								<td class="noVisible psIdx"><c:out value="${ps.psIdx }"/></td>
								<td id="dateTd"><c:out value="제${ps.psDate }일차"/></td>
								<td id="placeTd"><c:out value="${ps.psPlace }"/></td>
								<td id="transTd"><c:out value="${ps.psTrans }"/></td>
								<td id="timeTd"><c:out value="${ps.pstime }"/></td>
								<td id="scheTd"><c:out value="${ps.psSchedule }"/></td>
								<td id="remarkTd"><c:out value="${ps.psRemark }"/>
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
				<form action="${pageContext.request.contextPath}/plan/planTransCostReg" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">교통비 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="sumTransCost">
					<thead>
						<tr>
							<th class="noVisible ptcIdx"></th>
							<th>이동 날짜</th>
							<th>출발 지역</th>
							<th>출발 시간</th>
							<th>도착 지역</th>
							<th>도착 시간</th>
							<th>방법</th>
							<th>비용(인당)</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody id="DBlist">
						<c:set var="sumTransCost" value="0"></c:set>
						<c:forEach items="${ptc}" var="ptc" varStatus="status">
							<tr>
								<td class="noVisible ptcIdx"><c:out value="${ptc.ptcIdx }"/></td>
								<td id="psDateTd"><c:out value="제${ptc.psDate }일차"/></td>
								<td id="startPlaceTd"><c:out value="${ptc.startPlace }"/></td>
								<td id="startTimeTd"><c:out value="${ptc.startTime }"/></td>
								<td id="endPlaceTd"><c:out value="${ptc.endPlace }"/></td>
								<td id="endTimeTd"><c:out value="${ptc.endTime }"/></td>
								<td id="psTransTd"><c:out value="${ptc.psTrans }"/></td>
								<td id="ptcCostTd"><c:out value="${ptc.ptcCost }원"/></td>
								<td id="ptcRemarkTd"><c:out value="${ptc.ptcRemark }"/>
								<c:set var="sumTransCost" value="${sumTransCost+ptc.ptcCost}"></c:set>
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
				<form action="${pageContext.request.contextPath}/plan/planFoodCostReg" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">식비 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="foodCost">
					<thead>
						<tr>
							<th class="noVisible pfcIdx"></th>
							<th>구매 날짜</th>
							<th>구매 시간</th>
							<th>구매 내역</th>
							<th>비용</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody id="DBlist">
						<c:set var="sumFoodCost" value="0"></c:set>
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
				<form action="${pageContext.request.contextPath}/plan/planStayPlaceReg" method="post" onsubmit="return regCheck();">
				<h2 class="subTitle">숙박 정리</h2>
				<hr>
				<table class="table table-bordered tableMiddle" id="stayPlace">
					<thead>
						<tr>
							<th class="noVisible pspIdx"></th>
							<th>숙박 날짜</th>
							<th>숙박 지역</th>
							<th>입실 시간</th>
							<th>퇴실 시간</th>
							<th>숙박지 이름</th>
							<th>비용(1박)</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody id="DBlist">
						<c:set var="sumStayCost" value="0"></c:set>
						<c:forEach items="${psp}" var="psp" varStatus="status">
							<tr>
								<td class="noVisible pspIdx"><c:out value="${psp.pspIdx }"/></td>
								<td id="psDateTd"><c:out value="제${psp.psDate }일차"/></td>
								<td id="pspPlaceTd"><c:out value="${psp.pspPlace }"/></td>
								<td id="pspStartTimeTd"><c:out value="${psp.pspStartTime }"/></td>
								<td id="pspEndTimeTd"><c:out value="${psp.pspEndTime }"/></td>
								<td id="pspNameTd"><c:out value="${psp.pspName }"/></td>
								<td id="pspCostTd"><c:out value="${psp.pspCost }원"/></td>
								<td id="pspRemarkTd"><c:out value="${psp.pspRemark }"/>
								<c:set var="sumStayCost" value="${sumStayCost+ psp.pspCost}"></c:set>
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
				<form action="${pageContext.request.contextPath}/plan/planRequireArticleReg" method="post" onsubmit="return regCheck();">
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
					<tbody id="DBlist">
						<c:forEach items="${pra}" var="pra" varStatus="status">
							<tr>
								<td class="noVisible praIdx"><c:out value="${pra.praIdx }"/></td>
								<td id="midTd"><c:out value="${pra.mid }"/></td>
								<td id="praRequireArticleTd"><c:out value="${pra.praRequireArticle }"/></td>
								<td id="praRemarkTd"><c:out value="${pra.praRemark }"/>
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
				<input type="hidden" name="ptIdx" value="${pt.pIdx}">
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