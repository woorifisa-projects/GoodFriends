<template>
  <DefaultMyPage>
    <div class="wrap">
      <ul>
        <li>
          <div class="item">
            <div class="img">
              <img src="@/assets/tmp/images/image.png" alt="" />
            </div>
            <div class="detail">
              <div>판매 물품 명</div>
              <div>yyyy-mm-dd</div>
            </div>
          </div>
        </li>
        <li>
          <div class="item">
            <div class="img">
              <img src="@/assets/tmp/images/image.png" alt="" />
            </div>
            <div class="detail">
              <div>판매 물품 명</div>
              <div>yyyy-mm-dd</div>
            </div>
          </div>
        </li>
        <li>
          <div class="item">
            <div class="img">
              <img src="@/assets/tmp/images/image.png" alt="" />
            </div>
            <div class="detail">
              <div>판매 물품 명</div>
              <div>yyyy-mm-dd</div>
            </div>
          </div>
        </li>
        <li>
          <div class="item">
            <div class="img">
              <img src="@/assets/tmp/images/image.png" alt="" />
            </div>
            <div class="detail">
              <div>판매 물품 명</div>
              <div>yyyy-mm-dd</div>
            </div>
          </div>
        </li>
        <li>
          <div class="item">
            <div class="img">
              <img src="@/assets/tmp/images/image.png" alt="" />
            </div>
            <div class="detail">
              <div>판매 물품 명</div>
              <div>yyyy-mm-dd</div>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </DefaultMyPage>
</template>

<script setup lang="ts">
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
</script>

<style scoped>
.wrap {
  margin: 20px;
}

.wrap > ul {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 12px 20px;
  width: 100%;
  border: 1px solid rgb(235, 235, 235);
  border-radius: 10px;
  box-shadow: 1px 1px 7px #0000001b;
}

.item > .img {
  width: 100px;
  height: 100px;
  overflow: hidden;
  border: 1px solid gray;
  border-radius: 20px;
}

.img > img {
  height: 100%;
}

.detail {
  display: flex;
  justify-content: space-around;
  border: 1px solid gray;
  height: 100px;
  width: calc(100% - 100px);
  border-radius: 20px;
  overflow: hidden;
}

.detail > div {
  background-color: rgb(232, 232, 232);
  width: 100%;
  height: 100%;
  padding: 20px;
  text-align: center;
  line-height: 58px;
  font-size: 24px;
}
</style>
