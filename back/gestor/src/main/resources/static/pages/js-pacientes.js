
function listarPacientes() {
    axios.get(API_PACIENTES)
        .then(response => {
            const lista = document.getElementById("lista-pacientes");
            lista.innerHTML = "";  // Limpa a lista

            response.data.forEach(paciente => {
                let item = document.createElement("li");
                item.textContent = `${paciente.nome} - ${paciente.idade} anos`;
                lista.appendChild(item);
            });
        })
        .catch(error => console.error("Erro ao buscar pacientes:", error));
    }

    function adicionarPaciente() {
        const nome = document.getElementById("paciente-nome").value;
        const idade = document.getElementById("paciente-idade").value;
    
        axios.post(API_PACIENTES, { nome, idade })
            .then(() => {
                listarPacientes(); // Atualiza a lista
                document.getElementById("paciente-nome").value = "";
                document.getElementById("paciente-idade").value = "";
            })
            .catch(error => console.error("Erro ao adicionar paciente:", error));
    }
    