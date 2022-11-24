<template>
  <div>
    <div id="div">
      <b-row class="text-center">
        <b-col id="col-logo"
          ><router-link :to="{ name: 'main' }"
            ><b-img
              :src="require('@/assets/house_logo.png')"
              style="width: 100px"
            ></b-img
          ></router-link>
        </b-col>
        <b-col cols="8" id="col-nav"
          ><router-link :to="{ name: 'board' }"> 공지사항 </router-link>
          <router-link :to="{ name: 'house' }"> 아파트정보 </router-link>
          <router-link :to="{ name: 'news' }"> 부동산뉴스 </router-link>
          <!-- <router-link :to="{ name: 'local' }"> 세권정보 </router-link> -->
          <router-link :to="{ name: 'star' }"> 관심목록 </router-link>
        </b-col>
        <b-col id="col-user" v-if="userInfo">
          <b-link @click.prevent="onClickLogout">로그아웃</b-link> |
          <router-link :to="{ name: 'mypage' }">내정보</router-link>
        </b-col>
        <b-col id="col-user" v-else>
          <router-link :to="{ name: 'login' }"> 로그인</router-link> |
          <router-link :to="{ name: 'join' }"> 회원가입 </router-link>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
#div {
  background-color: white;
  height: 60px;
  overflow: hidden;
  border-bottom: 1.5px solid #d3d3d3;
}
#col-logo {
  line-height: 60px;
  text-align: left;
  padding-left: 40px;
}
#col-nav {
  line-height: 62px;
}
#col-user {
  line-height: 63px;
  text-align: right;
  padding-right: 40px;
}
#col-nav > a {
  font-size: 15px;
  color: black;
  text-decoration: none;
  position: relative;
  padding: 0 15px;
  font-weight: bold;
}
#col-nav > a:hover {
  color: black;
  text-decoration: none;
}

#col-nav > a:after {
  content: "";
  position: absolute;
  bottom: -20px;
  left: 50%;
  transform: translateX(-50%);
  width: 0;
  height: 4px;
  background: #474747;
  transition: all 0.2s ease-out;
}
#col-nav > a:hover::after {
  width: 70%;
}
#col-user > a {
  font-size: 14px;
  color: black;
  text-decoration: none;
  position: relative;
  padding: 0 15px;
}
#col-nav .router-link-active:after {
  /* border-bottom: 4px solid #474747; */
  content: "";
  position: absolute;
  bottom: -20px;
  left: 50%;
  transform: translateX(-50%);
  width: 70%;
  height: 4px;
  background: #474747;
}
</style>
