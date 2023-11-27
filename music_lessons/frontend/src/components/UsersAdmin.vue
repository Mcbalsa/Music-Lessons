<template>
    <div :key="componentKey">
        <div v-if="isLoaded == true">
            <DataTable v-model:filters="filters" :value="teachers" @rowSelect="onRowSelect" :globalFilterFields="['firstName', 'lastName']"
                selectionMode="single" dataKey="id" :metaKeySelection="false" tableStyle="min-width: 50rem">
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
                <Column field="age" header="Age" style="width: 20%"></Column>
                <Column field="displayGenres" header="Genres" style="width: 20%"></Column>
                <Column field="displayInstruments" header="Instruments" style="width: 20%"></Column>
                <Column field="role" header="Role" style="width: 20%"></Column>
                <Column field="status" header="Status" style="width: 20%"></Column>
            </DataTable>
            <button type="button" class="btn btn-primary" @click="addUser()">Add User</button>
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
import AdminEdit from '../components/AdminEditUser.vue';
import NewUserForm from '../components/NewUser.vue';

const filters = ref({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
});

let teachers = []
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
        url: "http://localhost:8080/api/v1/User/GetAll",
        type: "get"
    }).done(data => {
        formatData(data)
        teachers = data
        isLoaded.value = true
    })
});

function formatData(data) {
    for (let i = 0; i < data.length; i++) {
        data[i].age = getAge(data[i].dob)
        data[i].displayPhoneNum = formatPhoneNumber(data[i].phoneNum)
        data[i].displayGenres = data[i].genres.toString()
        data[i].displayInstruments = data[i].instruments.toString()
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
    dialog.open(AdminEdit, {
        props: {
            header: 'Edit User',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        data: { user: event.data },
        onClose() {
            forceRerender()
        }
    });
};

function addUser() {
    dialog.open(NewUserForm, {
        props: {
            header: 'New User',
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
            forceRerender()
        }
    });
}

</script>

<style scoped></style>