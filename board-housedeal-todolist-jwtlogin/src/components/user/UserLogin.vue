<template>
  <div class="ct">
    <div class="center">
      <h1>Login</h1>
      <form class="text-left">
        <b-alert
          show
          variant="danger"
          v-if="isLoginError"
          style="text-align: center; margin-top: 10px"
          >아이디 또는 비밀번호를 확인하세요.</b-alert
        >
        <div class="txt_field">
          <input
            id="userid"
            v-model="user.userid"
            required
            placeholder="ID"
            @keyup.enter="confirm"
          />
          <span></span>
          <label>ID</label>
        </div>
        <div class="txt_field">
          <input
            type="password"
            id="userpwd"
            v-model="user.userpwd"
            required
            placeholder="password"
            @keyup.enter="confirm"
          />
          <span></span>
          <label>password</label>
        </div>
        <button type="button" variant="primary" class="m-1" @click="confirm">
          로그인
        </button>
        <div class="signup_link">
          회원이 아니십니까? <span @click="movePage">회원가입</span>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.ct {
  margin: 0;
  padding: 0;
  background-color: #efeff0;
  height: calc(100vh - 60px);
  overflow: hidden;
}
.center {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  background: white;
  border-radius: 10px;
  box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.05);
}
.center h1 {
  text-align: center;
  padding: 20px 0;
  border-bottom: 1px solid silver;
}
.center form {
  padding: 0 40px;
  box-sizing: border-box;
}
form .txt_field {
  position: relative;
  border-bottom: 2px solid #adadad;
  margin: 30px 0;
}
.txt_field input {
  width: 100%;
  padding: 0 5px;
  height: 40px;
  font-size: 16px;
  border: none;
  background: none;
  outline: none;
}
.txt_field label {
  position: absolute;
  top: 50%;
  left: 5px;
  color: #adadad;
  transform: translateY(-50%);
  font-size: 16px;
  pointer-events: none;
  transition: 0.5s;
}
.txt_field span::before {
  content: "";
  position: absolute;
  top: 40px;
  left: 0;
  width: 0%;
  height: 2px;
  background: #2691d9;
  transition: 0.5s;
}
.txt_field input:focus ~ label,
.txt_field input:valid ~ label {
  top: -5px;
  color: #2691d9;
}
.txt_field input:focus ~ span::before,
.txt_field input:valid ~ span::before {
  width: 100%;
}
.pass {
  margin: -5px 0 20px 5px;
  color: #a6a6a6;
  cursor: pointer;
}
.pass:hover {
  text-decoration: underline;
}
button[type="button"] {
  width: 100%;
  height: 50px;
  border: 1px solid;
  background: #2691d9;
  border-radius: 25px;
  font-size: 18px;
  color: #e9f4fb;
  font-weight: 700;
  cursor: pointer;
  outline: none;
}
button[type="button"]:hover {
  border-color: #2691d9;
  transition: 0.5s;
}
.signup_link {
  margin: 30px 0;
  text-align: center;
  font-size: 16px;
  color: #666666;
}
.signup_link span {
  color: #2691d9;
  text-decoration: none;
}
.signup_link span:hover {
  color: #165d8d;
  cursor: pointer;
}
</style>
