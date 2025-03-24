function showForm(formType) {
    const API_URL = "https://zany-trout-jjrx5qvgwxqr2j6jg-8080.app.github.dev/consultas";

    const conteudo = document.getElementById('conteudo');
    let formHTML = '';

    if (formType === 'marcar') {
        formHTML = `
            <form id="form-marcar">
                <label>Nome Completo do Paciente:</label>
                <input type="text" id="nomePaciente" placeholder="Digite o nome" required>
                <label>Data:</label>
                <input type="date" id="data" required>
                <label>Horário:</label>
                <input type="time" id="horario" required>
                <label>Nome Completo do Medico:</label>
                <input type="text" id="nomeMedico" placeholder="Digite o nome do Medico" required>
                <label>Tipo do Atendimento:</label>
                <input type="text" id="tipoAtendimento" placeholder="Presencial ou Online" required>
                <button type="submit">Marcar</button>
            </form>
        `;
    } else if (formType === 'retornar') {
        ;
    } else if (formType === 'atualizar') {
        formHTML = `
            <form id="form-atualizar">
                <label>Id da Consulta :</label>
                <input type="number" id="consultaId" placeholder="Digite o número ID do exame" required>
                <label>Nome Completo do Paciente:</label>
                <input type="text" id="nomePaciente" placeholder="Digite o nome">
                <label>Data:</label>
                <input type="date" id="data">
                <label>Horário:</label>
                <input type="time" id="horario">
                <label>Nome Completo do Medico:</label>
                <input type="text" id="nomeMedico" placeholder="Digite o nome do Medico">
                <label>Tipo do Atendimento:</label>
                <input type="text" id="tipoAtendimento" placeholder="Presencial ou Online">
                <button type="submit">Atualizar</button>
            </form>
        `;
    } else if (formType === 'deletar') {
        formHTML = `
            <form id="form-deletar">
                <label>Número ID da Consulta para DELETAR:</label>
                <input type="number" id="consultaId" placeholder="Digite o número ID da consulta" required>
                <button type="submit">Deletar</button>
            </form>
        `;
    } else {
        formHTML = `<p>Formulário para ${formType} ainda não foi implementado.</p>`;
    }

    conteudo.innerHTML = formHTML;

    let ePresencial = true;

    // Envio de formulário de marcar consulta
    if (formType === 'marcar') {
        const form = document.getElementById('form-marcar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const nomePaciente = document.getElementById('nomePaciente').value;
            const data = document.getElementById('data').value;
            const horario = document.getElementById('horario').value;
            const nomeMedico = document.getElementById('nomeMedico').value;
            const tipoAtendimento = document.getElementById('tipoAtendimento').value;
            if(tipoAtendimento == "presencial"){
                ePresencial = true;
            } else{
                ePresencial = false;
            }

            axios.post(API_URL, {
                nomePaciente: nomePaciente,
                data: data,
                horario: horario,
                nomeMedico: nomeMedico,
                ePresencial: ePresencial
            })
            .then(response => {
                conteudo.innerHTML = "<p>Consulta marcada com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao marcar consulta. Tente novamente.</p>";
            });
        });
    }

    // Envio de formulário de retornar consulta
    if (formType === 'retornar') {
        // Faz a requisição GET para pegar todos os objetos (consultas, por exemplo)
        axios.get(API_URL)  // Assumindo que a URL '/consultas' retorna todos os dados
        .then(response => {
            console.log(response.data);
            // Cria o HTML para exibir as consultas
            const consultas = response.data;  // Aqui estamos assumindo que a resposta é um array de objetos
            const conteudo = document.getElementById('conteudo');
            
            // Verifica se há dados
            if (consultas.length > 0) {
                consultas.forEach(consulta => {
                    const div = document.createElement('div');
                    div.classList.add('objeto');

                    // Estrutura do HTML de cada objeto
                    div.innerHTML = `
                        <h3>Consulta de ${consulta.nomePaciente}</h3>
                        <p><strong>Data:</strong> ${consulta.data}</p>
                        <p><strong>Horário:</strong> ${consulta.horario}</p>
                        <p><strong>Nome do Medico:</strong> ${consulta.nomeMedico}</p>
                        <p><strong>Tipo de Atendimento:</strong> ${consulta.ePresencial}</p>
                    `;
                    
                    // Adiciona o novo item na tela
                    conteudo.appendChild(div);
                });
            } else {
                // Caso não haja consultas
                conteudo.innerHTML = "<p>Nenhuma consulta marcada.</p>";
            }
        })
        .catch(error => {
            // Se houver um erro ao fazer a requisição
            console.error('Erro ao obter os dados:', error);
            document.getElementById('conteudo').innerHTML = "<p>Erro ao carregar as consultas.</p>";
        });
    }

    // Envio de formulário de atualizar paciente
    if (formType === 'atualizar') {

        const consultaId = document.getElementById('consultaId').value;

        const form = document.getElementById('form-atualizar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const nomePacienteNv = document.getElementById('nomePaciente').value;
            const dataNv = document.getElementById('data').value;
            const horarioNv = document.getElementById('horario').value;
            const nomeMedicoNv = document.getElementById('nomeMedico').value;
            const tipoAtendimentoNv = document.getElementById('tipoAtendimento').value;
            if(tipoAtendimentoNv == "presencial"){
                ePresencial = true;
            } else{
                ePresencial = false;
            }

            const consultaId = document.getElementById('consultaId').value;
            axios.patch(`${API_URL}/${consultaId}`, {
                nomePaciente: nomePacienteNv,
                data: dataNv,
                horario: horarioNv,
                nomeMedico: nomeMedicoNv,
                ePresencial: ePresencial
            })
            .then(response => {
                conteudo.innerHTML = "<p>Paciente atualizado com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao atualizar paciente. Tente novamente.</p>";
            });
        });
    }

    // Envio de formulário de deletar paciente
    if (formType === 'deletar') {
        const form = document.getElementById('form-deletar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const consultaDel = document.getElementById('consultaId').value;

            axios.delete(`${API_URL}/${consultaDel}`)
                .then(response => {
                    conteudo.innerHTML = "<p>Paciente deletado com sucesso!</p>";
                })
                .catch(error => {
                    conteudo.innerHTML = "<p>Erro ao deletar paciente. Tente novamente.</p>";
                });
        });
    }
}