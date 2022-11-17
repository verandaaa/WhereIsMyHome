<template>
  <div>
    <ul>
      <li v-for="(n, index) in paginavigation()" :key="index"></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "BoardListPage",
  props: ["pageNavigation"],
  paging: {
    startRange: this.pageNavigation.startRange,
    endRange: this.pageNavigation.endRange,
    totalCount: this.pageNavigation.totalCount,
    newCount: this.pageNavigation.newCount,
    totalPageCount: this.pageNavigation.totalPageCount,
    currentPage: this.pageNavigation.currentPage,
    naviSize: this.pageNavigation.naviSize,
    countPerPage: this.pageNavigation.countPerPage,
    navigator: this.pageNavigation.navigator,
    startPage: this.pageNavigation.startPage,
    endPage: this.pageNavigation.endPage,
  },
  paginavigation: function () {
    //페이징 처리 for문 커스텀
    let pageNumber = []; //;
    let startPage = this.paging.startPage;
    let endPage = this.paging.endPage;
    for (let i = startPage; i <= endPage; i++) pageNumber.push(i);
    return pageNumber;
  },

  data() {
    return {};
  },

  created() {},
};
</script>

<style></style>
