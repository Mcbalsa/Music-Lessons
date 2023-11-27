<template>
    <div>
        <form @submit.prevent="submitForm">
            <div>
                    <input v-model="state.creditCard.cardNum" type="text" class="form-control" placeholder="XXXX-XXXX-XXXX-XXXX"
                        aria-label="Credit Card Number">
            </div>
                <div>
                    <input v-model="state.creditCard.name" type="text" class="form-control" placeholder="Name on Card"
                    aria-label="Name On Card">
                </div>
                <div>
                    <input v-model="state.creditCard.cvv" type="text" class="form-control" placeholder="XXX"
                    aria-label="CVV">
                </div>
                <div class="col">
                <Dropdown v-model="state.paymentType" :options="store.paymentTypes" class="w-full md:w-14rem" />
                </div>
                <button type="submit" class="btn btn-primary">Add Card</button>
        </form>
    </div>
</template>

<script setup>
import $ from 'jquery';
import Dropdown from 'primevue/dropdown';
import { inject, ref } from 'vue';
const dialogRef = inject('dialogRef');
const store = inject('store');

const state = ref({
    creditCard: {
        cardNum: "",
        name: "",
        cvv: "",
        paymentType: ""
    }
})


function submitForm() {

    const card = {
        
            cardNum : state.value.creditCard.cardNum,
            name: state.value.creditCard.name,
            cvv: state.value.creditCard.cvv,
            type: state.value.creditCard.paymentType
        
    }

    store.student.creditCard = card

    console.log(store.student)
  $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: 'http://127.0.0.1:8080/api/v1/Student/Update',
        type: 'PUT',
        data: JSON.stringify(store.student),
    }).done((data) => {
        store.student = data
        dialogRef.value.close();
    })
}
</script>