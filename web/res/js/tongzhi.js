$(document).ready(function () {
    $(".back").bind('click',function(){
        $("#classStar9").fadeOut();
        $("#classTeam").fadeOut();
        $("#classMienContext").fadeOut();
        $(".cover").hide();
    })
});
//文章查看详情
function noteDetail(id){
    var url="/Article/findById.html?articleId="+id;
    ajax(url);
}
 function ajax(url){
     $.ajax({
         url : url,
         type : 'post',
         dataType : 'json',
         success : function (res) {
             var title=res.title;
             var content=res.content;
            // if(title.length>10){
               //  title=title.substring(0,10)+"...";
             //}
             $("#slideTitle").html(title);
             $("#slideContent").html(content);
             $("#classMienContext").fadeIn();
             $("#classMienContext").show();
         },error:function(){

         }
     });
 }
   /* function paginationAjax(url) {
        var type=$("#type").val();
        $.ajax({
            url : url,
            type : 'POST',
            timeout : '1000',
            async : false,
            dataType : 'json',
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
                type:type
            },
            success : function (res) {
                console.log(res)
                var datas=res.Rows;
                alert(datas);
                var html='';
                var count=datas.length;
                alert(count);
                for(var i=0;i<datas.length;i++){
                       var time= datas[i].time;
                       var time1=time.substring(0,3);
                       var time2=time.substring(5,6);
                       var time3=time.substring(8,9);
                        var title=datas[i],title;
                        html+="<li><i>";
                        html+=time+"</br>";
                        html+="<strong>";
                        html+=time2;
                        html+="/";
                        html+=time3;
                        html+="</strong>";
                        html+="</i>";
                        html+="<a>"+title+"</a>";
                        html+="</li>";
                }
                $("#noteList").append(html);
                pageNo++;
                if(pageNo*pageSize>=count){
                    alert("隐藏");
                    $(".load-more").hide();
                }
            },error:function(error){
                alert(error);
            }
        });
    };
*/
