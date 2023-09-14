<template>
  <div class="wrap">
    <ul>
      <li v-if="props.items.length">
        <div class="item" v-for="item in props.items" :key="item.productId" @click="onClick(item)">
          <div class="img">
            <img :src="item.imageUrl || tmpImage" alt="" />
          </div>
          <div class="detail">
            <div>{{ title(item.title) }}</div>
            <button
              class="btn_complete"
              v-if="props.type[item.status] == '예약중'"
              @click="onClickComplete"
            >
              거래완료
            </button>
            <div class="empty-div" v-else></div>
            <div>{{ item.sellPrice.toLocaleString() }}원</div>
            <div class="status">{{ props.type[item.status] }}</div>
          </div>
        </div>
      </li>
      <EmptyProductVue v-else :text="props.message" />
    </ul>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import type { IStringToString } from '@/types/dynamic';
import type { ISellAndPurchaseList } from '@/types/profile';
import { tmpImage } from '@/utils/image';
import { computed, ref, type PropType, onMounted } from 'vue';
import EmptyProductVue from '../EmptyProduct.vue';
import { PRODUCT } from '@/constants/strings/product';

const props = defineProps({
  items: {
    type: Array<ISellAndPurchaseList>,
    required: true
  },
  type: {
    type: Object as PropType<IStringToString>,
    required: true
  },
  message: {
    type: String,
    default: PRODUCT.NOTHING
  }
});

const onClick = (item: ISellAndPurchaseList) => {
  router.push('/product/' + item.productId);
};
const maxTitle = ref(window.innerWidth > 1023 ? 20 : 10);
const title = computed(() => {
  return (title: string) =>
    title.length > maxTitle.value ? title.substring(0, maxTitle.value) + '...' : title;
});
const onClickComplete = () => {
  router.push('/admin/log');
};

onMounted(() => {
  window.addEventListener('resize', () => {
    const { innerWidth } = window;
    if (innerWidth > 1023) {
      maxTitle.value = 20;
    } else {
      maxTitle.value = 6;
    }
  });
});

const item = props.type; // item을 정의하고 값을 할당
console.log(item);
</script>

<style scoped>
.wrap {
  margin: 20px 56px;
}

.wrap > ul {
  display: flex;
  flex-direction: column;
}
.wrap > ul > li {
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 10px 20px;
  width: 100%;
  height: fit-content;
  border: 1px solid rgb(240, 240, 240);
  border-radius: 10px;
  box-shadow: 0px 0px 3px #0000001b;

  transition: transform 0.3s ease;
}
.item:hover {
  transform: scale(1.02);
}
.item > .img {
  width: 80px;
  height: 80px;
  overflow: hidden;
  border: 1px solid rgb(240, 240, 240);
  box-shadow: 0px 0px 2px #888;
  border-radius: 20px;
}

.img > img {
  height: 100%;
}

.detail {
  display: flex;
  gap: 12px;
  justify-content: space-around;
  width: calc(100% - 100px);
  border-radius: 20px;
  overflow: hidden;
  cursor: pointer;
}

.detail > div {
  flex: 1;
  max-width: 700px;
  width: 100%;
  display: flex;
  justify-content: start;
  align-items: center;
  font-size: 14px;
  text-align: left;
}
.detail > div:first-child {
  font-size: 17px;
  flex: 4;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.detail > div:last-child {
  justify-content: center;

  font-size: 18px;
}
@media screen and (max-width: 1023px) {
  .detail {
    height: 60px;
  }
  .detail > div {
    font-size: 14px;
    line-height: 20px;
    padding: 0;
  }
  .item > .img {
    width: 50px;
    height: 50px;
  }
  .detail > div:first-child {
    flex: 2;
  }
  .detail > div:nth-child(2) {
    font-size: 13px;
  }
  .detail > div:last-child {
    font-size: 12px;
  }
}

@media screen and (max-width: 767px) {
}

.btn_complete {
  font-size: medium;
  border: 1px solid rgb(145, 145, 145);
  border-radius: 10px;
}
.btn_complete:hover {
  background-color: var(--color-yellow);
}
</style>
