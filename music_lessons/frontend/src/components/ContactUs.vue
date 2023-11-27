<template>
    <div style="width:50%;float:left">
      <form @submit.prevent="submitForm" v-if="!formSubmitted">
        <div>
            <div style="float:left;width:50%">
                <span>First Name</span>
                <br>
                <input 
                    v-model="firstName"
                    type="text"
                    placeholder="Enter your first name" 
                    />
            </div>
            <div >
                <span>Last Name</span>
                <br>
                <input
                    v-model="lastName"
                    type="text"
                    placeholder="Enter your last name" 
                    />
            </div>
        </div>

        <div>
            <div style="float:left;width:50%">
                <span>Date Of Birth</span>
                <br>
                <input 
                v-model="DOB"
                type="Date"
                placeholder="Date Of Birth" 
                :formatter="format"
                />
            </div>
            <div >
                <span>Phone Number</span>
                <br>
                <input 
                v-model="phone"
                type="tel"
                placeholder="Enter your Phone Number" 
                />
            </div>

        </div>
        <div>
            <span>Notes</span>
            <br>
            <textarea  style="width: 100%;height: 150px;" v-model="notes"></textarea>
            <br>
        </div>
        <input 
          class="submit" 
          type="submit" 
          value="Submit"
        >
      </form>
      <div v-if="formSubmitted">
        <h3>Form Submitted</h3>
        <p>Our team will contact you shortly!</p>
      </div>
    </div>
    <div style="width:50%; float: right;">
        <img style="width: 100%;height: 395px;" src="https://sage-prod.nyc3.cdn.digitaloceanspaces.com/media/images/guitar-teacher-and-student.width-1000.jpg" />
    </div>
    <div style="width:50%; float: right;">
        <h2>Contact Information</h2>
        <div class="Boarder"  style="">
            <p style="text-align: center;">Phone Number:{{store.phoneNum.value}}</p>
            <p style="text-align: center;">Address:{{store.addressStr.value}}</p>
        </div>
    </div>
    <div style="width:50%; float: left;">
        <h2 style="text-align: center;">Lessons Price</h2>
        <div class="Boarder Prices" style="float: left;">
            <h3>$30/hour</h3>
        </div>
        <div class="Boarder Prices" style="float: right; ">
            <h3>$20/half hour</h3>
        </div>
    </div>
  </template>
  <script setup>
  import $ from 'jquery';
import { inject, onMounted } from 'vue';


    const store = inject('store')
     onMounted( async () => {

  })
  
  </script>
  <script>

    
    export default {
      data() {
        return {
          firstName: "",
          lastName: "",
          phone: "",
          DOB: "",
          notes: "",
          formSubmitted: false
        };
      },
      methods: {
        submitForm: function () {
            if (!RegExp("(([0-9]{3})\)?[-.]?([0-9]{3})[-.]?([0-9]{4})").test(this.phone)) {
                alert("Please add a valid phone number!");
                return;
            }
            if(this.firstName == "")
            {
                alert("Please input your first name!");
                return;
            }
            if(this.lastName == "")
            {
                alert("Please input your last name!");
                return;
            }
            if(this.DOB == "")
            {
                alert("Please input your date of birth!");
                return;
            }
            var app = new Object(); 
            app.firstName = this.firstName;
            app.lastName = this.lastName;
            app.phoneNum = this.phone;
            app.dob = this.DOB;
            app.notes = this.notes;

            $.ajax({
            headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          
          url: "http://localhost:8080/api/v1/Application/New",
          type: "post",
          data: JSON.stringify(app),
        }).done(data => {
            this.formSubmitted = true
        })

        }
      },
    };
  </script>
  <style scoped>
    form {
      padding: 10px;
      border: 2px solid black;
      border-radius: 5px;
    }
  
    input {
      padding: 4px 8px;
      margin: 4px;
    }
  
    span {
      font-size: 18px;
      margin: 4px;
      font-weight: 500;
    }
  
    .submit {
      font-size: 15px;
      color: #fff;
      background: #222;
      padding: 6px 12px;
      border: none;
      margin-top: 8px;
      cursor: pointer;
      border-radius: 5px;
    }


    .Boarder
    {
        border-width:2px;
        border-style:solid;
        border-color:black;
    }
    .Prices
    {
        margin: 1%;
        padding: 1%; 
        width:48%;
    }
  
  </style>