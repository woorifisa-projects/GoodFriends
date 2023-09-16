<template>
  <div class="category">
    <button name="이전 버튼" @click="onClickPrevCategory">
      <span class="material-icons-outlined"> arrow_back_ios </span>
    </button>
    <ul>
      <li v-for="(category, index) in viewCategory" :key="index">
        <button
          :name="CATEGORY[category] + `버튼`"
          :class="category === props.selectedCategory ? `selected` : ``"
          @click="onClickCategory(category)"
        >
          {{ CATEGORY[category] }}
        </button>
      </li>
    </ul>
    <button name="다음 버튼" @click="onClickNextCategory">
      <span class="material-icons-outlined"> arrow_forward_ios </span>
    </button>
  </div>
  <div class="small-category">
    <select name="category" id="category" @change="onChangeCategory">
      <option v-for="(category, index) in categories" :key="index" :value="category">
        {{ CATEGORY[category] }}
      </option>
    </select>
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
const emits = defineEmits(['change']);

const categoryPageNumber = ref(0);
const viewCategoryNumber = ref(8);

const categories = ref(CATEGORY_LIST);

const onChangeCategory = (event: Event) => {
  const target = event.target as HTMLSelectElement;
  emits('change', target.value);
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
  emits('change', category);
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
  font-size: 12px;
  padding: 12px;
  transition: all 0.2s ease;
  color: #888888;
}
.category > button:active {
  opacity: 0.4;
}
.category > button:hover {
  transform: scale(1.1);
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
  background-color: white;

  min-width: 100px;
  max-width: fit-content;
  padding: 16px 0;

  border: 1px solid rgb(240, 240, 240);
  border-radius: 12px;

  font-family: 'LINESeedKR-Rg';

  font-size: 16px;

  transition: all 0.15s ease;
}
.category > ul > li > button:hover {
  transform: scale(1.05);
}
.category > ul > li > .selected {
  background-color: var(--color-yellow);
}
.small-category {
  display: none;
}

@media screen and (max-width: 1023px) {
  .category {
    display: none;
  }
  .small-category {
    width: fit-content;
    padding: 10px;

    display: flex;
    justify-content: center;
    align-items: center;
    gap: 12px;

    font-family: 'LINESeedKR-Rg';
  }

  .small-category select {
    border: 1px solid rgb(240, 240, 240);
    border-radius: 12px;
    padding: 10px;
    font-size: 16px;
    font-family: 'LINESeedKR-Rg';
    color: #888;
  }
  .small-category > ul {
    width: fit-content;
    flex-wrap: wrap;
  }
  .small-category > div {
    width: 100%;
    border: 1px solid rgb(240, 240, 240);
    border-radius: 12px;
    padding: 12px 24px;
    text-align: center;
    font-size: 18px;
    font-weight: 700;
    justify-content: right;
    cursor: pointer;
  }
}
</style>
