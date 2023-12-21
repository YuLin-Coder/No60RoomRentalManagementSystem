<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*"
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
				alert("收藏<%=newJcpeizhi.getShujuBieming()%>成功，请到个人中心查看租赁！");
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
                </div>

                <div class="col-sm-12 col-md-12 pad">
                    
                    <div class="col-sm-12 col-md-6 pad">
                        <div class="detailGlide" style="max-width:600px; margin:0 auto">
                            <div class="slider">
                                <ul class="slider__wrapper">
                                    <li data-responsive='' data-src='<%=basePath %><%=sousuoShuju.getShujuImg()%>' data-sub-html='' class='slider__item real'><a><img src='<%=basePath %><%=sousuoShuju.getShujuImg()%>'></a></li>
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
                            <%=sousuoShuju.getShujuName()%>
                        </div>
                        <div class="detailParameter" style="line-height:1.8em;">
                            	时间：<%=DateUtil.formatDate(sousuoShuju.getShujuDate(),"yyyy-MM-dd HH:mm:ss") %><br />
                            	<%=newJcpeizhi.getBuzhiBieming()%>：<%=sousuoShuju.getBuzhiName()%><br />
                            	<%=newJcpeizhi.getUserBieming()%>：<%=sousuoShuju.getUserName()%><br />
                            	<%=newJcpeizhi.getSjleixingBieming()%>：<%=sousuoShuju.getSjleixingName()%><br />
                            	标签：<%=sousuoShuju.getBuyuanName()%><br />
                            	朝向：<%=sousuoShuju.getShujuMark1()%><br />
                            	区域：<%=sousuoShuju.getShujuMark2()%><br />
                            	配套：<%=sousuoShuju.getShujuMark3()%><br />
                            	面积：<%=sousuoShuju.getShujuZong()%>㎡<br />
                            	价格：<%=sousuoShuju.getShujuDouble()%>元/月<br />
                        </div>
                        <div class="detailUrl"><a href="javascript:addSjshaochu(<%=sousuoShuju.getShujuId()%>)">收藏<%=newJcpeizhi.getShujuBieming()%></a></div>
                    </form>
                    </div>
                    
                    <div class="col-sm-12 col-md-12 pad">
                        <div class="detailTitleTxt">
                           	 详细介绍
                        </div>
                        <div class="detailContent" style="line-height:1.8em;">
                           	<%=sousuoShuju.getShujuMark()%>
                        </div>
                    </div>
                </div>

            </div>

            <%@ include file="left.jsp"%>

        </div>
    </div>
<%@ include file="../footer.jsp"%>