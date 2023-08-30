<template>
  <div class="category">
    <button @click="onClickPrevCategory">
      <span class="material-icons-outlined"> arrow_back_ios </span>
    </button>
    <ul>
      <li v-for="(category, index) in viewCategory" :key="index">
        <button
          :class="category === props.selectedCategory ? `selected` : ``"
          @click="onClickCategory(category)"
        >
          {{ CATEGORY[category] }}
        </button>
      </li>
    </ul>
    <button @click="onClickNextCategory">
      <span class="material-icons-outlined"> arrow_forward_ios </span>
    </button>
  </div>
  <div class="category small-category">
    <ul v-if="smallCategoryIsOpen">
      <li v-for="(category, index) in categories" :key="index">
        <button
          :class="category === props.selectedCategory ? `selected` : ``"
          @click="onClickCategory(category)"
        >
          {{ category }}
        </button>
      </li>
    </ul>
    <div v-else>카테고리 열기</div>
    <button @click="openCategory">{{ smallCategoryIsOpen ? `닫기` : `열기` }}</button>
  </div>
</template>

<script setup lang="ts">
import { CATEGORY, CATEGORY_LIST } from '@/constants/category';
import { computed, ref } from 'vue';

const props = defineProps({
  selectedCategory: {
    type: String,
    required: true
  }
});
const emits = defineEmits(['update:selectedCategory']);

const smallCategoryIsOpen = ref(false);

const categoryPageNumber = ref(0);
const viewCategoryNumber = ref(8);

// TODO: 수정 -> 서버로부터
const categories = ref(CATEGORY_LIST);

const openCategory = () => {
  smallCategoryIsOpen.value = !smallCategoryIsOpen.value;
};

const onClickPrevCategory = () => {
  categoryPageNumber.value -= 1;
  if (categoryPageNumber.value < 0) categoryPageNumber.value = 0;
};

const onClickNextCategory = () => {
  categoryPageNumber.value += 1;
  const maxCategoryPage = Math.floor(categories.value.length / viewCategoryNumber.value);
  if (maxCategoryPage < categoryPageNumber.value) categoryPageNumber.value = maxCategoryPage;
};

const onClickCategory = (category: string) => {
  emits('update:selectedCategory', category);
};

const viewCategory = computed(() => {
  const start = categoryPageNumber.value * viewCategoryNumber.value;
  return categories.value.slice(start, start + viewCategoryNumber.value);
});
</script>

<style scoped>
.category {
  width: 100%;
  padding: 20px;

  display: flex;
  justify-content: center;
  align-items: center;
  gap: 12px;
}
.category > button {
  font-size: 16px;
  padding: 12px;
}
.category > button:active {
  opacity: 0.4;
}
.category > ul {
  width: 950px;

  display: flex;
  flex-wrap: nowrap;
  gap: 12px;
  justify-content: center;

  padding: 12px 24px;
}
.category > ul > li {
  width: fit-content;
}
.category > ul > li > button {
  background-color: var(--category-item-bg);

  /* width: 100px; */
  min-width: 100px;
  max-width: fit-content;
  padding: 16px 0;

  border: 1px solid rgba(66, 66, 66, 0.605);
  border-radius: 12px;

  font-family: 'LINESeedKR-Bd';
  font-size: 16px;
}

.category > ul > li > .selected {
  background-color: var(--category-item-point-bg);
  color: var(--category-item-point-text);
}
.small-category {
  display: none;
}

@media screen and (max-width: 1023px) {
  .category {
    display: none;
  }
  .small-category {
    display: flex;
    flex-direction: column;
    align-items: end;
    width: 100%;
  }
  .small-category > ul {
    width: fit-content;
    flex-wrap: wrap;
  }
  .small-category > div {
    width: 100%;
    background-color: rgb(232, 249, 255);
    border: 1px solid rgba(135, 135, 135, 0.32);
    border-radius: 12px;
    padding: 12px 24px;
    text-align: center;
    font-size: 18px;
    font-weight: 700;
    justify-content: right;
  }
}
</style>
