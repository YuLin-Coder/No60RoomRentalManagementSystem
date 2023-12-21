<%@ page language="java" import="com.model.*"  pageEncoding="utf-8"%>
<% Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi"); %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=newJcpeizhi.getShujuBieming()%>信息</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/datagrid-export.js"></script>
<link rel="stylesheet" href="../fuwenben/themes/default/default.css" />
<link rel="stylesheet" href="../fuwenben/plugins/code/prettify.css" />
<script charset="utf-8" src="../fuwenben/kindeditor-all.js"></script>
<script charset="utf-8" src="../fuwenben/lang/zh-CN.js"></script>
<script charset="utf-8" src="../fuwenben/plugins/code/prettify.js"></script>

	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="shujuMark"]', {
				cssPath : '../fuwenben/plugins/code/prettify.css',
				uploadJson : '../upload/fileUpload',
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				},
				afterBlur: function(){this.sync();}
			});
			prettyPrint();
		});
	</script>
<script type="text/javascript">
var url;
	function searchShuju(){
		$('#dg').datagrid('load',{
			shujuName:$('#s_shujuName').val(),
			sjleixingId:$('#s_sjleixingId').combobox("getValue")
		});
	}
	
	function saveShuju(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
			
				var s=result;
				var result = eval('(' + result + ')');
			
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function openShujuAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加<%=newJcpeizhi.getShujuBieming()%>信息");
		url="../addShuju";
	}
	
	function resetValue(){
	}
	
	function deleteShuju(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].shujuId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteShuju",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].shujuName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function closeShujuDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openShujuModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑<%=newJcpeizhi.getShujuBieming()%>信息");
		$("#fm").form("load",row);
		url="../addShuju?shujuId="+row.shujuId;
	}
	
	function formatSex(shujuSex, row) {  
		if(shujuSex==0){
			return "男";
		}
		if(shujuSex==1){
			return "女";
		}
	}
	
	function formatType1(shujuType1, row) {  
		if(shujuType1==0){
			return "零";
		}
		if(shujuType1==1){
			return "一";
		}
	}
	
	function formatType(shujuType, row) {  
		if(shujuType==0){
			return "空闲";
		}
		if(shujuType==1){
			return "租出";
		}
	}
	
	function formatChakan(shujuImg, row) {
		if(shujuImg){
			return '<a target="_blank" style="color:red;" href="../' + shujuImg + '">查看' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function formatXiazai(shujuImgName, row) {
		if(shujuImgName){
			return '<a target="_blank" style="color:red;" href="../downFile?filename=' + shujuImgName + '">下载' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function daochuShuju(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要导出的数据！");
			return;
		}
		var shujuIds=[];
		for(var i=0;i<selectedRows.length;i++){
			shujuIds.push(selectedRows[i].shujuId);
		}
		var ids=shujuIds.join(",");
		$.messager.confirm("系统提示","您确认要导出数据吗？",function(r){
			if(r){
				$.post("../daochuShuju",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功导出数据：D:！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].shujuName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function doPrint() {      
        bdhtml=window.document.body.innerHTML;      
        sprnstr="<!--startprint-->";      
        eprnstr="<!--endprint-->";      
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);      
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));      
        window.document.body.innerHTML=prnhtml;   
        window.print();      
	}
	
	function daoruShujus(){
		$("#daoru").dialog("open").dialog("setTitle","导入<%=newJcpeizhi.getShujuBieming()%>信息");
		daoruurl="../daoruShuju";
	}
	
	function closeDaoruShuju(){
		$("#daoru").dialog("close");
		resetValue();
	}
	
	function saveDaoruShuju(){
		$("#drfm").form("submit",{
			url:daoruurl,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
			
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#daoru").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function shangchuanShuju(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#shangchuan").dialog("open").dialog("setTitle","上传<%=newJcpeizhi.getShujuBieming()%>信息");
		$("#shchfm").form("load",row);
		shchurl="../shangchuanShuju?shujuId="+row.shujuId;
	}
	
	function closeShangchuanShuju(){
		$("#shangchuan").dialog("close");
		resetValue();
	}
	
	function saveShangchuanShuju(){
		$("#shchfm").form("submit",{
			url:shchurl,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
			
				var s=result;
				var result = eval('(' + result + ')');
			
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#shangchuan").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function datetostr(date, row) {
		if(date){
			var date = new Date(date.time);
        	var y=date.getFullYear();
        	var m=date.getMonth()+1;
        	var d=date.getDate();
        	var h=date.getHours();
        	var m1=date.getMinutes();
        	var s=date.getSeconds();
        	m = m<10?("0"+m):m;
        	d = d<10?("0"+d):d;
        	return y+"-"+m+"-"+d;
			var text = JsonDateValue.toLocaleString();
			return text;
		}else{
			return "未填写";
		}
	}
	
	function shenheShuju(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addShuju?shujuId="+row.shujuId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{shujuType:1},function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","您已成功执行！");
						$("#dg").datagrid("reload");
					}
				},"json");
			}
		});
	}
	
	function shangjiaShuju(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addShuju?shujuId="+row.shujuId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{shujuType:0},function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","您已成功执行！");
						$("#dg").datagrid("reload");
					}
				},"json");
			}
		});
	}
	
	function xiajiaShuju(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addShuju?shujuId="+row.shujuId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{shujuType:1},function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","您已成功执行！");
						$("#dg").datagrid("reload");
					}
				},"json");
			}
		});
	}
	
	function chakanNeirong(shujuId, row) {
		return '<a style="color:red;"target="_blank" href="<%=basePath%>neirong/shujuneirong.jsp?shujuId=' + shujuId + '">查看' + '</a>';  
	}
	
	function numberSort(a,b){
        var number1 = parseFloat(a);
        var number2 = parseFloat(b);

        return (number1 > number2 ? 1 : -1);
    }
	function toExcel(){
        $('#dg').datagrid('toExcel','dg.xls');	// export to excel
    }
	function print(){
        $('#dg').datagrid('print','DataGrid');	// print the datagrid
    }
