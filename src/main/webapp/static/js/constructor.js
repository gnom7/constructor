//register form
$(".name").focus(function () {
    $(".name-help").slideDown(500);
}).blur(function () {
    $(".name-help").slideUp(500);
});

$(".email").focus(function () {
    $(".email-help").slideDown(500);
}).blur(function () {
    $(".email-help").slideUp(500);
});


$(document).ready(function () {
    $.ajaxSetup({
        headers: {'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')}
    });
});

