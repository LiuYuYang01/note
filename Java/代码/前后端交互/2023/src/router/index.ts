import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [
  {
    path: "/order",
    component: () => import("@/view/Order.vue"),
  }
];

const router = createRouter({
  // 选择history模式
  history: createWebHistory(),
  routes,
});

export default router;
