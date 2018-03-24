/**
 * @author pan
 * Created on 2018/3/21 10:24
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
//@ sourceURL=subjectManagerDetail.js
(function ($) {

    var examinationId = $("#innerHtml").attr("loadData");

    var getChangeParam = function(){
        var retData = {};
        retData.examinationId = $("#examinationTitle").val();
        retData.examinationType = $("#examinationType").val();
        return retData;
    }

    var init = function(){

        BuildSelectBox("examinationType","examinationType");

        var queryData={
            "examinationId":examinationId
        };
        loadTable("SubjectTable",queryData);

    };

    //查询
    $("#addSubject").click(function(){
        window.open("subjectModify.html?isAdd=1&examinationId="+examinationId, "modifyWindow", "height=600, width=510, top=20, left=450, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
    });
    //修改
    $(document).on("click","button[id^=modify_]",function (){
        var subjectId = this.getAttribute("id").split("_")[1];
        window.open("subjectModify.html?subjectId="+subjectId+"&examinationId="+examinationId, "modifyWindow", "height=600, width=510, top=20, left=450, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
    });

    init();


    })(jQuery);


