<template>
  <div id="total">
    <div class="table">
      <div class="table-in">
        <Table
          :data="data"
          :header-text="tableHeader"
          :dataKey="tableDataKey"
          @click="onClickDetailUser"
        />
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import Table from '@/components/CommonTable.vue';
import router from '@/router';
import allUserAPI from '@/apis/admin/allUserInfo';
import type { IDataType } from '@/types/table';
const usersApi = await allUserAPI.selectLog();
const data: Array<IDataType> = [];
if (usersApi.isSuccess === true && usersApi.data) {
  const temp = usersApi.data;
  for (let i = 0; i < temp.length; i++) {
    const item = temp[i];
    let dataObject = {
      id: i + 1,
      userid: item.id,
      email: item.email,
      nickname: item.nickname,
      profileImageUrl: item.profileImageUrl,
      createdAt: item.createdAt,
      lastModifiedAt: item.lastModifiedAt,
      banCount: item.banCount,
      phone: item.phone,
      address: item.address,
      activated: item.activated ? '활성화' : '비활성화',
      accountType: item.accountType,
      accountNumber: item.accountNumber
    };
    data.push(dataObject);
  }
} else if (usersApi.isSuccess === false) {
  alert('페이지 오류입니다.');
}
const onClickDetailUser = (item: IDataType) => {
  router.push({
    name: 'admin manage user detail',
    params: { id: item.id },
    state: { item, data }
  });
};
//TODO: api 사용자데이터 가져오기
const tableDataKey = ['email', 'nickname', 'createdAt', 'lastModifiedAt', 'banCount', 'activated'];
const tableHeader = {
  email: '이메일',
  nickname: '닉네임',
  createdAt: '생성일자',
  lastModifiedAt: '최신일자',
  banCount: '신고당한 횟수',
  activated: '활성화여부'
};
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
