<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
	<script src="js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<h1>Hello World</h1>
	<div id="context">

	</div>
<script type="text/javascript">
    onload();

    function onload() {
        var url = "/employee/getAllEmployeeInfo.do";
        $.ajax({
            url:url,
            async:true,
            type:"POST",
            dataType:"text",
            data:{

            },
            success:function(data){
				console.info(data);
				data = JSON.parse(data);
				if(data.errorInfo.code=="0000"){
				    var list = data.data;
				    for(var i=0;list&&i<list.length;i++){
				        var item = data.data[i];
                        $("#context").append("<div>"+item.name+"</div>");
					}
				}else{
				    alert(data.errorInfo.message);
				}
            },
            error:function(xhr,status,error){
                alert("请求出错");
            }
        });
    }
</script>
</body>
</html>
