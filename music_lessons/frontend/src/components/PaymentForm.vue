<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="form-group row">
                <div class="col">
                    <input type="text" v-model="state.name" class="form-control" placeholder="Name"
                        aria-label="Name">
                </div>
                <div class="col">
                    <Dropdown v-model="state.type" :options="store.paymentTypes" placeholder="Card Type"
                        class="w-full md:w-14rem" />
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <input type="text" v-model="state.cardNum" class="form-control" placeholder="Card Number"
                        aria-label="Card Number">
                </div>
                <div class="p-float-label">
                    <Calendar inputId="dob" v-model="state.expDate" />
                    <label for="expDate" class="form-label">Expiration Date</label>
                </div>
                <div class="col">
                    <label for="CVV"></label>
                    <InputMask v-model.lazy="state.cvv" date="CVV" mask="999"
                        placeholder="CVV" />
                </div>
            </div>
            <button v-if="validForm()" type="submit" class="btn btn-primary">Add Payment</button>
            <button v-else type="submit" class="btn btn-primary" disabled>Add Payment</button>
        </form>
        <button @click="payWithCash()" type="button" class="btn btn-primary">Pay With Cash</button> 
    </div>
</template>

<script setup>
import $ from 'jquery';
import Calendar from 'primevue/calendar';
import Dropdown from 'primevue/dropdown';
import InputMask from 'primevue/inputmask';
import { inject, onMounted, reactive, ref } from 'vue';

const dialogRef = inject('dialogRef');
const store = inject('store');
const lesson = reactive(dialogRef.value.data.lesson);
const student = reactive(dialogRef.value.data.studentCC);

const state = ref({
    cardNum: "",
    name: "",
    expDate: "",
    cvv: "",
    type: "",
})



onMounted(async () => {
    // await store.methods.loadTeachers();
    console.log(lesson);
    console.log(student);
    if(student.creditCard != null)
    {
        state.value = 
        {
            cardNum : student.creditCard.cardNum,
            name : student.creditCard.name,
            expDate : student.creditCard.expDate,
            cvv : student.creditCard.cvv,
            type : student.creditCard.type,
        };
    }
});

function submitForm() {
    
    store.lesson.status = "Paid";
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Lesson/Update",
        type: "put",
        data: JSON.stringify(store.lesson),
    }).done(data => {
        dialogRef.value.close();
    });
  
}

function payWithCash() {
    
    store.lesson.status = "Paid";
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Lesson/Update",
        type: "put",
        data: JSON.stringify(store.lesson),
    }).done(data => {
        dialogRef.value.close();
    });
  
}

function validForm() {
    return (state.value.cardNum !== "" &&
        state.value.name !== "" &&
        state.value.expDate !== "" &&
        state.value.cvv !== "" &&
        state.value.type !== "" 
    );
}




</script>

<style scoped>
.row {
    padding-bottom: 30px;
}
</style>