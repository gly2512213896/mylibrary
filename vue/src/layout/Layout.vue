<template>
  <div >
    <!--    头部-->
    <Header/>
    <!--    主体-->
    <div id="building" style="display: flex">
      <!--      侧边栏-->
      <div id="aside">
        <Aside/>
      </div>
      <!--      内容区域-->
      <router-view id="content"  style="flex: 1;"/>

    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
import Aside from "../components/Aside";

export default {
  name: "Layout",
  components:{
    Header,
    Aside
  }
}
</script>

<style scoped>
#content{
  z-index:1;
}
#building{
  background:url("../assets/images/borrowBG.png");
  width:100%;
  height:100%;
  position:fixed;
  overflow: hidden;
  z-index: -1;
}

#aside{
  z-index: 1;
}
</style>