<template>
  <div style="height: 100%">
    <div style="height: 50px"></div>
    <div
      style="
        height: calc(100% - 100px);
        overflow-x: hidden;
        overflow-y: auto;
        padding: 10px 5px 10px 10px;
      "
      class="house-scroll"
    >
      <div id="hdm"><star-detail-main></star-detail-main></div>
      <div style="border-bottom: 1.5px solid #d3d3d3; margin: 20px 0"></div>
      <div id="hdp">
        <star-detail-price></star-detail-price>
      </div>
      <div style="border-bottom: 1.5px solid #d3d3d3; margin: 20px 0"></div>
      <div id="hdg">
        <h3 style="margin: 0 0 25px 0">년도별 평균 거래가</h3>
        <star-detail-graph
          :chartData="chartData"
          :chartOptions="chartOptions"
          :width="100"
          :height="50"
        ></star-detail-graph>
      </div>
      <div style="border-bottom: 1.5px solid #d3d3d3; margin: 20px 0"></div>

      <div id="hda"><star-detail-around></star-detail-around></div>
    </div>
  </div>
</template>

<script>
import StarDetailMain from "@/components/star/item/StarDetailMain";
import StarDetailGraph from "@/components/star/item/StarDetailGraph";
import StarDetailAround from "@/components/star/item/StarDetailAround";
import StarDetailPrice from "@/components/star/item/StarDetailPrice";

import { mapState, mapActions } from "vuex";

import { getYearlyPrice } from "@/api/house.js";

const houseStore = "houseStore";

export default {
  name: "StarView",
  components: {
    StarDetailMain,
    StarDetailGraph,
    StarDetailAround,
    StarDetailPrice,
  },
  data() {
    return {
      popupVal: false,
      chartData: {
        // 거래년도
        labels: [],
        datasets: [
          {
            label: "매매가격",
            backgroundColor: "#f87979",
            // 거래년도별 평균가격
            data: [],
            borderColor: "red",
          },
        ],
      },
      chartOptions: {
        responsive: true,
      },
    };
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    setGraph() {
      // console.log("this.house.aptCode,", this.house.aptCode);
      getYearlyPrice(
        this.house.aptCode,
        ({ data }) => {
          console.log(data["year"]);
          console.log(data["avgPrice"]);

          this.chartData.labels = data["year"];
          this.chartData.datasets[0].data = data["avgPrice"];
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  created() {
    this.setGraph();
  },
};
</script>

<style scoped>
.house-scroll::-webkit-scrollbar {
  width: 15px;
}
.house-scroll::-webkit-scrollbar-thumb {
  background-color: #e2e2e2;
  border-radius: 10px;
  background-clip: padding-box;
  border: 4px solid transparent;
}
.house-scroll::-webkit-scrollbar-track {
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0px 0px 2px white;
}
a {
  color: white;
  text-decoration: none;
  font-size: 14px;
}
a:hover {
  color: white;
  text-decoration: none;
}
</style>
