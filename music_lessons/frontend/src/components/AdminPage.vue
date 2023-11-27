<template>
    <TabView>
        <TabPanel header="Applications">
            <Applications />
        </TabPanel>
        <TabPanel header="Users">
            <Users />
        </TabPanel>
        <TabPanel header="Students">
            <Students />
        </TabPanel>
        <TabPanel header="Lessons">
            <Lessons />
        </TabPanel>
    </TabView>

</template>

<script setup>

import TabPanel from 'primevue/tabpanel';
import TabView from 'primevue/tabview';
import Students from '../components/StudentsAdmin.vue';
import Applications from '../components/UserApplications.vue';
import Users from '../components/UsersAdmin.vue';
import Lessons from '../components/Lesson.vue';

</script>