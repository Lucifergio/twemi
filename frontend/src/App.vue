<template>
  <v-app>

    <v-app-bar
        app
        color="green-lighten-3"
    >
      <v-app-bar-title>
        <h3 class="text-h6 white--text"
            style="margin-top: 8px; color: green">TWEMI</h3>
      </v-app-bar-title>

      <div class="text-left" id="category"
           v-if="this.$store.getters.isAuthenticated">
        <v-menu>
          <template v-slot:activator="{ props: menu }">
            <v-tooltip location="top">
              <template v-slot:activator="{ props: tooltip }">
                <v-btn
                    color="green-darken-4"
                    v-bind="mergeProps(menu, tooltip)"
                >
                  <span class="material-icons">list</span>
                  Категории
                </v-btn>
              </template>
              <span>Выпадающее меню категорий !</span>
            </v-tooltip>
          </template>
          <v-list>
            <v-list-item
                v-for="(category) in categories"
                :key="category.id"
            >
              <h1>{{categories.title}}</h1>
              <v-list-item-title>{{ category.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>

      <v-spacer></v-spacer>

      <div id="search">
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="⠀Поиск"
            single-line
            v-if="this.$store.getters.isAuthenticated">
          >
        </v-text-field>
      </div>

      <div id="lk"
           v-if="this.$store.getters.isAuthenticated">
        <div class="text-center">
          <v-menu
              v-model="menu"
              :close-on-content-click="false"
              location="end"
          >
            <template v-slot:activator="{ props }">
              <div>
                <v-btn
                    color="indigo"
                    v-bind="props"
                >
                  <span class="material-icons">account_box</span>
                  Личный кабинет
                </v-btn>
              </div>
            </template>

            <v-card min-width="300">
              <v-list>
                <v-list-item
                    prepend-avatar="https://cdn.vuetifyjs.com/images/john.jpg"
                    title="John Leider"
                    subtitle="Founder of Vuetify"
                >
                  <template v-slot:append>
                    <v-btn
                        variant="text"
                        :class="fav ? 'text-red' : ''"
                        icon="mdi-heart"
                        @click="fav = !fav"
                    ></v-btn>
                  </template>
                </v-list-item>
              </v-list>

              <v-divider></v-divider>

              <v-list>
                <v-list-item>
                  <v-switch
                      v-model="message"
                      color="purple"
                      label="Enable messages"
                      hide-details
                  ></v-switch>
                </v-list-item>

                <v-list-item>
                  <v-switch
                      v-model="hints"
                      color="purple"
                      label="Enable hints"
                      hide-details
                  ></v-switch>
                </v-list-item>
              </v-list>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn
                    variant="text"
                    @click="menu = false"
                >
                  Отмена
                </v-btn>
                <v-btn
                    color="primary"
                    variant="text"
                    @click="menu = false"
                >
                  Сохранить
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-menu>
        </div>
      </div>

      <v-btn href="/create-project"
             class="mx-auto"
             width="200px"
             color="green-darken-4"
             v-if="this.$store.getters.isAuthenticated">
        <span class="material-icons">create</span> Создать проект
      </v-btn>

      <v-btn href="/login"
             class="mx-auto"
             width="100px"
             color="green-darken-4"
             v-if="!this.$store.getters.isAuthenticated">

        <span class="material-icons">login</span> Войти
      </v-btn>


      <v-btn href="/register"
             class="mx-auto"
             color="light-blue-darken-3"
             width="200px"
             v-if="!this.$store.getters.isAuthenticated">

        <span class="material-icons">assignment_ind</span> Регистрация
      </v-btn>

      <v-btn href="/"
             class="mx-auto"
             width="200px"
             color="red"
             v-if="this.$store.getters.isAuthenticated"
             v-on:click="logout">
        <span class="material-icons">exit_to_app</span> Выйти
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>

  </v-app>
</template>

<script>
import {mergeProps} from 'vue'
import {AXIOS} from "@/components/http-common";

export default {

  data: () => ({
    fav: true,
    menu: false,
    message: false,
    hints: true,
    categories: []
  }),

  name: 'App',
  methods: {
    mergeProps,
    logout() {
      this.$store.dispatch('logout')
      this.$router.push('/login')
    },
    list() {
      const header = {'Authorization': 'Bearer ' + this.$store.getters.getToken};
      AXIOS.get(`/category`, { headers: header })
          .then(response => {
            this.categories = response.data
          })
    }
  },
  beforeMount(){
    this.list()
  }
}
</script>

<style>
#category {
  padding-right: 30%;
}

#search {
  height: 55px;
  width: 400px;
}
</style>
