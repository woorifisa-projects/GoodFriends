<template>
  <div id="total">
    <div class="table">
      <div class="table-in">
        <Table :data="data" :header-text="tableHeader" :dataKey="tableDataKey" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import Table from '@/components/CommonTable.vue';
import manageLogAPI from '@/apis/admin/log';
import type { IDataType } from '@/types/table';

const logApi = await manageLogAPI.selectLog();

const data: Array<IDataType> = [];

if (logApi.isSuccess === true && logApi.data) {
  const temp = logApi.data;
  for (let i = 0; i < temp.length; i++) {
    const item = temp[i];

    let dataObject = {
      id: i + 1,
      email: item.email,
      nickname: item.nickname,
      ban: item.banCount.toString(),
      last_modified_at: item.lastModifiedAt
    };
    data.push(dataObject);
  }
}
//데이터 확인 콘솔 console.log(data);

const tableDataKey = ['email', 'nickname', 'ban', 'last_modified_at'];
const tableHeader = {
  email: '계정',
  nickname: '닉네임',
  ban: '신고당한 횟수',
  last_modified_at: '마지막 접속날짜'
};
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
