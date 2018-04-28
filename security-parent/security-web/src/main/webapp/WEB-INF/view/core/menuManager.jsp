<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>HelloWorld</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/customer.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
<style type="text/css">
.label-padding{
padding-left: 0px;
padding-right: 0px;
}


</style>
<script type="text/javascript">
$(function(){
	 $("#dg").datagrid({
		 url:'${ctx}/core/ajax/loadResource.do',
		 columns:[[
		              {field:'ck',checkbox:true},
		              {field:'text',title:'资源名称',width:'30%',align:'center'},
		              {field:'url',title:'URL地址',width:'30%',align:'center'},
		              {field:'leaf',title:'是否叶子',width:'30%',align:'center',formatter: function(value,row,index){
		            	  if(value){
		            		  return "是";
		            	  }else{
		            		  return "否";
		            	  }
		              }}
		          ]],
	   loadMsg:'正在加载....',
	   rownumbers:true,
	   fit:true,
	   singleSelect:false,
	   pagination:true,
	   toolbar:'#tb'
	 });
	 
	//设置分页控件 
	    var p = $('#dg').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });  
});
//增加资源信息
function addInfo(){
	//var url="${ctx}/menu/addResourceInfo";
    //parent.showDailog(url,"新增资源信息",650,400,reloadData);
    $('#win').window('open');
}
//编辑资源信息
function editorInfo(){
	var rows=$("#dg").datagrid('getChecked');
	if(rows && rows.length>1){
		$.messager.alert('系统提示','请选择一条记录!');
		return;
	}else if(rows.length==1){
		var obj=rows[0];
		var id=obj.id;
		var url="${ctx}/page/editorResourceInfo.page?id="+id;
	    parent.showDailog(url,"编辑资源信息",700,400,reloadData);
	}else{
		$.messager.alert('系统提示','请选择一条记录!');
	}
	
	
}
//重新加载数据表格
function reloadData(){
	$('#dg').datagrid('reload');
}

function confirmDeleteResourceInfo(){
	$.messager.confirm('系统提示', '确定要删除吗?', function(result){
		if (result){
			deleteResourceInfo();
		}
	});
}

//删除资源信息
function deleteResourceInfo(){
	var rows=$("#dg").datagrid('getChecked');
	var array=new Array();
	if(rows.length>0){
		$.each(rows,function(index,item){
			var id=item.id;
			var text=item.text;
			var obj={
					id:id,
				    text:text
					};
			array.push(obj);
		});
		var parameter = JSON.stringify(array);
		$.ajax({  
            type: "post", // 请求方式  
            url: "${ctx}/core/ajax/deleteResourceInfo.do", //url地址  
            data:parameter, //数据  
            contentType: "application/json",  
            dataType: "json",  
            success: function (res) { 
            	if(res.flag){
            		 reloadData();
            	}else{
            		$.messager.alert('系统提示','操作失败了!');
            	}
               
            }, error: function (res) {  
            	$.messager.alert('系统提示','操作失败了!');
            }  
        })
		
	}else{
		$.messager.alert('系统提示','请选择一条记录!');
	}
}

</script>
</head>
     <div class="easyui-layout" fit="true">
		<div id="northPanel" data-options="region:'north',split:true" title="查询条件" style="height: 200px;background-color: #F2F2F2;">
                 <div class="container" style="margin-top: 20px;">
                      <form class="form-horizontal" role="form">
						   <div class="form-group">
						      <label for="firstname" class="col-xs-1 control-label label-padding">资源名称:</label>
						      <div class="col-xs-3">
						         <input type="text" class="easyui-textbox"/>
						      </div>
						      
						      <label for="lastname" class="col-xs-1 control-label label-padding">URL:</label>
						      <div class="col-xs-3">
						          <input type="text" class="easyui-textbox"/>
						      </div>
						      
						      <label for="firstname" class="col-xs-1 control-label label-padding">状态:</label>
						      <div class="col-xs-3">
						          <input type="text" class="easyui-textbox"/>
						      </div>
						   </div>
                         </form>
					<div class="col-xs-12" align="center" style="vertical-align: middle;margin-top: 10px;">
					   <span id="cancel" class="btn btn-default">
					                        查询
						</span>
						<span id="btnConfirm" class="btn btn-primary">
						           取消
						</span>
					</div>
              </div>
		</div>
		<div data-options="region:'center'" border="false" title="详情">
		<div id="tb">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addInfo();">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editorInfo();">编辑</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="confirmDeleteResourceInfo();">删除</a>
		</div>
		   <table id="dg" style="overflow: hidden;background-color: #F2F2F2;">
	       </table>
		</div>
	</div>
	
 <div id="win" class="easyui-window" title="Login" style="width:300px;height:180px;" data-options="closed:true">
    <form style="padding:10px 20px 10px 40px;" class="form-horizontal">
                     <div class="form-group">
						      <label for="firstname" class="col-xs-1 control-label label-padding">资源名称:</label>
						      <div class="col-xs-3">
						         <input type="text" class="easyui-textbox"/>
						      </div>
						      
						      <label for="lastname" class="col-xs-1 control-label label-padding">URL:</label>
						      <div class="col-xs-3">
						          <input type="text" class="easyui-textbox"/>
						      </div>
						      
						      <label for="firstname" class="col-xs-1 control-label label-padding">状态:</label>
						      <div class="col-xs-3">
						          <input type="text" class="easyui-textbox"/>
						      </div>
						   </div>
        <div style="padding:5px;text-align:center;">
            <a href="#" class="easyui-linkbutton" icon="icon-ok">Ok</a>
            <a href="#" class="easyui-linkbutton" icon="icon-cancel">Cancel</a>
        </div>
    </form>
</div>
</html>