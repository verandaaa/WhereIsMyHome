<template>
  <div class="box-wrap">
    <div class="test">
      {{ addressName }}
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["region2depthName", "addressName"]),
  },
  watch: {
    region2depthName(newValue, oldValue) {
      console.log("new : " + newValue + " / old : " + oldValue);

      const nvd = newValue;
      if (!oldValue) oldValue = "0000000000";

      this.dongCode = nvd;
      this.searchApt();
    },
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    searchApt() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
  },
};
</script>

<style scoped>
.test {
  padding: 5px 10px;
  font-size: 14px;
  border-radius: 50px;
  background-color: white;
  border: 1.5px solid gray;
  font-weight: bold;
}
</style>
