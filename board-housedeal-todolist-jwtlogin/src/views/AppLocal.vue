<template>
  <b-container class="bv-example-row mt-3 text-center">
    세권 자리 ~~ !!
    <div id="map4" style="width: 500px; height: 500px"></div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "AppLocal",

  data() {
    return {
      places: ["스타벅스", "맥도날드", "다이소"],
      locations: [
        [37.5012987488515, 127.03948026981598],
        [37.20128315163999, 127.11064864341267],
        [37.55060279605923, 127.07435007854544],
      ],
      max: 0,
    };
  },
  computed: {
    ...mapState(houseStore, ["houseinfos"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=892d495e179cd940ef03591ce36ec983&libraries=services&autoload=false";
      document.head.appendChild(script);
    }
  },

  methods: {
    initMap() {
      const container4 = document.getElementById("map4");
      const options4 = {
        center: new kakao.maps.LatLng(37.5012987488515, 127.03948026981598),
        level: 4,
      };
      this.map4 = new kakao.maps.Map(container4, options4);

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places();

      const keywordSearch = (searchOption) => {
        return new Promise((resolve) => {
          this.ps.keywordSearch(
            "스타벅스",
            function (data) {
              console.log(data);
              resolve(data);
            },
            searchOption
          );
        });
      };
      // async-await
      (async () => {
        try {
          // for (const houseinfo of this.houseinfos) {
          for (let i = 0; i < 9000; i += 300) {
            let searchOption = {
              // location: new kakao.maps.LatLng(houseinfo.lat, houseinfo.lng),
              location: new kakao.maps.LatLng(
                this.houseinfos[i].lat,
                this.houseinfos[i].lng
              ),
              radius: 500,
              size: 5,
            };
            await keywordSearch(searchOption);
            // console.log(this.houseinfos[i]);
          }
        } catch (e) {
          console.log(e);
        }
      })();
    },
  },
};
</script>

<style scoped></style>
