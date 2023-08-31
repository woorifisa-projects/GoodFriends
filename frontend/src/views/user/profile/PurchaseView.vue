<template>
  <DefaultMyPage>
    <FilterListVue
      :product-status="productStatus"
      @click="onClickFilter"
      :checkedStatus="checkedStatus"
    />
    <ItemList :items="tmpItemList" />
  </DefaultMyPage>
</template>

<script setup lang="ts">
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
import ItemList from '@/components/profile/ItemList.vue';
import FilterListVue from '@/components/profile/FilterList.vue';
import type { IItem } from '@/types/profile';
import { ref } from 'vue';

const tmpItemList = ref<Array<IItem>>([
  { id: 1, name: '구매 물품명1', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 2, name: '구매 물품명2', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 3, name: '구매 물품명3', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 4, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 5, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 6, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 7, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 8, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 9, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 10, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' },
  { id: 11, name: '구매 물품명4', date: 'yyyy-mm-dd', path: '/profile/purchase' }
]);

const productStatus = [
  {
    id: 0,
    name: '전체',
    value: 'all'
  },
  {
    id: 1,
    name: '예약',
    value: 'reservation'
  },
  {
    id: 2,
    name: '구매',
    value: 'purchase'
  }
];
const checkedStatus = ref('all');

const onClickFilter = (status: string) => {
  console.log('filter: ', status);
};
</script>

<style scoped></style>
