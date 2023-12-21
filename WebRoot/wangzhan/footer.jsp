<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div style="background:#f9f9f9; padding-top:20px; margin-top:20px; padding-bottom:20px;">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="linkBox">
                        <div class="titleBar">
                           <h1>友情链接</h1>
                            <span></span>
                            <ul class="linkList">
                            <%if(sjlianjies!=null){ %>
                             <%for(int i = 0; i < sjlianjies.size(); i++){ %>
			            		<%Sjlianjie newSjlianjie = sjlianjies.get(i); %>
                                <li><a href="<%=newSjlianjie.getSjlianjieMark() %>" target="_blank"><%=newSjlianjie.getSjlianjieName() %> </a></li>
                             <%} %>
                            <%} %>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <footer>
    
        <div class="copyright">
            <p>
                <a href="<%=basePath %>">网站首页</a> | 
                <a href="<%=basePath %>wangzhan/sjxiaoxi/sjxiaoxishow.jsp?sjxiaoxiId=1">关于我们</a> | 
                <a href="<%=basePath %>wangzhan/shuju/shujulist.jsp"><%=newJcpeizhi.getShujuBieming() %>信息</a> | 
                <a href="<%=basePath %>wangzhan/gonggao/gonggaolist.jsp"><%=newJcpeizhi.getGonggaoBieming() %>信息</a> | 
                <a href="<%=basePath %>wangzhan/sjxiaoxi/sjxiaoxishow.jsp?sjxiaoxiId=2">联络我们</a>
            </p>
            <p class="copyright_p">
			地址：北京市西城区XXXXXXXXXXXXX街XXX路XXXX号　　邮箱：13012345678@163.com　　电话：010- 88888888   手机：13912345678<br />
		Copyright @ 2019-2020. 版权所有<%=newJcpeizhi.getJcpeizhiName()%>. 备案号：京ICP备0000000000号-1
			</p>
        </div>

    </footer>

</body>
</html>