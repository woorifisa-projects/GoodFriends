<template>
  <LoadingIconVue />
</template>

<script setup lang="ts">
import loginAPI from '@/apis/user/login';
import LoadingIconVue from '@/components/LoadingIcon.vue';
import { goErrorWithReload, goPageWithReload } from '@/utils/goPage';
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const getQuery = () => {
  const code = route.query.code as string;
  return code || '';
};

onMounted(async () => {
  const res = await loginAPI.getAccessToken(getQuery());

  if (res.isSuccess && res.data) {
    localStorage.setItem('token', res.data);
    goPageWithReload('');
  } else {
    goErrorWithReload(res.type);
  }
});
</script>

<style scoped></style>
