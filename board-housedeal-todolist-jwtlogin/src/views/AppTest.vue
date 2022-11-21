<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-green">
      <b-icon icon="instagram"></b-icon> 인별그램 Service
    </h3>

    <p style="margin-top: -12px">
      <em class="link">
        <a href="/web/documentation/#CategoryCode" target="_blank"
          >카테고리 코드목록을 보시려면 여기를 클릭하세요!</a
        >
      </em>
    </p>
    <div id="map" style="width: 100%; height: 350px"></div>
  </b-container>
</template>

<script>
export default {
  name: "AppInstagram",
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
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5012863640697, 127.039602741448),
        level: 3,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      /////////////////////////////////////////////////

      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(this.map);

      // 카테고리로 은행을 검색합니다
      ps.categorySearch("BK9", this.placesSearchCB, { useMapBounds: true });
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
        }
      }
    },

    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "click", function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        this.infowindow.setContent(
          '<div style="padding:5px;font-size:12px;">' +
            place.place_name +
            "</div>"
        );
        this.infowindow.open(this.map, marker);
      });
    },
  },
};
</script>

<style scoped>
</style>
