<template>
  <q-page>
    <q-container>
      <h1 class="text-h6">Lista de Usuários</h1>
      <q-btn color="primary" @click="addUser">Adicionar Usuário</q-btn>
      <q-table
        :rows="users"
        :columns="columns"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td auto-width>{{ props.row.nome }}</q-td>
            <q-td auto-width>{{ props.row.idade }}</q-td>
            <q-td auto-width>{{ props.row.email }}</q-td>
            <q-td auto-width>
              <q-btn color="primary" @click="editUser(props.row)">Editar</q-btn>
              <q-btn color="negative" @click="deleteUser(props.row.id)">Excluir</q-btn>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </q-container>
  </q-page>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: [],
      editedUser: {
        id: null,
        nome: '',
        idade: null,
        email: '',
      },
      isEditModalVisible: false,
    };
  },
  computed: {
    columns() {
      return [
        { name: 'nome', required: true, label: 'Nome', align: 'left', field: 'nome', sortable: true },
        { name: 'idade', required: true, label: 'Idade', align: 'left', field: 'idade', sortable: true },
        { name: 'email', required: true, label: 'Email', align: 'left', field: 'email', sortable: true },
        { name: 'actions', label: 'Ações', field: 'actions', sortable: false },
      ];
    },
  },
  created() {
    this.loadUsers();
  },
  methods: {
    loadUsers() {
      axios.get('http://localhost:8080/user')
        .then(response => {
          this.users = response.data;
        })
        .catch(error => {
          console.error('Erro ao buscar dados dos usuários: ', error);
        });
    },
    addUser() {
      this.isEditModalVisible = true;
      this.editedUser = {
        nome: '',
        idade: null,
        email: '',
      };
    },
    editUser(user) {
      this.isEditModalVisible = true;
      this.editedUser = { ...user };
    },
    saveUserChanges() {
      axios.put(`http://localhost:8080/user/${this.editedUser.id}`, this.editedUser)
        .then(() => {
          this.loadUsers();
          this.isEditModalVisible = false;
        })
        .catch(error => {
          console.error('Erro ao editar o usuário: ', error);
        });
    },
    cancelEdit() {
      this.isEditModalVisible = false;
    },
    deleteUser(userId) {
      axios.delete(`http://localhost:8080/user/${userId}`)
        .then(() => {
          this.loadUsers();
        })
        .catch(error => {
          console.error('Erro ao excluir o usuário: ', error);
        });
    },
  },
};
</script>
