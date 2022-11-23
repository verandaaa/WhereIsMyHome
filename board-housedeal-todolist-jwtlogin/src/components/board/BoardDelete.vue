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
            <p style="color: white; font-size: 36px">글 삭제</p>
          </div>
        </div>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { deleteArticle } from "@/api/board";

export default {
  name: "BoardDelete",
  created() {
    let param = this.$route.params.articleno;
    deleteArticle(
      param,
      ({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "boardlist" });
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped>
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
