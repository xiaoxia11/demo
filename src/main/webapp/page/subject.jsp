<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>asien科目信息</title>
<link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<style type="text/css">
</style>
</head>
<body>
<div style="padding:20px;" id="app">
	<div class="panel panel-primary">
        <div class="input-group" style="margin-bottom: 10px;">
            <input type="text" class="form-control input-group-sm" placeholder="输入名称进行搜索" >
            <span class="input-group-btn">
                <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
            </span>
        </div>
		<table class="table table-bordered table-striped text-center">
			<thead>
				<tr>
					<th>姓名</th>
	                <th>年龄</th>
	                <th>性别</th>
	                <th>备注</th>
	                <th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="sub in list">
					<td>{{sub.name}}</td>
	                <td>{{sub.age}}</td>
	                <td>{{sub.sex}}</td>	                
	                <td>{{sub.txt}}</td>
					<td><a href="#" @click="Edit(sub)">编辑</a>&nbsp;&nbsp;<a href="#" @click="Delete(sub.id)">删除</a></td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" v-model="subRow.name" /></td>
					<td><input type="text" class="form-control" v-model="subRow.age" /></td>
					<td><input type="text" class="form-control" v-model="subRow.sex" /></td>
					<td><input type="text" class="form-control" v-model="subRow.txt" /></td>
					<td><button type="button" class="btn btn-primary" v-on:click="Save">保存</button></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/vue/2.5.8/vue.min.js"></script>
<script src="http://www.zymseo.com/js/vue-resource.js"></script>
<script type="text/javascript">

var vue = new Vue({
    el: '#app',    
    data: {
    	list: [],
    	subRow: { id: 0, name: '', age: '', sex: '', txt: '' },
    	subSearch: {englishKey: '', chinaName: '', label: ''},
    	//all: datas.pages,
        //cur: datas.pageNum
    },
    created:function(){
        this.getList();
	},
	methods:{		
        //列表
        getList: function(){
        	let this_ = this;
        	$.ajax({
        		url:"/demo/selectAll",
        		type:"get",
        		data:"",
        		dataType:"json",
        		success:function(res){
        			this_.list = res.list;
        		}
        	});
        },
    	//保存按钮
        Save: function (event) {
        	let this_ = this;
        	if(this.subRow.id==0){       		
        		$.ajax({
                	url:"/demo/addUser",
                	type:"post",
                	data:this.subRow,
                	dataType:"json",
                	headers:{
          		      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
          		    },
                	success:function(data){
                		alert("新增成功!");
                		this_.getList();
                	}
                });	        		
        	}else{
        		$.ajax({
                	url:"/demo/upUser",
                	type:"post",
                	headers:{
          		      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
          		    },
                	data:this.subRow,
                	dataType:"json",
                	success:function(data){
                		alert("修改成功!");
                		this_.getList();
                	}
                });
        	}
            //还原模板
            this.subRow = { id: 0, name: '', age: '', sex: '', txt: '' }
        },
        //删除按钮
        Delete: function (id) {
        	let this_ = this;
        	if (!confirm("您确定要删除选择的数据吗？")){
                return false;
            }
        	$.ajax({
               	url:"/demo/deUser",
               	type:"get",
               	data:"&id="+id,
               	dataType:"json",
               	success:function(data){
               		alert("删除成功!");
               		this_.getList();
               	}
			});
        },
        //修改按钮
        Edit: function (sub) {
            this.subRow = sub;
        }
    }
});

</script>
</body>
</html>