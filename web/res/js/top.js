	
	var timer;
	function TOTOP(id,options){
		var defaults = {
			speed : 1000/60,  //滚动速度
			referClass : "",  //滚动到顶部按钮位置的参考class
			isMobile : false  //是否运行在手机
		};
		
		
		
		options = options || {};
		extend(defaults,options,true);
		function extend(o,n,override){
		   for(var p in n)if(n.hasOwnProperty(p) && (!o.hasOwnProperty(p) || override))o[p]=n[p];
		};
		
		document.getElementById("goTopBtn").style.display="none"
		defaults.isMobile || resize();
		var raf = window.mozRequestAnimationFrame
					||window.msRequestAnimationFrame
					||window.oRequestAnimationFrame
					||window.requestAnimationFrame
					|| function( callback ){
						window.setTimeout(callback, 10);
					  };
					  
		TOTOP.prototype.toTop = function(){
			document.getElementById(id).onclick=function(){
				timer=setInterval(function(){
					raf(scrollUp)
				},defaults.speed);
			};
		}
		
		function scrollUp(){
			var temp = document.documentElement.scrollTop==0?document.body.scrollTop:document.documentElement.scrollTop;
			if(temp>50){
				 document.documentElement.scrollTop==0?document.body.scrollTop= document.body.scrollTop-50:document.documentElement.scrollTop= document.documentElement.scrollTop-50;
			}else if(temp<=50&&temp>0){
				document.documentElement.scrollTop==0?document.body.scrollTop= 0:document.documentElement.scrollTop= 0;
			}else{
				clearInterval(timer);	
			}
		}
		
		if(!defaults.isMobile){
			window.addEventListener("resize",resize,false);
		}else{
			document.getElementById(id).style.right=5+"px";	
		}
		
		function resize(){
			var type=window.navigator.userAgent.toLowerCase();
			var bodyWidth,containerW;
			type == "ie" ? bodyWidth=window.document.body.offsetWidth : bodyWidth=window.document.body.clientWidth;
			if(document.getElementsByClassName(defaults.referClass).item(0)){
				containerW = document.getElementsByClassName(defaults.referClass).item(0).clientWidth;
			
			}else{
				containerW = 99999;
			}
			var long = bodyWidth-containerW;
			if(long>=0){
				document.getElementById(id).style.right=long/2-50+"px";
			}else{
				document.getElementById(id).style.right=10+"px";
			}
		}
		function scrollListener(){
			var scrollTopValue = document.documentElement.scrollTop==0?document.body.scrollTop:document.documentElement.scrollTop;
			scrollTopValue <window.screen.height ? document.getElementById(id).style.display="none" : document.getElementById(id).style.display="";
		}
		
		window.addEventListener("scroll",scrollListener,false);
		
		
	}
	