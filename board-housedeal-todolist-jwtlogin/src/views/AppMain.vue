<template>
  <div style="height: calc(100vh - 60px)">
    <div class="box01"></div>
  </div>
</template>

<script>
export default {
  name: "AppMain",
};
</script>

<style scoped>
.box01 {
  height: 100%;
  position: relative;
  background-image: url("@/assets/city.jpg");
  background-size: cover;
}
.box01::before {
  content: "";
  opacity: 0.5;
  position: absolute;
  top: -1px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  background-color: #000;
}
.box01 h1 {
  color: #fff;
  text-align: center;
  line-height: 300px;
  position: relative;
}
</style>
