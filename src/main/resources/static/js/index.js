
/*初始化文章分页信息*/
$(function () {
    $('body').addClass('loaded');
            $.ajax({
                type: 'GET',
                url: '/pager/articles/load',
                data:pager,
                success: function (data){
                    pager = data;
                    $("#pagination").data("type","article");
                    if (pager.totalCount > 0){
                        initPage(null);
                    }
                }
        });

})




