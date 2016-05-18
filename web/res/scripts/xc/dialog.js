var Dialog = new function () {
    this.show = function (b, l, f) {
        this.hide();
        var g = l || 0;
        if (g > 3) {
            g = 0;
        }
        var m = f || 5000;
        var e = new Array();
        e[0] = "../../../res/images/book/info.png";
        e[1] = "../../../res/images/book/success.png";
        e[2] = "../../../res/images/book/error.png";
        e[3] = "../../../res/images/book/ajax-loader.gif";
        var a = document.createElement("div");
        a.id = "div_tip_1";
        a.className = "dialog-div-box";
        var d =
            '<div class="dialog-div"><div class="u-guodu-box"><div><table width="100%" cellpadding="0" cellspacing="0" border="0" ><tr><td  align="center"><img src="' +
            e[g] + '"></td></tr><tr><td align="center" style="font-size:14px;font-weight: blod;line-height:30px">' +
            b + "</td></tr></table></div></div></div>";
        a.innerHTML = d;
        document.body.appendChild(a);
        var c = document.documentElement.scrollTop;
        var k = $(window).width();
        var i = $(window).height();
        $(".dialog-div").css("display", "block");
        $(".dialog-div").css("top", c + "px");
        var h = $(".dialog-div").width();
        var j = $(".dialog-div").height();
        $(".dialog-div").css("left", (k / 2 - h / 2) + "px");
        $(".dialog-div").css("top", (i / 2 - j / 2) + "px");
        if (m > 0) {
            setTimeout(function () {
                a.parentNode.removeChild(a)
            }, m);
        }
        return a;
    };
    this.hide = function () {
        var a = document.getElementById("div_tip_1");
        if (a !== null || typeof (boj) !== "undefined") {
            a.parentNode.removeChild(a);
        }
    }
};