<template>
  <div class="header">
    <div class="title">logo</div>
    <div class="wrap">
      <div>
        <button>판매하기</button>
      </div>
      <button id="login">login</button>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
.header {
  width: 100%;
  height: 64px;
  padding: 0 20px;

  position: sticky;
  top: 0;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

  background: white;
  box-shadow: 2px 2px 10px rgba(202, 202, 202, 0.389);

  font-family: 'IBM Plex Sans KR', sans-serif;
}

.title {
  font-size: 24px;
  font-weight: 400;
}
.wrap {
  display: flex;
  align-items: center;
  gap: 20px;
  font-weight: 300;
}

.wrap button {
  font-size: 16px;
}
</style>
