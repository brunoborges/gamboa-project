/* <![CDATA[ */
	// et_switcher plugin v2
	(function($)
	{
		$.fn.et_shortcodes_switcher = function(options)
		{
			var defaults =
			{
			   slides: '>div',
			   activeClass: 'active',
			   linksNav: '',
			   findParent: true, //use parent elements to define active states
			   lengthElement: 'li', //parent element, used only if findParent is set to true
			   useArrows: false,
			   arrowLeft: 'a#prev-arrow',
			   arrowRight: 'a#next-arrow',
			   auto: false,
			   autoSpeed: 5000,
			   slidePadding: '',
			   pauseOnHover: true,
			   fx: 'fade',
			   sliderType: ''
			};

			var options = $.extend(defaults, options);

			return this.each(function()
			{
								
				var slidesContainer = jQuery(this).parent().css('position','relative'),
					$slides = jQuery(this).css({'overflow':'hidden','position':'relative'}),
					$slide = $slides.find(options.slides).css({'opacity':'1','position':'absolute','top':'0px','left':'0px','display':'none'}),
					slidesNum = $slide.length,
					zIndex = slidesNum,
					currentPosition = 1,
					slideHeight = 0,
					$activeSlide,
					$nextSlide;
				
				if (options.fx === 'slide') {
					$slide.css({'opacity':'0','position':'absolute','top':'0px','left':'0px','display':'block'});
				} else {
					$slide.filter(':first').css({'display':'block'});
				}
				
				if (options.slidePadding != '') $slide.css('padding',options.slidePadding);
				
				$slide.each(function(){
					jQuery(this).css('z-index',zIndex).addClass('clearfix');
					if (options.fx === 'slide') zIndex--;
					
					slideH = jQuery(this).innerHeight();
					if (slideH > slideHeight) slideHeight = slideH;
				});
				
				//check left tabs container height
				if ( slidesContainer.hasClass('tabs-left') ) {
					var leftTabsHeight = slidesContainer.find('ul.et-tabs-control').innerHeight();
					if ( leftTabsHeight > slideHeight ) slideHeight = leftTabsHeight;
				}
				
				slidesContainer.find('.et-learn-more').not('.et-open').find('.learn-more-content').css({'display':'none','visibility': 'visible'});
				
				if ( jQuery.browser.msie ) {
					if ( slidesContainer.hasClass('et-simple-slider') ) $slides.css('height', slideHeight+10);
					else $slides.css('height', slideHeight+40);
				} else {
					$slides.css('height', slideHeight);
				}
				$slides.css('width', $slides.width());
								
				var slideWidth = $slide.width(),
					slideOuterWidth = $slide.outerWidth();
				
				if ( jQuery.browser.msie && !slidesContainer.hasClass('et-simple-slider') ) {
					$slide.css('width',slideWidth-22);
				} else {
					$slide.css('width',slideWidth);
				}
				
				$slide.filter(':first').css('opacity','1');
				
				if (options.sliderType != '') {
					if (options.sliderType === 'images') {
						controllersHtml = '<div class="controllers-wrapper"><div class="controllers"><a href="#" class="left-arrow">Previous</a>';
						for ($i=1; $i<=slidesNum; $i++) {
							controllersHtml += '<a class="switch" href="#">'+$i+'</a>';
						}
						controllersHtml += '<a href="#" class="right-arrow">Next</a></div><div class="controllers-right"></div></div>';		
						$controllersWrap = jQuery(controllersHtml).prependTo($slides.parent());
						jQuery('.controllers-wrapper .controllers').css('width', 65 + 18*slidesNum);
					}
					
					var etimage_width = $slide.width();
		
					slidesContainer.css({'width':etimage_width});
					$slides.css({'width':etimage_width});
										
					if (options.sliderType === 'images') {
						slidesContainer.css({'height':$slide.height()});
						$slides.css({'height':$slide.height()});
						
						var controllers_width = $controllersWrap.width(),
						leftPosition = Math.round((etimage_width - controllers_width) / 2);
					
						$controllersWrap.css({left: leftPosition});
					}	
				}
				
				
				if (options.linksNav != '') {
					var linkSwitcher = jQuery(options.linksNav);
					
					var linkSwitcherTab = '';
					if (options.findParent) linkSwitcherTab = linkSwitcher.parent();
					else linkSwitcherTab = linkSwitcher;
					
					if (!linkSwitcherTab.filter('.active').length) linkSwitcherTab.filter(':first').addClass('active');
									
					linkSwitcher.click(function(){
						
						var targetElement;

						if (options.findParent) targetElement = jQuery(this).parent();
						else targetElement = jQuery(this);
						
						var orderNum = targetElement.prevAll(options.lengthElement).length+1;
						
						if (orderNum > currentPosition) gotoSlide(orderNum, 1);
						else gotoSlide(orderNum, -1); 
						
						return false;
					});
				}
				
				
				if (options.useArrows) {
					var $right_arrow = jQuery(options.arrowRight),
						$left_arrow = jQuery(options.arrowLeft);
										
					$right_arrow.click(function(){				
						if (currentPosition === slidesNum) 
							gotoSlide(1,1);
						else 
							gotoSlide(currentPosition+1),1;
						
						if (options.linksNav != '') changeTab();
												
						return false;
					});
					
					$left_arrow.click(function(){
						if (currentPosition === 1)
							gotoSlide(slidesNum,-1);
						else 
							gotoSlide(currentPosition-1,-1);
						
						if (options.linksNav != '') changeTab();
						
						return false;
					});
					
				}
				
								
				function changeTab(){
					if (linkSwitcherTab != '') { 
						linkSwitcherTab.siblings().removeClass('active');
						linkSwitcherTab.filter(':eq('+(currentPosition-1)+')').addClass('active');
					}
				}
				
				function gotoSlide(slideNumber,dir){
					if ($slide.filter(':animated').length) return;
				
					$slide.css('opacity','0');
																		
					$activeSlide = $slide.filter(':eq('+(currentPosition-1)+')').css('opacity','1');
									
					if (currentPosition === slideNumber) return;
									
					$nextSlide = $slide.filter(':eq('+(slideNumber-1)+')').css('opacity','1');
									
					if ((currentPosition > slideNumber || currentPosition === 1) && (dir === -1)) {
						if (options.fx === 'slide') slideBack(500);
						if (options.fx === 'fade') slideFade(500);
					} else {
						if (options.fx === 'slide') slideForward(500);
						if (options.fx === 'fade') slideFade(500);
					}
					
					currentPosition = $nextSlide.prevAll().length + 1;
					
					if (options.linksNav != '') changeTab();
					
					if (typeof interval_shortcodes != 'undefined' && options.auto) {
						clearInterval(interval_shortcodes);
						auto_rotate();
					}
										
					return false;
				}
				
				
				if (options.auto) {
					auto_rotate();
					var pauseSlider = false;
				}
				
				if (options.pauseOnHover) { 				
					slidesContainer.hover(function(){
						pauseSlider = true;
					},function(){
						pauseSlider = false;
					});
				}
				
				function auto_rotate(){
					
					interval_shortcodes = setInterval(function(){
						if (!pauseSlider) { 
							if (currentPosition === slidesNum) 
								gotoSlide(1,1);
							else 
								gotoSlide(currentPosition+1),1;
							
							if (options.linksNav != '') changeTab();
						}
					},options.autoSpeed);
					
				}
				
				function slideForward(speed){
					$nextSlide.css('left',slideOuterWidth+'px');
					$activeSlide.animate({left: '-'+slideOuterWidth},speed);
					$nextSlide.animate({left: 0},speed);
				}
				
				function slideBack(speed){
					$nextSlide.css('left','-'+slideOuterWidth+'px');
					$activeSlide.animate({left: slideOuterWidth},speed);
					$nextSlide.animate({left: 0},speed);
				}
				
				function slideFade(speed){					
					$activeSlide.css({zIndex: slidesNum}).fadeOut(700);
					$nextSlide.css({zIndex: (slidesNum+1)}).fadeIn(700);
				}
				
			});
		} 
	})(jQuery);
	// end et_switcher plugin v2
		
	/////// Shortcodes Javascript ///////
	(function($){
		$et_tooltip = $('.et-tooltip');
		$et_tooltip.live('mouseover mouseout', function(event){
			if (event.type == 'mouseover') {
				$(this).find('.et-tooltip-box').animate({ opacity: 'show', bottom: '25px' }, 300);
			} else {
				$(this).find('.et-tooltip-box').animate({ opacity: 'hide', bottom: '35px' }, 300);
			}
		});
		// learn more
		$et_learn_more = $('.et-learn-more .heading-more');
		$et_learn_more.live('click', function() {
			if ( $(this).hasClass('open') ) 
				$(this).removeClass('open');
			else 
				$(this).addClass('open');
			
			$(this).parent('.et-learn-more').find('.learn-more-content').animate({ opacity: 'toggle', height: 'toggle' }, 300);
		});
	})(jQuery);
/* ]]> */