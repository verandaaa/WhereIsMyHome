<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>관심매물 목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          id="tbstar"
          striped
          hover
          :items="stars"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
        >
          <template #cell(deletechk)>
            <b-form-checkbox> </b-form-checkbox>
          </template>

          <template #cell(aptName)="data">
            <router-link
              :to="{
                name: 'StarView',
                params: { starno: data.item.starNo },
              }"
            >
              {{ data.item.aptName }}
            </router-link>
          </template>

          <template #cell(deletebtn)="data">
            <b-button
              size="sm"
              @click="delConfirm(data.item.starNo)"
              class="mr-2"
            >
              삭제
            </b-button>
          </template>
        </b-table>
        <b-pagination
          pills
          align="center"
          size="bg"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="tbarticle"
        ></b-pagination>
        <p class="mt-3">전체페이지: {{ currentPage }} / {{ totalPage() }}</p>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listStars, deleteStar } from "@/api/star";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "StarList",

  data() {
    return {
      currentPage: 1,
      perPage: 10,
      stars: [],
      fields: [
        { key: "deletechk", label: "선택", tdClass: "tdDeleteChk" },
        { key: "photo", label: "사진", tdClass: "tdPhoto" },
        { key: "lawArea", label: "소재지", tdClass: "tdlawArea" },
        { key: "address", label: "상세주소", tdClass: "tdAddress" },
        { key: "aptName", label: "아파트이름", tdClass: "tdaptName" },
        { key: "buildYear", label: "건축년도", tdClass: "tdbuildYear" },
        { key: "price", label: "최근 거래 가격", tdClass: "tdPrice" },
        { key: "deletebtn", label: "삭제", tdClass: "tdDeletebtn" },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    // computed는 잘 안바뀌는 값을 마치 properties(메소드가 아닌 특성값)처럼 쓰기 위해 선언함. 호출 방식도 메서드 방식이 아닌 일반적인 변수와 비슷.
    rows() {
      return this.stars.length;
    },
  },

  created() {
    listStars(
      this.userInfo.userid,
      ({ data }) => {
        console.log("관심목록 도착", data);
        this.stars = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    viewStar(star) {
      this.$router.push({
        name: "StarView",
        params: { starno: star.starNo },
      });
    },

    delConfirm(starNo) {
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteStar(
          starNo,
          ({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료됐습니다.";
            }
            alert(msg);
          },
          (error) => {
            console.log(error);
          }
        ); //deleteStar
      } //if
    }, //deleteConfirm

    totalPage() {
      return Math.ceil(this.rows / this.perPage);
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
</style>
