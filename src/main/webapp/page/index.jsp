<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<hr/>
	<form action="/demo/selectAll">
		<button type="submit">查询</button>
    </form>
    <hr/>
    <form action="/demo/selectUser">
		编号：<input name="id" /><br/>
		<button type="submit">查询</button>
    </form>
    <hr/>
    <form action="/demo/addUser">
		编号：<input name="id" /><br/>
		姓名：<input name="name" /><br/>
		年龄：<input name="age" /><br/>
		性别：<input name="sex" /><br/>
		备注：<input name="txt" /><br/>
        <button type="submit">新增</button>
    </form>
    <hr/>
    <form action="/demo/upUser">
    	编号：<input name="id" /><br/>
		姓名：<input name="name" /><br/>
		年龄：<input name="age" /><br/>
		性别：<input name="sex" /><br/>
		备注：<input name="txt" /><br/>
        <button type="submit">修改</button>
    </form>
    <hr/>
    <form action="/demo/deUser">
		编号：<input name="id" /><br/>
        <button type="submit">删除</button>
    </form>
</body>
</html>
