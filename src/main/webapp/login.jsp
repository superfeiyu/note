<%--
  Created by IntelliJ IDEA.
  User: junior
  Date: 08/10/2021
  Time: 3:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>云R记</title>
    <link href="statics/css/login.css" rel="stylesheet" type="text/css"/>
    <script src="statics/js/jquery-1.11.3.js" type=text/javascript></script>
    <script src="statics/js/config.js" type=text/javascript></script>
    <script src="statics/js/util.js" type=text/javascript></script>

</head>
<body>
<!--head-->
<div id="head">
    <div class="top">
        <div class="fl yahei18">开启移动办公新时代！</div>
    </div>
</div>

<!--login box-->
<div class="wrapper">
    <div id="box">
        <div class="loginbar">用户登录</div>
        <div id="tabcon">
            <div class="box show">
                <form action="user" method="post" id="loginForm">
                    <input type="hidden" name="actionName" value="login">
                    <input type="text" class="user yahei16" id="userName" name="userName" value=""/><br/><br/>
                    <input type="password" class="pwd yahei16" id="userPwd" name="userPwd" value=""/><br/><br/>
                    <input name="" type="checkbox" value="" class="inputcheckbox"/> <label>记住我</label>&nbsp; &nbsp;
                    <span id="msg" style="color: red; font-size: 12px;">${resultInfo.msg}</span><br/><br/>
                    <input type="button" class="log jc yahei16" value="登 录" onclick="checkLogin()"/><input type="reset"
                                                                                                           value="取 消"
                                                                                                           class="reg jc yahei18"/>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="flash">
    <div class="pos">
        <a bgUrl="statics/images/banner-bg1.jpg" id="flash1" style="display:block;"><img
                src="statics/images/banner_pic1.png"></a>
        <a bgUrl="statics/images/banner-bg2.jpg" id="flash2"><img src="statics/images/banner-pic2.jpg"></a>
    </div>
    <div class="flash_bar">
        <div class="dq" id="f1" onclick="changeflash(1)"></div>
        <div class="no" id="f2" onclick="changeflash(2)"></div>
    </div>
</div>

<!--bottom-->
<div id="bottom">
    <div id="copyright">
        <div class="quick">
            <ul>
                <li><input type="button" class="quickbd iphone" onclick="location.href='http://lezijie.com'"/></li>
                <li><input type="button" class="quickbd android" onclick="location.href='http://lezijie.com'"/></li>
                <li><input type="button" class="quickbd pc" onclick="location.href='http://lezijie.com'"/></li>
                <div class="clr"></div>
            </ul>
            <div class="clr"></div>
        </div>
        <div class="text">
            Copyright © 2006-2026 <a href="http://www.shsxt.com">敦煌石窟.com</a> All Rights Reserved
        </div>
    </div>
</div>
</body>

</html>
