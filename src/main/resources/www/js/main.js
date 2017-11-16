"use strict";

// Collapse Nav

$(window).scroll(collapseNavbar);
$(document).ready(collapseNavbar);

function collapseNavbar() {
    if ($(".navbar").offset().top > 50) {
        $(".navbar-fixed-top").addClass("top-nav-collapse");
    } else {
        $(".navbar-fixed-top").removeClass("top-nav-collapse");
    }
};

// Closes the Responsive Menu on Menu Item Click

$('.navbar-collapse ul li a').click(function () {
    $(".navbar-collapse").collapse('hide');
});

// jQuery Easing

$(function () {
    $('a.page-scroll').bind('click', function (event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1250, 'easeInOutExpo');
        event.preventDefault();

    });
});

// Slide Animation

$(window).scroll(function () {
    $(".slideanim").each(function () {
        var pos = $(this).offset().top;

        var winTop = $(window).scrollTop();
        if (pos < winTop + 800) {
            $(this).addClass("slide");
        }
    });
});

// Header Color Scroll

$(window).scroll(function () {
    var aboutScroll = $('#about').position().top;
    var serviceScroll = $('#services').position().top;
    var learnScroll = $('#learn').position().top;
    var connectScroll = $('#connect').position().top;
    var y = $(this).scrollTop() + 100;

    if (y >= aboutScroll && y < serviceScroll) {
        $(".navbar").css("border-top-color", "#3CBE56");
    } else if (y >= serviceScroll && y < learnScroll) {
        $(".navbar").css("border-top-color", "#1192FE");
    } else if (y >= learnScroll && y < connectScroll) {
        $(".navbar").css("border-top-color", "#ff6000");
    } else if (y >= connectScroll) {
        $(".navbar").css("border-top-color", "#DD2D21")
    }
    else {
        $(".navbar").css("border-top-color", "#fff");
    }
});

// Terminal Date

$("#terminal-date").text("Last login: " + new Date() + " on ttys000");

// Blog Post Feed
$.getJSON('https://blog.nullable.software/feeds/posts/default/?alt=json' + '&callback=?', function (data) {
    // for loop gets three most recent posts
    for (var i = 0; i < 3; i++) {
        $('#js-blog-container').append('<div class="blog-post">' +
            '<h4 class="blog-title">' + data.feed.entry[i].title.$t + '</h4>' +
            '<a class="blog-url main-button" href="' + data.feed.entry[i].link[2].href + '">' + 'View Post' + '</a>' +
            '</div>' + '<br>')
    }
});