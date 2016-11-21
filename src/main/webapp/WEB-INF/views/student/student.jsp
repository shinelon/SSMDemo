<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/static/jquery/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/static/jquery/click.js"></script>
</head>
<body>
	姓名： ${student.name}
	${json} 
	<input type="button" id="bt1" value="bt1value" >
</body>
<script>
 var jsonstr;
 var jsonobj;
$(document).ready(function(){
	
// 	  jsonobj=${json};
// 	  jsonstr= JSON.stringify(jsonobj)
// 	  console.info("jsonstr");
// 	  console.info(jsonstr);
// 	  console.info("jsonobj");
// 	  console.info(jsonobj);
// 	  onclick();
      var btval=$("#bt1").val();
      console.info(btval);
      $("#bt1").bind("click",onclick);
	  
	});
	
</script>
<script>
function onclick(){
    console.info("123")
    console.info("onclick")
    var hostUrl="http://localhost:8080/maven-web-demo"
    var saveDataAry=[];  
    var data1={"mobile":"test","password":"123"};  

     $.ajax({
         type:"POST",
         url:hostUrl+"/student/showInfo/ajaxjson.htmls",
         dataType: 'json',
         contentType:'application/json;charset=UTF-8',
         data:JSON.stringify(data1) ,             
         success:function(jsonData){ 
             console.log(jsonData);              
             
         },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
               }
     });
    
}
</script>
</html>