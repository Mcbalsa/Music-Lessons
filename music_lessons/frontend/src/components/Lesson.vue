<template>
    <div>
        <DataTable v-model:filters="filters" :value="lessons" selectionMode="single" dataKey="id" :metaKeySelection="false" @rowSelect="onRowSelect"
            paginator :rows="5" :rowsPerPageOptions="[5, 10, 20, 50]" :globalFilterFields="['displayDate', 'studentName', 'teacherName']" tableStyle="min-width: 50rem">
            <template #header>
                <div class="flex justify-content-end">
                    <span class="p-input-icon-left">
                        <i class="pi pi-search" />
                        <InputText v-model="filters['global'].value" placeholder="Keyword Search" />
                    </span>
                </div>
            </template>
            <Column field="displayDate" header="Date"></Column>
            <Column field="displayTime" header="Time"></Column>
            <Column field="room" header="room"></Column>
            <Column field="teacherName" header="Teacher Name"></Column>
            <Column field="studentName" header="Student Name"></Column>
            <Column field="status" header="status"></Column>
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
const router = useRouter();
const store = inject('store');
const lessons = ref();
const isLoaded = ref(false)

const filters = ref({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
});

onMounted(async () => {
    await $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Lesson/GetAll",
        type: "get"
    }).done(data => {
        formatData(data)
        lessons.value = data
        isLoaded.value = true
    })
});


function formatData(data) {
    for (let i = 0; i < data.length; i++) {
        const dateTime = new Date(data[i].date)
        const date = dateTime.getMonth().toString() + '/' + dateTime.getDate().toString()
        const time = dateTime.getHours().toString() + ':' + dateTime.getMinutes().toString()
        console.log(time)
        data[i].displayDate = date
        data[i].displayTime = time
    }
}

const onRowSelect = (event) => {
    console.log(event.data);
    store.lesson = event.data
    router.push("/lessonDetails")
};
</script>