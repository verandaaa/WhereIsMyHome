<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <div class="col-md-5 offset-5">
        <form class="d-flex" id="form-search" action="">
          <input type="hidden" name="pgno" value="1" />
          <select
            class="form-select form-select-sm ms-5 me-1 w-50"
            id="skey"
            name="key"
            aria-label="검색조건"
          >
            <option value="" selected>검색조건</option>
            <option value="subject">제목</option>
            <option value="userid">작성자</option>
          </select>
          <div class="input-group input-group-sm">
            <input
              type="text"
              class="form-control"
              id="sword"
              name="word"
              placeholder="검색어..."
            />
            <button id="btn-search" class="btn btn-dark" type="button">
              검색
            </button>
          </div>
        </form>
      </div>
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-row
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>

    <board-list-page v-bind:pageNavigation="pageNavigation"></board-list-page>

    <form id="form-param" method="get" action="">
      <input type="hidden" id="pgno" name="pgno" v-bind:value="search.pg" />
      <input type="hidden" name="key" v-bind:value="search.key" />
      <input type="hidden" name="word" v-bind:value="search.word" />
    </form>
  </b-container>
</template>

<script>
import BoardListRow from "@/components/board/child/BoardListRow";
import BoardListPage from "@/components/board/child/BoardListPage";
import { listArticle } from "@/api/board.js";

export default {
  name: "BoardList",
  components: {
    BoardListRow,
    BoardListPage,
  },
  data() {
    return {
      articles: [],
      search: {
        pg: this.$route.query.pg,
        key: "",
        word: "",
      },
      pageNavigation: {},
    };
  },
  created() {
    let param = {
      pg: this.search.pg,
      spp: 10,
      key: this.key,
      word: this.word,
    };

    listArticle(
      param,
      (response) => {
        console.log(response.data);
        this.articles = response.data.articles;
        this.pageNavigation = response.data.pageNavigation;
        this.pg = response.data.pg;
        this.key = response.data.key;
        this.word = response.data.word;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
  },
};

let pages = document.querySelectorAll(".page-link");
pages.forEach(function (page) {
  page.addEventListener("click", function () {
    let form = document.querySelector("#form-param");
    document.querySelector("#pgno").value =
      page.parentNode.getAttribute("data-pg");
    form.setAttribute("action", "${root}/board/list");
    form.submit();
  });
});
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
