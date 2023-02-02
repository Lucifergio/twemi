<template>
  <v-container>
    <v-sheet class="bg-green-lighten-1 pa-10 mx-auto" width="26%" style="margin-left: 650px">
    <v-card
        id="login-form"
        class="mx-auto"
        max-width="344"
        title="Регистрация"
    >
      <v-container>
        <v-text-field
            v-model="username"
            color="green"
            label="Имя пользователя"
            variant="underlined"
        ></v-text-field>

        <v-text-field
            v-model="firstname"
            color="green"
            label="Имя"
            variant="underlined"
        ></v-text-field>

        <v-text-field
            v-model="lastname"
            color="green"
            label="Фамилия"
            variant="underlined"
        ></v-text-field>

        <v-text-field
            v-model="email"
            color="green"
            label="Email"
            variant="underlined"
        ></v-text-field>

        <v-text-field
            v-model="password"
            color="green"
            label="Пароль"
            placeholder="Введите пароль"
            variant="underlined"
        ></v-text-field>

        <v-checkbox
            v-model="terms"
            color="green"
            label="⠀⠀⠀Я согласен с правилами сайта"
            class="material-icons"
        >⠀⠀⠀⠀⠀⠀check_circle
        </v-checkbox>
      </v-container>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn color="success"
               v-on:click="register">
          Регистрация
          <span class="material-icons">arrow_circle_right</span>
        </v-btn>
      </v-card-actions>
    </v-card>
    </v-sheet>
  </v-container>
</template>

<script>
import {AXIOS} from './http-common'

export default {
  name: 'SignUp',
  data: () => ({
    username: null,
    firstname: null,
    lastname: null,
    email: null,
    password: null,
    terms: false,
  }),
  methods: {
    register: function () {
      if (this.$data.username === '' || this.$data.username == null) {
        // this.$data.alertMessage = 'Please, fill "Username" field';
        // this.showAlert();
      // } else if (this.$data.firstname === '' || this.$data.firstname == null) {
      //   // this.$data.alertMessage = 'Please, fill "First name" field';
      //   this.showAlert();
      // } else if (this.$data.lastname === '' || this.$data.lastname == null) {
      //   // this.$data.alertMessage = 'Please, fill "Last name" field';
      //   this.showAlert();
      // } else if (this.$data.email === '' || this.$data.email == null) {
      //   // this.$data.alertMessage = 'Please, fill "Email" field';
      //   this.showAlert();
      // } else if (!this.$data.email.includes('@')) {
      //   // this.$data.alertMessage = 'Email is incorrect';
      //   this.showAlert();
      // } else if (this.$data.password === '' || this.$data.password == null) {
      //   // this.$data.alertMessage = 'Please, fill "Password" field';
      //   this.showAlert();
      // } else if (this.$data.confirmpassword === '' || this.$data.confirmpassword == null) {
      //   // this.$data.alertMessage = 'Please, confirm password';
      //   this.showAlert();
      // } else if (this.$data.confirmpassword !== this.$data.password) {
      //   // this.$data.alertMessage = 'Passwords are not match';
      //   this.showAlert();
      } else {
        const newUser = {
          'username': this.$data.username,
          'firstName': this.$data.firstname,
          'lastName': this.$data.lastname,
          'email': this.$data.email,
          'password': this.$data.password
        };
        AXIOS.post('/users', newUser)
            .then(response => {
              console.log(response);
              this.successAlert();
            // }, error => {
              // this.$data.alertMessage = (error.response.data.message.length < 150) ? error.response.data.message : 'Request error. Please, report this error website owners'
              // this.showAlert();
            })
            .catch(error => {
              console.log(error);
              // this.$data.alertMessage = 'Request error. Please, report this error website owners';
              // this.showAlert();
            });
      }
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown
    },
    showAlert() {
      this.dismissCountDown = this.dismissSecs
    },
    successAlert() {
      this.username = '';
      this.firstname = '';
      this.lastname = '';
      this.email = '';
      this.password = '';
      this.confirmpassword = '';
      this.successfullyRegistered = true;
    }
  }
}
</script>

<style>
#login-form {
  margin-left: 38%;
  /*margin-top: 50px;*/
}
</style>