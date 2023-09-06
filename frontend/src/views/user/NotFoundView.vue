<template>
  <div class="not-found-page">
    <div class="img">\(^ . ^)/</div>
    <div class="text">
      <p>
        <span> {{ ERROR_MSG.NOT_FOUND }} </span>
        {{ ERROR_MSG.NOT_FOUND_MESSAGE }}
      </p>
      <p>{{ ERROR_MSG.NOT_FOUND_ERR_DETAIL }}</p>
    </div>
    <button @click="goHome">{{ ERROR_MSG.GO_HOME }}</button>
  </div>
</template>

<script setup lang="ts">
import { ERROR_MSG } from '@/constants/strings/error';
import router from '@/router';

const goHome = () => {
  router.push('/');
};
</script>

<style scoped>
.not-found-page {
  width: 100%;
  height: 100%;
  margin-top: 32px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 24px;
}
.img {
  font-size: 120px;
  font-weight: 800;
  color: gray;
}
.text {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 32px;
}
.text > p {
  font-weight: 700;
}
p > span {
  color: #0076ba;
  font-weight: 900;
}
button {
  border: 1px solid rgba(131, 131, 131, 0.632);
  padding: 16px;
  border-radius: 12px;
  font-size: 24px;

  transition: all 0.5s ease;
}
button:hover {
  transform: scale(1.05);
  box-shadow: 1px 1px 10px rgba(155, 155, 155, 0.237);
}

@media screen and (max-width: 767px) {
  .img {
    font-size: 100px;
    font-weight: 800;
  }
  .text {
    font-size: 24px;
  }
  button {
    font-size: 18px;
  }
}
</style>
