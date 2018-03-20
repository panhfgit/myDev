/**
 * @author pan
 * Created on 2018/3/7 17:23
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
//@ sourceURL=scienceManager.js
(function ($) {

    var userId = getParam("userId");
    var userName = getParam("userName");

    var getScienceParam = function(){
        var data = {};
        data.firstScience = $("#firstScience").val();
        data.secondScience = $("#secondScience").val();
        data.thirdScience = $("#thirdScience").val();
        return data;
    };


    var init = function(){
        BuildSelectBox("firstScience","firstScience");
        BuildSelectBox("secondScience","secondScience");
        BuildSelectBox("thirdScience","thirdScience");
        loadTable("scienceTable");

    };

    $("#deleteButton").click(function(){
        var selected = GetTableSelected("scienceTable");
        if(null != selected && selected.length>0){
            $.ajax({
                url:"/science/deleteData.do",
                async:false,
                type:"POST",
                dataType:"text",
                data: {
                    "deleteIds": selected
                },
                success:function(data){
                    //console.info(data);
                    data = JSON.parse(data);
                    if(data.errorInfo.code=="0000"){
                        alert("删除成功！");
                        loadTable("scienceTable");
                    }else{
                        alert("删除出错,"+data.errorInfo.message);
                    }
                },
                error:function(xhr,status,error){
                    alert("请求出错");
                }
            });
        }else{
            alert("请选中要删除的数据！");
        }
    });

    //查询
    $("#queryButton").click(function(){

        loadTable("scienceTable",getScienceParam());
    });

    //插入
    $("#addButton").click(function(){
        $.ajax({
            url:"/science/addData.do",
            async:false,
            type:"POST",
            dataType:"text",
            data: {

            },
            success:function(data){
                //console.info(data);
                data = JSON.parse(data);
                if(data.errorInfo.code=="0000"){
                    alert("新增数据成功！");
                    loadTable("scienceTable");
                }else{
                    alert("新增出错,"+data.errorInfo.message);
                }
            },
            error:function(xhr,status,error){
                alert("请求出错");
            }
        });
    });


    init();
})(jQuery);

