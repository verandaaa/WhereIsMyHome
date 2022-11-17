<template>
  <div class="container">
    <div style="height: 70px"></div>
    <div class="row justify-content-center">
      <div class="col-lg-5 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원가입</mark>
        </h2>
      </div>
      <div class="w-100"></div>
      <div style="height: 20px"></div>
      <div class="col-lg-5 col-md-10 col-sm-12">
        <form
          id="form-join"
          method="POST"
          action=""
          @submit.prevent="registerUser"
        >
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input
              type="text"
              class="form-control"
              id="username"
              name="userName"
              placeholder="이름..."
              v-model="user.username"
            />
          </div>
          <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="userId"
              placeholder="아이디..."
              v-model="user.userid"
            />
          </div>
          <div id="idcheck-result"></div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input
              type="password"
              class="form-control"
              id="userpwd"
              name="userPwd"
              placeholder="비밀번호..."
              v-model="user.userpwd"
            />
          </div>
          <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                id="emailid"
                name="emailId"
                placeholder="이메일아이디"
                v-model="user.emailId"
              />
              <span class="input-group-text">@</span>
              <b-form-select
                v-model="user.emailDomain"
                :options="domains"
              ></b-form-select>
            </div>
          </div>
          <button type="submit">보내기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { register } from "@/api/member";

export default {
  name: "UserRegister",

  data() {
    return {
      user: {
        username: "",
        userid: "",
        userpwd: "",
        emailId: "",
        emailDomain: "",
      },
      domains: ["ssafy.com", "google.com", "naver.com", "kakao.com"],
    };
  },

  methods: {
    registerUser() {
      let param = {
        username: this.user.username,
        userid: this.user.userid,
        userpwd: this.user.userpwd,
        email: this.user.emailId + "@" + this.user.emailDomain,
      };
      register(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveHome();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveHome() {
      this.$router.push({ name: "main" });
    },
  },
};
</script>

<style></style>
