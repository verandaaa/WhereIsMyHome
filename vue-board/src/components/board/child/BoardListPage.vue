<template>
  <div v-html="pageNavigation.navigator"></div>
</template>

<script>
export default {
  name: "BoardListPage",
  props: ["pageNavigation"],

  data() {
    return {};
  },

  created() {},
};
</script>

<style></style>
