<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*"
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
                    
                    <div class="col-sm-12 col-md-6 pad">
                        <div class="detailGlide" style="max-width:600px; margin:0 auto">
                            <div class="slider">
                                <ul class="slider__wrapper">
                                    <li data-responsive='' data-src='<%=basePath %><%=sousuoUser.getUserImg()%>' data-sub-html='' class='slider__item real'><a><img src='<%=basePath %><%=sousuoUser.getUserImg()%>'></a></li>
                                </ul>
                            </div>
                            <div id="detailNav">
                            </div>
                        </div>
                        <script type="text/javascript">
                            var glide = $('.slider').glide({ navigationImg: true, navigation: "#detailNav" });
                        </script>
                    </div>
                
                    <div class="col-sm-12 col-md-6 pad">
                    <form name="form1" method="post">
                        <div class="detailTitle">
                            <%=sousuoUser.getUserMark1()%>
                        </div>
                        <div class="detailParameter" style="line-height:1.8em;">
                            	时间：<%=DateUtil.formatDate(sousuoUser.getUserDate(),"yyyy-MM-dd HH:mm:ss") %><br />
                            	<%=newJcpeizhi.getBuzhiBieming()%>：<%=sousuoUser.getBuzhiName()%><br />
                            	注册金：<%=sousuoUser.getUserMark2()%><br />
                            	地址：<%=sousuoUser.getUserMark1()%><br />
                            	联系人：<%=sousuoUser.getUserXingming()%><br />
                            	电话：<%=sousuoUser.getUserPhone()%><br />
                        </div>
                        <div class="detailUrl"><a href="<%=basePath %>wangzhan/shuju/shujulist.jsp?userId=<%=sousuoUser.getUserId()%>"><%=newJcpeizhi.getShujuBieming() %>信息</a></div>
                    </form>
                    </div>
                    
                    <div class="col-sm-12 col-md-12 pad">
                        <div class="detailTitleTxt">
                           	 详细介绍
                        </div>
                        <div class="detailContent" style="line-height:1.8em;">
                           	<p style="margin-top:10px; text-indent:2em;"><%=sousuoUser.getUserMark()%></p>
                        </div>
                    </div>
                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="../footer.jsp"%>