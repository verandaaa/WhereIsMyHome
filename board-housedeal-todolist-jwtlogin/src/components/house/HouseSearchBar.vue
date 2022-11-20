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
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col>
        <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
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
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "region1depthName",
      "region2depthName",
    ]),
  },
  watch: {
    sidoCode() {
      console.log("시도코드 바뀜");
      this.getGugun(this.sidoCode);
    },
    region2depthName(newValue, oldValue) {
      console.log("new : " + newValue + " / old : " + oldValue);
      const nvsd = newValue.substring(0, 2);
      const nvgg = newValue.substring(0, 5);
      if (!oldValue) oldValue = "00000";
      const ovsd = oldValue.substring(0, 2);

      if (nvsd !== ovsd) {
        //도가 달려졌다 -> 시도 달라졌다.
        console.log("1 : " + this.guguns);
        this.sidoCode = nvsd;
        this.gugunList();
        console.log("6 : " + this.guguns);
      }
      //도는 그대로이다 -> 시만 달라졌다.
      console.log("7 : ");
      this.gugunCode = nvgg;
      this.searchApt();
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.CLEAR_REGION_1DEPTH();
    this.CLEAR_REGION_2DEPTH();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_REGION_1DEPTH",
      "CLEAR_REGION_2DEPTH",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      console.log("dongList 도착");
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
  },
};
</script>

<style></style>
