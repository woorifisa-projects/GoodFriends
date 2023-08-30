<template>
  <div></div>
</template>

<script setup lang="ts">
import { ERROR } from '@/constants/strings/error';
import { useRoute } from 'vue-router';

const route = useRoute();
const { type } = route.params;

if (type === 'login') {
  alert(ERROR.LOGIN);
  window.location.href = import.meta.env.BASE_URL;
} else {
  alert(ERROR.SERVER);
  window.location.href = import.meta.env.BASE_URL;
}
</script>

<style scoped></style>
