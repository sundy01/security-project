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
<link rel="stylesheet" type="text/css" href="${ctx}/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${ctx}/admin/static/h-ui.admin/css/style.css" />
<title>员工列表</title>
</head>
<body>
<div class="page-container">
	<div class="text-c"> 
	  <form action="" method="post" class="form form-horizontal" id="formQuery">
		<div class="row cl">
			<label class="form-label col-xs-2"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-4">
				<input type="text" class="input-text" value="" placeholder="" id="username" name="username">
			</div>
			<label class="form-label col-xs-2"><span class="c-red">*</span>账号是否过期：</label>
			<div class="formControls col-xs-4">
				  <span class="select-box">
					<select class="select" size="1" name="city">
						<option value="" selected>--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-2"><span class="c-red">*</span>账号是否被锁定：</label>
			<div class="formControls col-xs-4">
				<span class="select-box">
					<select class="select" size="1" name="city">
						<option value="" selected>--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</span> 
			</div>
			<label class="form-label col-xs-2"><span class="c-red">*</span>认证是否过期：</label>
			<div class="formControls col-xs-4">
				  <span class="select-box">
					<select class="select" size="1" name="city">
						<option value="" selected>--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-2">
			  <span class="c-red">*</span>账号是否开启：
			</label>
			<div class="formControls col-sm-1">
			<input type="checkbox" id="checkbox-2" checked>
			</div>
		</div>
	    <div class="row cl">
		    <div class="col-xs-3">
		    </div>
			<div class="col-xs-6">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;查询&nbsp;&nbsp;">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;重置&nbsp;&nbsp;">
			</div>
			<div class="col-xs-3">
		    </div>
		</div>
	</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span> <span class="r">共有数据：<strong>88</strong> 条</span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg" id="sample-table-2">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">序号</th>
				<th width="100">用户名称</th>
				<th width="40">性别</th>
				<th width="90">账号是否过期</th>
				<th width="150">账号是否被锁定</th>
				<th width="130">认证是否过期</th>
				<th width="70">是否开启</th>
				<th width="100">操作</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${ctx}/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ctx}/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${ctx}/admin/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${ctx}/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx}/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${ctx}/admin/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$(function(){
	 var oTable1 = $('#sample-table-2').dataTable( 
	            { 
	              "bPaginate" : true,//分页工具条显示 
	              //"sPaginationType" : "full_numbers",//分页工具条样式 
	              "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  
	              "bScrollCollapse" : true, //当显示的数据不足以支撑表格的默认的高度 
	              "bLengthChange" : true, //每页显示的记录数 
	              "bFilter" : false, //搜索栏 
	              "bSort" : true, //是否支持排序功能 
	              "bInfo" : true, //显示表格信息 
	              "bAutoWidth" : true, //自适应宽度 
	              "bJQueryUI" : false,//是否开启主题 
	              "bDestroy" : true, 
	              "bProcessing" : true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好 
	              "bServerSide" : true,//服务器处理分页，默认是false，需要服务器处理，必须true 
	              "sAjaxDataProp" : "aData",//是服务器分页的标志，必须有  
	              "fnServerData": retrieveData, // 获取数据的处理函数
	              "sAjaxSource" : "${ctx}/employee/loadEmployeeData",//通过ajax实现分页的url路径。  
	              "aoColumns" : [//初始化要显示的列 
	                  { 
	                    "mDataProp" : "id",//获取列数据，跟服务器返回字段一致 
	                    "sClass" : "center",//显示样式 
	                    "mRender" : function(data, type, full) {//返回自定义的样式 
	                      return "<label><input type='checkbox' class='ace' /><span class='lbl'></span></label>"
	                    } 
	                  }, 
	                  { 
	                    "mDataProp" : "name"
	                  }, 
	                  { 
	                    "mDataProp" : "gender"
	                  }, 
	                  { 
	                    "mDataProp" : "accountNonExpired"
	                  }, 
	                  { 
	                    "mDataProp" : "accountNonLocked", 
	                  }, 
	                  { 
	                    "mDataProp" : "credentialsNonExpired"
	                  },
	                  { 
		                "mDataProp" : "enabled"
	                  },  
	                  { 
	                    "mDataProp" : "createTime", 
	                    "mRender" : function(data, type, full) { 
	                      return "<div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'><a class='blue' href='#'><i class='icon-zoom-in bigger-130'></i></a><a class='green' href='#'><i class='icon-pencil bigger-130'></i></a><a class='red' href='#'><i class='icon-trash bigger-130'></i></a></div>"
	                    } 
	                  } ], 
	              "aoColumnDefs" : [ {//用来设置列一些特殊列的属性 
	                "bSortable" : false, 
	                "aTargets" : [ 0 ] 
	              //第一列不排序 
	              }, { 
	                "bSortable" : false, 
	                "aTargets" : [ 5 ] 
	              }, { 
	                "bSortable" : false, 
	                "aTargets" : [ 6 ] 
	              } ], 
	              "oLanguage" : {//语言设置 
	                "sProcessing" : "处理中...", 
	                "sLengthMenu" : "显示 _MENU_ 项结果", 
	                "sZeroRecords" : "没有匹配结果", 
	                "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项", 
	                "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项", 
	                "sInfoFiltered" : "(由 _MAX_ 项结果过滤)", 
	                "sInfoPostFix" : "", 
	                "sSearch" : "搜索:", 
	                "sUrl" : "", 
	                "sEmptyTable" : "表中数据为空", 
	                "sLoadingRecords" : "载入中...", 
	                "sInfoThousands" : ",", 
	                "oPaginate" : { 
	                  "sFirst" : "首页", 
	                  "sPrevious" : "上页", 
	                  "sNext" : "下页", 
	                  "sLast" : "末页"
	                }, 
	                "oAria" : { 
	                  "sSortAscending" : ": 以升序排列此列", 
	                  "sSortDescending" : ": 以降序排列此列"
	                } 
	              } 
	            });
	
});
function retrieveData( sSource111,aoData111, fnCallback111) {
    $.ajax({
        url : sSource111,//这个就是请求地址对应sAjaxSource
        data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
            fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
        },
        error : function(msg) {
        }
    });
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
</script> 
</body>
</html>