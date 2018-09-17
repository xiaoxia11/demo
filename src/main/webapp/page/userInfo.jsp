<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
    <form action="/demo/selectUser" id="searchUser" method="get">
        <div class="form-group">
            <label class="col-xs-3">编号：</label>
            <div class="col-xs-3">
                <input id="id" name="id" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <button id="searchBtn" type="submit">查询</button>
                <button id="sesetBtn" type="reset">重置</button>
            </div>
        </div>
    </form>
    <div id="userGrid" class="row"></div>
</body>
</html>

<script type="text/javascript">

$(function(){
	bindGrid();
	
	$("#searchBtn").submit(function(e){
		bindGrid();
	});
});

function bindGrid(){
	$("#userGrid").kendoGrid({
		columns:[
                 {field:"id"title:"编号"},
                 {field:"name"title:"姓名"},
                 {field:"age"title:"年龄"},
                 {field:"sex"title:"性别"},
                 {field:"stxt",title:"备注"}
                ],
		dataSource:{
			transport:{
				read:function(options){
					$.ajax({	     
  						url:"/selectAll.do",
						data:$("#searchUser").serialize(),
						dataType:"josn",
						type:"POST",
						success:function(result){
							options.success(result);
						},
						error:function(result){
							options.error(result);
						}
    		        });
    		    }
    		},
    		serverPaging:true,
            serverSorting:true,
    		serverFiltering:true,
    		schema:{
    		    data:function(response){
    		        return response.data;
    		    },
    		    total:function(response){
                	return response.total;
    		    }
    		}
        }
	});
}
    
</script>