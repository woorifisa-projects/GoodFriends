<template>
  <div>
    <input class="address-btn" type="button" @click="getRoadAddress" :value="props.text" />
  </div>
</template>

<script setup lang="ts">
import type { AddressDaum } from '@/types/profile';
const props = defineProps({
  text: {
    type: String,
    default: '우편번호 찾기'
  }
});

const emits = defineEmits(['click']);

function getRoadAddress() {
  new window.daum.Postcode({
    oncomplete: function (data: AddressDaum) {
      emits('click', data.roadAddress);
    }
  }).open();
}
</script>

<style scoped>
.address-btn {
  width: 100%;
  padding: 12px 24px;
  border: none;
  background-color: white;
  border: 1px solid black;
  border-radius: 6px;

  cursor: pointer;
}
</style>
