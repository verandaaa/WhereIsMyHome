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
      v-if="open"
      class="house-detail"
      style="
        position: absolute;
        top: 0px;
        left: 0px;
        z-index: 1;
        width: 400px;
        height: 100%;
        background-color: white;
        <!-- border: 1px solid red; -->
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

import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "AppHouse",
  components: {
    HouseSearchBar,
    HouseDetail,
    HouseMap,
    HouseMenu,
  },
  computed: {
    ...mapState(houseStore, ["open"]),
  },
  created() {
    this.CLEAR_OPEN();
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_OPEN"]),
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
.house-detail {
  overflow-x: hidden;
  overflow-y: auto;
  padding: 10px;
}
.house-detail::-webkit-scrollbar {
  width: 15px;
}
.house-detail::-webkit-scrollbar-thumb {
  background-color: #e2e2e2;
  border-radius: 10px;
  background-clip: padding-box;
  border: 4px solid transparent;
}
.house-detail::-webkit-scrollbar-track {
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0px 0px 2px white;
}
</style>