</script>
</head>
<body style="margin: 5px;">
<!--startprint-->
	<table id="dg" title="<%=newJcpeizhi.getShujuBieming()%>信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../getShujus" fit="true" rownumbers="true" toolbar="#tb" remoteSort="false">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="shujuId" width="10" formatter="chakanNeirong">查看</th>
				<th field="shujuName" width="40">名称</th>
				<th field="shujuDouble" width="10">元/月</th>
				<th field="shujuZong" width="10">面积</th>
				<th field="shujuMark1" width="10">朝向</th>
				<th field="shujuMark2" width="20">区域</th>
				<th field="shujuMark3" width="60">配套</th>
				<th field="sjleixingId" width="20" hidden="true"><%=newJcpeizhi.getSjleixingBieming()%>ID</th>
				<th field="sjleixingName" width="20"><%=newJcpeizhi.getSjleixingBieming()%></th>
				<th field="buyuanName" width="40"><%=newJcpeizhi.getBuyuanBieming()%></th>
				<th field="userId" width="20" hidden="true"><%=newJcpeizhi.getUserBieming()%>ID</th>
				<th field="userName" width="20"><%=newJcpeizhi.getUserBieming()%></th>
				<th field="shujuImg" width="20" formatter="formatChakan">缩略图</th>
				<th field="shujuType" width="10" formatter="formatType">状态</th>
				<th field="shujuDate" width="20" formatter="datetostr">时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:xiajiaShuju()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">下架</a>
		</div>
		<div>
		&nbsp;名称：&nbsp;<input type="text" name="s_shujuName" id="s_shujuName" size="10"/>
		&nbsp;<%=newJcpeizhi.getSjleixingBieming()%>：&nbsp;<input class="easyui-combobox" id="s_sjleixingId" name="s_sjleixingId"  data-options="panelHeight:'auto',editable:false,valueField:'sjleixingId',textField:'sjleixingName',url:'../sjleixingComboList'"/>
		<a href="javascript:searchShuju()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
<!--endprint-->
	<div id="dlg" class="easyui-dialog" style="width: 850px;height: 420px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>名称：</td>
					<td><input type="text" name="shujuName" id="shujuName" class="easyui-validatebox" required="true"/></td>
					<td><%=newJcpeizhi.getSjleixingBieming()%>：</td>
					<td><input class="easyui-combobox" id="sjleixingId" name="sjleixingId"  data-options="panelHeight:'auto',editable:false,valueField:'sjleixingId',textField:'sjleixingName',url:'../sjleixingComboList'"/></td>
				</tr>
				<tr>
					<td>售价：</td>
					<td><input type="text" name="shujuDouble" id="shujuDouble" class="easyui-validatebox" required="true"/></td>
					<td><%=newJcpeizhi.getSjxingtaiBieming()%>：</td>
					<td><input class="easyui-combobox" id="sjxingtaiId" name="sjxingtaiId"  data-options="panelHeight:'auto',editable:false,valueField:'sjxingtaiId',textField:'sjxingtaiName',url:'../sjxingtaiComboList'"/></td>
				</tr>
				<tr>
					<td valign="top"><%=newJcpeizhi.getShujuBieming()%>描述：</td>
					<td colspan="4"><textarea name="shujuMark" id="shujuMark" cols="100" rows="12" style="width:700px;height:250px;visibility:hidden;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveShuju()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShujuDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
<!--上传-->	
	<div id="shangchuan" class="easyui-dialog" style="width: 320px;height: 140px;padding: 10px 20px"
		closed="true" buttons="#shangchuan-buttons">
		<form id="shchfm" method="post" enctype="multipart/form-data">
			<table cellspacing="5px;">
				<tr>
					<td><input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="shangchuan-buttons">
		<a href="javascript:saveShangchuanShuju()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShangchuanShuju()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
<!--导入-->	
	<div id="daoru" class="easyui-dialog" style="width: 320px;height: 140px;padding: 10px 20px"
		closed="true" buttons="#daoru-buttons">
		<form id="drfm" method="post" enctype="multipart/form-data">
			<table cellspacing="5px;">
				<tr>
					<td><input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="daoru-buttons">
		<a href="javascript:saveDaoruShuju()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDaoruShuju()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>