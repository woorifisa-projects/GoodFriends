<template>
  <div class="edit-profile">
    <div class="img-wrap">
      <div class="img">
        <img src="@/assets/tmp/images/image.png" alt="" />
        <button><span class="material-icons-outlined"> photo_camera </span></button>
      </div>
      <div class="img-explain">img 에 대한 정보(.png, .jpg, 크기 등등...)</div>
    </div>
    <div class="input-wrap name">
      <label for="name">name</label>
      <input type="text" />
    </div>
    <div class="input-wrap gender">
      <label for="gender">gender</label>
      <select name="gender" id="gender">
        <option value="default">default</option>
        <option value="man">man</option>
        <option value="woman">woman</option>
      </select>
    </div>
    <div class="input-wrap address">
      <label for="address">address</label>
      <input type="text" />
    </div>
    <div class="input-wrap phone">
      <label for="phone">phone</label>
      <input type="text" />
    </div>
    <div class="input-wrap birthday">
      <label for="birthday">birthday</label>
      <input type="text" />
    </div>
    <div class="btn-wrap">
      <button>수정하기</button>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
.edit-profile {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 18px;

  margin: 48px 0;
}
.img-wrap {
  border-bottom: 1px solid black;

  width: 100%;
  padding: 24px;
  display: flex;
  align-items: center;
  gap: 16px;
}
.img {
  border: 1px solid black;
  width: 250px;
  height: 250px;
  overflow: hidden;
  border-radius: 18px;
  position: relative;
}
.img > button {
  position: absolute;
  bottom: 0;
  right: 0;

  background-color: transparent;
  width: 60px;
  height: 60px;
}
.img > button > span {
  background-color: white;
  border: 1px solid rgba(138, 138, 138, 0.529);
  border-radius: 50%;
  padding: 2px;
  transform: scale(1.5);
}
.img > img {
  max-width: 100%;
  max-height: 100%;
}
.img-explain {
  margin-left: 36px;
  text-align: center;
  height: fit-content;
}
.input-wrap {
  width: 100%;
  padding: 24px;
  border: 1px solid pink;
  display: flex;
}
.input-wrap > label {
  border: 1px solid black;
}
.name {
  border: 1px solid purple;
}
.gender {
  border: 1px solid blue;
}
.address {
  border: 1px solid brown;
}
.phone {
  border: 1px solid aqua;
}
.birthday {
  border: 1px solid darkcyan;
}
.btn-wrap {
  border: 1px solid darkkhaki;
}
</style>
