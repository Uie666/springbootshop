<template>
  <div class="home">
    <van-row>
      <van-col span="24">
        <van-tabs @click="onClick" sticky title-active-color="#E32DAB" color="#E32DAB" :line-width="100" :line-height="2">
          <van-tab  title="大大" class="tab">
            <van-card
                num="2"
                price="2.00"
                desc="描述信息"
                title="商品标题"
                thumb="https://img95.699pic.com/xsj/1d/42/zk.jpg!/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast"
            >
              <template #tags>
                <van-tag plain type="danger">标签</van-tag>
                <van-tag plain type="danger">标签</van-tag>
              </template>
              <template #footer>
                <van-button size="mini">按钮</van-button>
                <van-button size="mini">按钮</van-button>
              </template>
            </van-card>

          </van-tab>
          <van-tab  title="打到" class="tab">
            <van-card
                num="2"
                price="2.00"
                desc="描述信息"
                title="商品标题"
                thumb="https://img95.699pic.com/xsj/1d/42/zk.jpg!/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast"
            >
              <template #tags>
                <van-tag plain type="danger">标签</van-tag>
                <van-tag plain type="danger">标签</van-tag>
              </template>
              <template #footer>
                <van-button size="mini">按钮</van-button>
                <van-button size="mini">按钮</van-button>
              </template>
            </van-card>
          </van-tab>
          <van-tab  title="发生" class="tab">
            <van-card
                num="2"
                price="2.00"
                desc="描述信息"
                title="商品标题"
                thumb="https://img95.699pic.com/xsj/1d/42/zk.jpg!/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast"
            >
              <template #tags>
                <van-tag plain type="danger">标签</van-tag>
                <van-tag plain type="danger">标签</van-tag>
              </template>
              <template #footer>
                <van-button size="mini">按钮</van-button>
                <van-button size="mini">按钮</van-button>
              </template>
            </van-card>
          </van-tab>
          <van-tab  title="打赏" class="tab">
            <van-card
                num="2"
                price="2.00"
                desc="描述信息"
                title="商品标题"
                thumb="https://img95.699pic.com/xsj/1d/42/zk.jpg!/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast"
            >
              <template #tags>
                <van-tag plain type="danger">标签</van-tag>
                <van-tag plain type="danger">标签</van-tag>
              </template>
              <template #footer>
                <van-button size="mini">按钮</van-button>
                <van-button size="mini">按钮</van-button>
              </template>
            </van-card>
          </van-tab>
        </van-tabs>
      </van-col>
    </van-row>

  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>
