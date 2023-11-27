<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="row">
                <div class="col">
                    <Dropdown v-model="state.role" :options="store.roles" placeholder="Select a role"
                        class="w-full md:w-14rem" />
                </div>
                <div class="col">
                    <Dropdown v-model="state.status" :options="store.statuses" placeholder="Select a status"
                        class="w-full md:w-14rem" />
                </div>
            </div>
            <button type="submit" class="btn btn-primary" >Update User</button>
        </form>
    </div>
</template>


<script setup>

import $ from 'jquery';
import Dropdown from 'primevue/dropdown';
import { inject, reactive } from 'vue';
const dialogRef = inject('dialogRef');
const store = inject('store');

const state = reactive(dialogRef.value.data.user);
const prevStatus = state.status;

console.log(state)

function submitForm() {
    
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://127.0.0.1:8080/api/v1/User/Update',
        type: 'PUT',
        data: JSON.stringify(state),
    }).done(() => {
        if (prevStatus === 'ACTIVE' && state.status === 'INACTIVE')
            setInactive()
        if (prevStatus === 'INACTIVE' && state.status === 'ACTIVE')
            setActive()
        dialogRef.value.close();
    })
  
}

function setInactive() {
    console.log("inactive")
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://127.0.0.1:8080/api/v1/User/Inactive',
        type: 'PUT',
        data: JSON.stringify(state),
    })
}

function setActive() {
    console.log("active")
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://127.0.0.1:8080/api/v1/User/Active',
        type: 'PUT',
        data: JSON.stringify(state),
    })
}

</script>

<style scoped></style>