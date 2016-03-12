$(document).ready(
    function () {
        setInterval(function () {
            setCurrentTime();
        }, 60000);
       // readBJ();
       // setCurrentTime();
       // setCurrentDate();
        $("#seats").bind({
            click: function () {
                var seatsState = $(this).data("seatsState");
                if (seatsState == "black") {
                    $(this).data("seatsState", "none");
                    $("#divSeats").slideUp();
                }
                else {
                    $(this).data("seatsState", "black");
                    $("#divSeats").slideDown();
                }
            }
        });

        $("#divSeats").children().bind({
            click: function () {
                $("#divSeats").children().removeClass("current");
                $(this).addClass("current");
                $("#divSeats").slideUp();
            }
        });
        $("#navHeard").children().bind({
            click: function () {
                $("#navHeard").children().removeClass("current");
                $(this).addClass("current");
                if ($(this).attr("id") != "seats") {
                    $("#seats").data("seatsState", "none");
                    $("#divSeats").slideUp();
                }
            }
        });
        $("#navHeard").children().bind({
            click: function () {
                var url = $(this).attr("url");
                if (url) {
                    window.location.href = window.location.protocol+"//" + window.location.host + url + "?" + new Date().getTime();
                }
            }
        });
        //payByCardRead();
    });

var nums = ["一", "二", "三", "四", "五", "六", "七", "八", "九"];

function setCurrentTime() {
    var curDate = new Date();
    $("#currentTime").text(curDate.Format("HH:mm"));
}
function setCurrentDate() {
    var curDate = new Date();
    $("#currentDate").text(curDate.Format("yyyy-MM-dd"));
}

function ajaxGetRequest(url, callBackFun, isAsync) {
    if (!isAsync) { isAsync = true;}
    $.ajax({
        type: "get",
        url: url,
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        async: false,
        success: function (msg) {
            callBackFun(msg);
        },
        error: function (xhr, st, err) {//执行错误走此方法
        }
    });
}
function AddDays(date, days) {
    return new Date(Date.parse(date) + (days * 24 * 60 * 60 * 1000));
}
//Date.prototype.AddDays = function (days) {
//    return new Date(Date.parse(this) + (days * 24 * 60 * 60 * 1000));
//}
//String.prototype.AddDays = function (days) {
//    return new Date(Date.parse(this.replace(/-/g, '/')) + (days * 24 * 60 * 60 * 1000));
//}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
String.prototype.Format = function (fmt) {
    var dateResult = new Date(this.replace(/-/g, '/'));
    var o = {
        "M+": dateResult.getMonth() + 1, //月份
        "d+": dateResult.getDate(), //日
        "H+": dateResult.getHours(), //小时
        "m+": dateResult.getMinutes(), //分
        "s+": dateResult.getSeconds(), //秒
        "q+": Math.floor((dateResult.getMonth() + 3) / 3), //季度
        "S": dateResult.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (dateResult.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//String.prototype.parseDate = function () {
//    var dateResult = new Date(this.replace(/-/g, '/'));
//    return dateResult;
//}

function readBJ() {
    ajaxGetRequest("ashx/classInfo.ashx", loadBJ);
}

function loadBJ(msg) {
    if(msg){
        $("header").find(".dib").attr("data-code", msg.ClassId);
        $("header").find(".dib").text(msg.ClassName);
    }
}


function payByCardRead() {
    var payID=0;
    if ($("#payByCardBg").length <= 0) {
        $("body").append('<section class="cover" id="payByCardBg"  style="display:none"></section>');
        $("#payByCardBg").bind({click:function(){
            $("#payByCard").fadeOut();
            $("#payByCardBg").fadeOut();
            setIntervalPayByCard();
        }});
    }
    if ($("#payByCard").length <= 0) {
        $("body").append('<section class="exam-info" id="payByCard" style="display:none">'
        +'<section class="infor-title">个人信息</section>'
        +'<a class="back">返回</a>'
        +'<section class="exam-0 hz-hid">'
        +'<span class="img"></span>'
        +'<div class="exam-left">'
        +'</div>'
        +'</section>'
        +'<section class="exam-2">'
        +'刷卡成功'
        +'</section>'
        +'<ul class="exam-ul">'
        +'<li>今日课表</li>'
        +'<li>班级课表</li>'
        +'<li>教室课表</li>'
        +'</ul>'
        +'</section>');
        $("#payByCard").find(".back").bind({click:function(){
            $("#payByCard").fadeOut();
            $("#payByCardBg").fadeOut();
            setIntervalPayByCard();
        }});
    }
    setIntervalPayByCard();

    function setIntervalPayByCard(){
        payID =  setInterval(function () {
            getPayByCard();
        }, 1000);
    }
    function getPayByCard(){
        $.ajax({
            type: "get",
            url: "ashx/payByCard.ashx",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (msg) {
                loadData(msg);
            },
            error: function (xhr, st, err) {//执行错误走此方法
                setIntervalPayByCard();
            }
        });
    }
    function loadData(msg){
        if(msg){
            payID=window.clearInterval(payID);
            $("#payByCard").find(".exam-left").html("");
            $("#payByCard").find(".exam-left").append('<p>姓&nbsp;&nbsp;&nbsp; 名  : '+ msg.StudentName+'</p>'
            +'<p>班&nbsp;&nbsp;&nbsp; 级  :  '+ msg.ClassName+'</p>'
            +'<p>班主任  : '+ msg.ClassTeacher+'</p>');
            $("#payByCardBg").fadeIn();
            $("#payByCard").fadeIn();
        }
    }

}
