function collapseNavbar() {
    $(".navbar").offset().top > 50 ? $(".navbar-fixed-top").addClass("top-nav-collapse") : $(".navbar-fixed-top").removeClass("top-nav-collapse")
}
$(window).scroll(collapseNavbar), $(document).ready(collapseNavbar), $(".navbar-collapse ul li a").click(function () {
    $(".navbar-collapse").collapse("hide")
}), $(function () {
    $("a.page-scroll").bind("click", function (o) {
        var a = $(this);
        $("html, body").stop().animate({
            scrollTop: $(a.attr("href")).offset().top
        }, 1250, "easeInOutExpo"), o.preventDefault()
    })
}), $(window).scroll(function () {
    $(".slideanim").each(function () {
        $(this).offset().top < $(window).scrollTop() + 800 && $(this).addClass("slide")
    })
}), $(document).scroll(function () {
    var o = $("#about").offset(),
        a = $("#services").offset(),
        t = $("#connect").offset(),
        l = $(this).scrollTop() + 100;
    l >= o.top && l < a.top ? $(".navbar").css("border-top-color", "#3CBE56") : l >= a.top && l < t.top ? $(".navbar").css("border-top-color", "#1192FE") : l >= t.top ? $(".navbar").css("border-top-color", "#DD2D21") : $(".navbar").css("border-top-color", "#fff")
}), $("#terminal-date").text("Last login: " + new Date + " on tty00");