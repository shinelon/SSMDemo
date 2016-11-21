<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta content="yes" name="apple-mobile-web-app-capable"/>
<meta content="yes" name="apple-touch-fullscreen"/>
<meta content="telephone=no" name="format-detection"/>
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="wap-font-scale" content="no">
<!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->

<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
<title>ת�̳齱</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="">
<link href="${ctx}/static/style/component.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/style/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/static/style/bootstrap-switch.css" type="text/css">
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<script src="${ctx}/static/js/bootstrap.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/awardRotate.js"></script> --%>
<script type="text/javascript" src="${ctx}/static/js/index.js"></script>
<script type="text/javascript">
 var template;
 var id;
	function init(){
		template=${json};
		id=${id};
	  }; 
 $(function(){  
		/*  localStorage.removeItem("ifLogin");
	     localStorage.removeItem("resultTxt");
		 var aa =  localStorage.getItem("ifLogin");
		 var bb = localStorage.getItem("resultTxt");  
		 alert(aa+bb); */
	 if(localStorage.getItem("ifLogin")==id+"ifLogin"){
         $("#zj-main").fadeIn();
         var txt = localStorage.getItem("resultTxt"); 
         var resultTxt=txt.replace(/[\r\n]/g,"");//ȥ���س�����
         $("#jiangpin").text(resultTxt);
         $(".close_zj").fadeOut();
     }
		});                                                                                               
	 function submit(){
		  $.ajax({
		         type:"POST",
		         url:${ctx}"/make/1",
		         dataType: 'text',
		         data:'p='+'${json}', 
		         success:function(jsonData){ 
// 		             alert(jsonData);
		             var returnUrl=$("#returnUrl");
		             returnUrl.html(jsonData);
		         },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
// 		             alert(XMLHttpRequest.status);
// 		             alert(XMLHttpRequest.readyState);
// 		             alert(textStatus);
		               }
		     });

	 };
</script>

    <style type="text/css">
        .ml-main > div.title {
            height: 100px;
            text-align: center;
            color: #FF6316;
            font: 700 30px/100px "microsoft yahei";
            text-shadow: #fff 1px 0 0, #fff 0 1px 0, #fff -1px 0 0, #fff 0 -1px 0;
            -webkit-text-shadow: #fff 1px 0 0, #fff 0 1px 0, #fff -1px 0 0, #fff 0 -1px 0;
            -moz-text-shadow: #fff 1px 0 0, #fff 0 1px 0, #fff -1px 0 0, #fff 0 -1px 0;
        }



    </style>
</head>

<body>
<!-------------�齱ҳ��-------------->
   
    <div class="ml-main" id="ml-main">
      <div class="title">${templatebean.customerName }</div>
     
        <div class="kePublic"  style="position: relative;bottom: 0">
            <!--ת��Ч����ʼ-->
            <div style="max-width:100% margin:0 auto">
                <div class="banner">
                    <div class="turnplate" style="background-image:url(${ctx}/static/images/turnplate-bg_2.png);background-size:100% 100%;">
                        <canvas class="item" id="wheelcanvas" width="516" height="516"></canvas>
                        <img id="tupBtn" class="pointer" src="${ctx}/static/images/turnplate-pointer_2.png"/>
                    </div>
                </div>
            </div>
            <!--ת��Ч������-->
            <div class="clear"></div>
             <div class="rules">
	           <div>
	           <label style="float:left;">�����:</label> 
	           <div class="title" style="float:left; margin-left:10px;">${templatebean.description}
	           </div>
	           </div>
	           <div style="float:left;width:100%">
	         <label  style="float:left;">���ַ:</label> <div id="address" style="float:left;margin-left:10px;">${templatebean.address} </div>
	         </div>
	         
	         <div style="float:left;width:100%">
	         <label  style="float:left;">��ϵ�绰:</label> <div id="telphone" style="float:left;margin-left:10px">${templatebean.telphone} </div>
	         </div>
            
            </div>
              
             <div style="float:left;margin-top:10px;width:100%">
             <button id="createTemplate" class="btn btn-default" onclick="javascript:submit();" style="width:100%">����ģ��</button>
            </div>
            <div id="returnUrl" style="float:left;margin-top:10px"></div>
        </div>
            
    </div>
    
    <!-------------�н�����ҳ��-------------->
    <div class="zj-main" id="zj-main">
            <div class="txzl">
                <h3>HI �ף���Ʒ������</h3>
                <h2>��ϲ����<br /><span id="jiangpin"></span></h2>
                <p>ÿ����ֻ�ܳ�ȡһ��Ŷ������</p>
               
                <div class="info_tj"></div> 
            </div>
            <div class="close_zj"><img src="${ctx}/static/images/close_1.png" /></div>
    </div>
    
    <!-------------лл���뵯��-------------->
    <div class="xxcy-main" id="xxcy-main">
        <div class="xxcy">
            <h3>лл���룡</h3>
            <p>��Ҫ���٣��������ͶƱ�����Գ齱Ŷ��</p>
            
        </div>
        <div class="close_xxcy"><img src="${ctx}/static/images/close_1.png" /></div>
    </div>
    
    <!-------------�ύ�ɹ�����-------------->
    <div class="tjcg-main" id="tjcg-main">
        <div class="tjcg">
            <h3>ÿ����ֻ�ܳ�һ��Ŷ</h3>
            <h2>��ϲ����<br /></h2>
            <span id="jiangpin"></span>
            
        </div>
        <div class="close_tjcg"><img src="${ctx}/static/images/close_1.png" /></div>
    </div>
</body>
</html>
