function showForm(formType) {

    const API_URL = "http://vitamed.us-east-1.elasticbeanstalk.com/pacientes";

    const conteudo = document.getElementById('conteudo');
    let formHTML = '';

    if (formType === 'criar') {
        formHTML = `
            <form id="form-criar">
                <label>Nome Completo do Paciente:</label>
                <input type="text" id="nomePaciente" placeholder="Digite o nome" required>
                <label>CPF:</label>
                <input type="text" id="CPF" placeholder="Digite o CPF" required>
                <label>Altura:</label>
                <input type="float" id="altura" required>
                <label>Peso:</label>
                <input type="float" id="peso" required>
                <label>Idade:</label>
                <input type="number" id="idade" required>
                <button type="submit">Marcar</button>
            </form>
        `;
    } else if (formType === 'retornar') {
        ;
    } else if (formType === 'atualizar') {
        formHTML = `
            <form id="form-atualizar">
                <label>Id do Paciente :</label>
                <input type="number" id="pacienteId" placeholder="Digite o número ID do paciente" required>
                <label>Nome Completo do Paciente:</label>
                <input type="text" id="nomePaciente" placeholder="Digite o nome">
                <label>CPF:</label>
                <input type="text" id="CPF" placeholder="Digite o CPF">
                <label>Altura:</label>
                <input type="number" id="altura">
                <label>Peso:</label>
                <input type="number" id="peso">
                <label>Idade:</label>
                <input type="number" id="idade">
                <button type="submit">Marcar</button>
            </form>
        `;
    } else if (formType === 'deletar') {
        formHTML = `
            <form id="form-deletar">
                <label>Número ID do Paciente para DELETAR:</label>
                <input type="number" id="pacienteId" placeholder="Digite o número ID do paciente" required>
                <button type="submit">Deletar</button>
            </form>
        `;
    } else {
        formHTML = `<p>Formulário para ${formType} ainda não foi implementado.</p>`;
    }

    conteudo.innerHTML = formHTML;

    

    // Envio de formulário de marcar consulta
    if (formType === 'criar') {
        const form = document.getElementById('form-criar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const nomePaciente = document.getElementById('nomePaciente').value;
            const cpf = document.getElementById('CPF').value;
            const altura = document.getElementById('altura').value;
            const peso = document.getElementById('peso').value;
            const idade = document.getElementById('idade').value;

            axios.post(API_URL, {
                nome: nomePaciente,
                cpf: cpf,
                altura: altura,
                peso: peso,
                idade: idade
            })
            .then(response => {
                conteudo.innerHTML = "<p>Paciente criado com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao criar paciente. Tente novamente.</p>";
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
            const pacientes = response.data;  // Aqui estamos assumindo que a resposta é um array de objetos
            const conteudo = document.getElementById('conteudo');
            
            // Verifica se há dados
            if (pacientes.length > 0) {
                pacientes.forEach(paciente => {
                    const div = document.createElement('div');
                    div.classList.add('objeto');

                    // Estrutura do HTML de cada objeto
                    div.innerHTML = `
                        <h3>Dados de ${paciente.nome}</h3>
                        <p><strong>Id do paciente:</strong> ${paciente.id}</p>
                        <p><strong>CPF:</strong> ${paciente.cpf}</p>
                        <p><strong>Altura:</strong> ${paciente.altura}</p>
                        <p><strong>Peso:</strong> ${paciente.peso}</p>
                        <p><strong>Idade:</strong> ${paciente.idade}</p>
                    `;
                    
                    // Adiciona o novo item na tela
                    conteudo.appendChild(div);
                });
            } else {
                // Caso não haja consultas
                conteudo.innerHTML = "<p>Nenhum paciente registrado.</p>";
            }
        })
        .catch(error => {
            // Se houver um erro ao fazer a requisição
            console.error('Erro ao obter os dados:', error);
            document.getElementById('conteudo').innerHTML = "<p>Erro ao carregar os pacientes.</p>";
        });
    }

    // Envio de formulário de atualizar paciente
    if (formType === 'atualizar') {

        const consultaId = document.getElementById('pacienteId').value;

        const form = document.getElementById('form-atualizar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const nomePacienteNv = document.getElementById('nomePaciente').value;
            const cpfNv = document.getElementById('CPF').value;
            const alturaNv = document.getElementById('altura').value;
            const pesoNv = document.getElementById('peso').value;
            const idadeNv = document.getElementById('idade').value;


            const pacienteId = document.getElementById('pacienteId').value;
            axios.patch(`${API_URL}/${pacienteId}`, {
                nome: nomePacienteNv,
                cpf: cpfNv,
                altura: alturaNv,
                peso: pesoNv,
                idade: idadeNv
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
            const pacienteDel = document.getElementById('pacienteId').value;

            axios.delete(`${API_URL}/${pacienteDel}`)
                .then(response => {
                    conteudo.innerHTML = "<p>Paciente deletado com sucesso!</p>";
                })
                .catch(error => {
                    conteudo.innerHTML = "<p>Erro ao deletar paciente. Tente novamente.</p>";
                });
        });
    }
}