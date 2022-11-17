<template>
  <div style="height: calc(100vh - 65px)">
    <div id="map"></div>
    <div class="button-group">
      <!-- <button @click="displayMarker(markerPositions1)">marker set 1</button>
      <button @click="displayMarker(markerPositions2)">marker set 2</button>
      <button @click="displayMarker([])">marker set 3 (empty)</button>
      <button @click="displayInfoWindow">infowindow</button> -->
      <!-- <button @click="findHouse">검색</button> -->
    </div>
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
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      overlays: [],
      infowindow: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
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
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.541, 126.986),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    check() {
      alert("눌렀습니다");
      console.log("눌렀습니다");
    },
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse(item) {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(item);
    },
    priceFilter(price) {
      price = parseInt(price.replace(",", "")) * 10000;
      if (price > 100000000) {
        price = price / 100000000 + "억";
      } else {
        price = price / 10000 + "만";
      }
      //price = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      return price;
    },
    findHouse() {
      let i = 0;
      for (let k = 0; k < this.markers.length; k++) {
        this.markers[k].setMap(null);
        this.overlays[k].setMap(null);
      }
      //오버레이 지우는 코드 넣기

      this.markers = [];
      this.overlays = [];
      this.geocoder = new kakao.maps.services.Geocoder();

      this.houses.forEach((item) => {
        let adress =
          item.도로명 +
          " " +
          item.도로명건물본번호코드 +
          " " +
          item.도로명건물부번호코드;
        this.geocoder.addressSearch(adress, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            //console.log(coords);

            var marker = new kakao.maps.Marker({
              map: this.map,
              position: coords,
            });

            // var content =
            //   '<div class="overlay"><a><span class="title">' +
            //   item.거래금액 +
            //   "만원</span></a></div>";

            const content = document.createElement("div");
            content.className = "overlay";
            const a = document.createElement("a");
            const span = document.createElement("span");
            span.className = "title";
            //span.appendChild(document.createTextNode(item.거래금액 + "만"));
            span.appendChild(
              document.createTextNode(this.priceFilter(item.거래금액))
            );
            a.appendChild(span);
            content.appendChild(a);

            content.onclick = () => {
              this.selectHouse(item);
              //console.log(item);
            };

            var overlay = new kakao.maps.CustomOverlay({
              map: this.map,
              position: coords,
              content: content,
              yAnchor: 1,
            });

            i++;
            this.markers.push(marker);
            this.overlays.push(overlay);
            if (i == 1) {
              this.map.panTo(coords);
            }
          }
        });
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
