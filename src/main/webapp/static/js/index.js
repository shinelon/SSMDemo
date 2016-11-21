// JavaScript Document
var turnplate={
		restaraunts:[],				//��ת�̽�Ʒ����
		colors:[],	                //��ת�̽�Ʒ�����Ӧ������ɫ
		//fontcolors:[],				//��ת�̽�Ʒ�����Ӧ������ɫ
		outsideRadius:222,			//��ת����Բ�İ뾶
		textRadius:165,				//��ת�̽�Ʒλ�þ���Բ�ĵľ���
		insideRadius:65,			//��ת����Բ�İ뾶
		startAngle:0,				//��ʼ�Ƕ�
		bRotate:false				//false:ֹͣ;ture:��ת
};

$(document).ready(function(){
	//��̬��Ӵ�ת�̵Ľ�Ʒ�뽱Ʒ���򱳾���ɫ
	init();
	var prize = template.prize;
	if(template.flag==1){
		$('#createTemplate').hide();
	}
	var strs= new Array(); //����һ���� 
	strs=prize.split(","); //�ַ��ָ�
	turnplate.restaraunts = [strs[0], strs[1], strs[2], strs[3], strs[4], strs[5]];
	turnplate.colors = ["#FF8584", "#FFEE7B", "#FF8584", "#FFEE7B","#FF8584", "#FFEE7B"];
	
	var rotateTimeOut = function (){
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:2160,
			duration:6000,
			callback:function (){
				alert('���糬ʱ�����������������ã�');
			}
		});
	};
	
	
	//��תת�� item:��Ʒλ��; txt����ʾ��;
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
				//�н�ҳ����лл����ҳ�浯��
				//alert(txt);
				if(txt.indexOf("лл����")>=0){
						//$("#ml-main").fadeIn();
						//$("#zj-main").fadeOut();
						$("#xxcy-main").fadeIn();
				}else{
					//$("#ml-main").fadeIn();
					$("#zj-main").fadeIn();
					//$("#xxcy-main").fadeOut();
					var resultTxt=txt.replace(/[\r\n]/g,"");//ȥ���س�����
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
	
	/********����ҳ�����**********/
	
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
	
	
	/********�齱��ʼ**********/
	$('#tupBtn').click(function (){
		/*if(localStorage.getItem("ifLogin")==id+"ifLogin"){
			$("#zj-main").fadeIn();
			var txt = localStorage.getItem("resultTxt"); 
			var resultTxt=txt.replace(/[\r\n]/g,"");//ȥ���س�����
			$("#jiangpin").text(resultTxt);
		}*/
//		else{
		if(turnplate.bRotate)return;
		turnplate.bRotate = !turnplate.bRotate;
		//��ȡ�����(��Ʒ������Χ��)
		var item = rnd(1,turnplate.restaraunts.length);
		
		//��Ʒ��������10,ָ�����ڶ�Ӧ��Ʒ��������ĽǶ�[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
		rotateFn(item, turnplate.restaraunts[item-1]);
//		console.log(item);
//		}
	})
		
});

function rnd(n, m){
	var random = Math.floor(Math.random()*(m-n+1)+n);
	return random;
	
}


//ҳ������Ԫ�ؼ�����Ϻ�ִ��drawRouletteWheel()������ת�̽�����Ⱦ
window.onload=function(){
	drawRouletteWheel();
};

function drawRouletteWheel() {    
  var canvas = document.getElementById("wheelcanvas");    
  if (canvas.getContext) {
	  //���ݽ�Ʒ��������Բ�ܽǶ�
	  var arc = Math.PI / (turnplate.restaraunts.length/2);
	  var ctx = canvas.getContext("2d");
	  //�ڸ������������һ������
	  ctx.clearRect(0,0,516,516);
	  //strokeStyle �������û򷵻����ڱʴ�����ɫ�������ģʽ  
	  ctx.strokeStyle = "#FFBE04";
	  //font �������û򷵻ػ������ı����ݵĵ�ǰ��������
	  ctx.font = 'bold 22px Microsoft YaHei';      
	  for(var i = 0; i < turnplate.restaraunts.length; i++) {       
		  var angle = turnplate.startAngle + i * arc;
		  ctx.fillStyle = turnplate.colors[i];
		  ctx.beginPath();
		  //arc(x,y,r,��ʼ��,������,���Ʒ���) ����������/���ߣ����ڴ���Բ�򲿷�Բ��    
		  ctx.arc(258, 258, turnplate.outsideRadius, angle, angle + arc, false);    
		  ctx.arc(258, 258, turnplate.insideRadius, angle + arc, angle, true);
		  ctx.stroke();  
		  ctx.fill();
		  //������(Ϊ�˱���֮ǰ�Ļ���״̬)
		  ctx.save();   
		  
		  //----���ƽ�Ʒ��ʼ----
		  ctx.fillStyle = "#CB0030";
		  //ctx.fillStyle = turnplate.fontcolors[i];
		  var text = turnplate.restaraunts[i];
		  var line_height = 30;
		  //translate��������ӳ�仭���ϵ� (0,0) λ��
		  ctx.translate(258 + Math.cos(angle + arc / 2) * turnplate.textRadius, 258 + Math.sin(angle + arc / 2) * turnplate.textRadius);
		  
		  //rotate������ת��ǰ�Ļ�ͼ
		  ctx.rotate(angle + arc / 2 + Math.PI / 2);
		  
		  /** ���������ݽ�Ʒ���͡���Ʒ���Ƴ�����Ⱦ��ͬЧ���������塢��ɫ��ͼƬЧ����(�������ʵ������ı�) **/
		  if(text.indexOf("\n")>0){//����
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
		  }else if(text.indexOf("\n") == -1 && text.length>6){//��Ʒ���Ƴ��ȳ���һ����Χ 
			  text = text.substring(0,6)+"||"+text.substring(6);
			  var texts = text.split("||");
			  for(var j = 0; j<texts.length; j++){
				  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
			  }
		  }else{

			  //�ڻ����ϻ�����ɫ���ı����ı���Ĭ����ɫ�Ǻ�ɫ
			  //measureText()�������ذ���һ�����󣬸ö�����������ؼƵ�ָ��������
			  ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
		  }
		  
		  //�ѵ�ǰ�������أ�����������һ��save()״̬֮ǰ 
		  ctx.restore();
		  //----���ƽ�Ʒ����----
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
