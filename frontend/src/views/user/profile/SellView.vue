<template>
  <DefaultMyPage>
    <FilterListVue
      :product-status="productStatus"
      @click="onClickFilter"
      :checkedStatus="checkedStatus"
      :type="PRODUCT_STATUS"
    />
    <ItemList :items="sellList" :type="PRODUCT_STATUS" />
  </DefaultMyPage>
</template>

<script setup lang="ts">
import profileAPI from '@/apis/user/profile';
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
import FilterListVue from '@/components/profile/FilterList.vue';
import ItemList from '@/components/profile/ItemList.vue';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { PRODUCT_STATUS } from '@/constants/strings/product';
import type { ISellAndPurchaseList } from '@/types/profile';

import { onMounted, ref } from 'vue';

const sellList = ref<Array<ISellAndPurchaseList>>([]);
const productStatus = ['ALL', 'SELL', 'RESERVATION', 'COMPLETED'];
const checkedStatus = ref('ALL');

const getList = async (status: string, important = false) => {
  if (!important && checkedStatus.value === status) return;
  const res = await profileAPI.getSellList(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    status
  );
  if (!res.isSuccess) {
    alert(res.message);
    return;
  }
  if (!res.data) return;
  sellList.value = res.data;
};

const onClickFilter = async (status: string) => {
  console.log(status);
  if (checkedStatus.value === status) return;
  await getList(status);
};

onMounted(async () => {
  await getList('ALL', true);
});
</script>

<style scoped></style>
