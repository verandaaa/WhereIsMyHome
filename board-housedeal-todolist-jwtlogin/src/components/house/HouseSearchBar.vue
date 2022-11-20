<template>
  <div class="box-wrap">
    <b-modal id="modal-1" title="BootstrapVue" @ok="searchApt">
      <div class="item">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
          style="width: 100px; font-size: 13px; font-weight: 700"
        ></b-form-select>
      </div>
      <div class="item">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
          style="width: 100px; font-size: 13px; font-weight: 700"
        ></b-form-select>
      </div>
      <div class="item">
        <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
      </div>
    </b-modal>
    <div class="test">
      {{ find1(sidoCode) }} {{ find2(gugunCode) }}
      <b-button v-b-modal.modal-1>검색</b-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

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
    // console.log("-----------------");
    // console.log(this.sidos);
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
    find1(sidoCode) {
      for (let i = 0; i < this.sidos.length; i++) {
        if (this.sidos[i].value === sidoCode) return this.sidos[i].text;
      }
    },
    find2(gugunCode) {
      for (let i = 0; i < this.guguns.length; i++) {
        if (this.guguns[i].value === gugunCode) return this.guguns[i].text;
      }
    },
  },
};
</script>

<style scoped>
.h-container {
  padding: 5px 20px;
  border-radius: 50px;
  background-color: white;
  border: 1.5px solid gray;
}
.h-container:after {
  clear: both;
  display: block;
  content: "";
}
.h-container .item {
  float: left;
  text-align: left;
  margin: 5px;
}
.h-container .item.last {
  float: right;
  border-right: none;
}

.test {
  padding: 5px 20px;
  border-radius: 50px;
  background-color: white;
  border: 1.5px solid gray;
}
</style>
