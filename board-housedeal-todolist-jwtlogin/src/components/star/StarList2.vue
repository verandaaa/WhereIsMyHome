<template>
  <div class="contents-mid">
    <section class="contents-part">
      <div class="part-top">
        <div class="sorting-amount left-float">
          전체 <span class="em-blue totalCount">0건</span>의 매물이 있습니다.
        </div>
        <div class="right-float">
          <!-- <button type="button" class="left-float btn-map">Map으로보기</button> -->
          <button class="btn-function fix-width left-float right btnDelete">
            삭제
          </button>
        </div>
      </div>

      <div class="part-mid">
        <table class="vt-table">
          <colgroup>
            <col style="width: 43px" />
            <col style="width: 105px" />
            <col style="width: 80px" />
            <col style="width: 100px" />
            <col style="width: auto" />
            <col style="width: 140px" />
            <col style="width: 160px" />
            <col style="width: 90px" />
          </colgroup>

          <thead>
            <th scope="col">
              <div class="check-wrap left middle">
                <input
                  id="check_all"
                  name="check_all"
                  class="check-box"
                  type="checkbox"
                />
                <label for="check_all" class="check-label blind"
                  >전체선택</label
                >
              </div>
            </th>
            <th scope="col">구분</th>
            <th scope="col">사진</th>
            <th scope="col">거래종류<br />매물종류</th>
            <th scope="col">소재지 / 상세주소 매물설명</th>
            <th scope="col">면적</th>
            <th scope="col">가격<br />(만원)</th>
            <th scope="col">삭제</th>
          </thead>

          <tbody id="list_data"></tbody>
        </table>

        <div class="pagenate_area">
          <ul class="pagenate clfix" id="ajaxPaging"></ul>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "StarList2",
};
</script>

<style></style>
