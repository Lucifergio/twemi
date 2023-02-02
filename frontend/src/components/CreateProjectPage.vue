<template>
  <v-container>
    <v-sheet width="700" class="mx-auto">
      <v-form fast-fail @submit.prevent>
        <v-text-field
            v-model="title"
            label="Название проекта"
        ></v-text-field>

        <v-textarea
            name="input-7-1"
            variant="filled"
            label="Описание проекта"
            auto-grow
            model-value="Пожалуйста, подробно опишите вашу идею !"
        ></v-textarea>

        <v-text-field :rules="rules"
                      label="Необходимая сумма в руб."
        ></v-text-field>

        <v-btn type="submit" block class="mt-2">Создать новый проект</v-btn>
      </v-form>
    </v-sheet>
  </v-container>

</template>

<script>
export default {
  name: "CreateProjectPage",
  data: () => ({
    rules: [
      value => !!value || 'Необходимо заполнить',
      value => (value || '').length <= 20 || 'Максимум 20 символов',
      value => {
        const pattern = /^[1-9][0-9]*$/
        return pattern.test(value) || 'Можно вводить только цифры !'
      },
    ],
  }),
}
</script>

<style scoped>

</style>