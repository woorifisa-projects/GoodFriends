<template>
  <div id="app">
    <section class="report">
      <div>
        <h1 class="title">상품 신고</h1>
      </div>
      <div class="report-detail">
        <ol>
          <li>
            <div class="report-number">
              <div class="circle">1</div>
              <h2 class="semi-title">아래에 신고 사유를 선택해 주세요.</h2>
            </div>
            <div style="height: 754.797px; overflow: visible">
              <div>
                <div class="category">
                  <div class="category-1">판매 금지 물품이에요.</div>
                  <div class="category-2">중고거래 게시글이 아니에요.</div>
                  <div class="category-3">전문 판매업자 같아요.</div>
                  <div class="category-4">사기 글이에요.</div>
                  <div class="category-5">거래 / 환불 분쟁</div>
                  <div class="category-6">기타 사유</div>
                </div>
                <button class="p-button" type="button" aria-disabled="false">다음</button>
              </div>
            </div>
          </li>
        </ol>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
#app {
  margin: 0;
  font-weight: normal;
  font-family: 'LINESeedKR-Rg';
}
.report {
  position: relative;
  width: 100%;
  overflow: hidden;
  background-color: #fff;
  margin: 64px 0;
}
.title {
  font-size: 36px;
  font-weight: 700;
  line-height: 47px;
  color: rgb(51, 61, 75);
  font-family: 'LINESeedKR-Bd';
  margin: 20px 0;
}
.semi-title {
  font-size: 20px;
  align-items: center;
}
.report-detail {
}
.report-number {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  margin-bottom: 48px;
  cursor: pointer;
  opacity: 1;
  transition: opacity 0.3s;
}
.circle {
  width: 32px;
  height: 32px;
  background-color: #3182f6;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px; /* 숫자의 크기를 조절할 수 있습니다. */
  color: white; /* 숫자의 색상을 설정할 수 있습니다. */
  margin: 5px;
}
.category {
  display: flex;
  flex-direction: column;
  margin-bottom: 40px;
}
.category-1 {
  background-color: rgb(249, 250, 251);
  border-radius: 6px;
  width: 100%;
  padding: 16px 24px;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
  /* border: 0.1px solid rgb(173, 173, 173); */
}
.category-2 {
  background-color: rgb(249, 250, 251);
  border-radius: 6px;
  width: 100%;
  padding: 16px 24px;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
}
.category-3 {
  background-color: rgb(249, 250, 251);
  border-radius: 6px;
  width: 100%;
  padding: 16px 24px;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
}
.category-4 {
  background-color: rgb(249, 250, 251);
  border-radius: 6px;
  width: 100%;
  padding: 16px 24px;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
}
.category-5 {
  background-color: rgb(249, 250, 251);
  border-radius: 6px;
  width: 100%;
  padding: 16px 24px;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
}
.category-6 {
  background-color: rgb(249, 250, 251);
  border-radius: 6px;
  width: 100%;
  padding: 16px 24px;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
}
.p-button {
  border-radius: var(--radius-m);
  line-height: 26px;
  background-color: #3182f6;
  justify-content: center;
  width: 100%;
  color: azure;
  padding: 16px 24px;
  font-size: 20px;
  font-family: 'LINESeedKR-Bd';
  flex: 1;
}
.category-1:hover {
  filter: brightness(90%);
}
.category-2:hover {
  filter: brightness(90%);
}
.category-3:hover {
  filter: brightness(90%);
}
.category-4:hover {
  filter: brightness(90%);
}
.category-5:hover {
  filter: brightness(90%);
}
.category-6:hover {
  filter: brightness(90%);
}
button:hover {
  filter: brightness(90%);
}
</style>
