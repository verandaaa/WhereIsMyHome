<template>
  <div class="ct">
    <div class="center">
      <h1>MyPage</h1>
      <b-row style="margin: 40px 20px 20px 20px">
        <b-col cols="2"></b-col>
        <b-col cols="2" style="text-align: left">아이디</b-col>
        <b-col cols="2"></b-col>
        <b-col cols="6" style="text-align: left">{{ userInfo.userid }}</b-col>
      </b-row>
      <b-row style="margin: 20px">
        <b-col cols="2"></b-col>
        <b-col cols="2" style="text-align: left">이름</b-col>
        <b-col cols="2"></b-col
        ><b-col cols="6" style="text-align: left">{{
          userInfo.username
        }}</b-col>
      </b-row>
      <b-row style="margin: 20px">
        <b-col cols="2"></b-col>
        <b-col cols="2" style="text-align: left">이메일</b-col>
        <b-col cols="2"></b-col
        ><b-col cols="6" style="text-align: left">{{ userInfo.email }}</b-col>
      </b-row>
      <b-row style="margin: 20px">
        <b-col cols="2"></b-col>
        <b-col cols="2" style="text-align: left">가입일</b-col
        ><b-col cols="2"></b-col
        ><b-col cols="6" style="text-align: left">{{
          userInfo.joindate
        }}</b-col>
      </b-row>
      <b-row style="margin: 40px 30px"
        ><b-col style="margin: 0 20px"
          ><button type="button">정보수정</button></b-col
        ><b-col style="margin: 0 20px"
          ><button type="button">회원탈퇴</button></b-col
        ></b-row
      >
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
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
