<template>
  <!-- <div>뉴스</div> -->
  <div class="container">
    <b-row>
      <b-col>
        <div class="user-wrap">
          <div class="user-image">
            <b-img
              :src="require('@/assets/news.png')"
              style="width: 100%; border-radius: 15px; filter: brightness(50%)"
            ></b-img>
          </div>
          <div class="user-text">
            <p style="color: white; font-size: 36px">부동산 뉴스</p>
          </div>
        </div>
      </b-col>
    </b-row>
    <div class="listcon">
      <h3 class="title">부동산(RealEstate)</h3>
      <ul class="ti-list-ul">
        <li class="li" v-for="(realEstate, index) in realEstates" :key="index">
          <div>
            <a target="_blank" :href="realEstate.originallink"
              ><div v-html="realEstate.title"></div
            ></a>
          </div>
        </li>
      </ul>
    </div>
    <div class="listcon">
      <h3 class="title">주택(Housing)</h3>
      <ul class="ti-list-ul">
        <li class="li" v-for="(housing, index) in housings" :key="index">
          <div>
            <a target="_blank" :href="housing.originallink"
              ><div v-html="housing.title"></div
            ></a>
          </div>
        </li>
      </ul>
    </div>
    <div class="listcon">
      <h3 class="title">사무실(Office)</h3>
      <ul class="ti-list-ul">
        <li class="li" v-for="(office, index) in offices" :key="index">
          <div>
            <a target="_blank" :href="office.originallink">
              <div v-html="office.title"></div
            ></a>
          </div>
        </li>
      </ul>
    </div>
    <div class="listcon">
      <h3 class="title">경제동향(EconomicTrend)</h3>
      <ul class="ti-list-ul">
        <li
          class="li"
          v-for="(economicTrend, index) in economicTrends"
          :key="index"
        >
          <div>
            <a target="_blank" :href="economicTrend.originallink"
              ><div v-html="economicTrend.title"></div
            ></a>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { apiInstance } from "@/api/index.js";

export default {
  name: "AppNews",

  data() {
    return {
      realEstates: [],
      housings: [],
      offices: [],
      economicTrends: [],
    };
  },

  created() {
    const api = apiInstance();
    let params = {
      realEstate: "부동산",
      housing: "주택",
      office: "사무실",
      economicTrend: "경제동향",
    };

    api.get(`/open/news`, { params: params }).then(({ data }) => {
      console.log("api 결과 도착");
      console.log(data);

      this.economicTrends = data[0].items;
      this.housings = data[1].items;
      this.offices = data[2].items;
      this.realEstates = data[3].items;
    });
  },
};
</script>

<style scoped>
.container {
  margin-top: 100px;
  display: grid;
  place-items: center;
}

.listcon {
  margin-top: 50px;
  text-align: left;
}

a:link {
  color: rgb(148, 141, 141);
  text-decoration: none;
}
a:visited {
  color: rgb(57, 113, 185);
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
a:active {
  text-decoration: none;
}

.title {
  margin-top: 30px;
  margin-bottom: 40px;
  border-bottom: 2px solid #ccc;
  width: 500px;
  padding-bottom: 10px;
}

.li {
  list-style-type: none;
  margin-top: 10px;
}

.user-wrap {
  width: 100%;
  position: relative;
}
.user-wrap img {
  width: 100%;
  vertical-align: middle;
}
.user-text {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 100%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  text-align: center;
}
</style>
