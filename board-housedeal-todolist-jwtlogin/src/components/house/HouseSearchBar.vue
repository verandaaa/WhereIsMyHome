<template>
  <div>
    <b-row
      class="mt-4 mb-4 text-center"
      style="
        border: 2px solid white;
        background-color: white;
        border-radius: 5px;
        padding: 15px 0;
      "
    >
      <b-col>
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col>
        <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
      </b-col>
      <b-col>
        <b-button @click="searchApt" variant="primary">검색</b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "region1depthName",
      "region2depthName",
    ]),
  },
  watch: {
    region1depthName() {
      this.sidos.forEach((sido) => {
        if (sido.text === this.region1depthName) {
          this.sidoCode = sido.value;
          this.CLEAR_GUGUN_LIST();
          if (this.sidoCode) this.getGugun(this.sidoCode);
        }
      });
    },
    region2depthName() {
      console.log(this.guguns);
      this.guguns.forEach((gugun) => {
        if (gugun.text === this.region2depthName) {
          this.gugunCode = gugun.value;
          this.searchApt();
        }
      });
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.CLEAR_REGION_1DEPTH();
    this.CLEAR_REGION_2DEPTH();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_REGION_1DEPTH",
      "CLEAR_REGION_2DEPTH",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
  },
};
</script>

<style></style>
