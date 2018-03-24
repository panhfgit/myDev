/**
 * @author pan
 * Created on 2018/3/18 22:16
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
//@ sourceURL=autoGenerateExam.js
(function ($) {
    //初始分类数量
    var classifyNum = 0;

    var getParam = function(){
        var retData = {};
        var classifyInfo = [];
        var examTitle = $("#examTitle").val();
        var examType = $("#examinationType").val();
        if(examTitle == "" || examTitle == ""){
            alert("请按要求填写相关信息");
            return;
        }
        for(var i=0 ; i<classifyNum ; i++){
            var scienceId = $("#classifyDetail_"+(i+1)).val();
            var singleChoiceNum = $("#singleChoiceNum_" + (i+1)).val();
            var multipleChoiceNum = $("#multipleChoiceNum_" + (i+1)).val();
            var subjectiveNum = $("#subjectiveNum_" + (i+1)).val();
            if(scienceId != "" && singleChoiceNum != "" && /^[0-9]\d*$/.test(singleChoiceNum)
                && multipleChoiceNum != "" && /^[0-9]\d*$/.test(multipleChoiceNum)
                && subjectiveNum != "" && /^[0-9]\d*$/.test(subjectiveNum)){
                classifyInfo[i] = scienceId + "_" + singleChoiceNum + "_" + multipleChoiceNum + "_" +subjectiveNum;
            }else{
                alert("请按要求填写相关信息");
                return;
            }
        }
        retData.examTitle = examTitle;
        retData.examType = examType;
        retData.classifyInfo = classifyInfo;
        return retData;
    }

    var addModel = function(){

        var addModel = $("#classifyModel");

        addModel.append("<ul id='classifyUl_" + (++classifyNum) + "'> " +
            "<li class='col-mdd-6'> " +
            "<span class='col-mdd-4 span-lineHeight40 text-right'>分类级别：</span> " +
            "<span class='col-mdd-8'> " +
            "<select id='classifyType_" + classifyNum + "' class='mySelect' /> " +
            "</span> " +
            "</li> " +
            "<li class='col-mdd-6'> " +
            "<span class='col-mdd-4 span-lineHeight40 text-right'>类别：</span> " +
            "<span class='col-mdd-8'> " +
            "<select id='classifyDetail_" + classifyNum + "' class='mySelect' /> " +
            "</span> " +
            "</li> " +
            "<li class='col-mdd-4'> " +
            "<span class='col-mdd-4 span-lineHeight40 text-right'>单选题数量：</span> " +
            "<span class='col-mdd-8'> " +
            "<input type='number' id='singleChoiceNum_" + classifyNum + "' class='mySelect' /> " +
            "</span> " +
            "</li> " +
            "</li> " +
            "<li class='col-mdd-4'> " +
            "<span class='col-mdd-4 span-lineHeight40 text-right'>多选题数量：</span> " +
            "<span class='col-mdd-8'> " +
            "<input type='number' id='multipleChoiceNum_" + classifyNum + "' class='mySelect' /> " +
            "</span> " +
            "</li> " +
            "</li> " +
            "<li class='col-mdd-4'> " +
            "<span class='col-mdd-4 span-lineHeight40 text-right'>主观题数量：</span> " +
            "<span class='col-mdd-8'> " +
            "<input type='number' id='subjectiveNum_" + classifyNum + "' class='mySelect' /> " +
            "</span> " +
            "</li> " +
            "<div class='clearfix'></div> " +
            "</ul>");

        BuildSelectBox("classifyType_" + classifyNum,"classifyType");

        $("select[id^=classifyType_]").bind("change",function () {
            if (this.value== "10001" && this.id.split("_")[1]){
                BuildSelectBox("classifyDetail_" + this.id.split("_")[1],"firstScience");
            }
            else if(this.value== "10002"){
                BuildSelectBox("classifyDetail_" + this.id.split("_")[1],"secondScience");
            }
            else if(this.value== "10003"){
                BuildSelectBox("classifyDetail_" + this.id.split("_")[1],"thirdScience");
            }
        });
    };

    var delModel = function(){
        if(classifyNum>1){
            $("#classifyUl_"+classifyNum).remove();
            classifyNum--;
        }else{
            alert("分类信息不能少于1条！");
        }
    }
    
    var init = function(){
        BuildSelectBox("examinationType","examinationType");
        addModel();
    };

    $("#addList").click(function(){
        addModel();
    });

    $("#deleteList").click(function(){
        delModel();
    });

    $("#autoGenerateButton").click(function(){
        autoGenerateExam(getParam());
    });


init();
})(jQuery);

