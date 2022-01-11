$(function() {

    slickSliderSync($('.slider-single'), $('.slider-nav'), $('.prev[name-category="slider"]'), $('.next[name-category="slider"]'), 4, true);
    slickSliderSync($('.product__img-single'), $('.product__img-nav'), $('.prev[name="images-product"]'), $('.next[name="images-product"]'), 6, false);
    slickSliderSingle($('.slider-single-left'), $('.prev[name-category="slider-left"]'), $('.next[name-category="slider-left"]'));
    slickSliderSingle($('.slider-single-right'), $('.prev[name-category="slider-right"]'), $('.next[name-category="slider-right"]'));


    function slickSliderSync(selectSingle, selectNav, prev, next, num, play) {
        selectSingle.slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            prevArrow: prev,
            nextArrow: next,
            fade: false,
            useTransform: true,
            speed: 400,
            autoplay: play,
            autoplaySpeed: 1500
        });

        selectNav
            .on('init', function(event, slick) {
                selectNav.find('.slick-slide.slick-current').addClass('is-active');
            })
            .slick({
                slidesToShow: num,
                slidesToScroll: num,
                arrows: false,
                dots: false,
                infinite: play,
                focusOnSelect: false,
                // responsive: [{
                //     breakpoint: 1024,
                //     settings: {
                //         slidesToShow: 3,
                //         slidesToScroll: 3,
                //     }
                // }, {
                //     breakpoint: 640,
                //     settings: {
                //         slidesToShow: 2,
                //         slidesToScroll: 2,
                //     }
                // }]
            });

        selectSingle.on('afterChange', function(event, slick, currentSlide, nextSlide) {
            selectNav.slick('slickGoTo', currentSlide);
            var currrentNavSlideElem = '.slick-slide[data-slick-index="' + currentSlide + '"]';
            selectNav.find('.slick-slide.is-active').removeClass('is-active');
            selectNav.find(currrentNavSlideElem).addClass('is-active');
        });

        selectNav.on('click', '.slick-slide', function(event) {
            event.preventDefault();
            var goToSingleSlide = $(this).data('slick-index');
            selectSingle.slick('slickGoTo', goToSingleSlide);
        });
    }


    function slickSliderSingle(selectSingle, prev, next) {
        selectSingle.slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            prevArrow: prev,
            nextArrow: next,
            fade: false,
            useTransform: true,
            speed: 400,
            dots: true,
            autoplay: true,
            autoplaySpeed: 1800
        });
    }
});