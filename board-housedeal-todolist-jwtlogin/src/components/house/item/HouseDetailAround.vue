<template>
  <div>
    <h3>주변정보(1km 내)</h3>
    <div id="map2" style="width: 100%; height: 250px"></div>
    <div>
      <b-row>
        <b-col
          :class="[
            whatFacility === 1 ? 'what-facility-on' : 'what-facility-off',
          ]"
          @click="updateWhatFacility(1)"
          >교통시설</b-col
        >
        <b-col
          :class="[
            whatFacility === 2 ? 'what-facility-on' : 'what-facility-off',
          ]"
          @click="updateWhatFacility(2)"
          >교육시설</b-col
        >
        <b-col
          :class="[
            whatFacility === 3 ? 'what-facility-on' : 'what-facility-off',
          ]"
          @click="updateWhatFacility(3)"
          >편의시설</b-col
        >
      </b-row>
      <div>
        <div v-show="whatFacility === 1">
          <div id="info1"></div>
        </div>

        <div v-show="whatFacility === 2">
          <div id="info2"></div>
        </div>

        <div v-show="whatFacility === 3">
          <div id="info3"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetailAround",
  data() {
    return {
      whatFacility: 1,
    };
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  watch: {
    house() {
      this.updateMap();
      this.facility();
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
      const container2 = document.getElementById("map2");
      const options2 = {
        center: new kakao.maps.LatLng(this.house.lat, this.house.lng),
        level: 3,
      };
      this.map2 = new kakao.maps.Map(container2, options2);

      // 마커를 생성합니다
      this.marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      this.marker.setMap(this.map2);

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.map2);

      this.facility();

      this.map2.setDraggable(false);
      this.map2.setZoomable(false);
    },
    updateMap() {
      this.marker.setMap(null);

      // 마커를 생성합니다
      this.marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      this.marker.setMap(this.map2);

      this.setCenter();
    },
    setCenter() {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);

      // 지도 중심을 이동 시킵니다
      this.map2.setCenter(moveLatLon);
    },
    placesSearchCB1(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        let info1 = document.getElementById("info1");
        let category = document.createElement("div");
        let type = document.createElement("h4");
        type.innerText = `${data[0].category_group_name}`;
        category.appendChild(type);

        for (var i = 0; i < data.length; i++) {
          let content = document.createElement("div");
          content.innerText += `${data[i].place_name}`;
          content.innerText += `${data[i].distance}m`;
          category.appendChild(content);
        }
        info1.appendChild(category);
      }
    },
    placesSearchCB2(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        let info2 = document.getElementById("info2");
        let category = document.createElement("div");
        let type = document.createElement("h4");
        type.innerText = `${data[0].category_group_name}`;
        category.appendChild(type);

        for (var i = 0; i < data.length; i++) {
          let content = document.createElement("div");
          content.innerText += `${data[i].place_name}`;
          content.innerText += `${data[i].distance}m`;
          category.appendChild(content);
        }
        info2.appendChild(category);
      }
    },
    placesSearchCB3(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        let info3 = document.getElementById("info3");
        let category = document.createElement("div");
        let type = document.createElement("h4");
        type.innerText = `${data[0].category_group_name}`;
        category.appendChild(type);

        for (var i = 0; i < data.length; i++) {
          let content = document.createElement("div");
          content.innerText += `${data[i].place_name}`;
          content.innerText += `${data[i].distance}m`;
          category.appendChild(content);
        }
        info3.appendChild(category);
      }
    },
    facility() {
      this.ps.categorySearch("SW8", this.placesSearchCB1, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("SC4", this.placesSearchCB2, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("PS3", this.placesSearchCB2, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("AC5", this.placesSearchCB2, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("CS2", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("MT1", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("PK6", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("OL7", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("BK9", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("CT1", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("AG2", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("PO3", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("AT4", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("AD5", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("FD6", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("CE7", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("HP8", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      this.ps.categorySearch("PM9", this.placesSearchCB3, {
        radius: 1000,
        location: new kakao.maps.LatLng(this.house.lat, this.house.lng),
      });
      document.getElementById("info1").innerHTML = "";
      document.getElementById("info2").innerHTML = "";
      document.getElementById("info3").innerHTML = "";
    },
    updateWhatFacility(opt) {
      this.whatFacility = opt;
    },
  },
};
</script>

<style scopped>
.what-facility-on {
  border-bottom: 2px solid #474747;
  margin: 10px;
  padding: 10px;
  font-size: 14px;
}
.what-facility-on:hover {
  cursor: pointer;
}
.what-facility-off {
  margin: 10px;
  padding: 10px;
  font-size: 14px;
}
.what-facility-off:hover {
  cursor: pointer;
}
</style>
