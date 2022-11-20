<template>
  <div style="position: relative; height: calc(100vh - 60px)">
    <div>
      <house-map></house-map>
    </div>
    <div
      style="
        position: absolute;
        top: 20px;
        left: 50%;
        transform: translate(-50%, 0);
        z-index: 1;
      "
    >
      <house-current-adress></house-current-adress>
    </div>

    <div
      style="
        position: absolute;
        top: 10px;
        left: 10px;
        z-index: 1;
        width: 380px;
      "
    >
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
        border-right: 1.5px solid #d3d3d3;
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
import HouseCurrentAdress from "@/components/house/HouseCurrentAdress.vue";

import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "AppHouse",
  components: {
    HouseSearchBar,
    HouseDetail,
    HouseMap,
    HouseMenu,
    HouseCurrentAdress,
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
</style>
