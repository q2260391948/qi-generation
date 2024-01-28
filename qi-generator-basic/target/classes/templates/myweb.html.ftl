<!DOCTYPE html>
<html>
<head>
    <title>我的官网</title>
</head>
<body>
<h1>欢迎来到小张的项目</h1>
<#--默认值-->
${userName!"用户为空"}
<ul>
    <#-- 循环渲染导航条 list遍历 -->
    <#list menuItems as item>
        <li><a href="${item.url}">${item.label}</a></li>
    </#list>
</ul>
<#-- 插值与内联函数应用（转换为大写）-->
<footer>
    ${currentYear?upper_case} 我的官网. All rights reserved.
</footer>
<#--if判断-->
<#if user == "小张">
    我是小张
<#else>
    我是小王
</#if>
<#--if判空-->
<#if user??>
    ${user}用户存在
<#else>
    ${user}用户不存在
</#if>

</body>
</html>