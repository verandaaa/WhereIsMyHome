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
            <p style="color: white; font-size: 36px">글 작성</p>
          </div>
        </div>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
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
