<template>
  <div>
    <form @submit.prevent="submitForm">
      <div class="col">
        <input type="text" v-model="store.student.firstName" class="form-control" placeholder="First Name"
          aria-label="First Name">
      </div>
      <div class="col">
        <input type="text" v-model="store.student.lastName" class="form-control" placeholder="Last Name"
          aria-label="Last Name">
      </div>
      <div class="form-group row">
        <div class="col">
          <input type="text" v-model="store.student.guardName" class="form-control" placeholder="Guardian Name" aria-label="Guardian Name">
        </div>
        <div class="col">
          <input type="text" v-model="store.student.phoneNum" class="form-control" placeholder="Phone Number" aria-label="Phone Number">
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Update Info</button>
    </form>
  </div>
</template>


<script setup>
import $ from 'jquery';
import Dropdown from 'primevue/dropdown';
import { inject } from 'vue';
const dialogRef = inject('dialogRef');
const store = inject('store');


function submitForm() {
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

<style scoped></style>