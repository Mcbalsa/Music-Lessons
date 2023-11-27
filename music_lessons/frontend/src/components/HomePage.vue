<template>
    <TabView>
        <TabPanel header="Overview">
            <Overview />
        </TabPanel>
        <TabPanel header="Teachers">
            <OurTeachers />
        </TabPanel>
        <TabPanel header="Contact Us">
            <ContactUs />
        </TabPanel>
    </TabView>

</template>

<script setup>

import TabPanel from 'primevue/tabpanel';
import TabView from 'primevue/tabview';

import { inject, onMounted } from 'vue';

import Overview from '../components/Overview.vue';
import ContactUs from '../components/ContactUs.Vue';
import OurTeachers from '../components/OurTeachers.vue';

const store = inject('store')


onMounted( async () => {
    await store.methods.loadStores();
  })


</script>

<style scoped>


</style>