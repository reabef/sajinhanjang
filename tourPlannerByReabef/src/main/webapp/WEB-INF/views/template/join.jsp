<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Join Section -->
    <section id="joinSection" class="container content-section text-center" style="display: none;">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <form action="member/join" class="form-container" method="post" onsubmit="return formCheck();">
                	<span style="float: right; cursor: pointer;" class="fa fa-times" onclick="closeJoin();"></span>
                	<div style="width:300px;margin: 0 auto">
						<div class="form-title">
							<h2>Join</h2>
						</div>
						<br>
						<div class="form-title"><span id="existId">ID</span></div>
						<input class="form-field" id="inputMid" type="text" name="mid"  placeholder="아이디를 입력해주세요"/><br />
						<div class="form-title"><span class="pwdTitle">PASSWORD</span></div>
						<input class="form-field" id="inputPwd" type="password" name="pwd" placeholder="비밀번호를 입력해주세요"/><br />
						<div class="form-title"><span class="pwdTitle" id="pwdTitle2">PASSWORD Confirm</span></div>
						<input class="form-field" id="inputPwd2" type="password" placeholder="비밀번호를 똑같이 입력해주세요"/><br />
						<div class="form-title"><span id="nameTitle">name</span></div>
						<input class="form-field" id="inputName" type="text" name="name" placeholder="이름을 입력해주세요"/><br />
						<div class="form-title"><span id="emailTitle">email</span></div>
						<input class="form-field" id="inputEmail" type="text" name="email" placeholder="이메일을 입력해주세요"/><br />
						<div class="submit-container">
							<input class="submit-button" type="submit" value="Join" />
						</div>
					</div>
					
				</form>
            </div>
        </div>
    </section>