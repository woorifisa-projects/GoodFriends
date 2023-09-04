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
import manageLogAPI from '@/apis/admin/log';
import { data } from 'node_modules/cypress/types/jquery';
import type { Test } from 'mocha';
import { pushScopeId } from 'vue';

const test = await manageLogAPI.selectLog();
console.log(test);
if (test.isSuccess === true && test.data) {
  console.log('test: ', test.data);
}

//TODO: api 로그데이터 가져오기
const tableDataKey = ['email', 'nickname', 'banCount', 'lastModifiedAt'];
const tableHeader = {
  email: '계정',
  nickname: '닉네임',
  banCount: '신고당한 횟수',
  authCheck: '인증여부',
  date: '마지막 접속날짜'
};
const testData = [
  {
    id: 1,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 2,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 3,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 4,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 5,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 6,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 7,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 8,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 9,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
  },
  {
    id: 10,
    user_id: 'phm9511',
    nickname: 'ddddd',
    banCount: '4',
    authCheck: '인증완료',
    date: '2023-02-02'
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
.page-title {
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.page-title-in {
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
