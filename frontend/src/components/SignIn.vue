<template>
<v-container>

  <v-sheet class="bg-green-lighten-1 pa-12" width="26%" style="margin-top: 100px; margin-left: 650px">
    <v-card class="mx-auto px-6 py-8" max-width="344">
    <v-card-text>
      <h3 class="text-h6 white--text"
                     style="margin-top: 8px; margin-left: 65px">Авторизация</h3>
    </v-card-text>
<!--    &nbsp;-->
  <v-sheet width="300" class="mx-auto">
    <v-form fast-fail @submit.prevent>
      <v-text-field
          v-model="username"
          label="Имя пользователя"
          :rules="firstNameRules"
      ></v-text-field>

      <v-text-field
          v-model="password"
          label="Пароль"
          type="password"
          :rules="passwordRules"
      ></v-text-field>

      <a href="#" class="link-primary" style="margin-left: 88px">Забыли пароль ?</a>

      <v-btn type="submit"
             block class="mt-2"
             color="success"
             v-on:click="login">Войти</v-btn>
    </v-form>
  </v-sheet>
    &nbsp;
    </v-card>
  </v-sheet>



<!--        <div id="alert" class="alert alert-warning">-->
<!--          {{ alertMessage }}-->
<!--        </div>-->
<!--СДЕЛАТЬ ОШИБКУ !!!!!-->

</v-container>
</template>

<script>
import {AXIOS} from './http-common'

export default {
  name: 'SignIn',
  data() {
    return {
      username: '',
      password: '',
      dismissSecs: 5,
      dismissCountDown: 0,
      alertMessage: 'Неправильный логин или пароль',
    }
  },
  methods: {
    login() {
      AXIOS.post(`/auth/login`, {'username': this.$data.username, 'password': this.$data.password})
          .then(response => {

            this.$store.dispatch('login', {
              'token': response.data.token,
              'roles': response.data.authorities,
              'username': response.data.username
            });
            this.$router.push('/')
          }, error => {
            this.$data.alertMessage = (error.response.data.message.length < 150) ? error.response.data.message : 'Ошибка !!! Обратитесь к владельцу сайта';
            console.log(error)
          })
          .catch(e => {
            console.log(e)
            this.showAlert();
          })
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown
    },
    showAlert() {
      this.dismissCountDown = this.dismissSecs
    },
  }
}
</script>

<style>
#auth-form {
  margin-top: 150px;
}

#alert {
  /*width: 300px;*/
  /*margin-left: 42%;*/
}
</style>