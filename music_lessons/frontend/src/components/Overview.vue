<template>
    <div id="info">
        <!-- Chat GPT -->
        <h2>Introducing Harmony Sounds: Your Gateway to Musical Excellence!</h2>

        <p>At Harmony Sounds, we believe that music is the universal language that brings joy, passion, and creativity into
            our lives. Whether you're an aspiring virtuoso or simply looking to explore the magic of melodies, our company
            is here to guide you on your musical journey. With a team of dedicated and experienced instructors,
            state-of-the-art facilities, and a diverse range of instruments and genres to choose from, we are your premier
            destination for music lessons.</p>

        <p>Our mission is simple: to inspire, educate, and empower individuals of all ages and skill levels to unlock their
            musical potential. Whether you dream of mastering the piano, strumming the guitar, or hitting the high notes on
            a vocal scale, our expert instructors are committed to nurturing your talent and fostering a lifelong love for
            music.</p>

        <p>At Harmony Sounds, we understand that every student is unique, and we tailor our lessons to suit your individual
            goals and learning style. From classical compositions to contemporary chart-toppers, we offer comprehensive
            lessons in a wide array of musical genres. Whether you're seeking private instruction, group classes, or online
            lessons, we have a flexible schedule to accommodate your needs.</p>

        <p>Join us at Harmony Sounds, where the rhythm of your heart meets the melody of your soul. Let's embark on this
            harmonious journey together, and make music a part of your life like never before. Your musical dreams await –
            let's create beautiful music together!</p>


    </div>
    <div id="gallery">
        <Galleria :value="images" :numVisible="5" containerStyle="max-width: 640px;" :showThumbnails="false"
            :showIndicators="true" :changeItemOnIndicatorHover="true">
            <template #item="slotProps">
                <img :src="slotProps.item.itemImageSrc" :alt="slotProps.item.alt"
                    style="positon:absolute; width: 700px; height:500px; display: block" />
            </template>
        </Galleria>
    </div>
</template>

<script setup>

import Galleria from 'primevue/galleria';
import { inject, onMounted } from 'vue';


const store = inject('store')

onMounted(() => {
})


const images = [
    {
        itemImageSrc: 'https://images.pexels.com/photos/7521301/pexels-photo-7521301.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
    },
    {
        itemImageSrc: 'https://media.istockphoto.com/id/1130450624/photo/hispanic-young-man-teaching-mature-caucasian-man-to-play-guitar.webp?s=2048x2048&w=is&k=20&c=10Q900TsGLncU1JQgsdyVJdI8vm4c65W-OTxGnei4Y0=',
    },
    {
        itemImageSrc: 'https://images.squarespace-cdn.com/content/v1/5689d19d57eb8dfaac70248a/1483041975527-UCV7H8ZJE6XDKY50KLZD/Liam.jpg?format=1500w'
    },
    {
        itemImageSrc: 'https://www.parents.com/thmb/ZRneQeGqThv4iV61Nl25zgQMlhQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-96162171-2000-061841fa01cf428bb25fdf405e251907.jpg'
    },
    {
        itemImageSrc: 'https://images.squarespace-cdn.com/content/v1/5c6ed7497788977b00036fed/1551805603611-YAYY4BTPJEF90OF34YM8/Saxophone-lessons-in-hilliard.jpg'
    }
]



</script>

<style scoped>
#info {
    position: absolute;
    height: max-content;
    width: 50%;
    left: 3%;
}

#gallery {
    position: absolute;
    height: 60%;
    width: 40%;
    right: 3%;
}
</style>