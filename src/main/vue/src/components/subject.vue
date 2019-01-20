<template>
  <div class="div1">
    <div class="panel panel-primary">
      <div class="input-group div2">
        <input type="text" v-model="sex" class="form-control input-group-sm" placeholder="输入性别进行搜索" >
        <span class="input-group-btn">
           <button @click="Search(sex)" class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
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
          <tr v-for="(sub,index) in list" :key="index">
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
</template>

<script>
export default {
  name: 'subject',
  data () {
    return {
      list: [],
      subRow: { id: 0, name: '', age: '', sex: '', txt: '' },
      sex: ''
    }
  },
  created () {
    this.getList()
  },
  methods: {
    getList () {
      let this_ = this
      this.$http.get('/api/demo/selectAll')
        .then(function (response) {
          this_.list = response.data.list
          console.log(response.data)
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    Save (event) {
      let this_ = this
      if (this.subRow.id === 0) {
        this.$http.get('/api/demo/addUser',
          {
            data: this.subRow
          })
          .then(function (response) {
            alert('新增成功！')
            this_.getList()
            console.log(response.data)
          })
          .catch(function (error) {
            console.log(error)
          })
      } else {
        this.$http.get('/api/demo/upUser',
          {
            sub: this.subRow
          })
          .then(function (response) {
            alert('修改成功！')
            this_.getList()
            console.log(response.data)
          })
          .catch(function (error) {
            console.log(error)
          })
      }
      this.subRow = { id: 0, name: '', age: '', sex: '', txt: '' }
    },
    Delete (id) {
      let this_ = this
      if (!confirm('您确定要删除选择的数据吗？')) {
        return false
      }
      this.$http.get('/api/demo/deUser?id=' + id)
        .then(function (response) {
          alert('删除成功！')
          this_.getList()
          console.log(response.data)
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    Edit: function (sub) {
      this.subRow = sub
    },
    Search (sex) {
      let this_ = this
      this.$http.get('/api/demo/selectUser?sex=' + sex)
        .then(function (response) {
          this_.list = response.data
          console.log(response.data)
        })
        .catch(function (error) {
          console.log(error)
        })
      this.sex = ''
    }
  }
}
</script>

<style scoped>
.div1{
  padding: 20px;
 }
 .div2{
   margin-bottom: 10px;
 }
</style>
