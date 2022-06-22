<template >
  <div>
  <el-table
      :data="tableData"
      border
      style="width: 100%">
    <el-table-column
        fixed
        prop="id"
        label="号码"
        width="150">
    </el-table-column>
    <el-table-column
        prop="age"
        label="年龄"
        width="120">
    </el-table-column>
    <el-table-column
        prop="name"
        label="姓名"
        width="500">
    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="150">
      <template v-slot="scope">
        <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
        <el-button @click="deleteUser(scope.row)" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      background
      layout="prev, pager, next"
      page-size="5"
      :total="total"
  @current-change="page">
  </el-pagination>
  </div>
</template>

<script>
export default {
  methods: {
    deleteUser(row) {

      axios.delete('http://localhost:9494/user/deleteById/'+row.id).then(function (resp){
        window.location.reload()
      })

    },
    edit(row){
      this.$router.push({
        path:'/PageThree',
        query:{
          id:row.id
        }
      })

    },
    page(currentPage){
      const _this=this
      axios.get('http://localhost:9494/user/findAll/'+currentPage+'/5')
          .then(function (resp){
            _this.tableData=resp.data.content
            _this.total=resp.data.totalElements
          })
    }
  },
created() {
    const _this=this
axios.get('http://localhost:9494/user/findAll/0/5')
    .then(function (resp){
      _this.tableData=resp.data.content
      _this.total=resp.data.totalElements
  })
},
  data() {
    return {
      total:null,
      tableData:null
    }
  }
}
</script>