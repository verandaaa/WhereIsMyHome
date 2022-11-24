<template>
  <b-container class="bv-example-row mt-3 text-center">
    세권 자리 ~~ !!
    <div id="map4" style="width: 500px; height: 500px"></div>
  </b-container>
</template>

<script>
export default {
  name: "AppLocal",

  data() {
    return {
      places: ["스타벅스", "맥도날드", "다이소"],
    };
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

      // 검색 옵션 객체
      var searchOption = {
        location: new kakao.maps.LatLng(37.5012987488515, 127.03948026981598),
        radius: 1000,
        size: 5,
      };

      // 키워드로 장소를 검색합니다
      // for (const place of this.places) {
      //   this.ps.keywordSearch(place, this.placesSearchCB, searchOption);
      // }

      //async await

      for (const place of this.places) {
        async () => {
          this.ps.keywordSearch(place, await this.placesSearchCB, searchOption);
        };
      }
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        console.log(data);
      }
    },
  },
};
</script>

<style scoped></style>