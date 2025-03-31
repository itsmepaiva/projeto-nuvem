function showForm(formType) {

    const API_URL = "http://vitamed.us-east-1.elasticbeanstalk.com/medicos";

    const conteudo = document.getElementById('conteudo');
    let formHTML = '';

    if (formType === 'criar') {
        formHTML = `
            <form id="form-criar">
                <label>Nome Completo do Medico:</label>
                <input type="text" id="nomeMedico" placeholder="Digite o nome" required>
                <label>CRM:</label>
                <input type="text" id="crm" placeholder="Digite o CRM" required>
                <label>Especialidade:</label>
                <input type="text" id="especialidade" required>
                <button type="submit">Marcar</button>
            </form>
        `;
    } else if (formType === 'retornar') {
        ;
    } else if (formType === 'atualizar') {
        formHTML = `
            <form id="form-atualizar">
                <label>Id do Medico :</label>
                <input type="number" id="medicoId" placeholder="Digite o número ID do medico" required>
                <label>Nome Completo do Medico:</label>
                <input type="text" id="nomeMedico" placeholder="Digite o nome">
                <label>CRM:</label>
                <input type="text" id="crm" placeholder="Digite o CRM">
                <label>Especialidade:</label>
                <input type="text" id="especialidade">
                <button type="submit">Marcar</button>
            </form>
        `;
    } else if (formType === 'deletar') {
        formHTML = `
            <form id="form-deletar">
                <label>Número ID do Medico para DELETAR:</label>
                <input type="number" id="medicoId" placeholder="Digite o número ID do medico" required>
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
            const nomeMedico = document.getElementById('nomeMedico').value;
            const crm = document.getElementById('crm').value;
            const especialidade = document.getElementById('especialidade').value;


            axios.post(API_URL, {
                nome: nomeMedico,
                crm: crm,
                especialidade: especialidade
            })
            .then(response => {
                conteudo.innerHTML = "<p>Medico criado com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao criar medico. Tente novamente.</p>";
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
            const medicos = response.data;  // Aqui estamos assumindo que a resposta é um array de objetos
            const conteudo = document.getElementById('conteudo');
            
            // Verifica se há dados
            if (medicos.length > 0) {
                medicos.forEach(medico => {
                    const div = document.createElement('div');
                    div.classList.add('objeto');

                    // Estrutura do HTML de cada objeto
                    div.innerHTML = `
                        <h3>Dados de ${medico.nome}</h3>
                        <p><strong>Id do medico:</strong> ${medico.id}</p>
                        <p><strong>CRM:</strong> ${medico.crm}</p>
                        <p><strong>Especialidade:</strong> ${medico.especialidade}</p>
                    `;
                    
                    // Adiciona o novo item na tela
                    conteudo.appendChild(div);
                });
            } else {
                // Caso não haja consultas
                conteudo.innerHTML = "<p>Nenhum medico registrado.</p>";
            }
        })
        .catch(error => {
            // Se houver um erro ao fazer a requisição
            console.error('Erro ao obter os dados:', error);
            document.getElementById('conteudo').innerHTML = "<p>Erro ao carregar os medicos.</p>";
        });
    }

    // Envio de formulário de atualizar paciente
    if (formType === 'atualizar') {

        const form = document.getElementById('form-atualizar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const nomeMedicoNv = document.getElementById('nomeMedico').value;
            const crmNv = document.getElementById('crm').value;
            const especialidadeNv = document.getElementById('especialidade').value;
            const medicoId = document.getElementById('medicoId').value;
            axios.patch(`${API_URL}/${medicoId}`, {
                nome: nomeMedicoNv,
                crm: crmNv,
                especialidade: especialidadeNv
            })
            .then(response => {
                conteudo.innerHTML = "<p>Medico atualizado com sucesso!</p>";
            })
            .catch(error => {
                conteudo.innerHTML = "<p>Erro ao atualizar medico. Tente novamente.</p>";
            });
        });
    }

    // Envio de formulário de deletar paciente
    if (formType === 'deletar') {
        const form = document.getElementById('form-deletar');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const medicoDel = document.getElementById('medicoId').value;

            axios.delete(`${API_URL}/${medicoDel}`)
                .then(response => {
                    conteudo.innerHTML = "<p>Medico deletado com sucesso!</p>";
                })
                .catch(error => {
                    conteudo.innerHTML = "<p>Erro ao deletar medico. Tente novamente.</p>";
                });
        });
    }
}