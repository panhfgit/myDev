/**
 * @author pan
 * Created on 2018/3/7 15:50
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
//@ sourceURL=request.js

function getParam(value) {
    var url = decodeURI(location.search);
    var object = {};
    if(url.indexOf("?") != -1)//url中存在问号，也就说有参数。
    {
        var str = url.substr(1); //得到?后面的字符串
        var strs = str.split("&");
        for(var i = 0; i < strs.length; i ++)
        {
            object[strs[i].split("=")[0]]=strs[i].split("=")[1]
        }
    }
    return object[value];
}

function loadScript(url){
    var script = document.createElement ("script") ;
    script.type = "text/javascript";

    if (script.readyState){ //IE
        script.onreadystatechange = function(){
            if (script.readyState == "loaded" || script.readyState == "complete"){
                script.onreadystatechange = null;
            }
        };
    }
    else { //Others
        script.onload;

    }
    script.src = url;
    document.getElementsByTagName("head")[0].appendChild(script);
}

function GetTableSelected(tableId){
    var selects = $('#'+tableId).children('tbody').children('tr.active');
    var ids = new Array();
    selects.each(function(index, el) {
        ids[index]=el.cells[1].innerText;
    });
    return ids;
}
function loadTable(tableId, paraData, selectId){
    var table = $("#"+tableId);
    var url = table.data("url");
    var ischeckbox = false;
    //获取数据项名称
    var fileds = new Array();
    table.children('thead').children('tr').children('th').each(function (index, el) {
        var type = 'Content';
        if ($(this).data('type')) type = $(this).data('type');
        if (type == 'Content') {
            var field = $(this).data('field');
            fileds[index] = field;
        }
        else if (type == 'CheckBox') {
            ischeckbox = true;
        }
    });
    $.ajax({
        url:url,
        async:true,
        type:"POST",
        dataType:"text",
        data:paraData,
        success:function(data){
            //console.info(data);
            data = JSON.parse(data);
            if(data.errorInfo.code=="0000"){
                var result = data.data;
                if(result){
                    //向表格内新增内容
                    var tbody = '';
                    $.each(result, function (index, el) {
                        var tr = "<tr>";
                        if (ischeckbox) {//生成复选按钮
                            //tr+='<td><div class="checker"><span><input class="checkboxes" type="checkbox"></span></div></td>'
                            tr += '<td><input type="checkbox"></td>'
                        }
                        $.each(fileds, function (i, el) {//生成内容
                            if (fileds[i]) {
                                if(result[index][fileds[i]]){
                                    tr += '<td>' + result[index][fileds[i]] + '</td>';
                                }else{
                                    tr += '<td>' + '' + '</td>';
                                }
                            }
                        });
                        tr += "</tr>";
                        tbody += tr;
                    });
                    table.children('tbody').empty();
                    table.children('tbody').append(tbody);//显示数据
                    table.children('tbody').children('tr').click(function (event) {//点击行事件
                        $(this).toggleClass('active');//增加选中效果
                        if (ischeckbox) {
                            $(this).find('input[type="checkbox"]').attr('checked', $(this).hasClass('active'));
                        }//选择复选框
                    });
                }
            }else{
                alert("查询出错,"+data.errorInfo.message);
            }
        },
        error:function(xhr,status,error){
            alert("请求出错");
        }
    });
};

function BuildSelectBox(sel, par) {
    var select = $("#"+sel);
    var url = "/staticData/getStaticDataByType.do";
    $.ajax({
        url:url,
        async:false,
        type:"POST",
        dataType:"text",
        data:{
            "codeType" : par
        },
        success:function(data){
            //console.info(data);
            data = JSON.parse(data);
            if(data.errorInfo.code=="0000"){
                var result = data.data;
                if(result){
                    select.empty();
                    for(var i=0 ; i<result.length ; i++){
                        select.prepend('<option value="' + result[i].Code_value + '">' + result[i].Code_text + '</option>')
                    }
                    select.prepend('<option value="" selected="selected">请选择</option>')
                }
            }else{
                alert("查询出错,"+data.errorInfo.message);
            }
        },
        error:function(xhr,status,error){
            alert("请求出错");
        }
    });

};

function loadExamtionPage(stuExamId,divId){
    var testModel = $("#"+divId);
    var result = {};
    $.ajax({
        url:"/examination/joinExamination.do",
        async:false,
        type:"POST",
        dataType:"text",
        data: {
            "stuExaminationId": stuExamId
        },
        success:function(data){
            //console.info(data);
            data = JSON.parse(data);
            if(data.errorInfo.code=="0000"){
                var stuExamination = data.data;
                //试卷标题
                var examinationTitle = stuExamination.Examination_title;
                //试卷
                var examination = stuExamination.examination;
                //试卷和题目的关系(题目：examSubjectRels[i].Subject)
                var examSubjectRels = examination.examSubjectRels;
                //题目数量
                var singleChoiceNum = 0;
                var multipleChoiceNum = 0;
                var subjectiveProblemNum = 0;

                result.stuExaminationId = stuExamination.Stu_examination_id;

                //添加问卷标题
                testModel.append("<div class='box-header span-lineHeight40 text-center'>"+examinationTitle+"</div>");

                //添加单选题
                testModel.append("<div class='col-mdd-12 span-lineHeight40'>单选题</div><div id='singleChoice'></div>");
                for(var i=0 ; i<examSubjectRels.length ; i++){
                    var subject = examSubjectRels[i].subject;
                    if(subject.Subject_type == "1") {
                        $("#singleChoice").append("<div id='singleChoice" + (++singleChoiceNum) + "' class='col-mdd-12 box-white span-lineHeight40'>" + (singleChoiceNum) + "." + subject.Subject_detail + "</div>");
                        for(var j=0 ; j<subject.choices.length ; j++){
                            $("#singleChoice"+singleChoiceNum).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type='radio' name='singleChoice"+singleChoiceNum+"Option' value='"+subject.choices[j].Choice_id+"'/>"+subject.choices[j].Choice_detail);
                        }
                    }
                }
                result.singleChoiceNum = singleChoiceNum;

                //添加多选题
                testModel.append("<div class='col-mdd-12 span-lineHeight40'>多选题</div><div id='multipleChoice'></div>");
                for(var i=0 ; i<examSubjectRels.length ; i++){
                    var subject = examSubjectRels[i].subject;
                    if(subject.Subject_type == "2") {
                        $("#multipleChoice").append("<div id='multipleChoice" + (++multipleChoiceNum) + "' class='col-mdd-12 box-white span-lineHeight40'>" + (multipleChoiceNum) + "." + subject.Subject_detail + "</div>");
                        for(var j=0 ; j<subject.choices.length ; j++){
                            $("#multipleChoice"+multipleChoiceNum).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='multipleChoice"+multipleChoiceNum+"Option' value='"+subject.choices[j].Choice_id+"'/>"+subject.choices[j].Choice_detail);
                        }
                    }
                }
                result.multipleChoiceNum = multipleChoiceNum;

                //添加主观题
                testModel.append("<div class='col-mdd-12 span-lineHeight40'>开放题</div><div id='subjective'></div>");
                for(var i=0 ; i<examSubjectRels.length ; i++){
                    var subject = examSubjectRels[i].subject;
                    if(subject.Subject_type == 3){
                        $("#subjective").append("<div id='subjective"+ (++subjectiveProblemNum) +"' class='col-mdd-12 box-white span-lineHeight40'>" + subjectiveProblemNum + "." + subject.Subject_detail + "</div>");
                        //开放题预留空间
                        $("#subjective"+subjectiveProblemNum).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='subjective" + subjectiveProblemNum + "Answer' name='" + subject.Subject_id + "' rows='3' cols='100'>");
                    }
                }
                result.subjectiveProblemNum = subjectiveProblemNum;

            }else{
                alert("加载试卷失败,"+data.errorInfo.message);
            }
        },
        error:function(xhr,status,error){
            alert("请求出错");
        }
    });
    return result;
}

function collectAnswer(examinationInfo){
    var result = {};

    var singleChoiceAnswer = new Array();
    for(var i=0 ; i<examinationInfo.singleChoiceNum ; i++){
        if($("input[name='singleChoice"+(i+1)+"Option']:checked").val() != null){
            singleChoiceAnswer[i] = $("input[name='singleChoice"+(i+1)+"Option']:checked").val();
        }else{
            alert("请答完所有题目！");
            window.location.hash = "#singleChoice"+(i+1);
            return;
        }
    }
    result.singleChoiceAnswer = singleChoiceAnswer;

    var multipleChoiceAnswer = new Array();
    for(var i=0 ; i<examinationInfo.multipleChoiceNum ; i++){
        var checkBox = $("input[name='multipleChoice"+(i+1)+"Option']");
        multipleChoiceAnswer[i] = "";
        for(k in checkBox){
            if(checkBox[k].checked){
                multipleChoiceAnswer[i] += checkBox[k].value+","
            }
        }
        if(multipleChoiceAnswer[i] != ""){
            multipleChoiceAnswer[i] = multipleChoiceAnswer[i].slice(0,multipleChoiceAnswer[i].length-1);
        }else{
            alert("请答完所有题目！") == true
            window.location.hash = "#multipleChoice"+(i+1);
            return;
        }
    }
    result.multipleChoiceAnswer = multipleChoiceAnswer;

    var subjectiveAnswer = new Array();
    for(var i=0 ; i<examinationInfo.subjectiveProblemNum ; i++){
        if($("#subjective" + (i+1) +"Answer").val() != ""){
            subjectiveAnswer[i] = $("#subjective" + (i+1) +"Answer").attr("name") + "_" + $("#subjective" + (i+1) +"Answer").val()
        }else{
            alert("请答完所有题目！") == true
            window.location.hash = "#subjective"+(i+1);
            return;
        }
    }
    result.subjectiveAnswer = subjectiveAnswer;

    result.stuExaminationId = examinationInfo.stuExaminationId;
    return result;
}

function saveAnswer(stuAnswer){
    var result = stuAnswer.stuExaminationId;
    $.ajax({
        url:"/examination/saveAnswer.do",
        async:false,
        type:"POST",
        dataType:"text",
        data:stuAnswer,
        success:function(data){
            //console.info(data);
            data = JSON.parse(data);
            if(data.errorInfo.code=="0000"){
                var result = data.data;
                if(result){
                    alert("提交成功");
                }
            }else{
                alert("提交出错,"+data.errorInfo.message);
            }
        },
        error:function(xhr,status,error){
            alert("请求出错");
        }
    });
    return result;
}

function autoGenerateExam(param){
    if(param != null){
        $.ajax({
            url:"/examination/autoGenerateExam.do",
            async:false,
            type:"POST",
            dataType:"text",
            data:{
                classifyInfo: param
            },
            success:function(data){
                //console.info(data);
                data = JSON.parse(data);
                if(data.errorInfo.code=="0000"){
                    var result = data.data;
                    if(result){
                        alert("生成成功");
                    }
                }else{
                    alert("生成出错,"+data.errorInfo.message);
                }
            },
            error:function(xhr,status,error){
                alert("请求出错");
            }
        });
    }
}
