<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>502 jsp study</title>
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body * {
	font-family: 'Jua';
}
</style>
</head>
<body>
<jsp:include page="../layout/title.jsp"/>
	<!-- The Modal -->
	<div class="modal" id="myUpdateModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">내 정보 수정</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<h6>이름</h6>
					<input type="text" class="from-control" id="nickname" value="${dto.nickname}">
					<h6>핸드폰</h6>
					<input type="text" class="from-control" id="hp" value="${dto.hp}">
					<br>
					<button type="button" class="btn btn-sm btn-success"
					id="btnupdate" data-bs-dismiss="modal">수정하기</button>
					
					<script>
						$("#btnupdate").click(function(){
							$.ajax({
								type:"post",
								dataType:"text",
								data:{"nickname":$("#nickname").val(), "hp":$("#hp").val(),
									"user_id":${dto.user_id}},
								url:"./update",
								success:function(){
									location.reload();
								}
							});
						});
					</script>
				</div>
			</div>
		</div>
	</div>
	<div style="margin: 30px 100px;">
		<div style="display: inline-block; margin: 20px 50px; width: 500px;">
			<h6>이름 : ${dto.nickname}</h6>
			<h6>핸드폰 : ${dto.hp}</h6>
			<h6>
				가입일 :
				<fmt:formatDate value="${dto.registered_date}" pattern="yyyy-MM-dd HH:mm" />
			</h6>
			<br>
			<br>
			<button type="button" class="btn btn-sm btn-danger"
				onclick="memberdel(${dto.user_id})">탈퇴하기</button>

			<script>
			function memberdel(user_id){
				let ans=confirm("정말 탈퇴하시겠습니까?");
				if(ans){
					$.ajax({
						type:"get",
						dataType:"text",
						data:{"user_id":user_id},
						url:"./mypagedel",
						success:function(){
							location.href='../';
						}
					});
				}
			}
		</script>
			<button type="button" class="btn btn-sm btn-success"
				data-bs-toggle="modal" data-bs-target="#myUpdateModal">
				정보수정
			</button>
		</div>
		
		<!-- 내가 작성한 리뷰 -->
		<div style="margin: 20px; width: 600px; clear: both;">
			<h5>내가 작성한 리뷰</h5>
			<table class="table table-bordered tabmyreview">
				<thead>
					<tr>
						<th width="60">번호</th>
						<th width="350">식 당</th>
						<th width="100">작성일</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list}" varStatus="i">
						<tr>
							<td align="center">${i.count}</td>
							<td>
								<a href="../review/detail?idx=${dto.idx}" style="color:black; text-decoration:none">
									${dto.subject}
									
								<!--  이미지가 한개 이상있는 경우 이미지 아이콘 넣기
								1개일 경우 한개이미지, 2개 이상일 경우 여러개 이미지 -->
								<c:if test="${dto.photoCount == 1}">
									<i class="bi bi-image picon"></i>
								</c:if>
								<c:if test="${dto.photoCount>1}">
									<i class="bi bi-images picon"></i>
								</c:if>
								</a>
							</td>
							<td align="center">
								<span style="font-size:0.8em;">
									<fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd"/>
								</span>
							</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>