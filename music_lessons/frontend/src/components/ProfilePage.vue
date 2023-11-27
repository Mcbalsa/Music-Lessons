<template>
    <div  v-if="renderComponent" class="container">
        <div class="col" >
            <div class="col profilePic" >
                <div>
                    <p>{{store.userState.user.firstName}}</p>
                </div>
                <img :src="store.userState.user.photoUrl" alt="Users's Profile" style="width: 100%;height: 100%;">
            </div>
            <div class="colInfo" >
                <div class="bio">
                    <h3>Instruments</h3>
                    <li v-for="instru in store.userState.user.instruments" :key="store.userState.user.id">
                            {{ instru}}
                    </li>
                </div>
                <div class="bio">
                    <h3>Genres</h3>
                    <li  v-for="instru in store.userState.user.genres" :key="store.userState.user.id">
                            {{ instru}}
                    </li>
                </div>
            </div>
   
            <div class="bio">
                <h3>Bio</h3>
                <p>{{store.userState.user.bio}}</p>
            </div>
            <button id="show-modal" @click="showModal2">Edit Profile</button>
        </div>
        <div class="col post"  >
            <h2>Post</h2>
            <div class="postCard" v-if="isLoaded == true">
                <h3  v-if="posts.length == 0">Make the first post!</h3>
                <div v-for="pst in posts" :key="pst.id">
                    <div class="post-card">
                        <div class="postHeader">
                            <h3>{{ pst.header }}</h3>
                        </div>
                        <div class="postHeader">
                            <h3>{{ pst.authorName }}</h3>
                        </div>
                        <div class="postHeader">
                            <h3>{{ pst.creationDate.substring(0,10)}}</h3>
                        </div>
                        <div class="bio">
                            <p>{{ pst.body }}</p>
                        </div>
                    </div> 
                </div>
            </div>
            
            <button id="show-modal" @click="showModal">New Post</button>
        </div>
    </div>

    <modal
      class="Modal"
      style="display: none;"
      @close="closeModal"
    />

    <update
      class="Modal2"
      style="display: none;"
      @close="closeModal"
      @submit="updateProfile"
    />
</template>

<script setup>
    import $ from 'jquery';
    import { onMounted, ref,inject,nextTick } from 'vue';
    import modal from '../components/NewPost.vue';
    import update from '../components/UpdateProfile.vue';
    const renderComponent = ref(true);
    const store = inject('store')
    const isLoaded = ref(false)
    let posts = []
    const forceRender = async () => {

        renderComponent.value = false;

        await nextTick();

        renderComponent.value = true;
    };
    function showModal()
    {
        $(".Modal").show();
    }

    function showModal2()
    {
        $(".Modal2").show();
    }

    function closeModal() 
    {
        $(".Modal").hide();
        location.reload();
    }
    
    function updateProfile(profile)
    {
        store.userState.user.firstName = profile.firstName;
        store.userState.user.lastname = profile.lastname;
        store.userState.user.genres = profile.genres;
        store.userState.user.instruments = profile.instruments;
        store.userState.user.bio = profile.bio;
        store.userState.user.password = profile.password;
        store.userState.user.phoneNum = profile.phoneNum;
        store.userState.user.email = profile.email;
        sessionStorage.setItem("user", JSON.stringify(store.userState.user));
        $.ajax
        ({
            headers: 
            {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            url: "http://localhost:8080/api/v1/User/Update",
            type: "Put",
            data: JSON.stringify(store.userState.user)
        }).done(data => {
            closeModal();
        });
        this.$forceUpdate(); 
    }

    onMounted(async () => 
    {
        await $.ajax
        ({
            headers: 
            {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            url: "http://localhost:8080/api/v1/Post/GetAll",
            type: "get"
        })
        .done(data => 
        {
            posts = data
            console.log(store.userState.user);
            isLoaded.value = true
        })
    });
</script>

<style scoped>

    button{
        margin: 10px;
        float: right;
    }
    .col{
        display: inline-block;
        width: 50%;
        padding: 10px;
        border: 2px solid black;
        border-radius: 5px;
    }

    .postHeader
    {
        display: inline-block;
        width: 33%;
        padding: 10px;
        border: 1px solid black;
        vertical-align: top;
        height: 100px;
        border-radius: 5px;
        word-wrap: break-word;
    }


    .profilePic
    {
 
        width: 50%;
        padding: 5px;
        vertical-align: top;
        height: 100%;
    }

    .post
    {
        vertical-align: top;
        height: 100%;
    }

    .bio{
        padding: 10px;
        border: 1px solid black;
        border-radius: 5px;
        flex: 1 1 auto;
    }

    h3,p,li {
        text-align: center;
    }

    .colInfo{
        display: inline-block;
        width: 50%;
        padding: 10px;
    }

    .postCard{
        padding: 10px;
        border: 2px solid black;
        border-radius: 5px;
    }

</style>