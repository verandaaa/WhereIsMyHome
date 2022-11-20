<template>
  <div style="height: 100%">
    <div style="height: 70px; border-bottom: 1.5px solid #d3d3d3">
      <div><house-detail-title></house-detail-title></div>
    </div>
    <div
      style="
        height: calc(100% - 70px);
        overflow-x: hidden;
        overflow-y: auto;
        padding: 10px;
      "
      class="house-scroll"
    >
      <div><house-detail-main></house-detail-main></div>
      <div><house-detail-graph></house-detail-graph></div>
      <div><house-detail-around></house-detail-around></div>
    </div>
  </div>

  <!-- <b-button v-b-toggle.sidebar-1>Toggle Sidebar</b-button>
    <b-sidebar id="sidebar-1" title="Sidebar" shadow>
      <house-detail-main></house-detail-main>
      <house-detail-graph></house-detail-graph>
      <house-detail-around></house-detail-around>
    </b-sidebar> -->
</template>

<script>
import HouseDetailMain from "@/components/house/item/HouseDetailMain";
import HouseDetailGraph from "@/components/house/item/HouseDetailGraph";
import HouseDetailAround from "@/components/house/item/HouseDetailAround";
import HouseDetailTitle from "@/components/house/item/HouseDetailTitle";

export default {
  name: "HouseDetail",
  components: {
    HouseDetailMain,
    HouseDetailGraph,
    HouseDetailAround,
    HouseDetailTitle,
  },
  data() {
    return {
      popupVal: false,
    };
  },
};
</script>

<style>
.house-scroll::-webkit-scrollbar {
  width: 15px;
}
.house-scroll::-webkit-scrollbar-thumb {
  background-color: #e2e2e2;
  border-radius: 10px;
  background-clip: padding-box;
  border: 4px solid transparent;
}
.house-scroll::-webkit-scrollbar-track {
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0px 0px 2px white;
}
</style>
