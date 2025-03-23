const API_URL = "https://zany-trout-jjrx5qvgwxqr2j6jg-8080.app.github.dev/pacientes";  // Endereço da sua API

function getPacientes() {
    return axios.get(API_URL);
}

// Função POST para adicionar um produto
function adicionarPaciente(paciente) {
    return axios.post(API_URL, paciente);
}

// Função PUT para atualizar um produto
function atualizarPaciente(id, paciente) {
    return axios.patch(`${API_URL}?${id}`, paciente);
}

// Função DELETE para remover um produto
function deletarPaciente(id) {
    return axios.delete(`${API_URL}?${id}`);
}

// Exportando as funções para serem usadas no script.js
export { getPacientes, adicionarPaciente, atualizarPaciente, deletarPaciente };