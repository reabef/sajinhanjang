<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Intro Header -->
    <header class="intro">
        <div class="intro-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h1 class="brand-heading">TourPlan</h1>
                        <p class="intro-text">Create Your TourPlan</p>
                        <ul class="list-inline banner-social-buttons">
	                    <c:if test="${empty sessionScope.mid}">
	                    <li>
	                        <a href="#joinSection" class="btn btn-default btn-lg" onclick="openJoin();"><i class="fa fa-pencil-square-o"></i> <span class="network-name">JOIN</span></a>
	                    </li>
	                    <li>
	                        <a href="#loginSection" class="btn btn-default btn-lg loginButton" onclick="openLogin();"><i class="fa fa-unlock"></i> <span class="network-name">Login</span></a>
	                    </li>
	                    </c:if>
	                    <c:if test="${!empty sessionScope.mid}">
	                    <li>
	                        <a href="${pageContext.request.contextPath }/member/logout" class="btn btn-default btn-lg"><i class="fa fa-lock"></i> <span class="network-name">Logout</span></a>
	                    </li>
	                    <li>
	                        <a href="${pageContext.request.contextPath }/plan/planReg" class="btn btn-default btn-lg">
	                        <i class="fa fa-plane"></i> 
	                        <span class="network-name">Plan-create</span>
	                        </a>
	                    </li>
	                    </c:if>
	                    <li>
	                        <a href="${pageContext.request.contextPath }/plan/planBook" class="btn btn-default btn-lg">
	                        <i class="fa fa-book"></i> 
	                        <span class="network-name">OpenPlanBook</span>
	                        </a>
	                    </li>
                		</ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
