<template>
    <div :key="componentKey">
        <DataTable v-model:filters="filters" :value="store.students.value" @rowSelect="onRowSelect" selectionMode="single" dataKey="id"
            :metaKeySelection="false" tableStyle="min-width: 50rem"
            :globalFilterFields="['firstName', 'lastName', 'guardName']">
            <template #header>
                <div class="flex justify-content-end">
                    <span class="p-input-icon-left">
                        <i class="pi pi-search" />
                        <InputText v-model="filters['global'].value" placeholder="Keyword Search" />
                    </span>
                </div>
            </template>
            <Column field="firstName" header="First Name" style="width: 20%"></Column>
            <Column field="guardName" header="Guardian Name" style="width: 20%"></Column>
            <Column field="lastName" header="Last Name" style="width: 20%"></Column>
            <Column field="displayPhoneNum" header="Phone Number" style="width: 20%"></Column>
            <Column field="age" header="Age" style="width: 20%"></Column>
            <Column field="displayTeachers" header="Teachers" style="width: 20%"></Column>
            <Column field="status" header="Status" style="width: 20%"></Column>
        </DataTable>
    </div>
</template>

<script setup>

import $ from 'jquery';
import { FilterMatchMode } from 'primevue/api';
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import InputText from 'primevue/inputtext';
import { inject, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const filters = ref({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
});

let students = []
let teachers = []
const isLoaded = ref(false);
const router = useRouter();
const componentKey = ref(0);
const store = inject('store');

const forceRerender = () => {
    componentKey.value += 1;
}

onMounted(async () => {
    await store.methods.loadStudents()
    formatData(store.students.value)
    isLoaded.value = true
    forceRerender()
});

async function formatData(data) {
    for (let i = 0; i < data.length; i++) {
        data[i].age = getAge(data[i].dob)
        data[i].displayPhoneNum = formatPhoneNumber(data[i].phoneNum)
        await getTeachers(data[i].id)
        data[i].displayTeachers = formatTeachers(teachers)
        forceRerender()
    }
}

function formatTeachers(teachersList) {
    let teacherString = ""
    for (let i = 0; i < teachersList.length; i++) {
        teacherString += teachersList[i].firstName
    }

    return teacherString
}

function getAge(dob) {
    const birthDate = new Date(dob)
    const clientDate = new Date();
    const diff = Math.abs(birthDate.getFullYear() - clientDate.getFullYear());
    return diff;
}

function formatPhoneNumber(phoneNumberString) {
    var cleaned = ('' + phoneNumberString).replace(/\D/g, '');
    var match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
        return '(' + match[1] + ') ' + match[2] + '-' + match[3];
    }
    return null;
}
async function getTeachers(studentId) {
    await $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Student/GetTeachers?studentId=" + studentId,
        type: "get"
    }).done(data => {
        teachers = data
        forceRerender()
    })
}

const onRowSelect = (event) => {
    store.student = event.data
    console.log(store.student)
    router.push("/StudentDetails")
};

</script>

<style scoped></style>