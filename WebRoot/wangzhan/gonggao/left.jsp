<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.model.*,java.util.List,java.util.ArrayList"
	pageEncoding="utf-8"%>
            <div class="col-xs-12 col-sm-4 col-md-3">

                <div class="navigationBox" id="classification">
                    <div class="titleBar">
                        <h1><%=newJcpeizhi.getGonggaoBieming() %><%=newJcpeizhi.getGgtypeBieming() %></h1>
                        <span></span>
                    </div>
                    <div class="list">
                        <ul id="firstpane">
                        <%if(ggtypes!=null){ %>
						<%for(int i = 0; i < ggtypes.size(); i++){ %>
                        <li>
                            <a class='' href="<%=basePath %>wangzhan/gonggao/gonggaolist.jsp?ggtypeId=<%=ggtypes.get(i).getGgtypeId() %>"><%=ggtypes.get(i).getGgtypeName() %></a>
                        </li>
                        <%} %>
                        <%} %>
                        </ul>
                    </div>
                </div>

            </div>