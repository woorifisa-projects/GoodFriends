<template>
  <div class="filter">
    <div v-for="status in props.productStatus" :key="status.id">
      <input
        type="radio"
        :id="status.value"
        name="filter"
        :checked="checkedStatus === status.value"
        @change="emits('click', status.value)"
      />
      <label :for="status.value">
        {{ status.name }}
      </label>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { filter } from '@/types/product';

const props = defineProps({
  productStatus: {
    type: Array<filter>,
    required: true
  },
  checkedStatus: {
    type: String,
    required: true
  }
});

const emits = defineEmits(['click']);
</script>

<style scoped>
.filter {
  width: 100%;
  padding: 18px 24px 0px 24px;
  display: flex;
  justify-content: center;
  gap: 24px;
}

.filter > div {
  width: 80%;
  display: flex;
  border: 1px solid black;
  border-radius: 12px;
}
.filter > div {
  flex: 1;
  background-color: lightblue;
  text-align: center;
  border-radius: 12px;
  overflow: hidden;
  min-width: fit-content;
  width: 50px;
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
  background-color: rgb(106, 127, 232);
  color: white;
}
.filter > div > label {
  display: block;

  width: 100%;
  padding: 12px;
  cursor: pointer;
}
</style>
