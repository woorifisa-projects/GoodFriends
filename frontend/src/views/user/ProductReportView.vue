<template>
  <div id="app">
    <section class="report">
      <div>
        <h1 class="title">{{ REPORT.TITLE_PRODUCT }}</h1>
      </div>
      <div class="report-detail">
        <ol>
          <li>
            <div class="report-reason">
              <div class="reason-circle">{{ REPORT.CIRCLE_ONE }}</div>
              <h2 class="reason-title">{{ REPORT.SEMI_TITLE_ONE }}</h2>
            </div>
            <div style="height: 500px; overflow: visible">
              <div class="report-category">
                <div class="category-content">{{ REPORT.CATEGORY_CONTENT_ONE }}</div>
                <div class="category-content">{{ REPORT.CATEGORY_CONTENT_TWO }}</div>
                <div class="category-content">{{ REPORT.CATEGORY_CONTENT_THREE }}</div>
                <div class="category-content">{{ REPORT.CATEGORY_CONTENT_FOUR }}</div>
                <div class="category-content">{{ REPORT.CATEGORY_CONTENT_FIVE }}</div>
                <div class="category-content">{{ REPORT.CATEGORY_CONTENT_ETC }}</div>
              </div>
            </div>
            <div class="report-reason">
              <div class="reason-circle">{{ REPORT.CIRCLE_TWO }}</div>
              <h2 class="reason-title">{{ REPORT.SEMI_TITLE_TWO }}</h2>
            </div>
            <div style="height: 500px; overflow: visible">
              <div>
                <div class="report-content">
                  <h3 class="content-title">{{ REPORT.REPORT_CONTENT }}</h3>
                  <div class="content-detail">{{ REPORT.REPORT_DETAIL }}</div>
                </div>
                <button class="p-button" type="button" aria-disabled="false">
                  {{ REPORT.BUTTON }}
                </button>
              </div>
            </div>
          </li>
        </ol>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
import { REPORT } from '@/constants/strings/report';
</script>

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
.report-reason {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  margin-bottom: 48px;
  cursor: pointer;
  opacity: 1;
  transition: opacity 0.3s;
}
.reason-circle {
  width: 32px;
  height: 32px;
  background-color: #3182f6;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px; /* 숫자의 크기를 조절할 수 있습니다. */
  color: white; /* 숫자의 색상을 설정할 수 있습니다. */
  margin: 0px;
}
.reason-title {
  font-size: 20px;
  align-items: center;
  margin-left: 10px;
}
.report-category {
  display: flex;
  flex-direction: column;
  margin-bottom: 40px;
}
.category-content {
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
.report-content {
  display: flex;
  flex-direction: column;
  margin-bottom: 40px;
}
.content-title {
  font-size: 24px;
}
.content-detail {
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
  font-family: 'LINESeedKR-Bd';
  flex: 1;
}
.category-content:hover {
  filter: brightness(90%);
}
button:hover {
  filter: brightness(90%);
}
</style>
