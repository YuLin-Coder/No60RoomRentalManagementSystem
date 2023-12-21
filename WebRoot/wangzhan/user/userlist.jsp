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
                        <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>wangzhan/user/userlist.jsp"><%=newJcpeizhi.getUserBieming() %>信息</a>
                    </div>
                </div>

                <div class="col-sm-12 col-md-12 pad">
                    
                    <div class="productList">
                    <%if(sousuoUsers!=null){ %>
						<%for(int i = 0; i < sousuoUsers.size(); i++){ %>
						<%User newUser = sousuoUsers.get(i); %>
                        <div class="col-xs-6 col-sm-4 col-md-3 col-mm-6 productImg">
                            <a title="<%=newUser.getUserName() %>" href='<%=basePath %>wangzhan/user/usershow.jsp?userId=<%=newUser.getUserId()%>'>
                                <span class="imgLink-hover"><span class="hover-link"></span></span>
                                <img src="<%=basePath %><%=newUser.getUserImg() %>" alt="<%=newUser.getUserName() %>" />
                            </a>
                            <a class="productTitle" href="<%=basePath %>wangzhan/user/usershow.jsp?userId=<%=newUser.getUserId()%>" title="<%=newUser.getUserName() %>">
                                <%=newUser.getUserMark1() %>
                            </a>
                        </div>
                    	<%} %>
                    <%} %>       
                    </div>

                    <div class='pageBar'>
                    <div class='pageList'>
                    <a href="userlist.jsp?page=1">首页</a>
                    <%if(usercurrPage!=1){ %>
            		<a href="userlist.jsp?page=<%=(usercurrPage-1) %>">上页</a>&nbsp;
            		<%} %>
   					<%if(usercurrPage!=usertotalPage){ %>
            		<a href="userlist.jsp?page=<%=(usercurrPage+1) %>">下页</a>&nbsp;
            		<a href="userlist.jsp?page=<%=usertotalPage %>">尾页</a>
            		<%} %>
                    </div>
                    </div>

                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="../footer.jsp"%>