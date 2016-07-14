<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<section id="PlanDetailSection" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <table class="table table-bordered">
				    <thead>
				      <tr>
				        <th>Plan번호</th>
				        <td> ${pt.pIdx}</td>
				        <th rowspan="3">기간</th>
				        <td rowspan="3">${pt.sdate} ~ ${pt.edate}</td>
				      </tr>
				      <tr>
				        <th>인원</th>
				        <td>${pt.numPeople}명</td>
				      </tr>
				      <tr>
				      	<th>여행 구분</th>
				        <td> ${pt.pType}</td>
				      </tr>
				    </thead>
				    <tbody>
				      <tr>
				        <th>Plan명</th>
				        <td colspan="3">${pt.pName}</td>
				      </tr>
				    </tbody>
				  </table>	
            </div>
        </div>
    </section>