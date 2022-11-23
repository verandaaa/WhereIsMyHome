<template>
  <div style="height: 100%">
    <div style="height: 100px; border-bottom: 1.5px solid #d3d3d3">
      <div style="height: 70px"><house-detail-title></house-detail-title></div>
      <div
        style="
          height: 30px;
          background-color: #084895;
          padding: 3px 15px 0 15px;
        "
      >
        <b-row
          ><b-col><a href="#hdm">메인</a></b-col
          ><span style="color: #215ba0">|</span
          ><b-col><a href="#hdg">차트</a></b-col
          ><span style="color: #215ba0">|</span
          ><b-col><a href="#hdp">목록</a></b-col
          ><span style="color: #215ba0">|</span
          ><b-col><a href="#hda">주변</a></b-col></b-row
        >
      </div>
    </div>
    <div
      style="
        height: calc(100% - 100px);
        overflow-x: hidden;
        overflow-y: auto;
        padding: 10px 5px 10px 10px;
      "
      class="house-scroll"
    >
      <div id="hdm"><house-detail-main></house-detail-main></div>
      <div style="border-bottom: 1.5px solid #d3d3d3; margin: 20px 0"></div>
      <div id="hdp">
        <house-detail-price></house-detail-price>
      </div>
      <div style="border-bottom: 1.5px solid #d3d3d3; margin: 20px 0"></div>
      <div id="hdg">
        <h3 style="margin: 0 0 25px 0">년도별 평균 거래가</h3>
        <house-detail-graph
          :chartData="chartData"
          :chartOptions="chartOptions"
          :width="100"
          :height="50"
        ></house-detail-graph>
      </div>
      <div style="border-bottom: 1.5px solid #d3d3d3; margin: 20px 0"></div>

      <div id="hda"><house-detail-around></house-detail-around></div>
    </div>
  </div>
</template>

<script>
import HouseDetailMain from "@/components/house/item/HouseDetailMain";
import HouseDetailGraph from "@/components/house/item/HouseDetailGraph";
import HouseDetailAround from "@/components/house/item/HouseDetailAround";
import HouseDetailTitle from "@/components/house/item/HouseDetailTitle";
import HouseDetailPrice from "@/components/house/item/HouseDetailPrice";

import { mapState } from "vuex";
import { getYearlyPrice } from "@/api/house.js";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  components: {
    HouseDetailMain,
    HouseDetailGraph,
    HouseDetailAround,
    HouseDetailTitle,
    HouseDetailPrice,
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
    setGraph() {
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
  watch: {
    house(newVal, oldVal) {
      console.log("new :" + newVal + " / old : " + oldVal);
      this.setGraph();
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
