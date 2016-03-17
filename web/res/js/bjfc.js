var dataIndex = 0;
$(document).ready(
    function () {
        $("#classTeamMember").bind({
            click: function () {
                $("#secContext").fadeIn();
                $("#classTeam").fadeIn();
                //$("#classTeam").show();
            }
        });
        $("#classStarMember").bind('click',function(){
            $("#classStar9").show();
        });

        $("#classStar").find(".prev").bind({
            click: function () {
                var stars=$(".banji-1");
                if (stars && stars.length > 0) {
                    if (dataIndex <= 0) {
                        dataIndex = stars.length - 1;
                    }else{
                        dataIndex--;
                    }
                    $("#classStar").find("#bjxmx").html($("#classStar").find("#banji-"+dataIndex).html());
                }
            }
        });
        $("#classStar").find(".next").bind({
            click: function () {
                var stars=$(".banji-1");
                if (stars && stars.length > 0) {
                    if (dataIndex >= stars.length - 1) {
                        dataIndex = 0;
                    }else{
                        dataIndex++;
                    }
                    $("#classStar").find("#bjxmx").html($("#classStar").find("#banji-"+dataIndex).html());

                }
            }
        });
        $("#secContext").bind({
            click: function () {
                $("#secContext").fadeOut();
                $("#classStar").fadeOut();
                //$("#secContext").fadeOut();
                $("#classTeam").fadeOut();
                $("#classMienContext").fadeOut();
            }
        });
        $(".back").bind('click',function(){
            $("#classStar9").fadeOut();
            $("#classTeam").fadeOut();
            $("#classMienContext").fadeOut();
            $(".cover").hide();
        })
       // initClassMien();
    });
function showStar(index){
    $("#classStar9").show();
    $("#classStar").find("#bjxmx").html($("#classStar").find("#banji-"+index).html());
}

