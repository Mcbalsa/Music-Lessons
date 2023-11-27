<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="row">
                <div class="col">
                    <Dropdown v-model="selectedInstrument" :options="store.instruments" class="w-full md:w-14rem" />
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Add Instrument</button>
        </form>
    </div>
</template>

<script setup>
import $ from 'jquery';
import { inject, ref } from 'vue';
const dialogRef = inject('dialogRef');
import Dropdown from 'primevue/dropdown';
const store = inject('store');

let selectedInstrument = ref();


async function submitForm() {

store.student.instruments.push(selectedInstrument.value);
  $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://127.0.0.1:8080/api/v1/Student/Update',
        type: 'PUT',
        data: JSON.stringify(store.student),
    }).done(() => {
        dialogRef.value.close();
    })
}
</script>