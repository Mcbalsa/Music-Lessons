<template>
    <div>
        <div id="container">
            <form>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input id="email" class="form-control" v-model="state.email" placeholder="Email">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" v-model="state.password"
                        placeholder="Password">
                </div>
            </form>
            <Button type="submit" @click="login($event)" label="Log in"></Button>
            <div>
            </div>
        </div>
    </div>
</template>

<script setup>

import Button from 'primevue/button';
import { inject } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const store = inject('store')

const state = {
    email: "",
    password: ""
}

async function login() {
    await store.methods.login(state.email, state.password)
    if (store.userState.user != null) {
        router.push('/profile')
    }
}

</script>

<style scoped>
#container {
    
    margin-left: auto;
    margin-right: auto;
    width: 50%;
    height: 50%;
}
</style>