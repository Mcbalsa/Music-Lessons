<template>
    <div id="container">
        <div class="row">
            <div class="col">
                <p>Student Name: {{ store.lesson.studentName }}</p>
            </div>
            <div class="col">
                <p>Teacher Name: {{ store.lesson.teacherName }}</p>
            </div>
            <div class="col">
                <p>Room: {{ store.lesson.room }}</p>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>Lesson Date: {{ store.lesson.date.replace("T", " ").substring(0,store.lesson.date.length-7) }}</p>
            </div>
            <div class="col">
                <p>Lesson Status: {{ store.lesson.status }}</p>
                <p>Price: {{ store.lesson.price }}</p>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p style="text-align: center;">Notes</p>
                <div >
                    <textarea id="notes" style="width: 100%;height: 100%;">{{ store.lesson.notes }}</textarea>
                </div>
            </div>
            <div class="col">
                <SplitButton label="Update Notes" icon="pi pi-plus" @click="save" :model="items" id="button" />
            </div>
            <DynamicDialog />
        </div>
    </div>
</template>

<script setup>

import { inject,provide } from 'vue';
import SplitButton from 'primevue/splitbutton';
import $ from 'jquery';
import { useDialog } from 'primevue/usedialog';
import paymentForm from '../components/PaymentForm.vue';


const dialog = useDialog();
provide('dialog', dialog);
const store = inject('store');
const items = [

    {
        label: 'Payment',
        icon: 'pi ',
        command: () => {
            payment();
    }
    },
    {
        label: 'Cancel',
        icon: 'pi ',
        command: () => {
            if(confirm("Are you sure you want to cancel the lesson?"))
            {
                store.lesson.status = "Cancelled";
                $.ajax({
                    headers: {
                        Accept: "application/json",
                        "Content-Type": "application/json",
                    },
                    url: "http://localhost:8080/api/v1/Lesson/Update",
                    type: "put",
                    data: JSON.stringify(store.lesson),
                }).done(data => {
                    this.$forceUpdate();
                });
            }
        }
    }
];

function payment() {
    store.methods.loadStudents();


    var student;
    var firstLast = store.lesson.studentName.split(" ");

    for(var i = 0; i < store.students.value.length; i++)
    {
        if(firstLast[0] == store.students.value[i].firstName &&
            firstLast[1] == store.students.value[i].lastName)
        {
            student = store.students.value[i];
            console.log(student);
        }
    }

    dialog.open(paymentForm, {
        props: {
            header: 'Payment Form',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        data: {studentCC: student , lesson : store.lesson} ,
        onClose() {

            this.$forceUpdate();
        }
    });
}


const save = () => {
    if(confirm("Are you sure you want to update the notes?"))
    {
        var notes = $("#notes").val();
        store.lesson.notes = notes;
        $.ajax({
            headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          
          url: "http://localhost:8080/api/v1/Lesson/Update",
          type: "put",
          data: JSON.stringify(store.lesson),
        }).done(data => {
            
        });
    }
};
</script>

<style lang="css" scoped>
    #container{
        border-style: solid;
        width: 80%;
        margin-left: auto;
        margin-right: auto;
    }
    p{
        margin: 2%;
    }
    #button
    {
        margin: 0;
        top: 50%;
        -ms-transform: translateY(-50%);
        transform: translateY(-50%);
    }
</style>