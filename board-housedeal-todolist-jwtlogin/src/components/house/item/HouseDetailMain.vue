<template>
  <div>
    <b-row class="mb-2 mt-1">
      <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.aptCode }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.aptName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >건축년도 : {{ house.buildYear }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">동이름 : {{ house.dongName }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >도로명주소 : {{ house.roadName }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >도로명본번 : {{ house.roadNameBonbun }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >도로명부번 : {{ house.roadNameBubun }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary">지번 : {{ house.jibun }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary">위도 : {{ house.lat }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary">경도 : {{ house.lng }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >동코드 : {{ house.dongCode }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger"
          >매매가격 :
          {{
            (parseInt(house.dealAmount.replace(",", "")) * 10000) | price
          }}원</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >거래년 : {{ house.dealYear }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >거래월 : {{ house.dealMonth }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary">거래일 : {{ house.dealDay }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning">면적 : {{ house.area }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >거래일 : {{ house.dealDate }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary">층수 : {{ house.floor }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >시도 이름 : {{ house.sidoName }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >구군 이름 : {{ house.gugunName }}</b-alert
        >
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetailMain",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
