<template>
    <div :key="componentKey">
        <div id="info" class="card flex">
            <div class="m-0">
                <h2>Student Information</h2>
                <p>Name: {{ store.student.firstName }} {{ store.student.lastName }}</p>
                <p>Guardian Name: {{ store.student.guardName }}</p>
                <p>Age: {{ getAge(store.student.dob) }}</p>
                <p>Instruments: {{ store.student.instruments.join(', ') }}</p>
                <p>Teachers: {{ teachersDisplay }}</p>
                <p>Status: {{ store.student.status }}</p>
                <p><button type="button" class="btn btn-primary" @click="updateStudent()">Edit Student Info</button></p>
            </div>

            <Divider layout="vertical" />

            <div class="m-0">
                <h2>Contact Information</h2>
                <p>Phone Number: {{ store.student.phoneNum }}</p>
                <p>Address:</p>
                <p>Street: {{ store.student.address.street }}</p>
                <p>City: {{ store.student.address.city }}</p>
                <p>State: {{ store.student.address.state }}</p>
                <p>Zip {{ store.student.address.areaCode }}</p>
                <p><button type="button" class="btn btn-primary" @click="updateAddress()">Update Address</button></p>
            </div>

            <Divider layout="vertical" />

            <div class="m-0">
                <h2>Card Information</h2>
                <p v-if="store.student.creditCard == null">
                    <button type="button" class="btn btn-primary" @click="addCard()">Add Card</button>
                </p>
                <div v-else>
                    <p>Card Holder: {{ store.student.creditCard.name }}</p>
                    <p>Card Number: {{ store.student.creditCard.cardNum }}</p>
                </div>
            </div>

            <Divider layout="vertical" />

            <div class="col">
                <SplitButton label="Update Notes" icon="pi pi-plus" @click="save" :model="items" id="button" />
            </div>
        </div>

    </div>
    <div :key="componentKey">
        <DataTable :value="Lesson" sortField="status" :sortOrder="-1" @rowSelect="onRowSelect" selectionMode="single" dataKey="id">
            <Column field="date" header="Lesson Date" style="width: 20%"></Column>
            <Column field="status" header="status" style="width: 20%"></Column>
            <Column field="teacherName" header="Teacher Name" style="width: 20%"></Column>
        </DataTable>
    </div>


    <DynamicDialog />
</template>

<script setup>

import $ from 'jquery';
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import Divider from 'primevue/divider';
import DynamicDialog from 'primevue/dynamicdialog';
import SplitButton from 'primevue/splitbutton';
import { useDialog } from 'primevue/usedialog';
import { inject, onMounted, provide, ref } from 'vue';
import { useRouter } from 'vue-router';
import NewCard from '../components/AddCreditCard.vue';
import AddInstrument from '../components/AddInstrument.vue';
import AddLesson from '../components/AddLesson.vue';
import AssignTeacher from '../components/AssignTeacher.vue';
import RemoveInstrument from '../components/RemoveInstrument.vue';
import EditStatus from '../components/StudentStatus.vue';
import UnassignTeacher from '../components/UnassignTeacher.vue';
import UpdateAddress from '../components/UpdateAddress.vue';
import UpdateCard from '../components/UpdateCard.vue';
import UpdateStudent from '../components/UpdateStudent.vue';
const router = useRouter();

let students = []
let instruments = []
let teachers = []
let teachersDisplay = ''
let Lesson = ref([]);
const dialog = useDialog();

provide('dialog', dialog);

const componentKey = ref(0);

const forceRerender = () => {
    componentKey.value += 1;
}



const store = inject('store');

const items = [
    {
        label: 'Edit Info',
        icon: 'pi pi-refresh',
        command: () => {
            updateStudent()
        }
    },
    {
        label: 'Edit Address',
        icon: 'pi pi-refresh',
        command: () => {
            updateAddress()
        }
    },
    {
        label: 'Edit Card',
        icon: 'pi pi-refresh',
        command: () => {
            updateCard()
        }
    },

    {
        label: 'Schedule Lesson',
        icon: 'pi pi-times',
        command: () => {
            NewLesson();
        }
    },
    {
        label: 'Status',
        icon: 'pi pi-external-link',
        command: () => {
            editStatus()
        }
    },
    {
        label: 'Assign Teacher',
        icon: 'pi pi-external-link',
        command: () => {
            assignTeacher()
        }
    },
    {
        label: 'Unassign Teacher',
        icon: 'pi pi-external-link',
        command: () => {
            unassignTeacher()
        }
    },
    {
        label: 'Add Instrument',
        icon: 'pi pi-external-link',
        command: () => {
            addInstrument()
        }
    },
    {
        label: 'Remove Instrument',
        icon: 'pi pi-external-link',
        command: () => {
            removeInstrument()
        }
    },
    { label: 'Upload', icon: 'pi pi-upload', to: '/fileupload' }
];

onMounted(async () => {
    getTeachers()
    getLessons();
    forceRerender()
});
function NewLesson() {
    store.methods.loadTeachers();
    dialog.open(AddLesson, {
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
        data: { student: store.student },
        onClose() {

        }
    });
}
async function getTeachers() {
    await $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Student/GetTeachers?studentId=" + store.student.id,
        type: "get"
    }).done(data => {
        teachers = data
        formatTeachers()
        forceRerender()
    })
}

function formatTeachers() {

    for (let i = 0; i < teachers.length; i++) {
        teachersDisplay += teachers[i].firstName
        if (i != teachers.length - 1) {
            teachersDisplay += ', '
        }
    }
}


function getAge(dob) {
    const birthDate = new Date(dob)
    const clientDate = new Date();
    const diff = Math.abs(birthDate.getFullYear() - clientDate.getFullYear());
    return diff;
}

async function getLessons(studentId) {
    console.log(studentId);
    await $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: "http://localhost:8080/api/v1/Student/GetLessons?studentId=" + store.student.id,
        type: "get"
    }).done(data => {
        console.log("The data", data)
        Lesson.value = data
        console.log(Lesson.value);
        forceRerender()
    })
}

function updateStudent() {
    dialog.open(UpdateStudent, {
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

        onClose() {
            forceRerender()
        }
    });
}


function updateCard() {
    dialog.open(UpdateCard, {
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

        onClose() {
            forceRerender()
        }
    });
}


function addInstrument() {
    dialog.open(AddInstrument, {
        props: {
            header: 'Add Instrument',
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

function removeInstrument() {
    dialog.open(RemoveInstrument, {
        props: {
            header: 'Remove Instrument',
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

function updateAddress() {
    dialog.open(UpdateAddress, {
        props: {
            header: 'Edit Address',
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

function addCard() {

    dialog.open(NewCard, {
        props: {
            header: 'New Card',
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

function assignTeacher() {
    dialog.open(AssignTeacher, {
        props: {
            header: 'Assign Teacher',
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

function unassignTeacher() {
    dialog.open(UnassignTeacher, {
        props: {
            header: 'Unassign Teacher',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        data: { teachers: teachers },
        onClose() {
            forceRerender()
        }
    });
}
const onRowSelect = (event) => {
    console.log(event.data);
    store.lesson = event.data
    router.push("/lessonDetails")
};
function editStatus() {
    dialog.open(EditStatus, {
        props: {
            header: 'Edit Status',
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

<style scoped>
#info {
    list-style-type: none;
    display: grid;
    align-items: center;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
    column-gap: 5pxx;

}
</style>
