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
	initLeftData();
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
	    
	    
	    
	    $('#westPanel').bind('contextmenu',function(e){
			e.preventDefault();
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		});
});
//重新加载数据表格
function reloadData(){
	$('#dg').datagrid('reload');
}
function initLeftData(){
	$('#leftTree').tree({
		url:'${ctx}/core/ajax/loadMenuInfo.do',
		method:'post',
		animate:'true',
		onClick:function(node){
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			$('#leftTree').tree('select', node.target);
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}
	});
}

function append(){
		var t = $('#leftTree');
		var node = t.tree('getSelected');
		if(node && node.id){
			var parentId=node.id;
			addInfo(parentId);
		}else{
			addInfo("-1");
		}
	
}
//增加资源信息
function addInfo(parentId){
	var url="${ctx}/page/addTreeInfo.page?parentId="+parentId;
    parent.showDailog(url,"新增资源信息",650,400,reloadData);
}
function reloadData(){
	$('#leftTree').tree("reload");
}
</script>
</head>
     <div class="easyui-layout" fit="true">
		<div id="westPanel" data-options="region:'west',split:true" title="菜单导航" style="width:15%;padding:5px;background-color: #F2F2F2;">
		
		 <div id="bodyclass">
		   <u id="leftTree"></u>
		 <div id="mm" class="easyui-menu" style="width:120px;">
			<div onclick="append()" data-options="iconCls:'icon-add'">增加菜单</div>
			<div onclick="editor()" data-options="iconCls:'icon-edit'">编辑菜单</div>
			<div onclick="remove()" data-options="iconCls:'icon-remove'">删除菜单</div>
        </div>
		 </div>
		 
        
		</div>
		<div data-options="region:'center'" border="false" title="详情">
		<div id="tb">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
		   <table id="dg" style="overflow: hidden;background-color: #F2F2F2;">
	       </table>
		</div>
	</div>
</html>