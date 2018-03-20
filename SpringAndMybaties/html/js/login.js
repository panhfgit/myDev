/**
 * @author pan
 * Created on 2018/3/3 22:05
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

var loginModel = loginModel || {};
(function ($) {
    var getParam = function(){
        var data = {};
        data.userName = $("#userName").val();
        data.password = $("#password").val();
        return data;
    };

    var init = function(){

    };

    $("#login").click(function(){
        var url = "/login/checkLogin.do";
        var paraData = getParam();
        if(paraData.userName == "" || paraData.password == ""){
            alert("缺少用户名和密码！");
            return;
        }
        $.ajax({
            url:url,
            async:false,
            type:"POST",
            dataType:"text",
            data:paraData,
            success:function(data){
                console.info(data);
                data = JSON.parse(data);
                if(data.errorInfo.code=="0000"){
                    var result = data.data;
                    if(result.isLogin == "1"){
                        if(result.userInfo.userType == "2"){
                            window.location.href = "/page/user/managerMenu.html?userId="+
                                result.userInfo.userId+"&userName="+encodeURI(result.userInfo.userName);
                            window.event.returnValue=false;
                        }else if(result.userInfo.userType == "1"){
                            window.location.href = "/page/user/studentMenu.html?userId="+
                                result.userInfo.userId+"&userName="+encodeURI(result.userInfo.userName);
                            window.event.returnValue=false;
                        }
                    }else{
                        alert("登陆失败，用户名密码错误！");
                    }
                }else{
                    alert("登录失败,"+data.errorInfo.message);
                }
            },
            error:function(xhr,status,error){
                alert("请求出错");
            }
        });
    });

    init();
})(jQuery);
