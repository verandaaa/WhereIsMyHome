<template>
  <div id="app">
    <the-header-navbar></the-header-navbar>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "App",
  components: {
    TheHeaderNavbar,
  },
  computed: {
    ...mapState(houseStore, ["houseinfos"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseInfos"]),
  },
  created() {
    if (this.houseinfos.length == 0) {
      // console.log(this.houseinfos);
      // console.log(this.houseinfos.length, "배열 길이 0");
      this.getHouseInfos();
    } else {
      // console.log(this.houseinfos.length, "배열 길이 0 초과");
    }
  },
};
</script>

<style>
@font-face {
  font-family: "MICEGothic Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-01@1.0/MICEGothic Bold.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}
#app {
  font-family: "MICEGothic Bold";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
