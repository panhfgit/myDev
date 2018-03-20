/**
 * @author pan
 * Created on 2018/3/13 22:06
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
//@ sourceURL=joinExamination.js
(function ($) {

    //定义全局变量，记录答卷信息；
    var examinationInfo = {};

    var userId = getParam("userId");
    var userName = getParam("userName");

    var getExaminationParam = function(){
        var data = {};

        return data;
    };


    var init = function(){

        loadTable("examinationTable");

    };

    //查询
    $("#queryButton").click(function(){

        loadTable("examinationTable",getExaminationParam());
    });

    //参加考试
    $("#joinTestButton").click(function(){
        var selected = GetTableSelected("examinationTable");
        if(null != selected && selected.length == 1){
            var testModel = $("#joinTest");
            testModel.empty();
            examinationInfo = loadExamtionPage(selected[0],"joinTest");
            //添加提交按钮
            testModel.append("<button class='btnn btnn-confirm text-center'  id='confirmExamination'>&nbsp;&nbsp;提交&nbsp;&nbsp;</button>");

            $("#confirmExamination").click(function(){
                if(confirm("确认提交吗？") == true){
                    var stuAnswer = collectAnswer(examinationInfo);
                    if(null != stuAnswer){
                        var result = saveAnswer(stuAnswer);
                        if(result != null && result != ""){
                            loadTable("examinationTable");
                        }
                    }

                }
            });
        }else{
            alert("请选择要参加的考试！");
        }
    });



    init();
})(jQuery);


