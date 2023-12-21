<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.model.*,java.util.List,java.util.ArrayList"
	pageEncoding="utf-8"%>
            <div class="col-xs-12 col-sm-4 col-md-3">

                <div class="navigationBox" id="classification">
                    <div class="titleBar">
                        <h1><%=newJcpeizhi.getUserBieming() %><%=newJcpeizhi.getBuzhiBieming() %></h1>
                        <span></span>
                    </div>
                    <div class="list">
                        <ul id="firstpane">
                        <%if(buzhis!=null){ %>
						<%for(int i = 0; i < buzhis.size(); i++){ %>
                        <li>
                            <a class='' href="<%=basePath %>wangzhan/user/userlist.jsp?buzhiId=<%=buzhis.get(i).getBuzhiId() %>"><%=buzhis.get(i).getBuzhiName() %></a>
                        </li>
                        <%} %>
                        <%} %>
                        </ul>
                    </div>
                </div>

            </div>