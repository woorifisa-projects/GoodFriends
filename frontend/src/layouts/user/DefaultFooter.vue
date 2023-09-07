<template>
  <div id="footer">
    <!-- <div class="logo">
      <img :src="LOGO_IMG" alt="" />
    </div> -->
    <ul class="links">
      <li>Copyright ⓒ 2023. Good Friends. All rights reserved.</li>
    </ul>
    <!-- <ul class="icons">
      <li>
        <a href="https://github.com/woorifisa/GoodFriends">
          <img class="icon" src="@/assets/images/github.png" alt="github icon" />
        </a>
      </li>
      <li>
        <a href="https://github.com/woorifisa/GoodFriends">
          <img class="icon" src="@/assets/images/blog.png" alt="blog icon" />
        </a>
      </li>
    </ul> -->
  </div>
</template>

<script setup lang="ts">
import { LOGO_IMG } from '@/constants/images';
</script>

<style scoped>
#footer {
  position: absolute;
  bottom: 0;

  width: 100%;
  height: 150px;

  display: flex;
  justify-content: center;
  align-items: center;

  padding: 0 200px;

  background: #f2f3f3;
}
.logo {
  width: 200px;
  overflow: hidden;
}
.logo > img {
  width: 100%;
}

#footer > ul {
  display: flex;
  gap: 16px;
}
.links {
  flex-direction: column;
}

.icons > li {
  width: 40px;
  height: 40px;
  /* border: 1px solid black; */
  background: #f2f1fa;
  border-radius: 10px;
  padding: 4px;
  margin: 20px;
}

.icon {
  height: 100%;
}

@media screen and (max-width: 1023px) {
  #footer {
    padding: 0;
  }
}

@media screen and (max-width: 767px) {
}
</style>
