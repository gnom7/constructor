$(document).ready(function () {
    $.ajaxSetup({
        headers: {'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')}
    });
});

$(document).on('click', '#selectDarkTheme', function () {
    setTheme("bootswatch-darkly");
});

$(document).on('click','#selectBrightTheme',function() {
    setTheme("bootswatch-cosmo");
});

function setTheme(theme) {
    var date = new Date();
    date.setTime(date.getTime()+(14 * 24 * 60 * 60 * 1000));
    var expires = date.toGMTString();
    document.cookie = "theme=" + theme + ";" + "expires=" + expires + ";" + "path=/";
}
