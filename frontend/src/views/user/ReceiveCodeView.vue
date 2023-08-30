<template>
  <LoadingIconVue />
</template>

<script setup lang="ts">
import { getAccessToken } from '@/apis/userLogin';
import LoadingIconVue from '@/components/LoadingIcon.vue';
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const getQuery = () => {
  const code = route.query.code as string;
  return code || '';
};

onMounted(async () => {
  const code = getQuery();

  const res = await getAccessToken(code);
  if (res.isSuccess) {
    window.location.href = import.meta.env.BASE_URL;
  } else {
    window.location.href = import.meta.env.BASE_URL + 'err/login';
  }
});
</script>

<style scoped></style>
