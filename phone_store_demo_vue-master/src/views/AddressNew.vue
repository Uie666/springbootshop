<template>
    <van-address-edit
            :area-list="areaList"
            show-delete
            @save="onSave"
            @delete="onDelete"
    />
</template>

<script>
    import AreaList from '../api/area';
    import { Toast } from 'vant';
    import {getCookie} from "@/plugins/cookie";
    export default {
        name: "AddressNew",
        data() {
            return {
                areaList: AreaList
            }
        },
        methods: {
            onSave(item) {
                const _this = this
                axios.post('http://localhost:8181/address/create',item).then(function (resp) {

                        let instance = Toast('添加成功');
                        setTimeout(() => {
                            instance.close();
                            _this.$router.push('/addressList')
                        }, 1000)

                })

            },
            onDelete() {
                history.go(-1)
            }
        },
      created(){
        const _this = this
        var username=getCookie('username');
        if(username==''||username=='undefind'){
          this.$router.push({
            path: '/login'
          });
        }
      },
    }
</script>

<style scoped>

</style>