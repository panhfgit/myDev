/**
 * @author pan
 * Created on 2018/3/21 10:24
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
//@ sourceURL=subjectManager.js

(function ($) {

    var getExaminationParam = function(){
        var retData = {};
        retData.examinationId = $("#examinationId").val();
        retData.examinationType = $("#examinationType").val();
        retData.createDate = $("#createDate").val();
        return retData;
    }

    var init = function(){

        BuildSelectBox("examinationType","examinationType");

        //执行一个laydate实例
        laydate.render({
            elem: '#createDate' //指定元素
        });

        loadTable("examinationTable");
    };

    //查询
    $("#queryButton").click(function(){

        loadTable("examinationTable",getExaminationParam());
    });

    //修改
    $("#updateButton").click(function(){
        var selected = GetTableSelected("examinationTable");
        if(null != selected && selected.length==1){
            $("#innerHtml").attr("loadData",selected[0]);
            $("#innerHtml").load("/page/user/subjectManagerDetail.html");
        }else{
            alert("请选中要修改的试卷！");
        }

    });

    init();
})(jQuery);

