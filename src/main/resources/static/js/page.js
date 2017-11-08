/**
 * 
 */
$(function () {
	    var initPagination = function () {
	        var maxentries = ${page.totalCount}; //总共多少项
	        // 创建分页
	        $("#Pagination").pagination(maxentries, {
	            items_per_page:${page.everyPage}, //每页显示多少项
	            current_page:${page.currentPage-1}, //当前所在页
	            num_display_entries: 4, //主体页数
	            prev_text: "&lt;",
	            next_text: "&gt;",
	            callback: pageselectCallback
	        });
	    }();
	
	    function pageselectCallback(page_id, jq) {

            var a = $(".pagination").find("a");
            $(a).each(function(n) {
            	$(this).addClass("page-number");
            	$(".prev").removeClass("page-number");
            	$(".next").removeClass("page-number");
            });
            
            var span = $(".pagination").find("span");
            $(span).each(function(n) {
            	$(this).addClass("page-number");
            	$(".prev").removeClass("page-number");
            	$(".next").removeClass("page-number");
            });
	    	
	        var cp = page_id + 1;
	        
	        if(cp==1){
	        	$(".prev").remove();
	        }
	        
	        if(cp==Math.ceil(${page.totalCount/page.everyPage})){
	        	$(".next").remove();
	        }
	        
	        if (cp !=${page.currentPage}) {
	            window.location.href = "/index?pn=" + cp;
	        }

	        return false;
	    }
	});