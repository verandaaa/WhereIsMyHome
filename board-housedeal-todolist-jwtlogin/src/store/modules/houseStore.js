import { sidoList, gugunList, dongList, houseList } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    region1depthName: null,
    region2depthName: null,
    open: false,
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_REGION_1DEPTH(state) {
      state.region1depthName = null;
    },
    CLEAR_REGION_2DEPTH(state) {
      state.region2depthName = null;
    },
    CLEAR_OPEN(state) {
      state.open = false;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      console.log("SET_GUGUN_LIST도착");
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      console.log("SET_DONG_LIST도착");
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_REGION_1DEPTH(state, region1depthName) {
      state.region1depthName = region1depthName;
    },
    SET_REGION_2DEPTH(state, region2depthName) {
      state.region2depthName = region2depthName;
    },
    SET_DETAIL_CLOSE(state) {
      //console.log(state.open);
      state.open = false;
      //console.log(state.open);
    },
    SET_DETAIL_OPEN(state) {
      state.open = true;
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      console.log("getGugun도착" + sidoCode);
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          //console.log("befere - " + data);
          commit("SET_GUGUN_LIST", data);
          //console.log("after - " + data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      console.log("getDong도착" + gugunCode);
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          //console.log("befere - " + data);
          commit("SET_DONG_LIST", data);
          //console.log("after - " + data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    // # 공공데이터 API 이용
    // getHouseList: ({ commit }, gugunCode) => {
    //   const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
    //   const params = {
    //     LAWD_CD: gugunCode,
    //     DEAL_YMD: "202207",
    //     serviceKey: decodeURIComponent(SERVICE_KEY),
    //   };
    //   houseList(
    //     params,
    //     ({ data }) => {
    //       commit("SET_HOUSE_LIST", data.response.body.items.item);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },

    // # DB 이용
    getHouseList: ({ commit }, dongCode) => {
      // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const params = {
        dongCode: dongCode,
        // DEAL_YMD: "202207",
        // serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        ({ data }) => {
          console.log("DB에서 동코드에 해당하는 최근 매물 목록 불러옴");
          console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    getRegion1depth: ({ commit }, region1depthName) => {
      commit("SET_REGION_1DEPTH", region1depthName);
    },
    getRegion2depth: ({ commit }, region2depthName) => {
      commit("SET_REGION_2DEPTH", region2depthName);
    },
    detailClose: ({ commit }) => {
      commit("SET_DETAIL_CLOSE");
    },
    detailOpen: ({ commit }) => {
      commit("SET_DETAIL_OPEN");
    },
  },
};

export default houseStore;
