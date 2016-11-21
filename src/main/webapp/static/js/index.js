// JavaScript Document
var turnplate={
		restaraunts:[],				//大转盘奖品名称
		colors:[],	                //大转盘奖品区块对应背景颜色
		//fontcolors:[],				//大转盘奖品区块对应文字颜色
		outsideRadius:222,			//大转盘外圆的半径
		textRadius:165,				//大转盘奖品位置距离圆心的距离
		insideRadius:65,			//大转盘内圆的半径
		startAngle:0,				//开始角度
		bRotate:false				//false:停止;ture:旋转
};

$(document).ready(function(){
	//动态添加大转盘的奖品与奖品区域背景颜色
	init();
	var prize = template.prize;
	if(template.flag==1){
		$('#createTemplate').hide();
	}
	var strs= new Array(); //定义一数组 
	strs=prize.split(","); //字符分割
	turnplate.restaraunts = [strs[0], strs[1], strs[2], strs[3], strs[4], strs[5]];
	turnplate.colors = ["#FF8584", "#FFEE7B", "#FF8584", "#FFEE7B","#FF8584", "#FFEE7B"];
	
	var rotateTimeOut = function (){
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:2160,
			duration:6000,
			callback:function (){
				alert('网络超时，请检查您的网络设置！');
			}
		});
	};
	
	
	//旋转转盘 item:奖品位置; txt：提示语;
	var rotateFn = function (item, txt){
		var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length*2));
		if(angles<270){
			angles = 270 - angles; 
		}else{
			angles = 360 - angles + 270;
		}
		$('#wheelcanvas').stopRotate();
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:angles+1800,
			duration:6000,
			callback:function (){
				//中奖页面与谢谢参与页面弹窗
				//alert(txt);
				if(txt.indexOf("谢谢参与")>=0){
						//$("#ml-main").fadeIn();
						//$("#zj-main").fadeOut();
						$("#xxcy-main").fadeIn();
				}else{
					//$("#ml-main").fadeIn();
					$("#zj-main").fadeIn();
					//$("#xxcy-main").fadeOut();
					var resultTxt=txt.replace(/[\r\n]/g,"");//去掉回车换行
					$("#jiangpin").text(resultTxt);
//					$(".close_zj").fadeOut();
				}								
				turnplate.bRotate = !turnplate.bRotate;
				if(id!=0){
					localStorage.setItem("ifLogin",id+"ifLogin");
					localStorage.setItem("resultTxt",resultTxt);
					$(".close_zj").fadeOut();
				}
			}
		});
	};
	
	/********弹窗页面控制**********/
	
	$('.close_zj').click(function(){
		$('#zj-main').fadeOut();
		//$('#ml-main').fadeIn();
	});
	
	$('.close_xxcy').click(function(){
		$('#xxcy-main').fadeOut();
		//$('#ml-main').fadeIn();
	});
	$('.close_tjcg').click(function(){
		$('#tjcg-main').fadeOut();
		//$('#ml-main').fadeIn();
	});
	
	$('.info_tj').click(function(){
		$('#zj-main').fadeOut();
		$('#tjcg-main').fadeIn();
	});
	
	
	/********抽奖开始**********/
	$('#tupBtn').click(function (){
		/*if(localStorage.getItem("ifLogin")==id+"ifLogin"){
			$("#zj-main").fadeIn();
			var txt = localStorage.getItem("resultTxt"); 
			var resultTxt=txt.replace(/[\r\n]/g,"");//去掉回车换行
			$("#jiangpin").text(resultTxt);
		}*/
//		else{
		if(turnplate.bRotate)return;
		turnplate.bRotate = !turnplate.bRotate;
		//获取随机数(奖品个数范围内)
		var item = rnd(1,turnplate.restaraunts.length);
		
		//奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
		rotateFn(item, turnplate.restaraunts[item-1]);
//		console.log(item);
//		}
	})
		
});

function rnd(n, m){
	var random = Math.floor(Math.random()*(m-n+1)+n);
	return random;
	
}


//页面所有元素加载完毕后执行drawRouletteWheel()方法对转盘进行渲染
window.onload=function(){
	drawRouletteWheel();
};

function drawRouletteWheel() {    
  var canvas = document.getElementById("wheelcanvas");    
  if (canvas.getContext) {
	  //根据奖品个数计算圆周角度
	  var arc = Math.PI / (turnplate.restaraunts.length/2);
	  var ctx = canvas.getContext("2d");
	  //在给定矩形内清空一个矩形
	  ctx.clearRect(0,0,516,516);
	  //strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式  
	  ctx.strokeStyle = "#FFBE04";
	  //font 属性设置或返回画布上文本内容的当前字体属性
	  ctx.font = 'bold 22px Microsoft YaHei';      
	  for(var i = 0; i < turnplate.restaraunts.length; i++) {       
		  var angle = turnplate.startAngle + i * arc;
		  ctx.fillStyle = turnplate.colors[i];
		  ctx.beginPath();
		  //arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）    
		  ctx.arc(258, 258, turnplate.outsideRadius, angle, angle + arc, false);    
		  ctx.arc(258, 258, turnplate.insideRadius, angle + arc, angle, true);
		  ctx.stroke();  
		  ctx.fill();
		  //锁画布(为了保存之前的画布状态)
		  ctx.save();   
		  
		  //----绘制奖品开始----
		  ctx.fillStyle = "#CB0030";
		  //ctx.fillStyle = turnplate.fontcolors[i];
		  var text = turnplate.restaraunts[i];
		  var line_height = 30;
		  //translate方法重新映射画布上的 (0,0) 位置
		  ctx.translate(258 + Math.cos(angle + arc / 2) * turnplate.textRadius, 258 + Math.sin(angle + arc / 2) * turnplate.textRadius);
		  
		  //rotate方法旋转当前的绘图
		  ctx.rotate(angle + arc / 2 + Math.PI / 2);
		  
		  /** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) **/
		  if(text.indexOf("\n")>0){//换行
			  var texts = text.split("\n");
			  for(var j = 0; j<texts.length; j++){
				  ctx.font = j == 0?'bold 22px Microsoft YaHei':'bold 22px Microsoft YaHei';
				  //ctx.fillStyle = j == 0?'#FFFFFF':'#FFFFFF';
				  if(j == 0){
					  //ctx.fillText(texts[j]+"M", -ctx.measureText(texts[j]+"M").width / 2, j * line_height);
					  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
				  }else{
					  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
				  }
			  }
		  }else if(text.indexOf("\n") == -1 && text.length>6){//奖品名称长度超过一定范围 
			  text = text.substring(0,6)+"||"+text.substring(6);
			  var texts = text.split("||");
			  for(var j = 0; j<texts.length; j++){
				  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
			  }
		  }else{

			  //在画布上绘制填色的文本。文本的默认颜色是黑色
			  //measureText()方法返回包含一个对象，该对象包含以像素计的指定字体宽度
			  ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
		  }
		  
		  //把当前画布返回（调整）到上一个save()状态之前 
		  ctx.restore();
		  //----绘制奖品结束----
	  }     
  } 
}

function showDialog(id) {
    document.getElementById(id).style.display = "-webkit-box";
}

function showID(id) {    
    document.getElementById(id).style.display = "block";  
}
function hideID(id) {
    document.getElementById(id).style.display = "none";
}
