<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- Login Section -->
    <section id="regSection" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
				<form action="${pageContext.request.contextPath }/plan/planReg" class="form-container" method="post" onsubmit="return planCheck();">
					<div style="width:300px;margin: 0 auto">
						<div class="form-title">
							<h2>Tour Plan Start</h2>
						</div>
						<br>
						<div class="form-title"><span id="pNameTitle">여행 이름</span></div>
						<input class="form-field" id="pName" type="text" name="pName" placeholder="여행 이름을 입력해주세요"/><br />
						<div class="form-title"><span id="pTypeTitle">여행 구분</span></div>
						<div id="radioDiv">
							<label><input TYPE='radio' name='pType' value='단체'/>단체</label><br>
							<label><input TYPE='radio' name='pType' value='개인' />개인</label><br>
							<label><input TYPE='radio' name='pType' value='소규모' checked='checked' />소규모</label><br>
						</div>
						<div class="form-title"><span id="numPeopleTitle">여행 인원을 입력해주세요</span></div>
						<input class="form-field" id="numPeople" type="text" name="numPeople" placeholder="여행 인원을 입력해주세요" onkeydown="return showKeyCode(event)"/><br />
						<div class="form-title"><span id="pPeriodTitle">여행 기간을 입력해주세요</span></div>
			            <div class="hero-unit">
							<span style="float:left;padding-left: 1em;">시작일 :</span> <input class="form-field" type="text" name="sdate" id="sdate" size="10" maxlength="10" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd a hh:mm" />" placeholder="클릭하시면 달력이 나옵니다" style="margin-left: 0.4em;"/>
							<br>
							<span style="float:left;padding-left: 1em;">종료일 :</span> <input class="form-field" type="text" name="edate" id="edate" size="10" maxlength="10" value="" placeholder="클릭하시면 달력이 나옵니다"/>
			            </div>
						<div class="submit-container">
							<input type="hidden" name="mid" value="${sessionScope.mid}">
							<input class="submit-button" type="submit" value="등록" />
						</div>
					</div>
				</form>
            </div>
        </div>
    </section>
