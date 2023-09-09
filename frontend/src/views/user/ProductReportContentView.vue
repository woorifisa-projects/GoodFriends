<template>
  <div id="app">
    <section class="report">
      <div>
        <h1 class="title">{{ REPORT.TITLE_PRODUCT }}</h1>
      </div>
      <div>
        <ol>
          <li>
            <div class="report-content">
              <div class="circle">{{ REPORT.CIRCLE_TWO }}</div>
              <h2 class="semi-title">{{ REPORT.SEMI_TITLE_TWO }}</h2>
            </div>
            <div style="height: 754.797px; overflow: visible">
              <div>
                <div class="content">
                  <h3 class="report-content">{{ REPORT.REPORT_CONTENT }}</h3>
                  <div class="report-detail">{{ REPORT.REPORT_DETAIL }}</div>
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
.semi-title {
  font-size: 20px;
  align-items: center;
}
.report-content {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  margin-bottom: 24px;
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
.report-content {
  font-size: 24px;
}
.report-detail {
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
