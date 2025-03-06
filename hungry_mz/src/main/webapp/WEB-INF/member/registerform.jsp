<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>502 jsp study</title>
	<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <style>
        body *{
            font-family: 'Jua';
        }
        
		.backimg {
			opacity : 0.2;
		}
        
        .tab1 tbody th{
        	background-color: #f0f8ff;
        	height : 90px;
        }
        
        div.register {
        	position: absolute;
        	left: 50%;
		    top: 58%;
		    transform: translate(-50%, -50%);
		    width: 670px;
		    height: 730px;
		    background: #f5f5dc;
		    border: 1px solid #808000;
		    box-shadow: 7px 7px 39px rgba(189, 183, 107, 0.6);
		    border-radius: 20px;
		    margin: 0px;
		    padding-top: 60px;
		    padding-left : 100px;
		    padding-right : 100px;
		    padding-bottom : 100px;
		    box-sizing: border-box;
		    display: flex;
		    flex-direction: column;
		    flex-wrap: nowrap;
        }
        
         h2.title{
        	width: 466px;
		    height: 80px;
		    left: 725px;
		    top: 100px;
		    font-family: 'Noto Sans CJK KR';
		    font-style: normal;
		    font-weight: 700;
		    font-size: 32px;
		    line-height: 47px;
		    color: #2f4f4f;
		    justify-content: space-evenly;
        }
        
       .btn2 {
       	width: 466px;
	    height: 50px;
	    left: 725px;
	    top: 875px;
	    background-color: #FFFFFF;
	    color: #2f4f4f;
	    border-radius: 8px;
	    border: #2f4f4f solid 2px;
	    font-size : 20px;
       }
       
       .btn-gradient{
       	padding: 2px 12px;  
  		font-size: 13px;
  		margin-left : 15px;	
  		margin-bottom : 10px;
  		background-color : #6b8e23;
  		position: relative;
		border: 0;
		display: inline-block;
		text-align: center;
		color: white;
		box-shadow: 0px 4px 0px #9acd32;
       }
       
       /*클릭 시 아래로 살짝 내려감*/
       .btn-gradient:active{
       	top: 4px;
       	box-shadow : 0 0 #ff4c4b;
       	background-color : #D6BB59;
       }
       
      	input {
       	padding: 0px;
	    border: none;
	    border-bottom: 1px solid #CFCFCF;
	    width: 466px;
	    height: 30px;       
       }
       
       label {
       	font-size : 20px;
       }
        
     </style>
     <script>
     	let jungbok=false;
     	
     	$(function(){
     		//중복버튼 이벤트
     		$("#btnusernamecheck").click(function(){
     			let username=$("#username").val();
     			$.ajax({
     				type:"get",
     				dataType:"json",
     				data:{"username":username},
     				url:"./usernamecheck",
     				success:function(res){
     					if(res.result=='success'){
     						jungbok=true;
     						alert("사용가능한 아이디입니다");     						
     					}else{
     						jungbok=false;
     						alert("존재하는 아이디입니다\n다시 입력해주세요");
     						$("#username").val("");
     					}
     				}
     			});
     		});
     		
     		//아이디를 입력시 중복변수 다시 false로
     		$("#username").keyup(function(){
     			jungbok=false;
     		});
     	});
     	
     	function check(){
     		
     		let p1=$("#password").val();
     		let p2=$("#passwordcheck").val();
     		if(p1!=p2){
     			alert("비밀번호가 맞지 않습니다");
     			return false; //false로 주면 action 으로 안넘어감
     		}
     		
     		if(!jungbok){
     			alert("중복체크 버튼을 눌러주세요");
     			return false;
     		}
     		
     	}
     </script>
</head>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<body>
<jsp:include page="../layout/title.jsp"/>
<div class="background">
	<img src="../../image.jpg" height ="760px" width = "2100px" class="backimg">
	<div class="register">
		<form action="./register" method="post">
			<div class="container">
			<h2 class="title">회원가입을 위해<br>정보를 입력해주세요.</h2>
				<label for = "username" style = "padding-top : 30px;"> 아이디 
					<button type="button" class="btn-gradient yellow"
					id="btnusernamecheck">중복체크</button>
					<input type="text" name = "username" id="username" required="required"><br><br>
				</label>
				
				<label for = "password"> 비밀번호
					<input type="password" name = "password" id="password1" required="required"><br><br>
				</label>
				<label for = "passwordcheck"> 비밀번호 확인
					<input type="password" name = "passwordcheck" id="passwordcheck" required="required"><br><br>
				</label>
				<label for = "nickname"> 이름
					<input type="text" name = "nickname" id="nickname" required="required"><br><br>
				</label>
				<label for = "hp"> 전화번호
					<input type="text" name = "hp" required="required"><br><br>
				</label>
				<button type="submit" class="btn2 btn-sm btn-success join">회원가입</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
