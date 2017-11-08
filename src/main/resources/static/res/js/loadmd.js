/**
 * 
 */
$(function() {
        var testEditormdView;
        var arr=location.href.split('/').pop().split('.'); 
        $.get("/blog/url/"+arr[0], function(markdown) {
        	
            testEditormdView = editormd.markdownToHTML("test-editormd-view", {
                markdown        : markdown ,//+ "\r\n" + $("#append-test").text(),
                //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                htmlDecode      : "style,script,iframe"  // you can filter tags decode
            });
            var h3 = $(".markdown-body").find("h3");
            $(h3).each(function(n) {
                var tocId = $(this).attr("id");
                var tocContent = $(this).text();           
                var tmpNav = '<li class="nav-item nav-level-2"><a class="nav-link" href="#'+tocId+'"><span class="nav-number">'+(n+1)+'.</span><span class="nav-text">'+tocContent+'</span></a></li>';
                $(".post-toc-content").children(".nav").append(tmpNav);

            });
        });

    });