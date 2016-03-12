$(document).ready(
    function () {
        $("#classTeamMember").bind({
            click: function () {
                $("#secContext").fadeIn();
                $("#classTeam").fadeIn();
            }
        });
        $("#classStarMember").bind({
            click: function () {
                var stars = $("#classStarMember").data("stars");
                if (stars && stars.length > 0) {
                    if (dataIndex < 0) {
                        dataIndex = stars.length - 1;
                    }
                    $("#classStar").find(".banji-0").html(stars[dataIndex].BriefIntroduction);
                    //$("#classStar").find(".txt").html(stars[dataIndex].txt);
                    //$("#classStar").find(".clear").html(stars[dataIndex].clear);
                    //$("#classStar").find(".bottom").html(stars[dataIndex].bottom);
                    dataIndex++;
                }
                $("#secContext").fadeIn();
                $("#classStar").fadeIn();

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

        initClassMien();
    });

function initClassMien() {
    ajaxGetRequest("ashx/classElegantDemeanour.ashx?classId=1", loadData);
}
function loadData(msg) {
    if (msg) {
        loadClassInfo(msg);
        loadEleDemeanour(msg);
    }
}

function loadClassInfo(msg) {
    $("#classTeacher").html(msg.ClassTeacher);
    var classCadre = msg.ClassCadre;
    var reArray = msg.ClassCadre.split(/,/);
    var LoadCadreCount = 0;
    if (reArray.length > 2) {
        LoadCadreCount = 2;
        classCadre = "";
    }
    $("#classTeamMember").text("");
    $("#classTeam").find(".bangan-main").html("");
    LoadCadreCount = addClassCadreHtml(reArray, "（班长）", LoadCadreCount);
    LoadCadreCount = addClassCadreHtml(reArray, "（副班长）", LoadCadreCount);
    LoadCadreCount = addClassCadreHtml(reArray, "（团支书）", LoadCadreCount);
    $("#classTeam").find(".bangan-main").append(" <br/>");
    $.each(reArray, function (i, item) {
        $("#classTeam").find(".bangan-main").append(" <p>" + item + "</p>");
    });
    loadStars(msg);

}

function loadStars(msg) {
    $("#classStarMember").html("");
    $("#classStarMember").removeData("stars");
    if (msg.Starts) {
        $.each(msg.Starts, function (i, item) {
            if (i == 0) {
                $("#classStarMember").text(item.StudentName);
            }
            else if (i <= 2) {
                $("#classStarMember").text($("#classStarMember").text() + "、" + item.StudentName);
            }
            else {
                $("#classStarMember").text($("#classStarMember").text() + "...");
            }
        });
        $("#classStarMember").data("stars", msg.Starts);
    }
}
function loadEleDemeanour(msg) {
    if (msg.ElegantDemeanours) {
        $(".ban-main").html("");
        $.each(msg.ElegantDemeanours, function (i, item) {
            var $itemDoc =  $('<li> <img src="'+item.EDPic+'" alt="" /><div class="txt hz-hid fr"><p class="p"><strong>'+item.Title+'</strong></p>'
            +'<p>'+item.Content+'</p>'
            +'</div>'
            + '<div class="time fl">' + item.EDTime.Format("yyyy.MM.dd") + '</div></li>');
            $itemDoc.bind({
                click: function () {
                    if (item.ElegantDemeanourId) {
                        //window.open(item.ElegantDemeanourUrl);

                        ajaxGetRequest("ashx/elegantDemeanour.ashx?edId=" + item.ElegantDemeanourId, function (msg) {
                            $("#classMienContext").find(".slide-title").text(item.Title);
                            $("#classMienContext").find("img").attr("src", item.EDPic);
                            $("#classMienContext").find(".txt").text(item.Content);
                            $("#secContext").fadeIn();
                            $("#classMienContext").fadeIn();
                        }, true);

                    }
                }});
            $(".ban-main").append($itemDoc);
        });
    }
}

function addClassCadreHtml(reArray, searchText, LoadCadreCount) {
    var itemText = removeArray(reArray, searchText);
    if (itemText) {
        $("#classTeam").find(".bangan-main").append("  <p>" + itemText + "</p>");

        if (LoadCadreCount == 2) {
            $("#classTeamMember").text(itemText);
        }
        else if (LoadCadreCount == 1) {
            $("#classTeamMember").text($("#classTeamMember").text() + "、"+itemText + "...");

        }
        LoadCadreCount--;
    }
    return LoadCadreCount;
}
function removeArray(arrayValue, seachText) {
    var removeItem = "";
    for (var i = 0; i < arrayValue.length; i++) {
        // 严格比较，即类型与数值必须同时相等。
        if (arrayValue[i].indexOf(seachText)) {
            removeItem = arrayValue[i];
            arrayValue.splice(i, 1);
            break;
        }
    }
    return removeItem;
}
