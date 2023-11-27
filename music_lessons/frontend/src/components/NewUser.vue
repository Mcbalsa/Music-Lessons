<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="row">
                <div class="col">
                    <input type="text" v-model="state.firstName" class="form-control" placeholder="First name"
                        aria-label="First name">
                </div>
                <div class="col">
                    <input type="text" v-model="state.lastName" class="form-control" placeholder="Last name"
                        aria-label="Last name">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="phone">Phone Number: </label>
                    <InputMask v-model.lazy="state.phoneNum" date="phone" mask="(999) 999-9999"
                        placeholder="(999) 999-9999" />
                </div>

            </div>
            <div class="row">
                <div class="col">
                    <input type="text" v-model="state.email" class="form-control" placeholder="Email" aria-label="Email">
                </div>
                <div class="col">
                    <div class="p-float-label">
                        <Calendar inputId="dob" v-model="state.dob" />
                        <label for="dob" class="form-label">Date of Birth</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <Dropdown v-model="state.genre1" :options="store.genres" placeholder="Select a genre"
                        class="w-full md:w-14rem" />
                </div>
                <div class="col">
                    <Dropdown v-model="state.genre2" :options="store.genres" placeholder="Select a genre"
                        class="w-full md:w-14rem" />
                </div>

            </div>
            <div class="row">
                <div class="col">
                    <Dropdown v-model="state.instrument" :options="store.instruments" placeholder="Select an instrument"
                        class="w-full md:w-14rem" />
                </div>
                <div class="col">
                    <Dropdown v-model="state.role" :options="store.roles" placeholder="Select a role"
                        class="w-full md:w-14rem" />
                </div>
            </div>
            <button v-if="validForm()" type="submit" class="btn btn-primary">Add User</button>
            <button v-else type="submit" class="btn btn-primary" disabled>Add User</button>
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
    phoneNum: "",
    email: "",
    dob: "",
    genre1: "",
    genre2: "",
    role: "",
    instrument: ""
})

function validForm() {
    return (state.value.firstName !== "" &&
        state.value.lastName !== "" &&
        state.value.phoneNum !== "" &&
        state.value.email !== "" &&
        state.value.dob !== "" &&
        state.value.genre1 !== "" &&
        state.value.role !== "" &&
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

    let userGenres = []
    userGenres.push(state.value.genre1)
    if (state.value.genre2 != "") {
        userGenres.push(state.value.genre2)
    }

    let userInstruments = []
    userInstruments.push(state.value.instrument)

    const user = {
        firstName: state.value.firstName,
        lastName: state.value.lastName,
        email: state.value.email,
        dob: state.value.dob,
        phoneNum: state.value.phoneNum,
        genres: userGenres,
        instruments: userInstruments,
        students: [],
        dateLeft: "",
        bio: "",
        photoUrl: "https://media.istockphoto.com/id/521573881/vector/unknown-person-silhouette-whith-tie.jpg?s=612x612&w=0&k=20&c=V9S3uOf99gGKjjHySFHHVr6aEcDiZyzLp5SNYhkjgDE=",
        status: "ACTIVE",
        password: "Password123!",
        role: state.value.role
    }

    console.log(user)


    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/User/New",
        type: "post",
        data: JSON.stringify(user),
        success: () => { dialogRef.value.close(); }
    });


}


</script>

<style scoped>
.row {
    padding-bottom: 30px;
}
</style>