<template>
  <div>
    <nav>
      <div class="nav nav-tabs justify-content-end mb-4" role="tablist">
        <a v-if="store.userState.user !== null" class="nav-item nav-link" data-toggle="tab" @click="doRoute('ProfilePage')" role="tab">Profile</a>
        <a v-if="isAdmin()" class="nav-item nav-link" data-toggle="tab" @click="doRoute('AdminPage')" role="tab">Admin</a>
        <a v-if="store.userState.user !== null" class="nav-item nav-link" data-toggle="tab" @click="doRoute('StudentsPage')" role="tab">Students</a>
        <a v-if="store.userState.user !== null" class="nav-item nav-link" data-toggle="tab" @click="doRoute('LessonsPage')" role="tab">Lessons</a>
        <a v-if="store.userState.user === null" class="nav-item nav-link" data-toggle="tab" @click="doRoute('LoginPage')" role="tab">Log In</a>
        <a v-if="store.userState.user !== null" class="nav-item nav-link" data-toggle="tab" @click="logout()" role="tab">Log Out</a>
      </div>
    </nav>
    <router-view />
  </div>
</template>

<script setup>

import store from '@/store';
import { provide } from 'vue';
import { useRouter } from 'vue-router';

provide('store', store);

const router = useRouter();

function isAdmin() {
  return store.userState.user !== null && (store.userState.user.role == 'ADMIN' || store.userState.user.role == 'OWNER')
}

function logout() {
  store.methods.logout()
  router.push('/')
}

function doRoute(whereTo) {

  switch (whereTo) {
    case 'LoginPage':
      router.push('/login')
      break;
    case 'ProfilePage':
      router.push('/profile')
      break;
    case 'AdminPage':
      router.push('/admin')
      break;
    case 'StudentsPage':
      router.push('/students')
      break;
    case 'LessonsPage':
      router.push('/lessons')
      break;
    default:
      router.push('/');
  }
}

</script>


<style scoped>
  
</style>