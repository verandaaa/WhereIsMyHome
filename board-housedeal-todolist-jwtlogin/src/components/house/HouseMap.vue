<template>
  <div style="height: calc(100vh - 65px)">
    <div id="map"></div>
    <div class="button-group"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      overlays: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "open"]),
  },
  watch: {
    houses() {
      this.findHouse();
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
    ...mapActions(houseStore, [
      "detailHouse",
      "getRegion1depth",
      "getRegion2depth",
      "detailOpen",
    ]),

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5012863640697, 127.039602741448),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      kakao.maps.event.addListener(this.map, "tilesloaded", this.test);
    },
    selectHouse(item) {
      this.detailHouse(item);
    },
    test() {
      this.geocoder = new kakao.maps.services.Geocoder();

      //console.log(this.map.getCenter());

      this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);
    },
    searchAddrFromCoords(coords, callback) {
      // 좌표로 행정동 주소 정보를 요청합니다
      this.geocoder.coord2RegionCode(
        coords.getLng(),
        coords.getLat(),
        callback
      );
    },
    displayCenterInfo(result, status) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < result.length; i++) {
          // 행정동의 region_type 값은 'H' 이므로
          if (result[i].region_type === "B") {
            // let region1depthName = result[i].region_1depth_name;
            // let region2depthName =
            //   result[i].region_1depth_name + " " + result[i].region_2depth_name;
            // console.log("!!!!!!!!!!!!!!!!!!!!!" + result[i].code);
            // console.log("!!!!!!!!!!!!!!!!!!!!!" + result[i].b_code);

            // this.getRegion1depth(region1depthName);
            this.getRegion2depth(result[i].code.substring(0, 5));
            break;
          }
        }
      }
    },
    priceFilter(price) {
      price = parseInt(price.replace(",", "")) * 10000;
      if (price >= 100000000) {
        price = price / 100000000 + "억";
      } else {
        price = price / 10000 + "만";
      }
      return price;
    },

    findHouse() {
      let i = 0;
      for (let k = 0; k < this.overlays.length; k++) {
        this.overlays[k].setMap(null);
      }

      this.overlays = [];
      this.geocoder = new kakao.maps.services.Geocoder();

      this.houses.forEach((item) => {
        let coords = new kakao.maps.LatLng(item.lat, item.lng);

        const content = document.createElement("div");
        content.className = "overlay";
        const a = document.createElement("a");
        const span = document.createElement("span");
        span.className = "title";
        span.appendChild(
          document.createTextNode(this.priceFilter(item.dealAmount))
        );
        a.appendChild(span);
        content.appendChild(a);

        content.onclick = () => {
          this.selectHouse(item);
          this.detailOpen();
        };

        var overlay = new kakao.maps.CustomOverlay({
          map: this.map,
          position: coords,
          content: content,
          yAnchor: 1,
        });

        i++;
        this.overlays.push(overlay);
        if (i == 1) {
          this.map.panTo(coords);
        }
      }); // foreach
    }, // findhouse

    // # 기존 버전(도로명 주소로 위도경도 얻어서 오버레이 생성)
    // findHouse() {
    //   let i = 0;
    //   for (let k = 0; k < this.overlays.length; k++) {
    //     this.overlays[k].setMap(null);
    //   }

    //   this.overlays = [];
    //   this.geocoder = new kakao.maps.services.Geocoder();

    //   this.houses.forEach((item) => {
    //     let adress =
    //       item.도로명 +
    //       " " +
    //       item.도로명건물본번호코드 +
    //       " " +
    //       item.도로명건물부번호코드;
    //     this.geocoder.addressSearch(adress, (result, status) => {
    //       if (status === kakao.maps.services.Status.OK) {
    //         let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    //         const content = document.createElement("div");
    //         content.className = "overlay";
    //         const a = document.createElement("a");
    //         const span = document.createElement("span");
    //         span.className = "title";
    //         span.appendChild(
    //           document.createTextNode(this.priceFilter(item.거래금액))
    //         );
    //         a.appendChild(span);
    //         content.appendChild(a);

    //         content.onclick = () => {
    //           this.selectHouse(item);
    //           this.detailOpen();
    //         };

    //         var overlay = new kakao.maps.CustomOverlay({
    //           map: this.map,
    //           position: coords,
    //           content: content,
    //           yAnchor: 1,
    //         });

    //         i++;
    //         this.overlays.push(overlay);
    //         if (i == 1) {
    //           this.map.panTo(coords);
    //         }
    //       } // if
    //     }); //addressSearch
    //   }); //foreach
    // }, // findhouse
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 100%;
}

.overlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.overlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.overlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.overlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.overlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
