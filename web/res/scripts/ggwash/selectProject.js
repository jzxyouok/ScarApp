/**
 * Created by Administrator on 2016/4/24.
 */
var sum=0;
$(document).ready(function(e) {
    /* 多选按钮 */
    $( ".radiooper").click( function(){
        var sum_pirce=parseFloat($("#total").html());
        if($(this).hasClass("on")){
            $( this ).removeClass( "on" )
            $( this ).attr( "src","../res/css/xc/img/radio_unsel.png" );
            var value=$(this).attr('value');
            sum_pirce-=parseFloat(value);
        }else{
            $( this ).addClass( "on" )
            $( this ).attr( "src","../res/css/xc/img/radio_sel.png" );
            var value=$(this).attr('value');
            sum_pirce+=parseFloat(value);
        }
        $("#total").html(parseFloat(sum_pirce).toFixed(2));
    });
    //单选按钮
    $( ".radio").click( function(){
        var current_click=$(this).attr('id');
        var index=$(this).attr('ids');
        var sum_price=$("#default_"+index).html();
        var name=$(this).attr('fname');
        $("#projectName").val(name);
        $(".radio").each(function(){
            var id=$(this).attr('id');
            if(current_click==id){
                $( this ).addClass( "on" )
                $( this ).attr( "src","../res/css/xc/img/radio_sel.png" );
            }else{
                $( this ).removeClass( "on" )
                $( this ).attr( "src","../res/css/xc/img/radio_unsel.png" );
            }
        });

        $("#total").html(parseFloat(sum_price).toFixed(2));
    });
});
//选择服务项目,需要把服务项目名称和总金额传递过去
function xzfwxm(){
    var carType=$("#carType").val();
    var carColor=$("#carColor").val();
    var carNo=$('#carNo').val();
    var washAddr=$('#washAddr').val();
    var mobile=$('#mobile').val();
    var name=$("#name").val();
    if(mobile==""||mobile==null){
        alert("请填写手机号码");
        return false;
    }
    if(name==""||name==null){
        alert("请填写称呼");
        return false;
    }
    if(carNo==""||carNo==null){
        alert("请填写车牌号");
        return false;
    }else if(carNo.length!=7){
        alert("车牌号填写错误");
        return false;
    }

    if(carColor==""||carColor==null){
        alert("请填写车颜色");
        return false;
    }
    if(carType==""||carType==null){
        alert("请填写车型");
        // hideloading();
        return false;
    }
    if(washAddr==""||washAddr==null||"定位中，请稍后......"==washAddr){
        alert("请填写洗车地点");
        return false;
    }
    var preTime=$("#preTime").val();
    if(preTime==""||preTime==null){
        alert("请选择预约时间");
        return false;
    }
    var money=$("#total").html();//总金额
    var projectName=$("#projectName").val();//服务项目
    var ids=getServicesProjectIds();
    if(''==ids||null==ids){
        alert("请选择服务项目");
        return;
    }
    $("#totalPrice").val($("#total").html());
    //跳转到微信支付订单页面
    //下单换成ajax方式
    var url="/order/wxpay.html";
    $("#myform").attr('action',url);
    var data=$('#myform').serialize();
    $("#myform").submit();

}
/*获取所有选中的服务*/
function getServicesProjectIds(){
    var ids="";
    $("img").each(function(){
        var src=$(this).attr("src");
        if(src.indexOf("radio_sel.png")>0){
            ids+=$(this).attr("ids")+",";
        }
    })
    ids=ids.substring(0,ids.length-1);
    return ids;
}
