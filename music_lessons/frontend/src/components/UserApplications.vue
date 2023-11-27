<template>
    <div :key="componentKey">
        <div v-if="isLoaded == true">
            <DataTable v-model:filters="filters" :value="applications" @rowSelect="onRowSelect"
                selectionMode="single" dataKey="id" :metaKeySelection="false" tableStyle="min-width: 50rem" :globalFilterFields="['firstName', 'lastName']">
                <template #header>
                <div class="flex justify-content-end">
                    <span class="p-input-icon-left">
                        <i class="pi pi-search" />
                        <InputText v-model="filters['global'].value" placeholder="Keyword Search" />
                    </span>
                </div>
            </template>
                <Column field="firstName" header="First Name" style="width: 20%"></Column>
                <Column field="lastName" header="Last Name" style="width: 20%"></Column>
                <Column field="displayPhoneNum" header="Phone Number" style="width: 20%"></Column>
                <Column field="age" header="Date of Birth" style="width: 20%"></Column>
                <Column field="notes" header="Notes" style="width: 20%"></Column>
            </DataTable>
            <button type="button" class="btn btn-primary" @click="addApplication()">Add Student</button>
            <DynamicDialog />
        </div>
    </div>
</template>

<script setup>

import $ from 'jquery';
import { FilterMatchMode } from 'primevue/api';
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import InputText from 'primevue/inputtext';
import { useDialog } from 'primevue/usedialog';
import { onMounted, provide, ref } from 'vue';
import EditApp from '../components/AdminEditApplication.vue';
import NewStudent from '../components/NewStudent.vue';
import store from '../store';

const filters = ref({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
});

let applications = []

const isLoaded = ref(false)
const dialog = useDialog();

provide('dialog', dialog);

const componentKey = ref(0);

const forceRerender = () => {
    componentKey.value += 1;
}


onMounted(async () => {
    await $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Application/GetAll",
        type: "get"
    }).done(data => {
        formatData(data)
        applications = data
        isLoaded.value = true
    })
});

function formatData(data) {
    for (let i = 0; i < data.length; i++) {
        data[i].age = getAge(data[i].dob)
        data[i].displayPhoneNum = formatPhoneNumber(data[i].phoneNum)
    }

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

const onRowSelect = (event) => {
    dialog.open(EditApp, {
        props: {
            header: 'New Student',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        data: {application: event.data} ,
        onClose() {
            store.methods.loadStudents()
            forceRerender()
        }
    });
};

function addApplication() {
    dialog.open(NewStudent, {
        props: {
            header: 'New Student',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        onClose() {
            store.methods.loadStudents()
            forceRerender()
        }
    });
}

</script>

<style scoped></style>