<%@ page language="java" import="com.model.*"  pageEncoding="utf-8"%>
<% Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi"); %>
   
<%
	User user = (User)session.getAttribute("user");
	int userId = user.getUserId();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjpinglunBieming()%></title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var url;
var userId = <%=userId%>;
	function searchSjpinglun(){
		$('#dg').datagrid('load',{
			shujuId:$('#s_shujuId').combobox("getValue")
		});
	}
	
	function saveSjpinglun(){
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
	
	function openSjpinglunAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjpinglunBieming()%>");
		url="../addSjpinglun?userId="+userId;
	}
	
	function resetValue(){
	}
	
	function deleteSjpinglun(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].sjpinglunId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../deleteSjpinglun",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].sjpinglunName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function closeSjpinglunDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openSjpinglunModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjpinglunBieming()%>");
		$("#fm").form("load",row);
		url="../addSjpinglun?sjpinglunId="+row.sjpinglunId;
	}
	
	function formatSex(sjpinglunSex, row) {  
		if(sjpinglunSex==0){
			return "男";
		}
		if(sjpinglunSex==1){
			return "女";
		}
	}
	
	function formatType1(sjpinglunType1, row) {  
		if(sjpinglunType1==0){
			return "零";
		}
		if(sjpinglunType1==1){
			return "一";
		}
	}
	
	function formatType(sjpinglunType, row) {  
		if(sjpinglunType==0){
			return "0";
		}
		if(sjpinglunType==1){
			return "1";
		}
	}
	
	function formatChakan(sjpinglunImg, row) {
		if(sjpinglunImg){
			return '<a target="_blank" style="color:red;" href="../' + sjpinglunImg + '">查看' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function formatXiazai(sjpinglunImgName, row) {
		if(sjpinglunImgName){
			return '<a target="_blank" style="color:red;" href="../downFile?filename=' + sjpinglunImgName + '">下载' + '</a>'; 
		}else {
			return "未上传";
		}
	}
	
	function daochuSjpinglun(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要导出的数据！");
			return;
		}
		var sjpinglunIds=[];
		for(var i=0;i<selectedRows.length;i++){
			sjpinglunIds.push(selectedRows[i].sjpinglunId);
		}
		var ids=sjpinglunIds.join(",");
		$.messager.confirm("系统提示","您确认要导出数据吗？",function(r){
			if(r){
				$.post("../daochuSjpinglun",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功导出数据：D:！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].sjpinglunName+'</font>'+result.errorMsg);
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
	
	function daoruSjpingluns(){
		$("#daoru").dialog("open").dialog("setTitle","导入<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjpinglunBieming()%>");
		daoruurl="../daoruSjpinglun";
	}
	
	function closeDaoruSjpinglun(){
		$("#daoru").dialog("close");
		resetValue();
	}
	
	function saveDaoruSjpinglun(){
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
	
	function shangchuanSjpinglun(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#shangchuan").dialog("open").dialog("setTitle","上传<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjpinglunBieming()%>");
		$("#shchfm").form("load",row);
		shchurl="../shangchuanSjpinglun?sjpinglunId="+row.sjpinglunId;
	}
	
	function closeShangchuanSjpinglun(){
		$("#shangchuan").dialog("close");
		resetValue();
	}
	
	function saveShangchuanSjpinglun(){
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
	
	function shenheSjpinglun(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要执行的数据！");
			return;
		}
		var row=selectedRows[0];
		url="../addSjpinglun?sjpinglunId="+row.sjpinglunId;
		$.messager.confirm("系统提示","您确认要执行吗？",function(r){
			if(r){
				$.post(url,{sjpinglunType:1},function(result){
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
</script>
</head>
<body style="margin: 5px;">
<!--startprint-->
	<table id="dg" title="<%=newJcpeizhi.getShujuBieming()%><%=newJcpeizhi.getSjpinglunBieming()%>" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../getSjpingluns?userId=<%=userId %>" fit="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="sjpinglunId" width="10" hidden="true">编号</th>
				<th field="yonghuId" width="20" hidden="true"><%=newJcpeizhi.getYonghuBieming()%>ID</th>
				<th field="yonghuName" width="20"><%=newJcpeizhi.getYonghuBieming()%></th>
				<th field="sjpinglunName" width="80">交流</th>
				<th field="userId" width="20" hidden="true"><%=newJcpeizhi.getUserBieming()%>ID</th>
				<th field="userName" width="20"><%=newJcpeizhi.getUserBieming()%></th>
				<th field="shujuId" width="20" hidden="true"><%=newJcpeizhi.getShujuBieming()%>ID</th>
				<th field="shujuName" width="20"><%=newJcpeizhi.getShujuBieming()%></th>
				<th field="sjpinglunMark" width="80">回复</th>
				<th field="sjpinglunDate" width="20" formatter="datetostr">时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openSjpinglunModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">回复</a>
		</div>
		<div>
		&nbsp;<%=newJcpeizhi.getShujuBieming()%>：&nbsp;<input class="easyui-combobox" id="s_shujuId" name="s_shujuId"  data-options="panelHeight:'auto',editable:false,valueField:'shujuId',textField:'shujuName',url:'../shujuComboList'"/>
		<a href="javascript:searchSjpinglun()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
<!--endprint-->
	
	<div id="dlg" class="easyui-dialog" style="width: 460px;height: 220px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td valign="top">回复：</td>
					<td colspan="4"><textarea rows="7" cols="50" name="sjpinglunMark" id="sjpinglunMark"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveSjpinglun()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeSjpinglunDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveShangchuanSjpinglun()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShangchuanSjpinglun()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
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
		<a href="javascript:saveDaoruSjpinglun()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDaoruSjpinglun()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>