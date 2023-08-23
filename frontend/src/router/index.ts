import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import DefaultLayout from '@/layouts/DefaultLayout.vue';
import ProfileView from '@/views/ProfileView.vue';
import PurchaseView from '@/views/PurchaseView.vue';
import SellView from '@/views/SellView.vue';
import AdminLogin from '@/views/AdminLogin.vue';
import AddProduct from '@/views/AddProductView.vue';
import EditProduct from '@/views/EditProductView.vue';
import ProductView from '@/views/ProductView.vue';
import NotFoundView from '@/views/NotFoundView.vue';

const router = createRouter({
  scrollBehavior() {
    return { top: 0 };
  },
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'defaultLayout',
      component: DefaultLayout,
      children: [
        { path: '/', name: 'home', component: HomeView },
        { path: 'profile', name: 'profile', component: ProfileView },
        { path: 'profile/purchase', name: 'purchase', component: PurchaseView },
        { path: 'profile/sell', name: 'sell', component: SellView },
        { path: 'product/:id', name: 'product', component: ProductView },
        { path: 'product/add', name: 'add product', component: AddProduct },
        { path: 'product/edit/:id', name: 'edit product', component: EditProduct },
        {
          path: '/404',
          name: 'notFound',
          component: NotFoundView
        },
        {
          path: '/:pathMatch(.*)*',
          redirect: '/404'
        }
      ]
    },
    {
      path: '/admin',
      name: 'admin page',
      component: AdminLogin
    }
  ]
});

export default router;
