<template>
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <div name="header">
            Update Profile!
          </div>

        </header>
  
        <section class="modal-body">
          <div name="body">
            <div>
                <h3>First Name</h3>
                <input id="FirstName"/>
                <h3>Last Name</h3>
                <input id="LastName"/>
                <h3>Email</h3>
                <input id="Email"/>
                <h3>Password</h3>
                <input id="Password"/>
                <h3>Phone Number</h3>
                <input id="PhoneNumber"/>
                <h3>Instruments (seprate by comma)</h3>
                <textarea id="Instruments" style="width: 20%;height: 150px;" v-model="notes"></textarea>
                <h3>Genres (seprate by comma)</h3>
                <textarea id="Genres" style="width: 20%;height: 150px;" v-model="notes"></textarea>
                <h3>Bio</h3>
                <textarea id="Bio" style="width: 20%;height: 150px;" v-model="notes"></textarea>
            </div>
          </div>
         </section>
  
        <footer class="modal-footer">
          <button
            type="button"
            class="btn-green"
            @click="submit"
          >
            Submit
          </button>
          <button
            type="button"
            class="btn-green"
            @click="close"
          >
            Close
          </button>
        </footer>
      </div>
    </div>
  </template>

<script>
import $ from 'jquery';

export default {
  name: 'ProfileUpdate',
  methods: {
    close() {
      this.$emit('close');
    },    
    submit() {
        var name = $("#FirstName").val() + '';
        var lastname = $("#LastName").val() + '';
        var instruments = $("#Instruments").val() + '';
        var genres = $("#Genres").val() + '';
        var bio = $("#Bio").val() + '';
        var Password = $("#Password").val() + '';
        var PhoneNumber = $("#PhoneNumber").val() + '';
        var Email = $("#Email").val() + '';

        var profile = new Object(); 
        profile.firstName = name;
        profile.lastname = lastname;
        profile.genres = genres.split(",");
        profile.instruments = instruments.split(",");
        profile.bio = bio;
        profile.password = Password;
        profile.phoneNum = PhoneNumber;
        profile.email = Email;
        this.$emit('submit',profile);
    },
  },
};
</script>


<style>
  .modal-backdrop {
    background-color: rgba(0, 0, 0, 0.3);
  }

  .modal {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    display: flex;
    flex-direction: column;
  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
  }

  .modal-header {
    position: relative;
    border-bottom: 1px solid;
    color: #4AAE9B;
    text-align: center;
  }

  .modal-footer {
    border-top: 1px solid;
    flex-direction: column;
    justify-content: flex-end;
  }

  .modal-body {
    position: relative;
    padding: 20px 10px;
    text-align: center;
  }

  .btn-close {
    border: none;
    font-size: 20px;
    padding: 10px;
    font-weight: bold;
    background: transparent;
  }

  .btn-green {
    border: 1px solid;
    border-radius: 2px;
  }
</style>