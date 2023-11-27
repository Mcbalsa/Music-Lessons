import './assets/main.css';

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import 'primeicons/primeicons.css';
import PrimeVue from 'primevue/config';
import ConfirmationService from 'primevue/confirmationservice';
import ConfirmDialog from 'primevue/confirmdialog';
import DialogService from 'primevue/dialogservice';
import DynamicDialog from 'primevue/dynamicdialog';
import 'primevue/resources/primevue.min.css';
import 'primevue/resources/themes/lara-light-indigo/theme.css';
import ToastService from 'primevue/toastservice';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';



const app = createApp(App)
app.use(router);
app.use(PrimeVue);
app.use(DialogService);
app.use(ConfirmationService);
app.use(ToastService);
app.component('DynamicDialog', DynamicDialog);
app.component('ConfirmDialog', ConfirmDialog);
app.mount("#app");
