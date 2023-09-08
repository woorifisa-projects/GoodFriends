<template>
  <div id="app">
    <section class="report">
      <div>
        <h1 class="title">상품 신고</h1>
      </div>
      <div class="report-detail">
        <ol>
          <li>
            <div class="report-number-1">
              <div class="circle">2</div>
              <h2 class="semi-title">해당 게시글을 신고하는 이유를 적어주세요.</h2>
            </div>
            <div style="height: 754.797px; overflow: visible">
              <div>
                <div class="content">
                  <h3 class="content-title">신고 내용</h3>
                  <div class="content-box">신고 내용을 적어주세요</div>
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
.report-number-1 {
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
.content {
  display: flex;
  flex-direction: column;
  margin-bottom: 40px;
}
.content-title {
  font-size: 24px;
}
.content-box {
  background-color: rgb(249, 250, 251);
  border: 1px solid rgb(200, 200, 200);
  border-radius: 6px;
  width: 100%;
  padding: 12px 18px;
  font-size: 15px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
  height: 350px;
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
button:hover {
  filter: brightness(90%);
}
</style>
