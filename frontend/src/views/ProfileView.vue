<template>
  <div class="profile">
    <div class="wrap">
      <div class="side">
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="profile img" />
        </div>
      </div>
      <div class="main"></div>
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
      <div class="main">
        <div class="profile_main">
          <div class="btn_wrap">
            <button>탈퇴</button>
            <button>수정</button>
          </div>
          <div class="profile_detail_wrap">
            <div class="profile_detail">
              <div class="item">
                <div>이메일</div>
                <div>00@0000.com</div>
              </div>
              <div class="item">
                <div>닉네임</div>
                <div>00</div>
              </div>
              <div class="item">
                <div>성별</div>
                <div>00</div>
              </div>
              <div class="item">
                <div>핸드폰</div>
                <div>000-0000-0000</div>
              </div>
              <div class="item">
                <div>주소</div>
                <div>00시 00구</div>
              </div>
            </div>
          </div>
        </div>
      </div>
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

.profile_main {
  /* border: 1px solid red; */
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.profile_main > div {
}

.btn_wrap {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
  gap: 20px;
}

.btn_wrap > button {
  background: rgb(240, 240, 240);
  padding: 16px 20px;
  border-radius: 8px;
  box-shadow: 1px 1px 6px rgba(0, 0, 0, 0.48);
}

.btn_wrap > button:first-child {
  background: lightblue;
}

.profile_detail_wrap {
  padding: 0 36px;
}

.profile_detail {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.item {
  width: 100%;
  border: 1px solid rgb(180, 180, 180);
  border-radius: 24px;
  display: flex;
  overflow: hidden;
  gap: 16px;
}

.item > div:first-child {
  height: 100%;
  width: 100px;
  padding: 16px;
  background: lightblue;
  text-align: center;
}

.item > div:last-of-type {
  display: flex;
  align-items: center;
}
</style>
