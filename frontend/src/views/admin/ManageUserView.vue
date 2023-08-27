<template>
  <div id="total">
    <div class="table">
      <div class="table-in">
        <Table
          :data="testData"
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

import type { dataType } from '@/types/table';

const onClickDetailUser = (item: dataType) => {
  router.push({
    name: 'admin manage user detail',
    params: { id: item.id },
    state: { item }
  });
};

//TODO: api 사용자데이터 가져오기
const tableDataKey = [
  'user_oauth_id',
  'email',
  'nickname',
  'profile_image',
  'created_at',
  'updated_at',
  'count',
  'mail_certification',
  'activated'
];
const tableHeader = {
  user_oauth_id: '소셜로그인id',
  email: '이메일',
  nickname: '닉네임',
  profile_image: '프로필 이미지',
  created_at: '생성일자',
  updated_at: '최신일자',
  count: '신고당한 횟수',
  mail_certification: '인증여부',
  activated: '활성화 여부'
};
const testData = [
  {
    id: 1,
    user_oauth_id: 'hmp9511',
    email: 'hmp9511@google.com',
    nickname: '킹왕짱',
    profile_image: '프로필 이미지',
    created_at: '2023-03-30',
    updated_at: '2023-08-26',
    count: '0',
    mail_certification: '완료',
    activated: '활성화'
  },
  {
    id: 2,
    user_oauth_id: 'hm2222',
    email: 'hmp9511@google.com',
    nickname: '킹왕짱',
    profile_image: '프로필 이미지',
    created_at: '2023-03-30',
    updated_at: '2023-08-26',
    count: '0',
    mail_certification: '완료',
    activated: '활성화'
  },
  {
    id: 3,
    user_oauth_id: '676767',
    email: 'hmp9511@google.com',
    nickname: '킹왕짱',
    profile_image: '프로필 이미지',
    created_at: '2023-03-30',
    updated_at: '2023-08-26',
    count: '0',
    mail_certification: '완료',
    activated: '활성화'
  },
  {
    id: 4,
    user_oauth_id: 'fghfgh',
    email: 'hmp9511@google.com',
    nickname: '킹왕짱',
    profile_image: '프로필 이미지',
    created_at: '2023-03-30',
    updated_at: '2023-08-26',
    count: '0',
    mail_certification: '완료',
    activated: '활성화'
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
