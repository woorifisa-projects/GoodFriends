<template>
  <div class="not-found-page">
    <div class="img">\(^ . ^)/</div>
    <div class="text">
      <p>
        <span> {{ ERROR.ERROR }} </span>
        {{ ERROR.MESSAGE }}
      </p>
      <p>{{ message }}</p>
    </div>
    <button @click="goHome">메인화면으로 이동</button>
  </div>
</template>

<script setup lang="ts">
import { ERROR } from '@/constants/strings/error';
import { useRoute } from 'vue-router';
import router from '@/router';
import { ref } from 'vue';

const route = useRoute();
const { type } = route.params;
const message = ref('');

if (type === 'login') {
  message.value = ERROR.LOGIN;
} else {
  message.value = ERROR.SERVER;
}

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
