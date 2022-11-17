<template>
  <!-- <div v-html="pageNavigation.navigator"></div> -->
  <ul>
    ??
    <li v-for="(n, index) in navigation()" :key="index">{{ n }}</li>
  </ul>
</template>

<script>
export default {
  name: "BoardListPage",
  props: ["pageNavigation"],

  data() {
    return {
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
      navigation: function () {
        //페이징 처리 for문 커스텀
        let pageNumber = []; //;
        let startPage = this.startPage;
        let endPage = this.endPage;
        for (let i = startPage; i <= endPage; i++) pageNumber.push(i);
        return pageNumber;
      },
    };
  },

  created() {},

  methods: {},
};
</script>

<style></style>
