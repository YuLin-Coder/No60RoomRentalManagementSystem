<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.model.*,java.util.List,java.util.ArrayList"
	pageEncoding="utf-8"%>
            <div class="col-xs-12 col-sm-4 col-md-3">

                <div class="navigationBox" id="classification">
                    <div class="titleBar">
                        <h1><%=newJcpeizhi.getSjduochuBieming() %><%=newJcpeizhi.getSjxingtaiBieming() %></h1>
                        <span></span>
                    </div>
                    <div class="list">
                        <ul id="firstpane">
                        <%if(sjxingtais!=null){ %>
						<%for(int i = 0; i < sjxingtais.size(); i++){ %>
                        <li>
                            <a class='' href="<%=basePath %>wangzhan/sjduochu/sjduochulist.jsp?sjxingtaiId=<%=sjxingtais.get(i).getSjxingtaiId() %>"><%=sjxingtais.get(i).getSjxingtaiName() %></a>
                        </li>
                        <%} %>
                        <%} %>
                        </ul>
                    </div>
                </div>

            </div>