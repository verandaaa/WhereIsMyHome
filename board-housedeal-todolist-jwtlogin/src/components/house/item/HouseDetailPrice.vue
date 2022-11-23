<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h5>실거래가 내역</h5></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          id="tbrecords"
          striped
          hover
          :items="records"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
        >
        </b-table>
        <b-pagination
          pills
          align="center"
          size="bg"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="tbrecords"
        ></b-pagination>
        <p class="mt-3">전체페이지: {{ currentPage }} / {{ totalPage() }}</p>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import { listArticle } from "@/api/board";
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "BoardList",

  data() {
    return {
      currentPage: 1,
      perPage: 10,
      records: [],
      fields: [
        { key: "dealDate", label: "거래일", tdClass: "tdDealDate" },
        { key: "dealAmount", label: "가격", tdClass: "tdAmount" },
        { key: "floor", label: "층", tdClass: "tdFloor" },
        { key: "area", label: "면적", tdClass: "tdArea" },
      ],
    };
  },
  created() {
    this.setDealList();
  },

  watch: {
    house(newVal, oldVal) {
      console.log("new :" + newVal + " / old : " + oldVal);
      this.setDealList();
    },
  },

  methods: {
    setDealList() {
      const api = apiInstance();
      api.get(`/deal/${this.house.aptCode}`).then(({ data }) => {
        this.records = data;
      });
    },
    totalPage() {
      return Math.ceil(this.rows / this.perPage);
    },
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    rows() {
      return this.records.length;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
