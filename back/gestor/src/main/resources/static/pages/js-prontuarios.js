function showForm(formType) {
    const API_URL = "http://vitamed.us-east-1.elasticbeanstalk.com/prontuarios";

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
                <input type="text" id="sintomas" placeholder="Digite os sintomas">
                <label>Diagnostico:</label>
                <input type="text" id="diagnostico" placeholder="Digite o diagnostico medico">
                <label for="opcoes">Precisa de Exame? Escolha uma opção:</label>
                <select id="opcoes">
                    <option value="true">SIM</option>
                    <option value="false">NAO</option>
                </select>
                <label>Id da Consulta:</label>
                <input type="number" id="consultaId">
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
            const prontuarios = response.data; 
            const conteudo = document.getElementById('conteudo');
            
            // Verifica se há dados
            if (prontuarios.length > 0) {
                prontuarios.forEach(prontuario => {
                    const div = document.createElement('div');
                    div.classList.add('objeto');

                    const listaString = JSON.stringify(prontuario.sintomas)
                    const stringExame = prontuario.precisaExame.toString();
                    // Estrutura do HTML de cada objeto
                    div.innerHTML = `
                        <h3>Consulta de id:  ${prontuario.id}</h3>
                        <p><strong>Sintomas:</strong> ${listaString}</p>
                        <p><strong>Diagnostico:</strong> ${prontuario.diagnostico}</p>
                        <p><strong>Precisa de exame:</strong> ${stringExame}</p>
                        <p><strong>Id da consulta:</strong> ${prontuario.consultaId}</p>
                    `;
                    
                    // Adiciona o novo item na tela
                    conteudo.appendChild(div);
                });
            } else {
                // Caso não haja consultas
                conteudo.innerHTML = "<p>Nenhum prontuario gerado.</p>";
            }
        })
        .catch(error => {
            // Se houver um erro ao fazer a requisição
            console.error('Erro ao obter os dados:', error);
            document.getElementById('conteudo').innerHTML = "<p>Erro ao carregar os prontuarios.</p>";
        });
    }

    // Envio de formulário de atualizar paciente
    if (formType === 'atualizar') {

        const form = document.getElementById('form-atualizar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const listaSintomasNv = pegarLista();
            const diagnosticoNv = document.getElementById('diagnostico').value;
            const precisaExameNv = capturarEscolha()
            const consultaIdNv = document.getElementById('consultaId').value;

            const prontuarioId = document.getElementById('prontuarioId').value;
            axios.patch(`${API_URL}/${prontuarioId}`, {
                sintomas: listaSintomasNv,
                diagnostico: diagnosticoNv,
                precisaExame: precisaExameNv,
                consultaId: consultaIdNv  
            })
            .then(response => {
                conteudo.innerHTML = "<p>Prontuario atualizado com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao atualizar prontuario. Tente novamente.</p>";
            });
        });
    }

    // Envio de formulário de deletar paciente
    if (formType === 'deletar') {
        const form = document.getElementById('form-deletar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const prontuarioId = document.getElementById('prontuarioId').value;

            axios.delete(`${API_URL}/${prontuarioId}`)
                .then(response => {
                    conteudo.innerHTML = "<p>Prontuario deletado com sucesso!</p>";
                })
                .catch(error => {
                    conteudo.innerHTML = "<p>Erro ao deletar Prontuario. Tente novamente.</p>";
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