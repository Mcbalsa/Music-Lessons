<template>
    <div>
        <form @submit.prevent="submitForm">
            <div class="form-group row">
                <div class="col">
                    <Dropdown v-model="state.teacher" :options="teachers" placeholder="Select Teacher"
                        class="w-full md:w-14rem" />
                </div>
                <div class="col">
                    <Calendar dateFormat="yy-mm-dd"  inputId="date" v-model="state.date" />
                </div>
                <div class="col">
                    <Dropdown v-model="state.time" :options="times" placeholder="Select Time"
                        class="w-full md:w-14rem" />
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <Dropdown v-model="state.room" :options="rooms" placeholder="Select Room"
                        class="w-full md:w-14rem" />
                </div>
                <div class="col">
                    <Dropdown v-model="state.price" :options="prices" placeholder="Select Price"
                        class="w-full md:w-14rem" />
                </div>
            </div>
            <button v-if="validForm()" type="submit" class="btn btn-primary">Add Lesson</button>
            <button v-else type="submit" class="btn btn-primary" disabled>Add Lesson</button>
        </form>
    </div>
</template>

<script setup>
import $ from 'jquery';
import Calendar from 'primevue/calendar';
import Dropdown from 'primevue/dropdown';
import { inject, onMounted, reactive, ref } from 'vue';

const dialogRef = inject('dialogRef');
const store = inject('store');
const student = reactive(dialogRef.value.data.student);
const teachers = ref([]);
const state = ref({
    teacher : "",
    date: "",
    time: "",
    price: "",
    room: "",
    studentId : "",
    teachId : "",
    studentName : "",
})

const times = ["13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00"];
const prices = [20, 30]
let rooms = store.rooms


onMounted( () => {
    store.methods.loadTeachers();
    for(var i = 0; i < store.teachers.value.length; i++)
    {
        teachers.value[i] = store.teachers.value[i].firstName + " " + store.teachers.value[i].lastName;
    }
    state.value.studentId = student.id;
    state.value.studentName = student.firstName + " " + student.lastName;
    console.log(teachers);
});

function submitForm() {
    var teachid = "0";
    for(var i = 0; i < store.teachers.value.length; i++)
    {
        if(store.teachers.value[i].firstName + " " + store.teachers.value[i].lastName == state.value.teacher)
            teachid = store.teachers.value[i].id;
    }

    var lessonObj = {
        "date" : $('#date').val() + "T" + state.value.time, 
        "status" : "SCHEDULED",
        "price" : state.value.price,
        "notes" : "",
        "room" : state.value.room,
        "teacherId" : teachid,
        "studentId" : state.value.studentId,
        "studentName" : state.value.studentName,
        "teacherName" : state.value.teacher,
     };
     console.log(lessonObj);
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Lesson/New",
        type: "post",
        data: JSON.stringify(lessonObj),
        success: function (data, text) {
            dialogRef.value.close();
        },
        error: function (request, status, error) {
            alert("Double Booked!");
        }
    });
  
}


function validForm() {
    return (state.value.teacher !== "" &&
        state.value.date !== "" &&
        state.value.time !== "" &&
        state.value.price !== "" &&
        state.value.room !== "" 
    );
}




</script>

<style scoped>
.row {
    padding-bottom: 30px;
}
</style>