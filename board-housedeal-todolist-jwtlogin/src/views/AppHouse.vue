<template>
  <div style="position: relative; height: calc(100vh - 65px)">
    <!-- <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3> -->
    <div>
      <house-map></house-map>
    </div>
    <div style="position: absolute; top: 15px; right: 50px; z-index: 1">
      <house-search-bar></house-search-bar>
    </div>

    <!-- <house-detail /> -->
    <div
      style="
        position: absolute;
        top: 0px;
        left: 0px;
        z-index: 1;
        width: 400px;
        overflow: scroll;
        height: 100%;
        background-color: white;
      "
    >
      <house-detail />
    </div>

    <div style="position: absolute; top: 135px; right: 50px; z-index: 1">
      <house-menu></house-menu>
    </div>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseMenu from "@/components/house/HouseMenu.vue";

export default {
  name: "AppHouse",
  components: {
    HouseSearchBar,
    HouseDetail,
    HouseMap,
    HouseMenu,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
