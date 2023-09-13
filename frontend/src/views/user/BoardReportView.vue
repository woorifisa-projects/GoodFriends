<template>
  <div class="report-main">
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
                <div>
                  <label
                    v-for="(category, index) in reportCategories.slice(0)"
                    :key="index"
                    :value="category"
                    class="category-item"
                  >
                    <input
                      type="radio"
                      :value="category"
                      v-model="data.reportCategory"
                      class="category-radio"
                    />
                    {{ REPORT_CATEGORY[category] }}
                  </label>
                </div>
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
                  <div class="content-detail">
                    <label for="detail"> </label>
                    <textarea
                      name=""
                      id="detail"
                      placeholder="신고 내용을 적어주세요"
                      v-model="data.content"
                    ></textarea>
                    <div class="text-length">{{ data.content.length }} / {{ maxLength }}</div>
                  </div>
                </div>
                <button class="submit-btn" @click="submit">
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
import { useLoadingStore } from '@/stores/loading';
import { ref } from 'vue';
import { REPORT } from '@/constants/strings/report';
import type { IPostReport } from '@/types/report';
import { checkReportCategory, checkReporDetail } from '@/utils/validation';
import reportAPI from '@/apis/user/report';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { useRoute } from 'vue-router';
import { REPORT_CATEGORY, REPORT_CATEGORY_LIST } from '@/constants/reportCategory';
import router from '@/router';

const route = useRoute();
const loadingStore = useLoadingStore();

const data = ref<IPostReport>({
  reportCategory: '',
  content: ''
});

const isDisabled = ref(true);
const id = route.params.productId?.toString() || '0';
const reportCategories = REPORT_CATEGORY_LIST;
const maxLength = ref(300);

const submit = async () => {
  // 신고 카테고리, 신고 내용 값이 들어있는지 체크
  if (!checkReportCategory(data.value)) {
    alert('신고카테고리');
    return;
  }
  if (!checkReporDetail(data.value)) {
    alert('신고내용');
    return;
  }
  loadingStore.setLoading(true);
  const res = await reportAPI.postReport(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    id,
    {
      ...data.value
    }
  );
  loadingStore.setLoading(false);
  if (res.isSuccess) {
    isDisabled.value = true;
    alert('신고가 접수되었습니다.');
    router.go(-1);
    return;
  }
  alert(res.message);
};
</script>

<style scoped>
.report-main {
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
  color: rgb(0, 0, 0);
  font-family: 'LINESeedKR-Bd';
  margin: 20px 0;
}
.report-reason {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  margin-bottom: 24px;
  cursor: pointer;
  opacity: 1;
  transition: opacity 0.3s;
}
.reason-circle {
  width: 32px;
  height: 32px;
  background-color: #198bf3;
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
.category-item {
  display: flex;
  align-items: center;

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

.category-radio {
  margin-right: 8px; /* 라디오 버튼 간의 간격 조절 */
}
.category-content {
  /* 현재 사용안함 -> category-item으로 대체 */
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
/* 선택된 라디오 버튼 스타일 */
.category-item input[type='radio']:checked {
  border-color: #198bf3; /* 선택된 상태의 테두리 색상 */
  background-color: #198bf3; /* 선택된 상태의 배경 색상 */
  color: #fff; /* 선택된 상태의 텍스트 색상 */
}
.report-content {
  display: flex;
  flex-direction: column;
  margin-bottom: 40px;
}
.content-title {
  font-size: 24px;
  margin-bottom: 10px;
}
.content-detail textarea {
  background-color: rgb(249, 250, 251);
  border: 1px solid rgb(200, 200, 200);
  border-radius: 6px;
  width: 100%;
  padding: 12px 18px;
  font-size: 18px;
  font-weight: 400;
  line-height: 24px;
  color: rgb(78, 89, 104);
  margin: 0;
  margin-bottom: 12px;
  height: 300px;
}
.text-length {
  text-align: end;
}
.submit-btn {
  flex: 1;
  font-family: 'LINESeedKR-Bd';
  font-size: 20px;
  padding: 16px 24px;
  border-radius: var(--radius-m);
  line-height: 26px;
  justify-content: center;
  width: 100%;
  color: azure;
  background-color: #198bf3;
}
.category-item:hover {
  filter: brightness(90%);
}
button:hover {
  filter: brightness(90%);
}
</style>
