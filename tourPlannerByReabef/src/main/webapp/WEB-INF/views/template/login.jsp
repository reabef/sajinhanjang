<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Login Section -->
    <section id="loginSection" class="container content-section text-center"  style="display: none;">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
				<form action="${pageContext.request.contextPath}/member/login" class="form-container" method="post" onsubmit="return loginCheck();">
					<span style="float: right; cursor: pointer;" class="fa fa-times" onclick="closeLogin();"></span>
					<div style="width:300px;margin: 0 auto">
						<div class="form-title">
							<h2>Login</h2>
						</div>
						<br>
						<div class="form-title"><span id="midTitle">ID</span></div>
						<input class="form-field" id="loginMid" type="text" name="mid" placeholder="아이디를 입력해주세요"/><br />
						<div class="form-title"><span id="pwdTitle">PASSWORD</span></div>
						<input class="form-field" id="loginPwd" type="password" name="pwd" placeholder="비밀번호를 입력해주세요"/><br />
						<div class="submit-container">
							<input class="submit-button" type="submit" value="Login" />
						</div>
					</div>
				</form>
            </div>
        </div>
    </section>