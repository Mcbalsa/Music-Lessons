<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="row">

                <div class="col">
                    <Dropdown v-model="store.student.status" :options="store.statuses" placeholder="Select a status"
                        class="w-full md:w-14rem" />
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Update Student</button>
        </form>
    </div>
</template>


<script setup>

import $ from 'jquery';
import Dropdown from 'primevue/dropdown';
import { inject } from 'vue';
const dialogRef = inject('dialogRef');
const store = inject('store');

const prevStatus = store.student.status;


function submitForm() {

    if (prevStatus === 'ACTIVE' && store.student.status === 'INACTIVE')
        setInactive()
    if (prevStatus === 'INACTIVE' && store.student.status === 'ACTIVE')
        setActive()
    dialogRef.value.close();
}


function setInactive() {
    console.log("inactive")
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://localhost:8080/api/v1/Student/SetInactive?studentId=' + store.student.id,
        type: 'PUT'
    }).done((data) => {
        store.student = data
        dialogRef.value.close();
    })
}

function setActive() {
    console.log("active")
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://localhost:8080/api/v1/Student/SetActive?studentId=' + store.student.id,
        type: 'PUT'
    }).done((data) => {
        store.student = data
        dialogRef.value.close();
    })
}

</script>

<style scoped></style>