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
import FilterListVue from '@/components/profile/FilterList.vue';
import ItemList from '@/components/profile/ItemList.vue';
import type { IItem } from '@/types/profile';

import { ref } from 'vue';

const tmpItemList = ref<Array<IItem>>([
  { id: 1, name: '판매 물품명1', date: 'yyyy-mm-dd', path: '/profile/sell', status: 'SELL' },
  { id: 2, name: '판매 물품명2', date: 'yyyy-mm-dd', path: '/profile/sell', status: 'SELL' },
  { id: 3, name: '판매 물품명3', date: 'yyyy-mm-dd', path: '/profile/sell', status: 'SELL' },
  { id: 4, name: '판매 물품명4', date: 'yyyy-mm-dd', path: '/profile/sell', status: 'SELL' }
]);

const productStatus = [
  {
    id: 0,
    name: '전체',
    value: 'all'
  },
  {
    id: 1,
    name: '판매중',
    value: 'selling'
  },
  {
    id: 2,
    name: '예약중',
    value: 'reservation'
  },
  {
    id: 3,
    name: '판매 완료',
    value: 'done'
  }
];
const checkedStatus = ref('all');

const onClickFilter = (status: string) => {
  console.log('filter: ', status);
};
</script>

<style scoped></style>
