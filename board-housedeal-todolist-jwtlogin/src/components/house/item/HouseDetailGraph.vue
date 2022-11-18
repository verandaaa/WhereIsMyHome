<template>
  <div>
    이 곳은 그래프가 나올 임시 컴포넌트입니다 !!! 이 곳은 그래프가 나올 임시
    컴포넌트입니다 !!! 이 곳은 그래프가 나올 임시 컴포넌트입니다 !!! 이 곳은
    그래프가 나올 임시 컴포넌트입니다 !!! 이 곳은 그래프가 나올 임시
    컴포넌트입니다 !!! 이 곳은 그래프가 나올 임시 컴포넌트입니다 !!! 이 곳은
    그래프가 나올 임시 컴포넌트입니다 !!! 이 곳은 그래프가 나올 임시
    컴포넌트입니다 !!!
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetailGraph",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
};
</script>

<style></style>
