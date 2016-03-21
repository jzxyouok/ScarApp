$(document).ready(
    function () {
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
        $(".nav").children().bind({
            click: function (i) {
                $("#navHeard").children().removeClass("current");
                $(this).addClass("current");
                var url = $(this).attr("url");
                var type=$(this).attr("id");
                if (url) {
                    window.location.href = window.location.protocol+"//" + window.location.host + url;
                }
            }
        });
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



