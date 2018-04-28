<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>登录页面</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<style>
.content{
    margin: 20px 20px 0 0;
	padding: 0px;
	background-color: #F2F2F2;
	height: auto;
	width: auto; /*1060px*/;
}
</style>
<script type="text/javascript">
$(function(){
	$('#btnConfirm').click(function(){
	   $('#formData').submit();
	});
});
function closeWindow(result){
	parent.closeDailog(result);
}
</script>

</head>
<body class="content">

<div style="width:800px;margin: 0px auto">
 <form class="form-horizontal" role="form" action="${ctx}/j_spring_security_check" id="formData" method="post">
   <div class="form-group">
      <div class="col-xs-3"></div>
      <label for="firstname" class="col-xs-2 control-label">账号名称:</label>
      <div class="col-xs-4">
         <input type="text" id="username" name="j_username" class="form-control"/>
      </div>
      <div class="col-xs-3"></div>
   </div>
    <div class="form-group">
      <div class="col-xs-3"></div>
      <label for="firstname" class="col-xs-2 control-label">账号名称:</label>
      <div class="col-xs-4">
         <input type="password" id="password" name="j_password" class="form-control"/>
      </div>
        <div class="col-xs-3"></div>
   </div>
</form>

<div class="col-xs-12" align="center" style="vertical-align: middle;">
   <span id="btnConfirm" class="btn btn-default">
                        确定
	</span>
	<span id="cancel" class="btn btn-primary">
	           取消
	</span>
</div>
     
</div>

</body>
    
</html>