<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="user-wrap">
          <div class="user-image">
            <b-img
              :src="require('@/assets/iocenters.jpg')"
              style="width: 100%; border-radius: 15px; filter: brightness(50%)"
            ></b-img>
          </div>
          <div class="user-text">
            <p style="color: white; font-size: 36px">공지사항</p>
          </div>
        </div>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          id="tbarticle"
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
          :per-page="perPage"
          :current-page="currentPage"
        >
          <template #cell(subject)="data">
            <router-link
              :to="{
                name: 'boardview',
                params: { articleno: data.item.articleno },
              }"
            >
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
        <b-pagination
          pills
          align="center"
          size="bg"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="tbarticle"
        ></b-pagination>
        <p class="mt-3">전체페이지: {{ currentPage }} / {{ totalPage() }}</p>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import { listArticle } from "@/api/board";
import { apiInstance } from "@/api/index.js";

export default {
  name: "BoardList",

  data() {
    return {
      currentPage: 1,
      perPage: 10,
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    const api = apiInstance();
    api.get(`/board`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
    totalPage() {
      return Math.ceil(this.rows / this.perPage);
    },
  },
  computed: {
    // computed는 잘 안바뀌는 값을 마치 properties(메소드가 아닌 특성값)처럼 쓰기 위해 선언함. 호출 방식도 메서드 방식이 아닌 일반적인 변수와 비슷.
    rows() {
      return this.articles.length;
    },
  },
};
</script>

<style scoped>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
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
