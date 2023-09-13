<template>
  <div id="footer">
    <ul class="links">
      <li>Copyright ⓒ 2023. Good Friends. All rights reserved.</li>
    </ul>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
#footer {
  position: absolute;
  bottom: 0;

  width: 100%;
  height: 66px;

  display: flex;
  justify-content: center;
  align-items: center;

  padding: 0 200px;

  background: #f2f3f3;
  box-shadow: rgb(146 153 184 / 5%) 0px -5px 10px;
}

#footer > ul {
  display: flex;
  gap: 16px;
}
.links {
  flex-direction: column;
}

@media screen and (max-width: 1023px) {
  #footer {
    padding: 0;
  }
}

@media screen and (max-width: 767px) {
}
</style>
