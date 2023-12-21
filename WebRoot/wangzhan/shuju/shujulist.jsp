<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../../jiazai/sousuobaohan.jsp"%>
<script type="text/javascript">
var yonghuId = <%=yonghuId%>;
	function addSjshaochu(shujuId){
		if (yonghuId==0) {
			if(confirm("注册登录网站？")){
				window.location.href="<%=basePath %>shouye/index.jsp";
				return false;
			}else{
				return false;
			}
		}
		$.post("<%=basePath %>addSjshaochu?sjshaochuType=0&yonghuId="+yonghuId,{shujuId:shujuId},function(result){
			if(result.errorMsg){
				alert(result.errorMsg);
			}else{
				alert("投递<%=newJcpeizhi.getShujuBieming() %>成功，请到个人中心查看详情！");
				location.reload();
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
                        <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>wangzhan/shuju/shujulist.jsp"><%=newJcpeizhi.getShujuBieming() %>信息</a>
                    </div>
                    <div class="titleBar">
                        <form id="zhiweiform" method="post" name="zhiweiform" action="<%=basePath%>wangzhan/shuju/shujulist.jsp">
						<input style="width:75px;height:20px;" type="text" placeholder="<%=newJcpeizhi.getSjleixingBieming() %>" id="sjleixingName" name="sjleixingName"/>
						<input style="width:50px;height:20px;" type="text" placeholder="朝向" id="shujuMark1" name="shujuMark1"/>
						<input style="width:75px;height:20px;" type="text" placeholder="区域" id="shujuMark2" name="shujuMark2"/>
						￥<input style="width:50px;height:20px;" type="text" placeholder="价格" id="shujuDouble3" name="shujuDouble3"/>--
						<input style="width:50px;height:20px;" type="text" placeholder="价格" id="shujuDouble4" name="shujuDouble4"/>
                        <a href="javascript:document.zhiweiform.submit();">搜 索</a>
                    	</form>
                    </div>
                </div>

                <div class="col-sm-12 col-md-12 pad">
                    
                    <div class="nameList" style="margin-top:10px;">
                    	<ul>
                    	<%if(sousuoShujus!=null){ %>
							<%for(int i = 0; i < sousuoShujus.size(); i++){ %>
							<%Shuju newShuju = sousuoShujus.get(i); %>
                        	<li>
                                <span><%=i+1 %></span>
                                <a href="<%=basePath %>wangzhan/shuju/shujushow.jsp?shujuId=<%=newShuju.getShujuId()%>">
                                	<%=newShuju.getSjleixingName() %>
                                	|
                                	<%=newShuju.getShujuName() %>
                                	|
                                	￥<%=newShuju.getShujuDouble() %>
                                	|
                                	<%=newShuju.getShujuZong() %>平米
                                	|
                                	<%=newShuju.getShujuMark1() %>
                                	|
                                	<%=newShuju.getShujuMark2() %>
                                	|
                                	<%=newShuju.getShujuMark3() %>
                                	|
                                	<%=newShuju.getUserName() %>
                                </a>
                                <span class="time"><%=DateUtil.formatDate(newShuju.getShujuDate(),"yyyy-MM-dd") %></span>
                            </li>
                    	<%} %>
                    	<%} %>
                    	</ul>       
                    </div>

                    <div class='pageBar'>
                    <div class='pageList'>
                    <a href="shujulist.jsp?page=1">首页</a>
                    <%if(shujucurrPage!=1){ %>
            		<a href="shujulist.jsp?page=<%=(shujucurrPage-1) %>">上页</a>&nbsp;
            		<%} %>
   					<%if(shujucurrPage!=shujutotalPage){ %>
            		<a href="shujulist.jsp?page=<%=(shujucurrPage+1) %>">下页</a>&nbsp;
            		<a href="shujulist.jsp?page=<%=shujutotalPage %>">尾页</a>
            		<%} %>
                    </div>
                    </div>

                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="../footer.jsp"%>