/**
 * @author pan
 * Created on 2018/3/7 14:33
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
var managerMenuModel = managerMenuModel || {};
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

    var manageScience = function(){
        $("#contatainHtml").load("/page/user/scienceManager.html");
    }

    var autoGenerateExam = function(){
        $("#contatainHtml").load("/page/user/autoGenerateExam.html");
    }
    init();

    managerMenuModel.manageScience = manageScience;
    managerMenuModel.autoGenerateExam = autoGenerateExam;
})(jQuery);
