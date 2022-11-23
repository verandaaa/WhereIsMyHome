<template>
  <div class="box-wrap">
    <div style="font-size: 20px; font-weight: 900; margin: 10px 0">
      검색 방법을 선택하세요
    </div>

    <b-tabs v-model="tabIndex" align="center">
      <b-tab
        title="주소로 검색"
        :title-link-class="linkClass(0)"
        @click="selectOptOne"
        ><div class="h-container">
          <div class="item">
            <b-form-select
              v-model="sidoCode"
              :options="sidos"
              @change="gugunList"
              style="width: 110px; font-size: 14px; font-weight: 700"
            ></b-form-select>
          </div>
          <div class="item">
            <b-form-select
              v-model="gugunCode"
              :options="guguns"
              @change="dongList"
              style="width: 110px; font-size: 14px; font-weight: 700"
            ></b-form-select>
          </div>
          <div class="item">
            <b-form-select
              v-model="dongCode"
              :options="dongs"
              style="width: 110px; font-size: 14px; font-weight: 700"
            ></b-form-select>
          </div>
        </div>
        <div>
          <b-button block variant="primary" @click="searchApt">검색</b-button>
        </div></b-tab
      >
      <b-tab
        title="아파트명으로 검색"
        :title-link-class="linkClass(1)"
        @click="selectOptTwo"
        ><div class="s-container">
          <b-form-input
            v-model="aptName"
            @keyup="gal"
            placeholder="아파트 이름"
          ></b-form-input>
        </div>
        <div>
          <b-button block variant="primary" @click="searchApt2">검색</b-button>
        </div></b-tab
      >
    </b-tabs>

    <!-- 검색어 자동완성 실험 -->
    <i class="fas fa-search">
      <input
        v-model="skillInput"
        @input="submitAutoComplete"
        type="text"
        style="margin-bottom: 15px"
      />
    </i>
    <div class="autocomplete disabled">
      <div
        @click="searchSkillAdd"
        style="cursor: pointer"
        v-for="(res, i) in result"
        :key="i"
      >
        {{ res }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import skills from "@/api/skills.js";

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
      tabIndex: 0,

      skillInput: null,
      result: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houseinfos"]),
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
      "selectOpt",
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
    linkClass(idx) {
      if (this.tabIndex === idx) {
        return ["text-primary"];
      } else {
        return ["text-dark"];
      }
    },
    selectOptOne() {
      // alert("주소로 검색 선택");
      this.selectOpt(0);
    },
    selectOptTwo() {
      // alert("아파트명으로 검색 선택");
      this.selectOpt(1);
    },

    submitAutoComplete() {
      const autocomplete = document.querySelector(".autocomplete");
      if (this.skillInput) {
        autocomplete.classList.remove("disabled");
        console.log(this.houseinfos);
        this.result = this.houseinfos.filter((house) => {
          return house.match(new RegExp("^" + this.skillInput, "i"));
        });
      } else {
        autocomplete.classList.add("disabled");
      }
    },
  },
};
</script>

<style scoped>
.h-container {
  /* border: 1px solid red; */
  display: flex;
  justify-content: center;
  margin: 10px 0;
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

.s-container {
  margin: 13.5px 0;
}
</style>
