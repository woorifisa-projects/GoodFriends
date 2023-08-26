<template>
  <RouterView />
  <!-- <CommonTableVue :data="testdate" :header-text="headerText" :data-key="dateKey" /> -->
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router';
// import CommonTableVue from './components/CommonTable.vue';

// const testdate = [
//   {
//     id: 0,
//     name: '123',
//     age: '12'
//   },
//   {
//     id: 1,
//     name: '1234',
//     age: '152'
//   },
//   {
//     id: 2,
//     name: '1223',
//     age: '123'
//   }
// ];

// const headerText = {
//   name: '이름',
//   age: '나이'
// };

// const dateKey = ['name', 'age'];
</script>

<style scoped></style>
