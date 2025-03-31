function showForm(formType) {
    const API_URL = "http://vitamed.us-east-1.elasticbeanstalk.com/exames";

    const conteudo = document.getElementById('conteudo');
    let formHTML = '';

    if (formType === 'marcar') {
        formHTML = `
            <form id="form-marcar">
                <label>Numero Id do Prontuario:</label>
                <input type="number" id="prontuarioId" placeholder="Digite o Id" required>
                <label>Tipo do Exame:</label>
                <input type="text" id="exame" placeholder="Digite o tipo do exame" required>
                <label>Horário:</label>
                <input type="time" id="horario" required>
                <label>Data:</label>
                <input type="date" id="data" required>
                <button type="submit">Marcar</button>
            </form>
        `;
    } else if (formType === 'retornar') {
        ;
    } else if (formType === 'atualizar') {
        formHTML = `
            <form id="form-atualizar">
                <label>Id do Exame :</label>
                <input type="number" id="exameId" placeholder="Digite o número ID do exame" required>
                <label>Numero Id do Prontuario:</label>
                <input type="number" id="prontuarioId" placeholder="Digite o Id" required>
                <label>Tipo do Exame:</label>
                <input type="text" id="exame" placeholder="Digite o tipo do exame" required>
                <label>Horário:</label>
                <input type="time" id="horario" required>
                <label>Data:</label>
                <input type="date" id="data" required>
                <button type="submit">Atualizar</button>
            </form>
        `;
    } else if (formType === 'deletar') {
        formHTML = `
            <form id="form-deletar">
                <label>Número ID do Exame para DELETAR:</label>
                <input type="number" id="exameId" placeholder="Digite o número ID do Exame" required>
                <button type="submit">Deletar</button>
            </form>
        `;
    } else {
        formHTML = `<p>Formulário para ${formType} ainda não foi implementado.</p>`;
    }

    conteudo.innerHTML = formHTML;


    // Envio de formulário de marcar consulta
    if (formType === 'marcar') {
        const form = document.getElementById('form-marcar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const prontuarioId = document.getElementById('prontuarioId').value;
            const data = document.getElementById('data').value;
            const horario = document.getElementById('horario').value;
            const exame = document.getElementById('exame').value;

            axios.post(API_URL, {
                prontuarioId: prontuarioId,
                data: data,
                hora: horario,
                tipoExame: exame 
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
            if (exames.length > 0) {
                exames.forEach(exame => {
                    const div = document.createElement('div');
                    div.classList.add('objeto');

                    // Estrutura do HTML de cada objeto
                    div.innerHTML = `
                        <h3>Consulta de ${exame.nomePaciente}</h3>
                        <p><strong>Id da Consulta:</strong> ${exame.id}</p>
                        <p><strong>Data:</strong> ${exame.data}</p>
                        <p><strong>Horário:</strong> ${exame.hora}</p>
                        <p><strong>Exame:</strong> ${exame.tipoExame}</p>
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
                hora: horario,
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
}