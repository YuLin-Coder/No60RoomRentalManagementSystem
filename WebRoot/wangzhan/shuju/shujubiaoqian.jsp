<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../../jiazai/sousuobaohan.jsp"%>
<%@ include file="../../jiazai/biaoqian/biaoqianshuju.jsp"%>
    
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
                </div>

                <div class="col-sm-12 col-md-12 pad">
                    
                    <div class="productList">
                    <%if(biaoqianShujus!=null){ %>
						<%for(int i = 0; i < biaoqianShujus.size(); i++){ %>
						<%Shuju newShuju = biaoqianShujus.get(i); %>
                        <div class="col-xs-6 col-sm-4 col-md-3 col-mm-6 productImg">
                            <a title="<%=newShuju.getShujuName() %>" href='<%=basePath %>wangzhan/shuju/shujushow.jsp?shujuId=<%=newShuju.getShujuId()%>'>
                                <span class="imgLink-hover"><span class="hover-link"></span></span>
                                <img src="<%=basePath %><%=newShuju.getShujuImg() %>" alt="<%=newShuju.getShujuName() %>" />
                            </a>
                            <a class="productTitle" href="<%=basePath %>wangzhan/shuju/shujushow.jsp?shujuId=<%=newShuju.getShujuId()%>" title="<%=newShuju.getShujuName() %>">
                                <%=newShuju.getShujuName() %>
                            </a>
                        </div>
                    	<%} %>
                    <%} %>       
                    </div>

                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="../footer.jsp"%>