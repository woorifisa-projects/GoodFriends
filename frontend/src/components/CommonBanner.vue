<template>
  <section class="fleamarket-cover">
    <div class="banner-content">
      <h1 class="banner-title">친구처럼<br />안심한 중고거래</h1>
      <p class="banner-description">편하게 거래할 수 있는 서비스<br />지금 시작해 보세요</p>
      <div class="banner-image">
        <div class="fleamarket-banner-image">
          <img alt="메인_배너_이미지" src="@/assets/images/banner2.png" />
        </div>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts"></script>

<style scoped>
.fleamarket-cover {
  background-color: #ffef9d;
  height: 322px;
  padding: 0 160px 0 160px;
}
.banner-content {
  position: relative;
  height: 100%;
  box-sizing: border-box;
}
.banner-title {
  font-family: 'LINESeedKR-Bd';
  line-height: 1.5;
  font-size: 34px;
  letter-spacing: -0.32px;
}
.banner-description {
  font-family: 'LINESeedKR-Rg';
  line-height: 1.32;
  font-size: 18px;
  letter-spacing: -0.18px;
  margin-top: 16px;
  display: block;
}
.banner-image {
  position: absolute;
  bottom: 0;
  display: flex;
}
.fleamarket-banner-image {
  box-sizing: border-box;
  overflow: hidden;
  width: initial;
  height: initial;
  background: none;
  opacity: 1;
  border: 0;
  margin: 0;
  padding: 0;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}
.fleamarket-banner-image span img {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  box-sizing: border-box;
  padding: 0;
  border: none;
  margin: auto;
  width: 0;
  height: 0;
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
}
@media (max-width: 768px) {
  .fleamarket-cover {
    height: 322px;
    padding: 0 0 0 24px;
  }
}
@media (min-width: 768px) {
  .banner-content {
    padding-top: 50px;
    max-width: 768px;
    margin: 0 auto;
  }
}

@media (min-width: 768px) {
  .banner-image {
    right: 0;
    width: 357px;
    height: 322px;
  }
}
</style>
