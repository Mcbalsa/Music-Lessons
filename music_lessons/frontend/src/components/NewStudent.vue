<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="form-group row">
                <div class="col">
                    <input type="text" v-model="state.firstName" class="form-control" placeholder="First name"
                        aria-label="First name">
                </div>
                <div class="col">
                    <input type="text" v-model="state.lastName" class="form-control" placeholder="Last name"
                        aria-label="Last name">
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <input type="text" v-model="state.guardName" class="form-control" placeholder="Guardian name"
                        aria-label="Guardian name">
                </div>
                <div class="p-float-label">
                    <Calendar inputId="dob" v-model="state.dob" />
                    <label for="dob" class="form-label">Date of Birth</label>
                </div>
                <div class="col">
                    <label for="phone"></label>
                    <InputMask v-model.lazy="state.phoneNum" date="phone" mask="(999) 999-9999"
                        placeholder="Phone Number" />
                </div>
            </div>
            <div class="col">
                <input type="text" v-model="state.address.street" class="form-control" placeholder="Street Address"
                    aria-label="Street Address">
            </div>
            <div class="form-group row">
                <div class="col">
                    <input type="text" v-model="state.address.city" class="form-control" placeholder="city"
                        aria-label="City">
                </div>
                <div class="col">
                    <Dropdown v-model="state.address.state" :options="store.usStates" placeholder="State"
                        class="w-full md:w-14rem" />


                </div>
                <div class="col">
                    <input type="text" v-model="state.address.areaCode" class="form-control" placeholder="zip" aria-label="Zip">
                </div>
            </div>

            <div class="for-group row">
                <div class="col">
                    <Dropdown v-model="state.instrument" :options="store.instruments" placeholder="Select an instrument"
                        class="w-full md:w-14rem" />
                </div>
                
            </div>
            <button v-if="validForm()" type="submit" class="btn btn-primary">Add Student</button>
            <button v-else type="submit" class="btn btn-primary" disabled>Add Student</button>
        </form>
    </div>
</template>

<script setup>
import $ from 'jquery';
import Calendar from 'primevue/calendar';
import Dropdown from 'primevue/dropdown';
import InputMask from 'primevue/inputmask';
import { inject, ref } from 'vue';

const state = ref({
    firstName: "",
    lastName: "",
    guardName: "",
    phoneNum: "",
    address: {
        street: "",
        city: "",
        state: "",
        areaCode: ""
    },
    dob: "",
    teacher: "",
    instrument: ""
})




function validForm() {
    return (state.value.firstName !== "" &&
        state.value.lastName !== "" &&
        state.value.phoneNum !== "" &&
        state.value.address.street !== "" &&
        state.value.address.city !== "" &&
        state.value.address.state !== "" &&
        state.value.address.areaCode !== "" &&
        state.value.dob !== "" &&
        state.value.instrument !== ""
    );
}
const dialogRef = inject('dialogRef');
const store = inject('store');

function submitForm() {

    // format to YYYY-MM-DD
    state.value.dob = state.value.dob.toLocaleString('sv', { timeZoneName: 'short' }).slice(0, 10)
    // TODO: Format phone numbers from inputMask
    const temp = state.value.phoneNum.replace(/\D/g, "");
    state.value.phoneNum = temp


    let studentInstruments = []
    studentInstruments.push(state.value.instrument)

    const student = {
        firstName: state.value.firstName,
        lastName: state.value.lastName,
        guardName: state.value.guardName,
        dob: state.value.dob,
        phoneNum: state.value.phoneNum,
        address: {
            "street": state.value.address.street,
            "city": state.value.address.city,
            "state": state.value.address.state,
            "areaCode": state.value.address.areaCode
        },
        // city: state.value.address.city,
        // state: state.value.address.state,
        // areaCode: state.value.address.areaCode,
        // teacher: state.value.teacher,
        instruments: studentInstruments,

    }


    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },

        url: "http://localhost:8080/api/v1/Student/Student",

        type: "post",
        data: JSON.stringify(student),
        success: () => {
            console.log(student)
            dialogRef.value.close();
        }
    });


}


</script>

<style scoped>
.row {
    padding-bottom: 30px;
}
</style>