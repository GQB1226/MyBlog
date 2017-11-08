/**
 * 
 */
 $(document).ready(function () {
        var tocSelector = '.post-toc';
        var $tocSelector = $(tocSelector);
        var activeCurrentSelector = '.active-current';
        $tocSelector
                .on('activate.bs.scrollspy', function () {
                    var $currentActiveElement = $(tocSelector + ' .active').last();
                    removeCurrentActiveClass();
                    $currentActiveElement.addClass('active-current');
                    $tocSelector[0].scrollTop = $currentActiveElement.position().top;
                })
                .on('clear.bs.scrollspy', function () {
                    removeCurrentActiveClass();
                });

        function removeCurrentActiveClass() {
            $(tocSelector + ' ' + activeCurrentSelector)
                    .removeClass(activeCurrentSelector.substring(1));
        }

        function processTOC() {
            getTOCMaxHeight();
            toggleTOCOverflowIndicators();
        }

        function getTOCMaxHeight() {
            var height = $('.sidebar').height() -
                    $tocSelector.position().top -
                    $('.post-toc-indicator-bottom').height();
            $tocSelector.css('height', height);
            return height;
        }

        function toggleTOCOverflowIndicators() {
            tocOverflowIndicator(
                    '.post-toc-indicator-top',
                    $tocSelector.scrollTop() > 0 ? 'show' : 'hide'
            );
            tocOverflowIndicator(
                    '.post-toc-indicator-bottom',
                    $tocSelector.scrollTop() >= $tocSelector.find('ol').height() - $tocSelector.height() ? 'hide' : 'show'
            )
        }

        $(document).on('sidebar.motion.complete', function () {
            processTOC();
        });

        $('body').scrollspy({target: tocSelector});
        $(window).on('resize', function () {
            if ($('.sidebar').hasClass('sidebar-active')) {
                processTOC();
            }
        });

        onScroll($tocSelector);
        function onScroll(element) {
            element.on('mousewheel DOMMouseScroll', function (event) {
                var oe = event.originalEvent;
                var delta = oe.wheelDelta || -oe.detail;

                this.scrollTop += ( delta < 0 ? 1 : -1 ) * 30;
                event.preventDefault();

                toggleTOCOverflowIndicators();
            });
        }

        function tocOverflowIndicator(indicator, action) {
            var $indicator = $(indicator);
            var opacity = action === 'show' ? 1 : 0;
            $indicator.velocity ?
                    $indicator.velocity('stop').velocity({
                        opacity: opacity
                    }, {duration: 100}) :
                    $indicator.stop().animate({
                        opacity: opacity
                    }, 100);
        }

    });