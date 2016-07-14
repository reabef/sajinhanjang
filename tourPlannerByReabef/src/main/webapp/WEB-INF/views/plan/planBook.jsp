<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<section id="PlanBookSection" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
            	<h2>TOURPLAN LIST</h2>
            </div>
            <div style="margin:0 auto;width: 100%;overflow: auto;text-align: center;">
            	<c:forEach items="${pts}" var="list" varStatus="status">
	            	<div class="col-xs-6 col-sm-4" style="cursor: pointer;" onclick="location.href='${pageContext.request.contextPath}/plan/${list.pIdx}'">
	            		<div class="tourListDiv">
	            			<span class="fa fa-times-circle-o removePlanTitle" id="removePlanTitle" onclick="event.cancelBubble=true;"></span>
			            	<div id="title"><h3 style="margin-top: 1em;"><c:out value="${list.pName }"/></h3></div>
			            	<div id="type"><c:out value="${list.pType }"/></div>
			            	<div id="number">인원 : <c:out value="${list.numPeople }명"/></div>
			            	<div id="holder">개최자 : <c:out value="${list.holder }" /></div>
			            	<div id="sdateDiv">시작일 : <c:out value="${list.sdate }"/></div>
			            	<div id="edateDiv">종료일 : <c:out value="${list.edate }"/></div>
		            	</div>
	            	</div>
            	</c:forEach>
			</div>
        </div>
    </section>