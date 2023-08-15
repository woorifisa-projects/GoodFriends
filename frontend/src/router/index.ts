import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import DefaultLayout from '@/layouts/DefaultLayout.vue';
import ProfileView from '@/views/ProfileView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'defaultLayout',
      component: DefaultLayout,
      children: [
        { path: '/', name: 'home', component: HomeView },
        { path: 'profile', name: 'profile', component: ProfileView }
      ]
    }
  ]
});

export default router;
