<template>
  <v-container>

    <v-card
        v-for="project in projects" :key="project.id"
        class="mx-auto"
        v-on="list"
        max-width="1300"
    >
      <v-img
          src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
          height="200px"
          cover
      ></v-img>

      <v-card-title>
        {{ project.title }}
      </v-card-title>

      <v-card-subtitle>
       Сумма сбора средств: {{project.fundraisingAmount}} руб.
      </v-card-subtitle>

      <v-card-actions>
        <v-btn
            color="orange-lighten-2"
            variant="text"
        >
          Узнать больше
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn
            @click="show = !show"
        >
          <span v-if="!show" class="material-icons">keyboard_arrow_down</span>
          <span v-if="show" class="material-icons">keyboard_control_key</span>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>
            {{project.description}}
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-container>
</template>

<script>
import {AXIOS} from './http-common'

export default {
  data() {
    return {
      projects: [],
      show: false
    };
  },

  methods: {
    list() {
      const header = {'Authorization': 'Bearer ' + this.$store.getters.getToken};
      AXIOS.get(`/project`, { headers: header })
          .then(response => {
            this.projects = response.data
            console.log(response.data)

          })
    }
  },
  beforeMount(){
    this.list()
  }
}
</script>

<style>

</style>