<template>
    <div>
        <RouterView/>
    </div>
    <div id = "sidebar">
      <div  class = "sidebar-inner">
          
          <div class = "logo">
          <div class = "logo-name">
            <button @click = "onClickMangeLog">GoodFriends</button>
          </div>
          <div class = "logo-admin">
            관리자
          </div>
          </div>

           <div class = "admin-menu">
            <ul class = "admin-menu-ul">
                <li><button @click = "onClickMangeLog">로그 관리</button></li>
                <li><button @click = "onClickManegeUser">사용자 관리</button></li>
                <li><button @click = "onClickManegeProduct">상품 관리</button></li>
                <li><button @click = "onClickManegeDeclaration">신고 관리</button></li>
            </ul>
           </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import router from '@/router';

const onClickMangeLog = () => {
    // Todo
    // router.push()
}

const onClickManegeUser = () => {
    // router.push()
}

const onClickManegeProduct = () => {
    // router.push()
}

const onClickManegeDeclaration = () => {
    // router.push()
}
</script>

<style scoped>
#sidebar{
    position: absolute;

    width: 170px;
    height: 100%;

    background-color: dodgerblue;


}

.sidebar-inner{
    padding-top:100px;
    
}
.logo{
    color : whitesmoke;
    padding-left: 20px;
    font-size: 21px;
    font-weight: bold;
}

.logo-admin{
    padding-top: 30px;
}
.admin-menu-ul{
    padding-top: 50px;
    text-align: center;
}
ul{
    display: flex;
    flex-direction: column;
    gap: 20px;
}

button{
    height: 30px;
    /* padding-top: 10px; */
    
    font-size: 18px;
    font-weight: bold;
    background-color: dodgerblue;
}

</style>