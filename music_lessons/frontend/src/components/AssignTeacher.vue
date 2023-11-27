<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="row">
                <div class="col">
                    <Dropdown v-model="teacher" :options="store.teachers.value" optionLabel="firstName"
                        placeholder="Select teacher" class="w-full md:w-14rem" />
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Assign Teacher</button>
        </form>
    </div>
</template>

<script setup>

import $ from 'jquery';
import Dropdown from 'primevue/dropdown';
import { inject, onMounted } from 'vue';
const dialogRef = inject('dialogRef');
const store = inject('store');

let teacher;

onMounted(async () => {
    store.methods.loadTeachers()
    console.log(store.teachers.value)
});

function submitForm() {

    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://127.0.0.1:8080/api/v1/Student/AssignTeacher?studentId=' +
            store.student.id +
            '&teacherId=' +
            teacher.id,
        type: 'put',
    }).done((data) => {
        store.student = data
        dialogRef.value.close();
    })
}


</script>

<style lang="scss" scoped></style>