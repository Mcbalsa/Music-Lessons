import $ from "jquery";
import { reactive, ref } from "vue";

const userState = reactive({
  user: JSON.parse(sessionStorage.getItem("user")),
});

const usStates = [
  "AL",
  "AK",
  "AS",
  "AZ",
  "AR",
  "CA",
  "CO",
  "CT",
  "DE",
  "DC",
  "FM",
  "FL",
  "GA",
  "GU",
  "HI",
  "ID",
  "IL",
  "IN",
  "IA",
  "KS",
  "KY",
  "LA",
  "ME",
  "MH",
  "MD",
  "MA",
  "MI",
  "MN",
  "MS",
  "MO",
  "MT",
  "NE",
  "NV",
  "NH",
  "NJ",
  "NM",
  "NY",
  "NC",
  "ND",
  "MP",
  "OH",
  "OK",
  "OR",
  "PW",
  "PA",
  "PR",
  "RI",
  "SC",
  "SD",
  "TN",
  "TX",
  "UT",
  "VT",
  "VI",
  "VA",
  "WA",
  "WV",
  "WI",
  "WY",
];

const roles = ["OWNER", "ADMIN", "USER"];

const statuses = ["ACTIVE", "INACTIVE"];

const lessonStatus = ["PAID", "SCHEDULED", "CANCELLED"];

const genres = ["rock", "metal", "blues", "jazz", "pop", "classical", "funk"];

const rooms = ["1234", "1467", "4536", "8763", "9845"]

const instruments = ["Guitar", "Piano", "Vocal", "Drums", "Bass"];
const paymentTypes = ["MasterCard", "Visa Card", "American Express"];
const store = ref(0);
const phoneNum = ref(1);
const addressStr = ref(2);
const teachers = ref(3);
const applications = ref(4);
const students = ref(5);
const student = reactive();
const creditCard = ref(6);

const methods = {
  async loadStores() {
    await $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/Store/GetAll",
      type: "get",
    }).done((data) => {
      // Though this gets all stores, the db should only have 1 store in for now, just use the first element
      store.value = data[0];
      phoneNum.value = store.value.phoneNum;
      var addy = store.value.address;
      addressStr.value =
        addy.street + " " + addy.city + " " + addy.state + " " + addy.areaCode;
    });
  },
  async loadTeachers() {
    await $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/User/GetAll",
      type: "get",
    }).done((data) => {
      teachers.value = data;
    });
  },
  async loadStudents() {
    console.log("starting")
    await $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/Student/GetAll",
      type: "get",
    }).done((data) => {
      students.value = data;
    });
  },
  async loadApplications() {
    await $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url: "http://localhost:8080/api/v1/Application/GetAll",
      type: "get",
    }).done((data) => {
      applications.value = data;
    });
  },
  async login(email, password) {
    await $.ajax({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      url:
        "http://localhost:8080/api/v1/User/Login?email=" +
        email +
        "&pass=" +
        password,
      type: "post",
      success: (data) => {
        if (data != null) {
          userState.user = data;
          sessionStorage.setItem("user", JSON.stringify(userState.user));
        }
      },
      error: (jqXHR) => {
        if (jqXHR.status == 404) {
          userState.user = null;
          alert("Credentials incorrect");
        }
      },
    });
  },

  logout() {
    userState.user = null;
    sessionStorage.removeItem("user");
  },
};

export default {
  userState,
  store,
  methods,
  roles,
  usStates,
  phoneNum,
  addressStr,
  genres,
  statuses,
  instruments,
  student,
  teachers,
  lessonStatus,
  students, 
  paymentTypes,
  creditCard,
  rooms
};
