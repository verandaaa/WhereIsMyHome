<template>
  <div class="con">
    <div id="slider">
      <input type="radio" name="slider" id="s1" checked />
      <input type="radio" name="slider" id="s2" />
      <input type="radio" name="slider" id="s3" />
      <input type="radio" name="slider" id="s4" />
      <input type="radio" name="slider" id="s5" />

      <label for="s1" id="slide1">
        <div>스세권</div>
        <div>1위</div>
      </label>
      <label for="s2" id="slide2">2</label>
      <label for="s3" id="slide3">3</label>
      <label for="s4" id="slide4">4</label>
      <label for="s5" id="slide5">5</label>
    </div>

    <!-- 세권 자리 ~~ !!
    <div id="map4" style="width: 500px; height: 500px"></div> -->
  </div>
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
              // console.log(data);
              resolve(data);
            },
            searchOption
          );
        });
      };
      // async-await
      (async () => {
        try {
          const results = [];
          // for (const houseinfo of this.houseinfos) {
          for (let i = 0; i < 9000; i += 3000) {
            let searchOption = {
              // location: new kakao.maps.LatLng(houseinfo.lat, houseinfo.lng),
              location: new kakao.maps.LatLng(
                this.houseinfos[i].lat,
                this.houseinfos[i].lng
              ),
              radius: 1000,
              size: 5,
            };
            const result = await keywordSearch(searchOption);
            results.push(result);
          }
          console.log(results);
        } catch (e) {
          console.log(e);
        }
      })();
    },
  },
};
</script>

<style scoped>
body {
  margin: 0;
  background: #eee;
  user-select: none;
}
#slider {
  position: relative;
  width: 50%;
  height: 30vw;
  margin: 80px auto;
  perspective: 1400px;
  transform-style: preserve-3d;
}

input[type="radio"] {
  position: relative;
  top: 108%;
  left: 50%;
  width: 18px;
  height: 18px;
  margin: 0 15px 0 0;
  opacity: 0.4;
  transform: translateX(-83px);
  cursor: pointer;
}

input[type="radio"]:nth-child(5) {
  margin-right: 0px;
}

input[type="radio"]:checked {
  opacity: 1;
}

#slider label {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  color: white;
  font-size: 70px;
  font-weight: bold;
  border-radius: 5px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: transform 400ms ease;
}

#slide1 {
  background: tomato;
}

#slide2 {
  background: yellowgreen;
}

#slide3 {
  background: dodgerblue;
}

#slide4 {
  background: slateblue;
}

#slide5 {
  background: violet;
}

/* Slider Functionality */

/* Active Slide */
#s1:checked ~ #slide1,
#s2:checked ~ #slide2,
#s3:checked ~ #slide3,
#s4:checked ~ #slide4,
#s5:checked ~ #slide5 {
  box-shadow: 0 13px 26px rgba(0, 0, 0, 0.3), 0 12px 6px rgba(0, 0, 0, 0.2);
  transform: translate3d(0%, 0, 0px);
}

/* Next Slide */
#s1:checked ~ #slide2,
#s2:checked ~ #slide3,
#s3:checked ~ #slide4,
#s4:checked ~ #slide5,
#s5:checked ~ #slide1 {
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.3), 0 2px 2px rgba(0, 0, 0, 0.2);
  transform: translate3d(15%, 0, -100px);
}

/* Next to Next Slide */
#s1:checked ~ #slide3,
#s2:checked ~ #slide4,
#s3:checked ~ #slide5,
#s4:checked ~ #slide1,
#s5:checked ~ #slide2 {
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.4);
  transform: translate3d(30%, 0, -250px);
}

/* Previous to Previous Slide */
#s1:checked ~ #slide4,
#s2:checked ~ #slide5,
#s3:checked ~ #slide1,
#s4:checked ~ #slide2,
#s5:checked ~ #slide3 {
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.4);
  transform: translate3d(-30%, 0, -250px);
}

/* Previous Slide */
#s1:checked ~ #slide5,
#s2:checked ~ #slide1,
#s3:checked ~ #slide2,
#s4:checked ~ #slide3,
#s5:checked ~ #slide4 {
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.3), 0 2px 2px rgba(0, 0, 0, 0.2);
  transform: translate3d(-15%, 0, -100px);
}
</style>
