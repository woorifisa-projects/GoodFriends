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

const test = await manageLogAPI.selectLog();
// console.log(test);
// const tableDataKey = [];
const testData: any[] = [];
if (test.isSuccess === true && test.data) {
  // console.log('test: ', test.data);
  const data = test.data;

  for (let i = 0; i < data.length; i++) {
    const item = data[i]; // data 배열의 각 항목을 가져옵니다.

    let dataObject = {
      id: (i + 1).toString(), // 1부터 시작하도록 수정합니다.
      email: item.email,
      nickname: item.nickname,
      ban: item.banCount.toString(),
      last_modified_at: item.lastModifiedAt
    };
    testData.push(dataObject);
    // console.log(testData);
  }
}
console.log(testData);
//TODO: api 로그데이터 가져오기
const tableDataKey = ['id', 'email', 'nickname', 'ban', 'last_modified_at'];
const tableHeader = {
  id: 'zz',
  email: '계정',
  nickname: '닉네임',
  ban: '신고당한 횟수',
  last_modified_at: '마지막 접속날짜'
};
// testData = [
//   {
//     id: 1,
//     user_id: 'phm9511',
//     nickname: 'ddddd',
//     banCount: '4',
//     authCheck: '인증완료',
//     date: '2023-02-02'
//   }
// ];
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
