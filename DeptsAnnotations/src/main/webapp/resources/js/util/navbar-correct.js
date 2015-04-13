(function($, undefined){

    $(window).on('resize load', function() {
        $('body').css({"padding-top": $(".navbar").height() + "px"});
    });

})(jQuery);
