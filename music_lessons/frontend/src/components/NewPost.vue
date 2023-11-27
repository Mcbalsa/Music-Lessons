<template>
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <div name="header">
            Make A New Post!
          </div>

        </header>
  
        <section class="modal-body">
          <div name="body">
            <div>
                <h3>Name</h3>
                <input id="Name"/>
                <h3>Id</h3>
                <input id="Id"/>
                <h3>Subject </h3>
                <input id="Subject" />
                <h3>Message</h3>
                <textarea id="Message" style="width: 20%;height: 150px;" v-model="notes"></textarea>
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
  name: 'Modal',
  methods: {
    close() {
      this.$emit('close');
    },    
    submit() {
        var name = $("#Name").val();
        var id = $("#Id").val();
        var subject = $("#Subject").val();
        var message = $("#Message").val();

        var post = new Object(); 
        post.authorName = name;
        post.authorId = id;
        post.header = subject;
        post.body = message;
        
        $.ajax
        ({
            headers: 
            {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            url: "http://localhost:8080/api/v1/Post/New",
            data: JSON.stringify(post),
            type: "Post"
        }).done(data => {
          //this.$emit('close');
        });

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