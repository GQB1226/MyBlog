
/*
 *   Copyright 2016-2017. the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

/*初始化文章分页信息*/
$(function () {
    var createTime = $("#main").data("id");
    $('body').addClass('loaded');
    $.ajax({
        type: 'GET',
        url: '/pager/archive/'+createTime,
        data:pager,
        success: function (data){
            pager = data;
            $("#pagination").data("type","createTime");
            if (pager.totalCount > 0){
                initPage(createTime);
            }
        }
    });

})




