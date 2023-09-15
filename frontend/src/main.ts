import './assets/main.css';

import { createApp } from 'vue';
import { createPinia } from 'pinia';
import 'aos/dist/aos.css';

import App from './App.vue';
import router from './router';
import 'material-icons/iconfont/material-icons.css';

import AOS from 'aos';

const app = createApp(App);

AOS.init();
app.use(createPinia());
app.use(router);

app.mount('#app');
