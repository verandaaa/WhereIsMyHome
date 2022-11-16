<template>
  <div style="position: relative">
    <!-- <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3> -->
    <div>
      <house-map></house-map>
    </div>
    <div style="position: absolute; top: 15px; right: 20px; z-index: 1">
      <house-search-bar></house-search-bar>
    </div>

    <!-- <house-detail /> -->
    <div
      style="position: absolute; top: 0px; left: 0px; z-index: 1; width: 400px"
    >
      <house-detail />
    </div>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import HouseMap from "@/components/house/HouseMap.vue";

export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseDetail,
    HouseMap,
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
