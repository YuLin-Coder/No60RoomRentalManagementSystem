<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>
<%@ include file="../jiazai/sousuobaohan.jsp"%>
  <!-- 内容 -->
    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-9 col-md-9">
                <div class="aboutBox">
                    <a href="<%=basePath %>wangzhan/gonggao/gonggaoshow.jsp?gonggaoId=1" class="aboutMore">MORE +</a>
                    <div class="aboutTitle">
                        <h1>关于我们</h1>
                        <span>ABOUT US</span></div>
                    <section>
						<%=StringUtil.jiequString(sjxiaoxi.getSjxiaoxiMark1(), 200) %>...
                    </section>
                </div>
            </div>

            <div class="col-xs-12 col-sm-3 col-md-3">
                <div class="searchGroup">
                    <div class="searchBox">
                    <form id="form" method="post" name="form" action="<%=basePath%>wangzhan/shuju/shujulist.jsp">
                        <div class="title"><%=newJcpeizhi.getShujuBieming() %>查询</div>
						<input type="text" placeholder="<%=newJcpeizhi.getShujuBieming() %>名称" id="shujuName" name="shujuName"/>
                        <a href="javascript:document.form.submit();" type="button">搜 索</a>
                    </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div style="background:#f9f9f9; padding-top:30px; margin-top:30px; padding-bottom:10px">
        <div class="container">
            <div class="row">

                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="productBox">
                        <div class="titleBar">
                            <h1><%=newJcpeizhi.getShujuBieming() %><%=newJcpeizhi.getSjleixingBieming() %></h1>
                            <span></span>
                            <%if(sjleixings!=null){ %>
							<%for(int i = 0; i < sjleixings.size(); i++){ %>
                            <a href="<%=basePath %>wangzhan/shuju/shujulist.jsp?sjleixingId=<%=sjleixings.get(i).getSjleixingId() %>"><%=sjleixings.get(i).getSjleixingName() %></a>
                            <%} %>
                            <%} %>
                            <a class="rightMore" href="<%=basePath %>wangzhan/shuju/shujulist.jsp">>></a>
                        </div>
                        <div class="list">
                        <%if(shujus!=null){ %>
			            <%for(int i = 0; i < shujus.size(); i++){ %>
			            	<%Shuju newShuju = shujus.get(i); %>
                            <div class="col-xs-6 col-sm-3 col-md-2 col-mm-6 productImg">
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

            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">

            <div class="col-xs-12 col-sm-9 col-md-9">
                <div class="newsBox">
                    <div class="titleBar">
                        <h1><%=newJcpeizhi.getGonggaoBieming() %>信息</h1>
                        <span></span>
                        <%if(ggtypes!=null){ %>
						<%for(int i = 0; i < ggtypes.size(); i++){ %>
                        <a href="<%=basePath %>wangzhan/gonggao/gonggaolist.jsp?ggtypeId=<%=ggtypes.get(i).getGgtypeId() %>"><%=ggtypes.get(i).getGgtypeName() %></a>
                        <%} %>
                        <%} %>
                        <a class="rightMore" href="<%=basePath %>wangzhan/gonggao/gonggaolist.jsp">>></a>
                    </div>
                    <ul class="indexNewsList">
                    <%if(gonggaos!=null){ %>
				        <%for(int i = 0; i < gonggaos.size(); i++){ %>
				        <%Gonggao newGonggao = gonggaos.get(i); %>
                        <li class="col-xs-12 col-sm-6 col-md-6">
                            <a href="<%=basePath %>wangzhan/gonggao/gonggaoshow.jsp?gonggaoId=<%=newGonggao.getGonggaoId()%>">
                                <div class="img" style="background-image: url(<%=basePath %><%=newGonggao.getGonggaoImg() %>)"></div>
                                <div class="txt">
                                    <span class="title">
                                        <%=newGonggao.getGonggaoName() %>
                                    </span>
                                    <span class="time">[ <%=DateUtil.formatDate(newGonggao.getGonggaoDate(),"yyyy-MM-dd HH:mm:ss") %> ]</span>
                                    <p>
	                                    <%=StringUtil.jiequString(newGonggao.getGonggaoMark(), 50) %>...
                                    </p>
                                </div>
                            </a>
                        </li>
                        <%} %>
                    <%} %>
                    </ul>
                </div>
            </div>
            <div class="col-xs-12 col-sm-3 col-md-3">
                <div class="contactBox" style="padding:10px;">
                    <p><%=newJcpeizhi.getJcpeizhiName()%></p>
                    <p>联系人：XXXXXX</p>
                    <p>手机：130XXXXXXXX</p>
                    <p>电话：XXX-XXXXXXXX</p>
                    <p>邮箱：XXXXXXXX@qq.com</p>
                    <p>地址： XXXXXXXXXXXXXXXX</p>
                </div>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp"%>