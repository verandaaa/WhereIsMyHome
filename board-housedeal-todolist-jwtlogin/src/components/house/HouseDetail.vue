<template>
  <div>
    <div>
      <house-detail-main></house-detail-main>
      <house-detail-graph></house-detail-graph>
      <house-detail-around></house-detail-around>
    </div>

    <!-- <b-button v-b-toggle.sidebar-1>Toggle Sidebar</b-button>
    <b-sidebar id="sidebar-1" title="Sidebar" shadow>
      <house-detail-main></house-detail-main>
      <house-detail-graph></house-detail-graph>
      <house-detail-around></house-detail-around>
    </b-sidebar> -->
  </div>
</template>

<script>
import HouseDetailMain from "@/components/house/item/HouseDetailMain";
import HouseDetailGraph from "@/components/house/item/HouseDetailGraph";
import HouseDetailAround from "@/components/house/item/HouseDetailAround";

export default {
  name: "HouseDetail",
  components: {
    HouseDetailMain,
    HouseDetailGraph,
    HouseDetailAround,
  },
  data() {
    return {
      popupVal: false,
    };
  },
};
</script>

<style></style>
