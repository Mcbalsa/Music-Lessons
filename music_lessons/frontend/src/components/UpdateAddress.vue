<template>
  <div>
    <form @submit.prevent="submitForm">
      <div class="col">
        <input type="text" v-model="store.student.address.street" class="form-control" placeholder="Street Address"
          aria-label="Street Address">
      </div>
      <div class="form-group row">
        <div class="col">
          <input type="text" v-model="store.student.address.city" class="form-control" placeholder="city" aria-label="City">
        </div>
        <div class="col">
          <Dropdown v-model="store.student.address.state" :options="store.usStates" placeholder="State"
            class="w-full md:w-14rem" />


        </div>
        <div class="col">
          <input type="text" v-model="store.student.address.areaCode" class="form-control" placeholder="zip" aria-label="Zip">
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Update Address</button>
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