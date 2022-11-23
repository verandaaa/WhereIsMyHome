<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="user-wrap">
          <div class="user-image">
            <b-img
              :src="require('@/assets/pencil.png')"
              style="width: 100%; border-radius: 15px; filter: brightness(50%)"
            ></b-img>
          </div>
          <div class="user-text">
            <p style="color: white; font-size: 36px">글 보기</p>
          </div>
        </div>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left" v-if="userInfo.userid === article.userid">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-primary" size="sm" @click="moveList"
          >글목록</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card class="mb-2" border-variant="dark" no-body>
          <template #header>
            <div>
              <span style="float: left"
                ><span style="font-size: 26px"
                  >[공지] {{ article.subject }}
                </span>
                <span style="color: gray; font-size: 16px">
                  {{ article.userid }}</span
                ></span
              ><span style="float: right"
                ><span style="font-size: 26px"> </span
                ><span style="font-size: 14px"
                  >{{ article.regtime }} [조회수 : {{ article.hit }}]</span
                ></span
              >
            </div>
          </template>
          <b-card-body class="text-left">
            <div
              v-html="message"
              class="content"
              style="height: 400px; overflow-y: auto"
            ></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.articleno;
    getArticle(
      param,
      ({ data }) => {
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style scoped>
.content::-webkit-scrollbar {
  width: 20px;
}
.content::-webkit-scrollbar-thumb {
  background-color: #e2e2e2;
  border-radius: 10px;
  background-clip: padding-box;
  border: 4px solid transparent;
}
.content::-webkit-scrollbar-track {
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0px 0px 2px white;
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
</style>
