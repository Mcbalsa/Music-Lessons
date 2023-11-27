import { createRouter, createWebHistory } from "vue-router";
import AdminPage from '../components/AdminPage.vue';
import HomePage from '../components/HomePage.vue';
import LessonDetailsPage from '../components/LessonDetailsPage.vue';
import LessonsPage from '../components/LessonsPage.vue';
import LoginPage from '../components/LoginPage.vue';
import ProfilePage from '../components/ProfilePage.vue';
import StudentDetailsPage from '../components/StudentDetailsPage.vue';
import StudentsPage from '../components/StudentsPage.vue';

const routes = [
    {
      path: "/",
      name: "HomePage",
      component: HomePage
    },
    {
      path: "/login",
      name: "LoginPage",
      component: LoginPage
    },
    {
      path: "/profile",
      name: "ProfilePage",
      component: ProfilePage
    },
    {
      path: ('/admin'),
      name: ('AdminPage'),
      component: AdminPage
    },
    {
      path: ('/students'),
      name: ('StudentsPage'),
      component: StudentsPage
    },
    {
      path: '/lessons',
      name: 'LessonsPage',
      component: LessonsPage
    },
    {
      path: "/studentDetails",
      name: 'StudentDetailsPage',
      component: StudentDetailsPage,
      meta: {
        auth: true
      }
    },
    {
      path: "/lessonDetails",
      name: 'LessonDetailsPage',
      component: LessonDetailsPage,
      meta: {
        auth: true
      }
    }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
