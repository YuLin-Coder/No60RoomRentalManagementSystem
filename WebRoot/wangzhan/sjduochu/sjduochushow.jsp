<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../../jiazai/sousuobaohan.jsp"%>
<script type="text/javascript">
var yonghuId = <%=yonghuId%>;
	
	function addSjqita(sjduochuId){
		var sjqitaName = document.form1.sjqitaName.value;
		if(sjqitaName==""){
			document.form1.sjqitaName.focus();
			alert("请填写回复");
			return false;
		}
		if (yonghuId==0) {
			alert("请登录后回复");
			return false;
		}
		$.post("<%=basePath %>addSjqita?sjqitaType="+yonghuId+"&sjqitaName="+sjqitaName,{sjqitaType1:sjduochuId},function(result){
			if(result.errorMsg){
				alert(result.errorMsg);
			}else{
				location.reload();
				alert("回复成功！");
			}
		},"json");
	}
	
</script>
    
    <!-- 内容 -->
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-8 col-md-9" id="rightBox">

                <div class="positionBox">
                    <div class="titleBar">
                        <h1>当前位置</h1>
                        <span></span>
                        <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>wangzhan/sjduochu/sjduochulist.jsp"><%=newJcpeizhi.getSjduochuBieming() %>信息</a>
                    </div>
                </div>

                <div class="col-sm-12 col-md-12 pad">
                    
                    <div class="detailTitle" style="border:0px; background:none; font-size:20px; color:#000;">
                        <%=sousuoSjduochu.getSjduochuName()%>
                    </div>

                    <div class="detailTime">
                       	 发布人：<%=sousuoSjduochu.getYonghuName()%>　发布时间：<%=DateUtil.formatDate(sousuoSjduochu.getSjduochuDate(),"yyyy-MM-dd HH:mm:ss") %>
                    </div>
                
                    <div class="detailContent">
                
                        <span style="color:#505050;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:14px;line-height:22px;">
                        <p style="margin-top:10px; text-indent:2em;"><%=sousuoSjduochu.getSjduochuMark()%></p>
                        </span>

                    </div>
                
                    <div class="detailContent">
                
                        <span style="color:#505050;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:14px;line-height:22px;">
                        <%if(sousuoSjqitas.size()!=0) {%>
						<%for(int i = 0; i < sousuoSjqitas.size(); i++){ %>
							<%Sjqita newSjqita = sousuoSjqitas.get(i); %>
				        	<p><b>楼层：</b><%=i+1 %>&nbsp;&nbsp;&nbsp;&nbsp;<b>用户：</b><%=newSjqita.getSjqitaPhone() %>&nbsp;&nbsp;&nbsp;&nbsp;<b>时间：</b><%=DateUtil.formatDate(newSjqita.getSjqitaDate(),"yyyy-MM-dd HH:mm:ss") %><br></p>
				            <p><b>回复：</b><%=newSjqita.getSjqitaName() %><br></p>
				            <br>
				        <%} %>
				        <%} %>
                        </span>

                    </div>
                
                    <div class="detailContent">
                
                        <span style="color:#505050;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:14px;line-height:22px;">
                        <form name="form1" method="post">
				        <p><textarea rows="5" cols="100" name="sjqitaName" id="sjqitaName"></textarea></p>
						<p><a href="javascript:addSjqita(<%=sousuoSjduochu.getSjduochuId()%>)"><input type="button" value="提交回复" /></a></p>
						</form>
                        </span>

                    </div>

                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="../footer.jsp"%>