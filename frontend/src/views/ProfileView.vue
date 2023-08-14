<template>
  <div class="profile">
    <div class="wrap">
      <div class="side">
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="profile img" />
        </div>
      </div>
      <div class="main">main</div>
    </div>
    <div class="wrap">
      <div class="side">
        <div class="list">
          <span>나의 정보</span>
          <ul>
            <li>프로필</li>
          </ul>
          <span>나의 거래</span>
          <ul>
            <li>판매목록</li>
            <li>구매목록</li>
            <li>거래후기</li>
          </ul>
        </div>
      </div>
      <div class="main">main</div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
.profile {
  display: flex;
  flex-direction: column;
  gap: 32px;

  margin-bottom: 36px;
}

.wrap {
  display: flex;
}

.wrap > div {
  margin: 20px;
}

.side {
  /* TODO: 반응형 추가할시 수정 */
  width: 350px;
  display: flex;
  justify-content: center;
}

.main {
  width: 100%;
}

.img {
  width: 180px;
  height: 180px;

  border: 1px solid black;
  border-radius: 100%;

  overflow: hidden;
}

.img > img {
  height: 100%;
}

.list {
  width: 100%;
  margin: 10px;
  padding: 10px;

  background: white;
  box-shadow: 2px 2px 17px rgb(186, 186, 186);
  border-radius: 16px;
}

.list > span {
  font-size: 24px;
}

.list > ul {
  display: flex;
  flex-direction: column;
  gap: 16px;

  margin: 20px 0;
}

.list > ul > li {
  padding: 8px 16px;

  background: lightgray;
  border-radius: 8px;
}
</style>
