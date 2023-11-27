<template>
  <div>
    <h2>The Teachers</h2>
    <ul v-if="isLoaded == true">
      <li v-for="teacher in teachers" :key="teacher.id">
        <div class="teacher-card">
          <div class="teacher-photo">
            <img :src="teacher.photoUrl" alt="Teacher's Profile">
          </div>
          <div class="teacher-info">
            <div class="teacher-name-age">
              <h3>{{ teacher.firstName }} {{ teacher.lastName }}</h3>
              <h4>Age: {{ age(teacher.dob) }}</h4>
            </div>
            <div class="bio-box">
                <p>{{ teacher.bio }}</p>
            </div>
          </div>
        </div> 
      </li>
    </ul>
  </div>
</template>

<script setup>

import $ from 'jquery';
import { onMounted, ref } from 'vue';

let teachers = []
const isLoaded = ref(false)



onMounted(async () => {
  await $.ajax({
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    url: "http://localhost:8080/api/v1/User/GetAllActive",
    type: "get"
  }).done(data => {
    teachers = data
    isLoaded.value = true
  })
});

var age = (dob) => {
  const DOB = new Date(dob);
  const clientDate = new Date();
  const diff = Math.abs(DOB.getFullYear() - clientDate.getFullYear());
  return diff;
};



</script>

  
<style scoped>

img {
  height: 400px;
  width: 400px;
}
.teacher-card {
  display: flex;
  border: 2px solid #000000;
  margin-bottom: 10px;
}

.teacher-photo {
  padding: 10px;
}

.teacher-info {
  flex: 3;
  padding: 10px;
  display: flex;
  flex-direction: column;
}

.teacher-name-age {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
}

.teacher-name-age h3 {
  margin: 0;
}

.teacher-info .bio-box {
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 5px;
  margin-top: 10px; 
}
</style>
  