/**
 * Created by Administrator on 2016/3/11.
 */
showTime();
function showTime(){
    var now= new Date();//获取当前时间
    var year=now.getFullYear();//年
    var month=now.getMonth()+1;//月
    var date=now.getDate();//日
    var day = now.getDay();//星期
    var hour=now.getHours();//时
    var minute=now.getMinutes();//分
    var seconds=now.getSeconds();    //获取当前秒数(0-59)
    var milliseconds=now.getMilliseconds();   //获取当前毫秒数(0-999)
    if(now == 0)
        day=" 星期日";
    else if(day == 1)
        day=" 星期一";
    else if(day == 2)
        day=" 星期二";
    else if(day == 3)
        day=" 星期三";
    else if(day == 4)
        day=" 星期四";
    else if(day == 5)
        day=" 星期五";
    else if(day == 6)
        day=" 星期六";
    if(hour<10)hour="0"+hour;
    if(minute<10)minute="0"+minute;
    if(date<10)date="0"+date;
    if(month<10)month="0"+month;
    $("#date").html(year+"/"+month+"/"+date);
    $("#time").html(hour+":"+minute);
//刷新时间
    setTimeout('showTime()',1000);
}
