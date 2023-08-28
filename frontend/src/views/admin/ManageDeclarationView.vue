<template>
  <div id="total">
    <div class="table">
      <div class="table-in">
        <Table :data="testData" :header-text="tableHeader" :dataKey="tableDataKey" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import Table from '@/components/CommonTable.vue';

//TODO: api 신고데이터 가져오기
const tableDataKey = ['created_at', 'product_id', 'user_id', 'content'];
const tableHeader = {
  created_at: '신고날짜',
  product_id: '상품ID',
  user_id: '피해자ID',
  content: '신고내용'
};
const testData = [
  {
    id: 1,
    created_at: '2023-09-01',
    product_id: 'product_123',
    user_id: 'jmp951',
    content: '터무니 없는 가격 확인 요청'
  },
  {
    id: 2,
    created_at: '2023-08-20',
    product_id: 'product_13',
    user_id: 'fdsfdf1',
    content: '거짓 신분'
  },
  {
    id: 3,
    created_at: '2023-08-13',
    product_id: 'product_123',
    user_id: 'j12351',
    content: '장기간 업로드 상품'
  },
  {
    id: 4,
    created_at: '2023-08-10',
    product_id: 'product_123',
    user_id: 'jmdddp951',
    content: '터무니 없는 가격 확인 요청'
  },
  {
    id: 5,
    created_at: '2023-08-01',
    product_id: 'product_123',
    user_id: 'jmp951',
    content: '터무니 없는 가격 확인 요청'
  }
];
</script>

<style scoped>
#total {
  display: flex;
  justify-content: center;
  width: 100%;
  height: 100%;
}

.table {
  width: 100%;
  display: flex;
  justify-content: center;
}
.table-in {
  width: 80%;
  max-height: 100vh;
  display: flex;
  justify-content: center;
  overflow-y: scroll;
}
</style>
