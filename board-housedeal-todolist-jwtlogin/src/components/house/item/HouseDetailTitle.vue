<template>
  <div>
    <div class="h-container" style="width: 100%">
      <div class="item">
        <div style="margin: 10px 0 0 15px; font-weight: 900; font-size: 18px">
          {{ house.aptName }}
        </div>
        <div style="margin: 0 0 0 15px; font-size: 12px">주소 뜨는곳~~~~~</div>
      </div>
      <div class="item last">
        <b-img
          v-if="hasStar"
          class="star"
          :src="require('@/assets/star_true.png')"
          style="width: 20px"
          @click="mvDeleteStar"
        >
        </b-img>
        <b-img
          v-else
          class="star"
          :src="require('@/assets/star_false.png')"
          style="width: 20px"
          @click="mvAddStar"
        >
        </b-img>

        <b-img
          class="x"
          :src="require('@/assets/close.png')"
          style="width: 16px; margin: 25px"
          @click="detail"
        ></b-img>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { mapActions } from "vuex";
import { addStar, getStar, deleteStar } from "@/api/star";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseDetailTitle",

  data() {
    return {
      hasStar: false,
      param: {
        aptCode: "",
        userId: "",
      },
    };
  },

  created() {
    this.param.aptCode = this.house.aptCode;
    this.param.userId = this.userInfo.userid;
    console.log("created title");
    console.log(this.param);
    getStar(
      this.param,
      ({ data }) => {
        if (data === "success") {
          console.log("getStar 도착", data);
          this.hasStar = true;
        } else {
          console.log("getStar 도착", data);
          this.hasStar = false;
        }
      },
      (error) => {
        console.log(error);
      }
    );
  },

  watch: {
    house() {
      console.log("house 바뀜", this.house.aptCode);
      this.param.aptCode = this.house.aptCode;
      getStar(
        this.param,
        ({ data }) => {
          if (data === "success") {
            console.log("getStar 도착", data);
            this.hasStar = true;
          } else {
            console.log("getStar 도착", data);
            this.hasStar = false;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },

  mounted() {},
  computed: {
    ...mapState(houseStore, ["house", "open"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailClose"]),
    detail() {
      this.detailClose();
    },
    changeImg() {
      this.imageIndex = (this.imageIndex + 1) % 2;
    },
    mvAddStar() {
      console.log("addStar 도착", this.param);
      if (!this.param.userId) {
        alert("로그인이 필요합니다");
        this.$router.push({ name: "login" });
      } else {
        addStar(
          this.param,
          ({ data }) => {
            if (data === "success") {
              console.log("success", data);
              this.hasStar = true;
            }
          },
          (error) => {
            alert("로그인이 필요합니다");
            console.log(error);
          }
        );
      }
    },

    mvDeleteStar() {
      console.log("delStar 도착");
      deleteStar(
        this.param,
        ({ data }) => {
          if (data === "success") {
            this.hasStar = false;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.x,
.star:hover {
  cursor: pointer;
}
.h-container:after {
  clear: both;
  display: block;
  content: "";
}
.h-container .item {
  float: left;
  text-align: left;
}
.h-container .item.last {
  float: right;
  border-right: none;
}
</style>
