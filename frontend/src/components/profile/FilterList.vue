<template>
  <div class="filter">
    <div v-for="(status, index) in props.productStatus" :key="index">
      <input
        type="radio"
        :id="status"
        name="filter"
        :checked="checkedStatus === status"
        @change="emits('click', status)"
      />
      <label :for="status">
        {{ props.type[status] }}
      </label>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { IStringToString } from '@/types/dynamic';
import type { PropType } from 'vue';

const props = defineProps({
  productStatus: {
    type: Array<string>,
    required: true
  },
  checkedStatus: {
    type: String,
    required: true
  },
  type: {
    type: Object as PropType<IStringToString>,
    required: true
  }
});

const emits = defineEmits(['click']);
</script>

<style scoped>
.filter {
  width: 100%;
  padding: 18px 24px 0px 24px;
  padding-right: 56px;
  display: flex;
  flex-direction: row-reverse;
  gap: 12px;
}

.filter > div {
  width: 80%;
  display: flex;
  border: 2px solid rgb(240, 240, 240);
  border-radius: 12px;
}
.filter > div {
  width: 80px;
  text-align: center;
  border-radius: 12px;
  overflow: hidden;
  min-width: fit-content;
  height: 40px;
  display: flex;
  align-items: center;
}

.filter > div > input {
  display: none;
  width: 100%;
  height: 100%;
}
.filter > div > input:checked + label {
  background-color: var(--color-yellow);
  color: black;
}
.filter > div > label {
  display: block;

  width: 100%;
  padding: 12px;
  cursor: pointer;
  font-family: 'LINESeedKR-Bd';
}
</style>
