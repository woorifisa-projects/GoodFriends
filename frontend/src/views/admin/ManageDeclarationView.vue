<template>
  <div id="total">
    <div class="table">
      <div class="table-in">
        <Table
          :data="testData"
          :header-text="tableHeader"
          :dataKey="tableDataKey"
          @click="onClickDec"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import Table from '@/components/CommonTable.vue';
import router from '@/router';
import type { IDataType } from '@/types/table';

const onClickDec = (item: IDataType) => {
  router.push({
    name: 'admin manage declaration detail',
    params: { id: item.id },
    state: { item }
  });
};

//TODO: api 신고데이터 가져오기
const tableDataKey = [
  'user_id',
  'do_user_nickname',
  'product_category',
  'product_title',
  'declaration_category',
  'created_at',
  'result'
];
const tableHeader = {
  user_id: '피해자ID',
  do_user_nickname: '피해자 닉네임',
  product_category: '상품 카테고리',
  product_title: '상품이름',
  declaration_category: '신고 카테고리',
  created_at: '신고날짜',
  result: '처리상태'
};
const testData = [
  {
    id: 1,
    user_id: 'moon@gmail.com',
    do_user_nickname: 'classicMoon',
    product_category: '전자',
    product_title: '에어팟 프로20',
    declaration_category: '사기',
    created_at: '2023-08-30',
    result: '처리중'
  },
  {
    id: 2,
    user_id: 'hmp9511@gmail.com',
    do_user_nickname: 'Min',
    product_category: '전자',
    product_title: '갤럭시20',
    declaration_category: '사기',
    created_at: '2023-08-29',
    result: '처리중'
  },
  {
    id: 3,
    user_id: 'han@gmail.com',
    do_user_nickname: 'kensayHan',
    product_category: '전자',
    product_title: '맥북 2023 M2',
    declaration_category: '사기',
    created_at: '2023-08-26',
    result: '처리완료'
  },
  {
    id: 4,
    user_id: 'yybeen@gmail.com',
    do_user_nickname: 'mapo security guard',
    product_category: '의류',
    product_title: '엘사드레스',
    declaration_category: '욕설',
    created_at: '2023-08-21',
    result: '처리완료'
  },
  {
    id: 5,
    user_id: 'yybeen@gmail.com',
    do_user_nickname: 'mapo security guard',
    product_category: '의류',
    product_title: '엘사드레스',
    declaration_category: '욕설',
    created_at: '2023-08-21',
    result: '처리완료'
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
