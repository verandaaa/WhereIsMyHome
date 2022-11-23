<template>
  <div style="height: calc(100vh - 60px)">
    <div id="map"></div>
    <ul id="category">
      <li id="BK9" data-order="0">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li id="CS2" data-order="5">
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>
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
      placeOverlay: [],
      contentNode: [],
      markers: [],
      currCategory: [],
      map: [],
      ps: [],
      infowindow: [],
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
      "getAddressName",
    ]),

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5012863640697, 127.039602741448),
        level: 3,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      kakao.maps.event.addListener(this.map, "tilesloaded", this.test);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      //----------------------------------------------------------------
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트
      this.markers = []; // 마커를 담을 배열입니다
      this.currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.map); // 장소 검색 객체

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces); // idle event 등록
      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.classList.add("placeinfo_wrap");

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap
      );
      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);
      // 각 카테고리에 클릭 이벤트를 등록합니다
      this.addCategoryClickEvent(); // 카테고리에 addEvent등록
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
            // console.log(result[i].address_name);
            this.getAddressName(result[i].address_name);
            this.getRegion2depth(result[i].code); //동까지
            break;
          }
        }
      }
    },
    priceFilter(price) {
      price = parseInt(price.replace(",", "")) * 10000;
      if (price >= 100000000) {
        price = Math.round((price / 100000000) * 10) / 10 + "억";
      } else {
        price = Math.round(price / 10000) + "만";
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
        const a = document.createElement("div");
        a.className = "box1 sb8";
        a.appendChild(
          document.createTextNode(this.priceFilter(item.dealAmount))
        );
        const b = document.createElement("div");
        b.className = "box2 sb9";
        b.appendChild(
          document.createTextNode(item.dealDate.substring(0, 4) + "년")
        );
        content.appendChild(a);
        content.appendChild(b);

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
    // 카테고리별 장소 검색하기 ---------------------------
    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order
        );
        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        var self = this;
        (function (marker, place) {
          kakao.maps.event.addListener(marker, "click", function () {
            self.displayplaceinfo(place);
          });
        })(marker, places[i]);
      }
    },
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayplaceinfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }

      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent() {
      var category = document.getElementById("category"),
        children = category.children;
      for (let i = 0; i < children.length; i++) {
        // console.log(children[i]);
        children[i].onclick = () => {
          console.log(children.className);
          this.onClickCategory(children[i].id, children[i].className);
        };
      }
    },
    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(id, className) {
      // var id = this.id,
      //   className = this.className;
      this.placeOverlay.setMap(null);
      console.log(className);
      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        if (id == "BK9") {
          this.changeCategoryClass(
            document.getElementById("category").children.BK9
          );
        } else if (id == "CE7") {
          this.changeCategoryClass(
            document.getElementById("category").children.CE7
          );
        } else if (id == "CS2") {
          this.changeCategoryClass(
            document.getElementById("category").children.CS2
          );
        } else if (id == "MT1") {
          this.changeCategoryClass(
            document.getElementById("category").children.MT1
          );
        } else if (id == "OL7") {
          this.changeCategoryClass(
            document.getElementById("category").children.OL7
          );
        } else if (id == "PM9") {
          this.changeCategoryClass(
            document.getElementById("category").children.PM9
          );
        }
        this.searchPlaces();
      }
    },
    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      var category = document.getElementById("category"),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        el.className = "on";
      }
    },
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
.box1 {
  height: 32px;
  width: 60px;
  border: 1px solid #1876d6;
  text-align: center;
  color: white;
  font-size: 16px;
  font-weight: bold;
  position: relative;
  border-radius: 5px 5px 0 0;
  background-color: #1876d6;
  line-height: 35px;
}

.box2 {
  height: 20px;
  width: 60px;
  border: 1px solid #1876d6;
  text-align: center;
  color: #007bff;
  font-size: 12px;
  font-weight: bold;
  position: relative;
  border-radius: 0 0 5px 5px;
  background-color: white;
}
/* speech bubble 9 */

.sb9:before {
  content: "";
  width: 0px;
  height: 0px;
  position: absolute;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-top: 5px solid #1876d6;
  border-bottom: 5px solid transparent;
  right: 40%;
  bottom: -10px;
}

.sb9:after {
  content: "";
  width: 0px;
  height: 0px;
  position: absolute;
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-top: 4px solid white;
  border-bottom: 3.5px solid transparent;
  right: 41%;
  bottom: -7px;
}

#category {
  position: absolute;
  top: 300px;
  left: 20px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 1;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
