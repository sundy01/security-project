<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-table.min.css" />
<title>员工列表</title>
</head>
<body>

	<div class="panel-body" style="padding-bottom:0px;">
  <div class="panel panel-default">
   <div class="panel-heading">查询条件</div>
   <div class="panel-body">
    <form id="formSearch" class="form-horizontal">
     <div class="form-group" style="margin-top:15px">
      <label class="control-label col-sm-1" for="name">员工姓名</label>
      <div class="col-sm-3">
       <input type="text" class="form-control" id="name">
      </div>
      <label class="control-label col-sm-1" for="address">家庭住址</label>
      <div class="col-sm-3">
       <input type="text" class="form-control" id="address">
      </div>
      <div class="col-sm-4" style="text-align:left;">
       <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询</button>
      </div>
     </div>
    </form>
   </div>
  </div>
  <div id="toolbar" class="btn-group">
   <button id="btn_add" type="button" class="btn btn-success">
    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
   </button>
  </div>
  <table id="tb_user"></table>
 </div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${ctx}/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ctx}/admin/lib/layer/2.4/layer.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/bootstrap-table.js"></script> 
<script type="text/javascript" src="${ctx}/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
//页面加载完成之后
var data = [
 { Id: 1, Name: 'Jim', Age: 30, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 2, Name: 'Kate', Age: 30, School: '光明小学', Address: '深圳市', Remark: 'My Name is Jim Green' },
 { Id: 3, Name: 'Lucy', Age: 30, School: '光明小学', Address: '广州天河机场', Remark: 'My Name is Jim Green' },
 { Id: 4, Name: 'Lilei', Age: 30, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 5, Name: 'Lintao', Age: 30, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 6, Name: 'Lily', Age: 30, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 7, Name: 'Hanmeimei', Age: 30, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 8, Name: '张三', Age: 46, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 9, Name: '李四', Age: 23, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 10, Name: '王五', Age: 33, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 11, Name: '赵六', Age: 22, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 12, Name: 'Polly', Age: 300, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
 { Id: 13, Name: 'Uncle', Age: 50, School: '光明小学', Address: '北京市光明小学旁', Remark: 'My Name is Jim Green' },
];
var childData = [
 { SourceField: 'A', BackField: 'BB' },
 { SourceField: 'CC', BackField: 'UU' },
 { SourceField: 'DD', BackField: 'J' },
];
$(function () {
 //表格的初始化
 $('#tb_user').bootstrapTable({
  data: data,       //直接从本地数据初始化表格
  method: 'get',      //请求方式（*）
  striped: true,      //是否显示行间隔色
  cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
  pagination: true,     //是否显示分页（*）
  queryParams: function (params) {
   return params;
  },         //传递参数（*）
  sidePagination: "client",   //分页方式：client客户端分页，server服务端分页（*）
  pageNumber: 1,      //初始化加载第一页，默认第一页
  pageSize: 5,      //每页的记录行数（*）
  pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
  strictSearch: true,
  minimumCountColumns: 2,    //最少允许的列数
  uniqueId: "Id", 
  height:400,
  selectItemName: 'parentItem',
  columns: [{
   checkbox: true
  }, {
   field: 'Name',
   title: '姓名',
width:200
  }, {
   field: 'Age',
   title: '年龄',
width:200
  }, {
   field: 'School',
   title: '毕业院校',
width:200
  }, {
   field: 'Address',
   title: '家庭住址',
width:100
  }, {
   field: 'Remark',
   title: '备注',
  width:100
  },{
   title: '操作',
width:200,
   formatter: function (value, row, index) {//这里的三个参数：value表示当前行当前列的值；row表示当前行的数据；index表示当前行的索引（从0开始）。
    var html = '<button type="button" onclick="editModel('+row.Id+')" class="btn btn-primary"><span class="glyphicon glyphicon-pencil" aria- hidden="true" ></span >编辑</button >  ' +
       '<button type="button" onclick="deleteModel(' + row.Id + ')" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria- hidden="true" ></span >删除</button >';
    return html;
   }
  }]
 });
 //新增事件
 $("#btn_add").on('click', function () {
$('#tb_user').bootstrapTable("resetView");
  //弹出模态框
  $("#myModal").modal();
  //给弹出框里面的各个文本框赋值
  $("#myModal input").val("");
  $("#myModal textarea").val("");
 });
});
//加载子表
var InitSubTable = function (index, row, $detail) {
 var parentid = row.MENU_ID;
 var cur_table = $detail.html('<table></table>').find('table');
 //子表的初始化和父表完全相同
 $(cur_table).bootstrapTable({
  //url: '/api/MenuApi/GetChildrenMenu',
  data: childData,
  method: 'get',
  queryParams: { strParentID: parentid },
  ajaxOptions: { strParentID: parentid },
  clickToSelect: true,
  uniqueId: "MENU_ID",
  pageSize: 10,
  pageList: [10, 25],
selectItemName: 'childItem'+index,
checkboxHeader:false,
  columns: [{
   checkbox: true
  }, {
    field: 'SourceField',
   title: '源端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }, {
   field: 'BackField',
   title: '备端字段'
  }],
  //无线循环取子表，直到子表里面没有记录
  onExpandRow: function (index, row, $Subdetail) {
   //oInit.InitSubTable(index, row, $Subdetail);
  }
 });
};
//编辑事件
var editModel = function (id) {
 //根据当前行的id获取当前的行数据
 var row = $("#tb_user").bootstrapTable('getRowByUniqueId', id);
 //弹出模态框
 $("#myModal").modal();
 //给弹出框里面的各个文本框赋值
 $("#myModal input[name='Name']").val(row.Name);
 $("#myModal input[name='Age']").val(row.Age);
 $("#myModal input[name='School']").val(row.School);
 $("#myModal input[name='Address']").val(row.Address);
 $("#myModal textarea[name='Remark']").val(row.Remark);
}
//删除事件
var deleteModel = function (id) {
	member_del('新增',500);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

function layer_show(title,url,w,h){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: title,
		content: url
	});
}
/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
</script> 
</body>
</html>