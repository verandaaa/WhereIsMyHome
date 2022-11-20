<template>
  <div class="box-wrap">
    <b-form-group label="검색 방법을 선택하세요" v-slot="{ ariaDescribedby }">
      <b-form-radio-group
        id="btn-radios-2"
        v-model="selected"
        :options="options"
        :aria-describedby="ariaDescribedby"
        button-variant="outline-primary"
        name="radio-btn-outline"
        buttons
      ></b-form-radio-group>
    </b-form-group>

    <!-- <b-form-group label="검색 방법을 선택하세요" v-slot="{ ariaDescribedby }">
      <b-form-radio
        v-model="selected"
        :aria-describedby="ariaDescribedby"
        name="some-radios"
        value="A"
        >동 검색</b-form-radio
      >
      <b-form-radio
        v-model="selected"
        :aria-describedby="ariaDescribedby"
        name="some-radios"
        value="B"
        >이름 검색</b-form-radio
      >
    </b-form-group> -->

    <div v-if="selected === 'A'">
      <div class="h-container">
        <div class="item">
          <b-form-select
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
            style="width: 120px; font-size: 16px; font-weight: 700"
          ></b-form-select>
        </div>
        <div class="item">
          <b-form-select
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"
            style="width: 120px; font-size: 16px; font-weight: 700"
          ></b-form-select>
        </div>
        <div class="item">
          <b-form-select
            v-model="dongCode"
            :options="dongs"
            style="width: 120px; font-size: 16px; font-weight: 700"
          ></b-form-select>
        </div>
      </div>
      <div><button @click="searchApt">검색</button></div>
    </div>
    <div v-else>
      <div>
        <b-form-input
          v-model="aptName"
          @keyup="gal"
          placeholder="아파트 이름"
        ></b-form-input>
        <button @click="searchApt2">검색</button>
      </div>
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
      aptName: "",
      selected: "radio1",
      options: [
        { text: "주소 검색", value: "A" },
        { text: "아파트 검색", value: "B" },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getHouseList2",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
    searchApt2() {
      if (this.aptName) this.getHouseList2(this.aptName);
    },
    gal() {
      console.log(this.aptName);
    },
  },
};
</script>

<style scoped>
.h-container {
  /* border: 1px solid red; */
  display: flex;
  justify-content: center;
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
.box-wrap {
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.16), 0 1px 2px rgba(0, 0, 0, 0.24);
  padding: 10px;
}
</style>
