<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>HelloWorld</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	initLeftData();
	//初始化左边树形结构数据
	function initLeftData(){
		$('#leftTree').tree({
			url:'${ctx}/main/loadMenuInfo?parentId=-1',
			method:'GET',
			onBeforeExpand:function(node,param){  
				console.log(node);
                $('#leftTree').tree('options').url = "${ctx}/main/loadMenuInfo?parentId=" + node.id;
            }, 
			animate:'false',
			onClick:function(node){
				var text=node.text;
				var leaf=node.leaf;
				var url=node.url;
				var tag=$('#centerPanel').tabs('exists',text);
				if(leaf){  //是叶子节点
					 
					if(tag){
						$('#centerPanel').tabs('select',text);
					}else{
						 
						addTabPanel(text,url);
					}
					
					 
				}
				
				
				
				
			}
		});
	}
	
	function addTabPanel(text,url){
		var src="${ctx}/"+url;
        $('#centerPanel').tabs('add',{
            title: text,
            content:'<iframe  frameborder="0" src='+src+' style="width: 100%; height: 100%;" scrolling="auto"></iframe>',
            closable: true
        });
	}
	
});

function showDailog(url,title,width,height,closeDialogCallback){
	$('#openWindowFrame')[0].src=url;
	$('#win').attr("result","");
   var dialog=$('#win').window({
        width:width,
        height:height,
        modal:true,
        title:title,
        resizable:false,
        maximizable:false,
        minimizable:false,
        collapsible:false,
        onClose:function(){
              if(closeDialogCallback && typeof closeDialogCallback=='function'){
            	  var result=$('#win').attr("result");
            	  if(result && result=='true'){
            		  closeDialogCallback();
            	  }
               }
       }
    });
   
   return dialog
}
function closeDailog(result){
	$('#win').attr("result",result);
    $('#win').window('close');
}

function showMessage(title,message){
	$.messager.alert(title,message);
}
</script>
</head>
<body>
    <div class="easyui-layout" fit="true">
         <div data-options="region:'center'">
                       <div class="easyui-layout" fit="true">
						        <div id="westPanel" data-options="region:'west',split:true" title="导航" style="width:15%;padding:5px;background-color: #F2F2F2;">
									   <u id="leftTree"></u>
								</div>
								<div data-options="region:'center'" class="easyui-tabs" id="centerPanel" fit="true" border="false">
								</div>
                       </div>
		</div>
		<div id="southPanel" data-options="region:'south'" style="height:30px;">
			   <div   align="center" style="font-size: 12px;height: 25px;line-height: 25px;overflow: hidden;">上海浦发银行灾备指挥系统</div>
		</div>
	</div>
	<div id="win">
	        <div class="easyui-layout" fit="true">
				<iframe  id="openWindowFrame"　frameborder="0" src="" style="width: 100%; height: 100%;border:0px;" scrolling="auto"></iframe>		   
            </div>
	</div>
</body>
</html>