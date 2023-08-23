<template>
  <Teleport to="body">
    <div v-if="props.isVisible" ref="back" class="modal-back" @click="closeClick">
      <div class="modal-front">
        <slot>
          <div>hi</div>
        </slot>
      </div>
    </div>
  </Teleport>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const props = defineProps({
  isVisible: {
    type: Boolean,
    required: true
  }
});

const emits = defineEmits(['updateVisible']);

const back = ref<Element>();

const closeClick = (e: Event) => {
  if (e.target === back.value) {
    emits('updateVisible', false);
  }
};
</script>

<style scoped>
.modal-back {
  height: 100vh;
  width: 100vw;

  z-index: 100;
  position: fixed;
  top: 0;

  display: flex;
  justify-content: center;
  align-items: center;

  background-color: rgba(213, 213, 213, 0.527);
}
.modal-front {
  min-width: 60%;
  max-width: 89%;
  min-height: 70%;
  max-height: 90%;

  background-color: rgb(255, 255, 255);
  box-shadow: 1px 1px 10px rgba(145, 145, 145, 0.495);
  border: 1px solid rgba(102, 102, 102, 0.587);
  border-radius: 16px;

  padding: 32px;
}
</style>
