<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="PlanDetailSection"
	class="container content-section text-center">
	<div class="row">
		<div class="col-lg-12 planDiv">
			<h2>TOURPLAN 계획서</h2>
			<hr>
			<form action="${pageContext.request.contextPath}/plan/${pt.pIdx}" method="post" onsubmit="return regCheck();">
				<table class="table table-bordered tableTop">
					<thead>
						<tr>
							<th>개최자</th>
							<td>${pt.holder}</td>
							<td id="pIdx" style="display: none;">${pt.pIdx}</td>
							<td rowspan="3">기간</td>
							<td rowspan="3"><span id="spanSdate">${pt.sdate}</span> ~ <span
								id="spanEdate">${pt.edate}</span></td>
						</tr>
						<tr>
							<th>인원</th>
							<td>${pt.numPeople}명</td>
						</tr>
						<tr>
							<th>여행 구분</th>
							<td>${pt.pType}</td>
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
							<th>비고</th>
							<td colspan="3"></td>
						</tr>
					</tbody>
				</table>
				<h2>상세 일정</h2>
				<table class="table table-bordered tableMiddle">
					<thead>
						<tr>
							<th class="psIdx"></th>
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
								<td class="psIdx"><c:out value="${ps.psIdx }"/></td>
								<td id="dateTd"><c:out value="제${ps.psDate }일차"/></td>
								<td id="placeTd"><c:out value="${ps.psPlace }"/></td>
								<td id="transTd"><c:out value="${ps.psTrans }"/></td>
								<td id="timeTd"><c:out value="${ps.pstime }"/></td>
								<td id="scheTd"><c:out value="${ps.psSchedule }"/></td>
								<td id="remarkTd"><c:out value="${ps.psRemark }"/><span class="fa fa-times-circle-o removeSchedule" id="removeSchedule" onclick="event.cancelBubble=true;"></span></td>
							</tr>
						</c:forEach>
					</tbody>
					<tbody id="inputlist">
					</tbody>
				</table>
				<div
					style="overflow: auto; padding-top: 0.5em; padding-bottom: 1em;">
					<!-- <span id="submitPlanDetail" class="spanButton">[등록]</span><span id="addPlanDetail" class="spanButton">[입력창 추가]</span> -->
					<ul style="list-style-type: none;">
						<li style="float: right; display: inline;">
							<button type="submit" class="btn btn-default btn-lg">
								<span id="submitPlanDetail" class="network-name">등록</span>
							</button>
						</li>

						<li style="float: right; display: inline;">
							<button type="button" class="btn btn-default btn-lg">
								<span id="addPlanDetail" class="network-name">입력창 추가</span>
							</button>
						</li>

					</ul>
				</div>
			</form>
		</div>
	</div>
</section>