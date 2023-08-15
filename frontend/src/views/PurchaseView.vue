<template>
  <DefaultMyPage>
    <ItemList :items="tmpItemList" />
  </DefaultMyPage>
</template>

<script setup lang="ts">
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
import ItemList from '@/components/profile/ItemList.vue';
import type { Item } from '@/types/Profile';
import { ref } from 'vue';

const tmpItemList = ref<Array<Item>>([
  { id: 1, name: '구매 물품명1', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 2, name: '구매 물품명2', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 3, name: '구매 물품명3', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 4, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' }
]);
</script>

<style scoped></style>
