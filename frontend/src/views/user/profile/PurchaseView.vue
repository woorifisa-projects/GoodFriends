<template>
  <DefaultMyPage>
    <FilterListVue
      :product-status="productStatus"
      @click="onClickFilter"
      :checkedStatus="checkedStatus"
      :type="CONFIRM_STATUS"
    />
    <ItemList :items="purchaseList" :type="CONFIRM_STATUS" :message="PRODUCT.PURCHASE_PRODUCT" />
  </DefaultMyPage>
</template>

<script setup lang="ts">
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
import ItemList from '@/components/profile/ItemList.vue';
import FilterListVue from '@/components/profile/FilterList.vue';
import type { ISellAndPurchaseList } from '@/types/profile';
import { onMounted, ref } from 'vue';
import profileAPI from '@/apis/user/profile';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { CONFIRM_STATUS, PRODUCT } from '@/constants/strings/product';
import { useLoadingStore } from '@/stores/loading';

const purchaseList = ref<Array<ISellAndPurchaseList>>([]);
const productStatus = ['ALL', 'ORDERING', 'COMPLETED'];
const checkedStatus = ref('ALL');

const getList = async (status: string, important = false) => {
  if (!important && checkedStatus.value === status) return;
  const res = await profileAPI.getPurchaseList(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    status
  );
  if (!res.isSuccess) {
    alert(res.message);
    return;
  }
  checkedStatus.value = status;
  if (!res.data) return;
  purchaseList.value = res.data;
};

const onClickFilter = async (status: string) => {
  if (checkedStatus.value === status) return;
  await getList(status);
};

onMounted(async () => {
  const loadingStore = useLoadingStore();
  loadingStore.setLoading(true);
  await getList('ALL', true);
  loadingStore.setLoading(false);
});
</script>

<style scoped></style>
