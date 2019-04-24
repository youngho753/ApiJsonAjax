<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<html>
<head>
	
</head>
<body>
	<div id = "container">
		<div>
			<input type = "text" id = "word">
			<input type = "button" value = "검색" onclick = Search()>
		</div>
		<div id = "result">
		</div>
	</div>
</body>
<script>
$("input[type=text]").keypress(function(e){
	if(e.keyCode == 13)
		Search();
});
function Search(){
	$.ajax({
			type : "get",
			url : "search.do",
			data : {"word": $("#word").val()},
			success:function(data){
					//JSON파싱
					data = $.parseJSON(data)
	                var htmlStr = "<table>";
	                
					for(i=0; i<data.length;i++){
						htmlStr += "<tr>";
						htmlStr += "<td>"+data[i].title+"</td>";
						htmlStr += "<td><a>"+data[i].link+"</a></td>";
						htmlStr += "</tr>";
					}
					htmlStr += "</table>";
					$("#result").html(htmlStr);
				},
			
			error:function(e){
				alert("error:"+e);
			}
		});
};
	
</script>


</html>
