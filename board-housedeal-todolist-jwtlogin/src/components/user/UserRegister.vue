<template>
  <div class="ct">
    <div class="center">
      <h1>Join</h1>
      <form
        id="form-join"
        method="POST"
        action=""
        @submit.prevent="registerUser"
      >
        <div class="txt_field">
          <input
            type="text"
            id="username"
            name="userName"
            placeholder="name"
            v-model="user.username"
            required
          />
          <span></span>
          <label>name</label>
        </div>
        <div class="txt_field">
          <input
            type="text"
            id="userid"
            name="userId"
            placeholder="ID"
            v-model="user.userid"
            ref="id"
            @keyup="idConfirm"
            required
          />
          <span></span>
          <label>ID</label>
        </div>
        <div style="text-align: left">
          <div
            v-bind:class="{ success: isSuccess, fail: isFail }"
            v-bind:style="myStyle"
            v-html="msg"
          ></div>
        </div>
        <div class="txt_field">
          <input
            type="password"
            id="userpwd"
            name="userPwd"
            placeholder="password"
            v-model="user.userpwd"
            required
          />
          <span></span>
          <label>password</label>
        </div>
        <div class="txt_field">
          <input
            type="text"
            id="email"
            name="email"
            placeholder="email"
            v-model="user.email"
            required
          />
          <span></span>
          <label>email</label>
        </div>

        <button type="submit">회원가입</button>
      </form>
    </div>
  </div>
</template>

<script>
import { register, idCheck } from "@/api/member";

export default {
  name: "UserRegister",

  data() {
    return {
      user: {
        username: "",
        userid: "",
        userpwd: "",
        email: "",
      },
      list: [],
      msg: "",
      isSuccess: false,
      isFail: false,
      myStyle: {
        fontSize: "15px",
      },
    };
  },

  methods: {
    registerUser() {
      let param = {
        username: this.user.username,
        userid: this.user.userid,
        userpwd: this.user.userpwd,
        email: this.user.email,
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

    idConfirm() {
      if (this.user.userid.length < 5 || this.user.userid.length > 12) {
        this.msg = `아이디는 5자이상 12자리 이하입니다.`;
        this.$refs.user.userid.focus(); //refs가 id인 것에 포커스를 갖다 놔라
        console.dir(this.$refs.user.userid);
        this.isSuccess = false;
        this.isFail = false;
        return;
      } else {
        idCheck(this.user.userid, ({ data }) => {
          if (data == 1) {
            console.log(data);
            this.msg = this.user.userid + `는 사용할 수 없습니다.`;
            this.isSuccess = false;
            this.isFail = true;
          } else {
            this.msg = this.user.userid + `는 사용할 수 있습니다.`;
            this.isSuccess = true;
            this.isFail = false;
          }
        });
      }
      console.log(this.$refs.user.userid.value);
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
  padding: 0 0 40px 0;
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
button[type="submit"] {
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
button[type="submit"]:hover {
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
.success {
  color: dodgerblue;
}

.fail {
  color: rgb(180, 0, 0);
}
</style>
