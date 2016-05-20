/**
 * Created by Administrator on 2016/5/20.
 */
function getCode() {
    var couponCode=$("#couponCode").val();
    if(""==couponCode||null==couponCode){
        Dialog.show("兑换码不能为空", 2, 1000);
        return ;
    }
    Dialog.show("正在处理请求", 3, -1);
    var url="${base}/vc/tuihuan.html";
    $.ajax({
        type:"post",
        url: url,
        data:{"couponCode":couponCode},
        async : false,
        success:function(data){
            if(data.success=='-1'){
                Dialog.show("兑换码不正确", 2, 2000);
            }else if(data.success=='1'){
                Dialog.show("兑换码已经被使用", 2, 2000);
            }else if(data.success=='2'){
                Dialog.show("兑换码已经失效", 2, 2000);
            }else if(data.success="0"){
                Dialog.show("兑换成功", 1, 2000);
                location.reload();

            }else{
                Dialog.show("服务器出问题了", 2, 2000);
            }


        },error:function(data){
            alert("服务器duang了,你可以休息片刻再回来收藏");
        }
    });
    // e.stopPropagation();
}
