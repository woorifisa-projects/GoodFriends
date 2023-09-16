<template>
  <div id="about-page">
    <div class="section main-section">
      <BallDrop />
      <div class="arrow-img" @click="goPage">
        <img src="@/assets/images/arrow.png" alt="아래로 이동" />
      </div>
    </div>
    <div class="section section-1">
      <div class="img">
        <img src="@/assets/images/security-icon.png" alt="안전 아이콘" />
      </div>
      <div class="text" data-aos="fade-left" ref="refs">
        <p>#인증, #중복가입방지</p>
        <div>
          <p>인증된 사람들만</p>
          <p>인심하게 거래할 수 있게</p>
          <span>프로필 정보에 본인의 계좌와 핸드폰 번호를 인증 해야만 거래를 할 수 있어요</span>
        </div>
        <div class="btn-wrap">
          <button @click="$router.push('/')">거래하러 가기</button>
        </div>
      </div>
    </div>
    <div class="section section-2">
      <div class="text" data-aos="fade-left">
        <p>#카테고리, #검색</p>
        <div>
          <p>원하는 카테고리와 상품 검색</p>
          <span>본인의 원하는 카테고리에 맞게 상품을 조회할 수 있어요</span>
          <span>또한, 원하는 상품 이름을 키워드로 입력하면 조회돼요.</span>
        </div>
      </div>
      <div class="img">
        <img src="@/assets/images/glass-icon.png" alt="돋보기 아이콘" />
      </div>
    </div>
    <div class="section section-3">
      <div class="img">
        <img src="@/assets/images/handshake-icon.png" alt="악수 아이콘" />
      </div>
      <div class="text" data-aos="fade-right">
        <p>#상품주문, #주문내역, #판매내역</p>
        <div>
          <p>서로가 원하는</p>
          <p>시간과 날짜에</p>
          <p>거래할 수 있게</p>
          <span>판매자와 주문자는 서로 원하는 시간과 날짜에 맞게 거래할 수 있어요</span>
        </div>
      </div>
    </div>
    <div class="section section-4">
      <div class="text" data-aos="fade-right">
        <p>#신고, #추가피해방지</p>
        <div>
          <p>신고를 통해</p>
          <p>부적절한 게시글 차단</p>
          <span>부적절한 게시글 차단을 통해 깨끗한 중고거래 환경을 조성해요</span>
          <span>누적 신고된 유저는 더이상 거래할 수 없도록 조치해요</span>
        </div>
      </div>
      <div class="img">
        <img src="@/assets/images/alarm-icon.png" alt="경보 아이콘" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import BallDrop from '@/components/threes/BallDrop.vue';
import { ref } from 'vue';

const refs = ref<HTMLDivElement>();

const goPage = () => {
  if (refs.value) {
    window.scrollTo({
      top: refs.value?.scrollWidth + 450,
      left: refs.value?.scrollHeight,
      behavior: 'smooth'
    });
  }
};
</script>

<style scoped>
#about-page {
  position: relative;
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
  height: 100%;
}
.main-section {
  flex-direction: column;
  position: relative;
}
.arrow-img {
  cursor: pointer;
  position: absolute;
  width: 50px;
  height: 50px;
  top: 700px;
  left: 50%;
  animation: arrow infinite 1s 0s ease;
}
.arrow-img > img {
  width: 50px;
  height: 50px;
}
@keyframes arrow {
  0% {
    opacity: 1;
    top: 700px;
  }
  50% {
    opacity: 0.3;
    top: 710px;
  }
  100% {
    top: 700px;
    opacity: 1;
  }
}
.section {
  height: 900px;
  font-size: 48px;
  font-family: 'LINESeedKR-Bd';
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 48px;
}

.img {
  width: 300px;
  height: 400px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.img > img {
  width: 100%;
  object-fit: contain;
}
.section img {
  animation: up-down 2s infinite 0.3s ease;
}
@keyframes up-down {
  0% {
    padding-bottom: 0px;
  }
  50% {
    padding-bottom: 20px;
  }
  100% {
    padding-bottom: 0px;
  }
}
.text {
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 12px;
}
.section > .text > p {
  font-size: 24px;
}
.section-1 {
  background-color: #6cd3ff;
}
.section-1 > .text > p {
  color: white;
}
.section-2 {
  background-color: antiquewhite;
}

.section-3 {
  background-color: white;
}
.section-4 {
  background-color: #d5ade6;
}
.section-4 > .text > p {
  color: white;
}
.btn-wrap {
  padding-top: 24px;
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 12px;
}
.btn-wrap > button {
  background-color: white;
  padding: 16px 32px;
  border-radius: 6px;

  font-size: 16px;

  transition: filter 0.3s ease;
  font-family: 'LINESeedKR-Bd';
}
.btn-wrap > button:hover {
  filter: brightness(96%);
}

span {
  font-size: 16px;
  font-family: 'LINESeedKR-Rg';
  display: block;
}
</style>
