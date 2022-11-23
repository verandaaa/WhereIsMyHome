<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="user-wrap">
          <div class="user-image">
            <b-img
              :src="require('@/assets/houses.jpg')"
              style="width: 100%; border-radius: 15px; filter: brightness(50%)"
            ></b-img>
          </div>
          <div class="user-text">
            <p style="color: white; font-size: 36px">관심목록</p>
          </div>
        </div>
      </b-col>
    </b-row>
    <div style="height: 30px"></div>
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

          <template #cell(photo)>
            <b-img
              :src="require('@/assets/ghm.jpg')"
              style="width: 150px"
            ></b-img>
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
            <button @click="delConfirm(data.item.aptCode)">삭제</button>
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
    this.showlist();
  },
  methods: {
    viewStar(star) {
      this.$router.push({
        name: "StarView",
        params: { starno: star.starNo },
      });
    },
    showlist() {
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

    delConfirm(aptCode) {
      console.log("delConfirm 도착");
      console.log(aptCode);
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteStar(
          { aptCode, userId: this.userInfo.userid },
          ({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료됐습니다.";
            }
            alert(msg);
            this.showlist();
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

<style scoped>
.tdClass {
  width: 50px;
  text-align: center;
}
.user-wrap {
  width: 100%;
  margin: 10px auto;
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
button {
  border: none;
  padding: 3px 10px;
  border-radius: 5px;
  background-color: rgb(235, 235, 235);
  border: 2px solid rgb(177, 177, 177);
}
button:hover {
  background-color: rgb(199, 199, 199);
}
</style>
