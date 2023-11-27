<template>
    <div :key="componentKey">
        <DataTable v-model:filters="filters" :value="students" @rowSelect="onRowSelect" selectionMode="single" dataKey="id"
            :metaKeySelection="false"  :globalFilterFields="['firstName', 'lastName', 'guardName']" tableStyle="min-width: 50rem">
            <template #header>
                <div class="flex justify-content-end">
                    <span class="p-input-icon-left">
                        <i class="pi pi-search" />
                        <InputText v-model="filters['global'].value" placeholder="Keyword Search" />
                    </span>
                </div>
            </template>
            <Column field="firstName" header="First Name" style="width: 20%"> </Column>
            <Column field="guardName" header="Guardian Name" style="width: 20%"></Column>
            <Column field="lastName" header="Last Name" style="width: 20%"></Column>
            <Column field="displayPhoneNum" header="Phone Number" style="width: 20%"></Column>
            <Column field="age" header="Age" style="width: 20%"></Column>
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
const isLoaded = ref(false);
const router = useRouter();
const componentKey = ref(0);
const store = inject('store');

const forceRerender = () => {
    componentKey.value += 1;
}



onMounted(async () => {
    await $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Student/GetAssigned?userId="
            + store.userState.user.id,
        type: "get"
    }).done(data => {
        formatData(data)
        students = data
        isLoaded.value = true
        forceRerender()
    })
});

async function formatData(data) {
    for (let i = 0; i < data.length; i++) {
        data[i].age = getAge(data[i].dob)
        data[i].displayPhoneNum = formatPhoneNumber(data[i].phoneNum)
        forceRerender()
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
    store.student = event.data
    router.push("/StudentDetails")
};

</script>

<style scoped></style>