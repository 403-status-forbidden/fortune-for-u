import { createApp } from 'vue';
import App from './App.vue';

//라우터
import router from './router';

//캘린더
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

const app = createApp(App)
app.use(router)
app.use(VCalendar, {})
app.mount('#app')