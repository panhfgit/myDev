/**
 * @author pan
 * Created on 2018/3/13 21:55
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
var studentMenuModel = studentMenuModel || {};
(function ($) {

    var userId = getParam("userId");
    var userName = getParam("userName");

    var init = function(){
        $("#user").html(userName);

    }

    $("#exit").click(function(){
        window.location.href = "/page/login.html";
        window.event.returnValue=false;
    });

    var getExamination = function(){
        $("#contatainHtml").load("/page/user/joinExamination.html");

    }
    init();

    studentMenuModel.getExamination = getExamination;
})(jQuery);

