<template>
  <div>
    <b-container class="bv-example-row">
      <b-row>
        <b-col cols="8"
          ><h4>{{ house.아파트 }}</h4></b-col
        >
        <b-col cols="4"><button @click="detail">X</button></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetailTitle",
  computed: {
    ...mapState(houseStore, ["house", "open"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailClose"]),
    detail() {
      this.detailClose();
    },
  },
};
</script>

<style scoped></style>
