<template>
  <CommonModalVue :is-visible="props.isVisible" @updateVisible="emits('update:isVisible', $event)">
    <div class="modal">
      <div class="modal-contents">
        <div>
          <p v-for="(text, index) in props.content" :key="index">{{ text }}</p>
        </div>
      </div>
      <div v-show="props.visibleButton" class="modal-buttons" @click="updateResponse">
        <button name="취소" class="no-btn" value="no">no</button>
        <button name="확인" class="yes-btn" value="yes">yes</button>
      </div>
      <div v-show="!props.visibleButton" class="modal-confirm-button" @click="confirmResponse">
        <button name="확인" class="confirm-btn" value="confirm">확인</button>
      </div>
    </div>
  </CommonModalVue>
</template>

<script setup lang="ts">
import CommonModalVue from '@/components/CommonModal.vue';

const props = defineProps({
  isVisible: {
    type: Boolean,
    required: true
  },
  response: {
    type: Boolean,
    required: true
  },
  content: {
    type: Array<string>,
    default: ['정말 등록하시겠습니까?', '변경내용은 저장되지 않습니다.']
  },
  visibleButton: {
    type: Boolean,
    default: true
  }
});

const emits = defineEmits(['update:isVisible', 'update:response']);

const updateResponse = (event: Event) => {
  const target = event.target as HTMLButtonElement;
  if (target.value === undefined) return;
  if (target.value === 'yes') {
    emits('update:response', true);
  }
  if (target.value === 'no') {
    emits('update:response', false);
  }
  emits('update:isVisible', false);
};

const confirmResponse = (event: Event) => {
  const target = event.target as HTMLButtonElement;
  if (target.value === undefined) return;
  if (target.value === 'confirm') {
    emits('update:isVisible', true);
  }
  emits('update:isVisible', false);
};
</script>

<style scoped>
.modal {
  font-family: 'LINESeedKR-Rg';
  padding: 40px;
  position: relative;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.modal-contents {
  font-size: 24px;
  padding: 0 24px;
}
.modal-buttons {
  display: flex;
  justify-content: space-around;
}
.modal-buttons > button {
  font-family: 'LINESeedKR-Bd';
  font-size: 18px;
  width: 100px;
  border: 1px solid rgb(240, 240, 240);
  border-radius: 12px;
  padding: 12px 18px;

  transition: transform 0.3s ease;
}
.yes-btn:hover {
  transform: scale(1.03);
}
.yes-btn {
  background-color: var(--color-yellow);
}

.modal-confirm-button {
  display: flex;
  justify-content: space-around;
}
.confirm-btn:hover {
  transform: scale(1.03);
}
.confirm-btn {
  font-family: 'LINESeedKR-Bd';
  font-size: 18px;
  width: 100px;
  border: 1px solid rgb(240, 240, 240);
  border-radius: 12px;
  padding: 12px 18px;

  transition: transform 0.3s ease;
  background-color: var(--color-yellow);
}

@media screen and (max-width: 1200px) {
  /* .modal {
    width: 90vw;
  } */
}

@media screen and (max-width: 767px) {
  /* .modal {
    width: 100vw;
    height: 90vh;
  } */
}
</style>
