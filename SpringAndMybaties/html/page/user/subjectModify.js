/**
 * @author pan
 * Created on 2018/3/24 10:34
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
(function($){
    var examinationId = getParam("examinationId");
    var subjectId = getParam("subjectId");
    var isAdd = getParam("isAdd") ? true : false;

    var choiceNum = 0;

    var initData = function(){
        $.ajax({
            url:"/examination/querySubject4Update.do",
            async:false,
            type:"POST",
            dataType:"text",
            data:{
                "examinationId":examinationId,
                "subjectId":subjectId
            },
            success:function(data){
                //console.info(data);
                data = JSON.parse(data);
                if(data.errorInfo.code=="0000"){
                    var result = data.data;
                    if(result){
                        //加载公共参数
                        $("#subjectType").val(result.subject.Subject_type);
                        $("#subjectDetail").val(result.subject.Subject_detail);
                        var scienceType = result.science.Science_type;
                        $("#scienceType").val(scienceType);
                        if(scienceType == 1){
                            BuildSelectBox("scienceDetail","firstScience");
                        }else if(scienceType== 2){
                            BuildSelectBox("scienceDetail","secondScience");
                        } else if(scienceType== 3){
                            BuildSelectBox("scienceDetail","thirdScience");
                        }
                        $("#scienceDetail").val(result.science.Science_id);

                    }
                }else{
                    alert("数据加载出错,"+data.errorInfo.message);
                }
            },
            error:function(xhr,status,error){
                alert("请求出错");
            }
        });
    };

    var addChoiceModel = function(){
        if($("#subjectType").val() == "1"){
            $("#choiceDiv").append("<ul id='choiceUl" + (++choiceNum) + "'><li class='col-mdd-12'>" +
                "<span class='col-mdd-4 span-lineHeight40 text-right'>选项" + choiceNum + "：</span>" +
                "<span class='col-mdd-8'>" +
                "<textarea id='choiceDetail_" + choiceNum + "' cols='32'></textarea>"+
                "<input type='radio' name='singleChoiceOption' value='choice"+choiceNum+"'></input>"+
                "</span></li></ul>");
        }else{
            $("#choiceDiv").append("<ul id='choiceUl" + (++choiceNum) + "'><li class='col-mdd-12'>" +
                "<span class='col-mdd-4 span-lineHeight40 text-right'>选项" + choiceNum + "：</span>" +
                "<span class='col-mdd-8'>" +
                "<textarea id='choiceDetail_" + choiceNum + "' cols='32'></textarea>"+
                "<input type='checkbox' name='singleChoiceOption' value='choice"+choiceNum+"'></input>"+
                "</span></li></ul>");
        }

    };

    var delChoiceModel = function(){
        if(choiceNum>2){
            $("#choiceUl"+choiceNum).remove();
            choiceNum--;
        }else{
            alert("选项不能少于2条");
        }
    };

    $("#addModel").click(function(){
            addChoiceModel();
    });

    $("#delModel").click(function(){
            delChoiceModel();
    });

    $("#cancelButton").click(function(){
        window.close();
    });

    var init = function(){
        //隐藏按钮
        $("#addModel").hide();
        $("#delModel").hide();
        $("#commitNote").hide();
        //下拉选加载
        BuildSelectBox("subjectType","subjectType");
        BuildSelectBox("scienceType","scienceType");
        $("#scienceType").bind("change",function () {
            if (this.value== "1"){
                BuildSelectBox("scienceDetail","firstScience");
            }
            else if(this.value== "2"){
                BuildSelectBox("scienceDetail","secondScience");
            }
            else if(this.value== "3"){
                BuildSelectBox("scienceDetail","thirdScience");
            }
        });

        //选择题添加选项部分
        $("#subjectType").bind("change",function () {
            $("#choiceDiv").empty();
            choiceNum = 0;
            $("#addModel").hide();
            $("#delModel").hide();
            $("#commitNote").hide();
            if (this.value == "1" || this.value == "2"){
                for(var i=0 ; i<4 ; i++){
                    addChoiceModel();
                }
                $("#addModel").show();
                $("#delModel").show();
                $("#commitNote").show();
            }
        });

        //恢复数据
        if(!isAdd){
            initData();
        }

    };

    init();
})(jQuery);
