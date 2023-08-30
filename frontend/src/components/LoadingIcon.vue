<template>
  <div class="square">
    <div class="spin"></div>
    <div class="loading-text">loading...</div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
.square {
  width: 100%;
  height: 100vh;

  position: absolute;
  top: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(211, 211, 211, 0.37);
  z-index: 1000;
}

.spin {
  height: 270px;
  width: 270px;
  border-radius: 50%;
  border: dashed 12px var(--category-item-point-bg);
  animation-name: spin;
  animation-duration: 6s;
  animation-iteration-count: infinite;
  animation-timing-function: linear;
}
.loading-text {
  position: absolute;
  font-size: 32px;
  font-family: 'LINESeedKR-Bd';
  animation-name: text-opacity;
  animation-duration: 2.5s;
  animation-iteration-count: infinite;
  animation-timing-function: linear;
}
@keyframes text-opacity {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>
