<template>
  <div>
    <h3 style="margin: 10px 0 25px 0">최근 거래 정보</h3>
    <b-row class="mb-2 mt-1">
      <b-col><div id="map3" style="width: 100%; height: 250px"></div></b-col>
    </b-row>
    <!-- <b-row
      ><b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img></b-col
    ></b-row> -->
    <b-row>
      <b-col>
        <table>
          <tr>
            <td colspan="3">
              <div><span>매매가격</span></div>
              <div>
                {{
                  (parseInt(house.dealAmount.replace(",", "")) * 10000) | price
                }}원
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="3">
              <div><span>거래일</span></div>
              <div>{{ house.dealDate }}</div>
            </td>
          </tr>
          <tr>
            <td>
              <div><span>면적</span></div>
              <div>{{ house.area | ftChange }}평</div>
            </td>
            <td>
              <div><span>층수</span></div>
              <div>{{ house.floor }}</div>
            </td>
            <td>
              <div><span>건축년도</span></div>
              <div>{{ house.buildYear }}</div>
            </td>
          </tr>
        </table>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetailMain",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    ftChange(value) {
      return Math.round(value / 3.3058);
    },
  },
  watch: {
    house() {
      this.updateMap();
    },
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
      const container3 = document.getElementById("map3");
      const options3 = {
        center: new kakao.maps.LatLng(this.house.lat, this.house.lng),
        level: 2,
      };
      this.map3 = new kakao.maps.Map(container3, options3);

      // 마커를 생성합니다
      this.marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      this.marker.setMap(this.map3);

      this.map3.setDraggable(false);
      this.map3.setZoomable(false);
    },
    updateMap() {
      this.marker.setMap(null);

      // 마커를 생성합니다
      this.marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      this.marker.setMap(this.map3);

      this.setCenter();
    },
    setCenter() {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);

      // 지도 중심을 이동 시킵니다
      this.map3.setCenter(moveLatLon);
    },
  },
};
</script>

<style scoped>
table,
td {
  border: 1px solid #d2d2d2;
}

table {
  width: 100%;
  border-collapse: collapse;
}

td {
  padding: 15px;
}

span {
  color: rgb(92, 92, 92);
  font-size: 14px;
}
</style>
