<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
 <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function sendIt(){
	   $.ajax({
	      type:"post",
	      url:"/ctrl/member/idCheck?id="+$("#userid").val(),
	      success: function(data){
	         if(data.trim()=="ok"){
	            alert("�����ִ� ���̵��Դϴ�.");
	            $(opener.document).find("#id").val($("#userid").val());    
	            $(opener.document).find("#userid").val($("#userid").val());    
	            self.close();
	            }else{
	            	alert("�������� ���̵��Դϴ�!");
	            }
	      },
	      error:function(e){
	         alert("������");
	      },
	   });
	}
</script>
<style>
.container{
	width: 60%;
	margin: 0 auto;
	padding-top: 30px;
}
.container h5{
	text-align: center;
}
</style>
<body>
<div class="container">
	<h5><b>�����Ͻ� ID�� �Է��ϼ���</b></h5>
	<input type="text" class="text" id="userid">
	<input type ="button" class="btn btn-default" onclick="sendIt()" value="�ߺ�Ȯ��">
</div>
</body>
</html>