




// 搜索
$("#category-search").on("click",function () {
    loadCategoryList();
});
//绑定删除分类的点击事件
$("#dataList").on('click','.category-delete',function () {
    alert("TODO:分类删除功能待完善！")
    var categoryId = $(this).parent().data("id");
    $.ajax({
        url:"/admin/category/query/"+categoryId,
        method: "GET",
        success : function (data) {
            if (data.resultStatu == 'fail'){
                new $.flavr({
                    content: '删除分类将会将此分类的文章移除此分类，您确定要删除吗?',

                    buttons: {
                        primary: {
                            text: '确定', style: 'primary', action: function () {
                                deleteCategory(categoryId);
                            }
                        },
                        success: {
                            text: '取消', style: 'danger', action: function () {

                            }
                        }
                    }
                });
            }else {
                new $.flavr({
                    content: '您确定要删除所选分类吗？？',

                    buttons: {
                        primary: {
                            text: '确定', style: 'primary', action: function () {
                                deleteCategory(categoryId);
                            }
                        },
                        success: {
                            text: '取消', style: 'danger', action: function () {

                            }
                        }
                    }
                });
            }
        }

    });
})
// 删除分类
function deleteCategory(id){
    $.ajax({
        url: "/admin/category/delete/"+id,
        success : function (data) {
            if(data.resultStatu == 'success'){
               initPage();
                autoCloseAlert(data.info,3000);
            }else{
                autoCloseAlert(data.info,3000);
            }
        }
    });
}

// 跳转编辑页
$("#dataList").on('click','.category-edit',function () {
    $.ajax({
        url : '/admin/category/editJump/'+$(this).parent()[0].id,
        method:"GET",
        success  : function(data) {
            $('#editCategoryContent').html(data);
            $('#editCategoryModal').modal('show');
            $('#editCategoryModal').addClass('animated');
            $('#editCategoryModal').addClass('flipInY');
        }
    });
});


// 关闭编辑分类窗口
function closeEditWindow(){
    $('#editCategoryModal').modal('hide');
}

// 关闭新增分类窗口
function closeAddWindow(){
    $('#addCategoryModal').modal('hide');
}


// 编辑分类
function saveEditCategory(){
    if(validateEditCategory()){
        var categoryName = $("#categoryName").val();
        var aliasName = $("#aliasName").val();
        var cid=$("#id").val();
        $.ajax({
            url : '/admin/category/update',
            data : "&cid="+cid+"&name="+encodeURI(categoryName)+"&alias="+encodeURI(aliasName),
            success  : function(data) {
                if(data.resultStatu == 'success'){
                    $('#editCategoryModal').modal('hide');
                    window.location.href = "/admin/category/categoryList";
                    closeEditWindow();
                    autoCloseAlert(data.info,3000);
                }else{
                    autoCloseAlert(data.info,3000);
                }
            }
        });
    }
}

// 保存分类
function saveAddCategory(){
    if(validateAddCategory()){
        var categoryName = $("#categoryName").val();
        var aliasName = $("#aliasName").val();
        var cid=$("#cid").val();
        $.ajax({
        	type:"POST",
            url : '/admin/category/save',
            data : "&name="+encodeURI(categoryName)+"&alias="+encodeURI(aliasName),
            success  : function(data) {
                if(data.resultStatu == 'success'){
                    $('#addCategoryModal').modal('hide');
                    window.location.href = "/admin/category/categoryList";
                    closeAddWindow();
                    autoCloseAlert(data.info,3000);
                }else{
                    autoCloseAlert(data.info,3000);
                }
            }
        });
    }
}

// 校验新增分类输入框
function validateAddCategory(){
    var categoryName = $("#categoryName").val();
    var aliasName = $("#aliasName").val();
    if(!isEmpty(categoryName)){
        if(isSpecialSymbols(categoryName)){
            autoCloseAlert("分类名称不能包含特殊符号",1000);
            return false;
        }
    }else{
        autoCloseAlert("分类名称不能为空",1000);
        return false;
    }
    if(!isEmpty(aliasName)){
        if(isSpecialSymbols(aliasName)){
            autoCloseAlert("分类别名不能包含特殊符号",1000);
            return false;
        }
    }else{
        autoCloseAlert("分类别名不能为空",1000);
        return false;
    }
    return true;
}

// 校验编辑分类输入框
function validateEditCategory(){
    var CategoryName = $("#categoryName").val();
    var aliasName = $("#aliasName").val();
    if(!isEmpty(CategoryName)){
        if(isSpecialSymbols(CategoryName)){
            autoCloseAlert("标签不能包含特殊符号",1000);
            return false;
        }
    }else{
        autoCloseAlert("标签不能为空",1000);
        return false;
    }
    if(!isEmpty(aliasName)){
        if(isSpecialSymbols(aliasName)){
            autoCloseAlert("分类别名不能包含特殊符号",1000);
            return false;
        }
    }else{
        autoCloseAlert("分类别名不能为空",1000);
        return false;
    }

    return true;
}

// 跳转新增分类页面
$("#category-add").on("click",function () {
    $.ajax({
        url : '/admin/category/addJump',
        success  : function(data) {
            $('#addCategoryContent').html(data);
            $('#addCategoryModal').modal('show');
            $('#addCategoryModal').addClass('animated');
            $('#addCategoryModal').addClass('bounceInLeft');
        }
    });
});