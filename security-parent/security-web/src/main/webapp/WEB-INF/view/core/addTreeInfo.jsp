<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>HelloWorld</title>
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
});
function closeWindow(result){
	parent.closeDailog(result);
}

function confirmSubmit(){
	var text=$('#name').val();
	var url=$('#url').val();
	var desc=$('#desc').val();
   var isLeaf=$('#leaf').is(':checked');
   var parentId=$('#parentId').val();
	$.ajax({
		url:'${ctx}/core/ajax/saveResourceInfo.do',
		data:{
			text:text,
			url:url,
			leaf:isLeaf,
			parentId:parentId
		},
		method:'post',
		success:function(res){
			if(res && res.flag){
				parent.showMessage('系统提示',res.message);
				//验证正确行
				closeWindow(true);
			}
		},
		error:function(res){
			
		}
	});
	
	
}

</script>

</head>
<body class="content">

  <div class="container">
   <form class="form-horizontal" role="form">
    <input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
  <div class="form-group" style="margin-left: 30px;">
      <label for="firstname" class="col-xs-2 control-label">资源名称:</label>
      <div class="col-xs-8">
         <input type="text" id="name" class="form-control"/>
      </div>
  </div>
   
     <div class="form-group" style="margin-left: 30px;">
         <label for="lastname" class="col-xs-2 control-label">
           URL:
         </label>
	     <div class="col-xs-8">
	        <input type="text" id="url" class="form-control"/>
	     </div>
     </div>
   <div class="form-group" style="margin-left: 30px;">
      <label for="firstname" class="col-xs-2 control-label">是否叶子:</label>
      <div class="col-xs-8">
         <div class="checkbox">
            <label>
               <input type="checkbox" id="leaf" name="leaf" value="1">
            </label>
         </div>
      </div>
   </div>
   
   <div class="form-group" style="margin-left: 30px;">
       <label for="lastname" class="col-xs-2 control-label">描述</label>
      <div class="col-xs-8">
          <textarea class="form-control" style="height:80px;" id="desc"></textarea>
      </div>
   </div>
</form>

<div class="col-xs-12" align="center" style="vertical-align: middle;">
   <span id="cancel" class="btn btn-default" onclick="confirmSubmit();">
                        确定
	</span>
	<span id="btnConfirm" class="btn btn-primary" onclick='closeWindow(false);'>
	           取消
	</span>
</div>
    </div> 
</body>
    
</html>