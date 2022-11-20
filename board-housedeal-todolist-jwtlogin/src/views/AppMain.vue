<template>
  <div style="height: calc(100vh - 60px)">
    <div class="box01">
      <div class="container">
        <div>--------------검색창------------</div>
        <div class="part-1">아파트 실거래가 조회</div>
        <div class="part-2">'우리집'<br />타이어보다 비싸다</div>
        <div class="part-3">집 정보, 관심목록 볼 수 있다!!</div>
        <div class="part-4">
          #아파트 #좋아요 #몇층 #얼마<br />#즐찾 #어디 #싸다
        </div>
        <div class="part-5"><span class="mv-house">아파트 보러 가기</span></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppMain",
};
</script>

<style scoped>
.box01 {
  width: 100%;
  height: 100%;
  text-align: center;
  position: relative;
  z-index: 1;
}
.box01::after {
  width: 100%;
  height: 100%;
  content: "";
  background: url("@/assets/city.jpg");
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  filter: brightness(50%);
}
.container {
  /* border: 1px solid red; */
  justify-content: center;
  align-items: center;
  width: 100%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}
.right {
  width: 50%;
}
.part-1 {
  font-size: 14px;
  font-weight: bold;
  margin: 10px;
}
.part-2 {
  font-size: 44px;
  font-weight: bold;
  margin: 20px;
}
.part-3 {
  font-size: 30px;
  font-weight: bold;
  margin: 10px;
}
.part-4 {
  font-size: 20px;
  margin: 10px;
}
.mv-house {
  font-size: 16px;
  border: 1px solid white;
  border-radius: 10px;
  padding: 10px;
  cursor: pointer;
}
</style>
