<template>
  <div style="margin: 15px;">
    <h1 class="text-h6">Lista de Usuários</h1>
    <q-btn style="margin-bottom: 15px;" color="secondary" @click="addUser">Adicionar Usuário</q-btn>
    <q-table :rows="users" :columns="columns">
      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td auto-width>{{ props.row.nome }}</q-td>
          <q-td auto-width>{{ props.row.idade }}</q-td>
          <q-td auto-width>{{ props.row.email }}</q-td>
          <q-td auto-width>
            <q-btn color="primary" @click="editUser(props.row)">Editar</q-btn>
            <q-btn style="margin-left: 15px;" color="negative" @click="deleteUser(props.row.id)">Excluir</q-btn>
          </q-td>
        </q-tr>
      </template>
    </q-table>

    <q-dialog v-model="isEditModalVisible">
      <q-card >
        <q-card-section>
          <q-card-title class="text-h6">{{ isEditingUser ? 'Editar Usuário' : 'Adicionar Usuário' }}</q-card-title>
        </q-card-section>
        <q-card-section style="width: 300px;">
          <q-input v-model="editedUser.nome" label="Nome"></q-input>
          <q-input v-model="editedUser.idade" label="Idade" type="number"></q-input>
          <q-input v-model="editedUser.email" label="Email"></q-input>
        </q-card-section>
        <q-card-actions align="right">
          <q-btn color="negative" @click="cancelEdit">Cancelar</q-btn>
          <q-btn color="secondary" @click="saveUserChanges">Salvar</q-btn>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
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
        { name: 'actions', label: 'Ações', align: 'left', field: 'actions', sortable: false },
      ];
    },
    isEditingUser() {
      return !!this.editedUser.id;
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
        id: null,
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
      if (this.isEditingUser) {
        this.updateUser();
      } else {
        this.createUser();
      }
    },
    createUser() {
      axios.post('http://localhost:8080/user', this.editedUser)
        .then(() => {
          this.loadUsers();
          this.isEditModalVisible = false;
        })
        .catch(error => {
          console.error('Erro ao adicionar novo usuário: ', error);
        });
    },
    updateUser() {
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
