import { createRouter, createWebHistory } from 'vue-router';

import DefaultLayout from '@/layouts/user/DefaultLayout.vue';

import HomeView from '@/views/user/HomeView.vue';
import ProfileView from '@/views/user/profile/ProfileView.vue';
import PurchaseView from '@/views/user/profile/PurchaseView.vue';
import SellView from '@/views/user/profile/SellView.vue';
import AddProduct from '@/views/user/AddProductView.vue';
import EditProduct from '@/views/user/EditProductView.vue';
import ProductView from '@/views/user/ProductView.vue';
import ProductReportView from '@/views/user/ProductReportView.vue';
import ProductReportContentView from '@/views/user/ProductReportContentView.vue';
import NotFoundView from '@/views/user/NotFoundView.vue';
import OrderView from '@/views/user/OrderView.vue';
import ReceiveCodeView from '@/views/user/ReceiveCodeView.vue';
import ErrorView from '@/views/user/ErrorView.vue';

import { goPageWithReload } from '@/utils/goPage';
import { useAdminStore } from '@/stores/admin';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { ERROR_MSG } from '@/constants/strings/error';

const router = createRouter({
  scrollBehavior() {
    return { top: 0 };
  },
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'defaultLayout',
      meta: { type: 'user' },
      component: DefaultLayout,
      children: [
        { path: '/', name: 'home', component: HomeView, meta: { every: true } },
        { path: 'profile/:id', name: 'profile', component: ProfileView },
        { path: 'profile/:id/purchase', name: 'purchase', component: PurchaseView },
        { path: 'profile/:id/sell', name: 'sell', component: SellView },
        { path: 'product/:id', name: 'product', component: ProductView },
        { path: 'product/report/:id/1', name: 'report category', component: ProductReportView },
        {
          path: 'product/report/:id/2',
          name: 'report content',
          component: ProductReportContentView
        },
        { path: 'product/:id/order', name: 'view order', component: OrderView },
        { path: 'product/add', name: 'add product', component: AddProduct },
        { path: 'product/edit/:id', name: 'edit product', component: EditProduct },
        { path: 'err/:type', name: 'login err', component: ErrorView },
        {
          path: '/google-callback',
          name: 'receive code',
          component: ReceiveCodeView,
          meta: { every: true }
        },
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
      meta: { type: 'login' },
      component: () => import('@/views/admin/AdminLogin.vue')
    },
    {
      path: '/admin',
      name: 'In admin page',
      meta: { type: 'admin' },
      component: () => import('@/layouts/admin/DefaultSide.vue'),
      children: [
        {
          path: 'log',
          name: 'admin log',
          component: () => import('@/views/admin/ManageLogView.vue')
        },
        {
          path: 'manage/user',
          name: 'admin manage user',
          component: () => import('@/views/admin/ManageUserView.vue')
        },
        {
          path: 'manage/user/:id',
          name: 'admin manage user detail',
          component: () => import('@/views/admin/EditUserInfoView.vue')
        },
        {
          path: 'product/edit/:id',
          name: 'admin edit product',
          component: () => import('@/views/admin/EditProductByAdminView.vue')
        },
        {
          path: 'product/manage',
          name: 'admin manage product',
          component: () => import('@/views/admin/ManageProductView.vue')
        },
        {
          path: 'product/manage/add',
          name: 'admin add product',
          component: () => import('@/views/admin/AddProductByAdminView.vue')
        },
        {
          path: 'product/manage/:id',
          name: 'admin product',
          component: () => import('@/views/admin/ManageProductView.vue')
        },
        {
          path: 'declaration',
          name: 'admin declaration',
          component: () => import('@/views/admin/ManageDeclarationView.vue')
        },
        {
          path: 'declaration/manage/:id',
          name: 'admin manage declaration detail',
          component: () => import('@/views/admin/DeclarationDetailView.vue')
        }
      ]
    }
  ]
});

router.beforeEach((to, form, next) => {
  const adminStore = useAdminStore();
  const isLogin = localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN);
  if (to.meta.type === 'login') {
    next();
    return;
  }
  if (to.meta.type === 'user') {
    if (!isLogin && !to.meta.every) {
      alert(ERROR_MSG.NEED_LOGIN);
      goPageWithReload();
      return;
    }
    next();
    return;
  }
  if (to.meta.type === 'admin' && !adminStore.accessToken) {
    alert(ERROR_MSG.NEED_LOGIN);
    goPageWithReload('admin');
    next();
    return;
  }

  next();
});
export default router;
