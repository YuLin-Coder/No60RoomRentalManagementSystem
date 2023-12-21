<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../../jiazai/sousuobaohan.jsp"%>
    
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
                    
                    <div class="nameList" style="margin-top:10px;">
                    	<ul>
                    	<%if(sousuoSjduochus!=null){ %>
							<%for(int i = 0; i < sousuoSjduochus.size(); i++){ %>
							<%Sjduochu newSjduochu = sousuoSjduochus.get(i); %>
                        	<li>
                                <span><%=i+1 %></span>
                                <a href="<%=basePath %>wangzhan/sjduochu/sjduochushow.jsp?sjduochuId=<%=newSjduochu.getSjduochuId()%>">
                                	<%=newSjduochu.getSjduochuName() %>
                                </a>
                                <span class="time"><%=DateUtil.formatDate(newSjduochu.getSjduochuDate(),"yyyy-MM-dd") %></span>
                            </li>
                    	<%} %>
                    	<%} %>
                    	</ul>       
                    </div>

                    <div class='pageBar'>
                    <div class='pageList'>
                    <a href="sjduochulist.jsp?page=1">首页</a>
                    <%if(sjduochucurrPage!=1){ %>
            		<a href="sjduochulist.jsp?page=<%=(sjduochucurrPage-1) %>">上页</a>&nbsp;
            		<%} %>
   					<%if(sjduochucurrPage!=sjduochutotalPage){ %>
            		<a href="sjduochulist.jsp?page=<%=(sjduochucurrPage+1) %>">下页</a>&nbsp;
            		<a href="sjduochulist.jsp?page=<%=sjduochutotalPage %>">尾页</a>
            		<%} %>
                    </div>
                    </div>

                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="../footer.jsp"%>