function showForm(formType) {
    const API_URL = "https://zany-trout-jjrx5qvgwxqr2j6jg-8080.app.github.dev/prontuarios";

    const conteudo = document.getElementById('conteudo');
    let formHTML = '';

    if (formType === 'gerar') {
        formHTML = `
            <form id="form-gerar">
                <label>Digite os sintomas:</label>
                <input type="text" id="sintomas" placeholder="Digite os sintomas" required>
                <label>Diagnostico:</label>
                <input type="text" id="diagnostico" placeholder="Digite o diagnostico medico" required>
                <label for="opcoes">Precisa de Exame?:</label>
                <select id="opcoes">
                    <option value="true">SIM</option>
                    <option value="false">NAO</option>
                </select>
                <label>Id da Consulta:</label>
                <input type="number" id="consultaId" required>
                <button type="submit">Gerar</button>
            </form>
        `;
    } else if (formType === 'retornar') {
        ;
    } else if (formType === 'atualizar') {
        formHTML = `
            <form id="form-atualizar">
                <label>Id do Prontuario :</label>
                <input type="number" id="prontuarioId" placeholder="Digite o número ID do Prontuario" required>
                <label>Digite os sintomas:</label>
                <input type="text" id="sintomas" placeholder="Digite os sintomas" required>
                <label>Diagnostico:</label>
                <input type="text" id="diagnostico" placeholder="Digite o diagnostico medico" required>
                <label for="opcoes">Escolha uma opção:</label>
                <select id="opcoes">
                    <option value="true">SIM</option>
                    <option value="false">NAO</option>
                </select>
                <p id="resultado"></p>
                <label>Id da Consulta:</label>
                <input type="number" id="consultaId" required>
                <button type="submit">Atualizar</button>
            </form>
        `;
    } else if (formType === 'deletar') {
        formHTML = `
            <form id="form-deletar">
                <label>Número ID do Prontuario para DELETAR:</label>
                <input type="number" id="prontuarioId" placeholder="Digite o número ID do Prontuario" required>
                <button type="submit">Deletar</button>
            </form>
        `;
    } else {
        formHTML = `<p>Formulário para ${formType} ainda não foi implementado.</p>`;
    }

    conteudo.innerHTML = formHTML;


    // Envio de formulário de marcar consulta
    if (formType === 'gerar') {
        const form = document.getElementById('form-gerar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const listaSintomas = pegarLista();
            const diagnostico = document.getElementById('diagnostico').value;
            const precisaExame = capturarEscolha()
            const consultaId = document.getElementById('consultaId').value;

            axios.post(API_URL, {
                sintomas: listaSintomas,
                diagnostico: diagnostico,
                precisaExame: precisaExame,
                consultaId: consultaId 
            })
            .then(response => {
                conteudo.innerHTML = "<p>Exame marcado com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao marcar exame. Tente novamente.</p>";
            });
        });
    }

    // Envio de formulário de retornar consulta
    if (formType === 'retornar') {
        // Faz a requisição GET para pegar todos os objetos (consultas, por exemplo)
        axios.get(API_URL)
        .then(response => {
            console.log(response.data);
            // Cria o HTML para exibir as consultas
            const exames = response.data; 
            const conteudo = document.getElementById('conteudo');
            
            // Verifica se há dados
            if (prontuarios.length > 0) {
                prontuarios.forEach(prontuario => {
                    const div = document.createElement('div');
                    div.classList.add('objeto');

                    // Estrutura do HTML de cada objeto
                    div.innerHTML = `
                        <h3>Consulta de ${prontuario.id}</h3>
                        <p><strong>Id da Consulta:</strong> ${prontuario.id}</p>
                        <p><strong>Data:</strong> ${prontuario.data}</p>
                        <p><strong>Horário:</strong> ${prontuario.horario}</p>
                        <p><strong>Nome do Medico:</strong> ${prontuario.tipoExame}</p>
                    `;
                    
                    // Adiciona o novo item na tela
                    conteudo.appendChild(div);
                });
            } else {
                // Caso não haja consultas
                conteudo.innerHTML = "<p>Nenhum exame marcado.</p>";
            }
        })
        .catch(error => {
            // Se houver um erro ao fazer a requisição
            console.error('Erro ao obter os dados:', error);
            document.getElementById('conteudo').innerHTML = "<p>Erro ao carregar os exames.</p>";
        });
    }

    // Envio de formulário de atualizar paciente
    if (formType === 'atualizar') {

        const form = document.getElementById('form-atualizar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const prontuarioId = document.getElementById('prontuarioId').value;
            const data = document.getElementById('data').value;
            const horario = document.getElementById('horario').value;
            const exame = document.getElementById('exame').value;

            const exameId = document.getElementById('exameId').value;
            axios.patch(`${API_URL}/${exameId}`, {
                prontuarioId: prontuarioId,
                data: data,
                horario: horario,
                tipoExame: exame 
            })
            .then(response => {
                conteudo.innerHTML = "<p>Exame atualizado com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao atualizar exame. Tente novamente.</p>";
            });
        });
    }

    // Envio de formulário de deletar paciente
    if (formType === 'deletar') {
        const form = document.getElementById('form-deletar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const exameId = document.getElementById('exameId').value;

            axios.delete(`${API_URL}/${exameId}`)
                .then(response => {
                    conteudo.innerHTML = "<p>Exame deletado com sucesso!</p>";
                })
                .catch(error => {
                    conteudo.innerHTML = "<p>Erro ao deletar exame. Tente novamente.</p>";
                });
        });
    }

    function pegarLista() {
        const entrada = document.getElementById("sintomas").value;
        const lista = entrada
            .split(/[,;]+/)
            .map(item => item.trim());

        return lista;
    }

    function capturarEscolha(){
        const select = document.getElementById("opcoes"); // Obtém o <select>
        const valorBooleano = select.value === "true"; // Converte a string para booleano
        return valorBooleano;
    }
}