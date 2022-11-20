<template>
  <div>
    <div class="h-container">
      <div class="item">
        <div style="margin: 10px 0 0 20px; font-weight: 900; font-size: 20px">
          {{ house.aptName }}
        </div>
        <div style="margin: 0 0 0 20px; font-size: 13px">
          {{ house.roadName }} {{ house.도로명건물본번호코드 }}-{{
            house.도로명건물부번호코드
          }}
        </div>
      </div>
      <div class="item last">
        <b-img
          :src="require('@/assets/star_false.png')"
          style="width: 20px"
        ></b-img
        ><b-img
          class="x"
          :src="require('@/assets/close.png')"
          style="width: 16px; margin: 25px"
          @click="detail"
        ></b-img>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetailTitle",
  computed: {
    ...mapState(houseStore, ["house", "open"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailClose"]),
    detail() {
      this.detailClose();
    },
  },
};
</script>

<style scoped>
.x:hover {
  cursor: pointer;
}
.h-container:after {
  clear: both;
  display: block;
  content: "";
}
.h-container .item {
  float: left;
  text-align: left;
}
.h-container .item.last {
  float: right;
  border-right: none;
}
</style>
