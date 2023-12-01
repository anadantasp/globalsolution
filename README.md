# Global Solution - FIAP - 2TDSPR

## Integrantes:
* Alicia Guiradelo
* Ana Prado
* Arthur Foschiani
* Bruna Menegatti
* Larah Correa

## Videos
Video pitch do projeto


[Vídeo sobre as funcionalidades do projeto](https://www.youtube.com/watch?v=LS9QPh3lNYs)

## Documentação
Acesse o escopo do projeto [aqui](https://github.com/anadantasp/globalsolution/tree/main/documentacao/escopo%20do%20produto)

## [Figma](https://www.figma.com/file/m5PeO0oFa0dUapGQAac471/GS---HealthSync?type=design&node-id=0-1&mode=design)


## Endpoints do projeto
### Medicamentos

Descrição dos endpoints de medicamentos:

- `GET` /medicamentos: lista todos os medicamentos cadastrados no sistema
- `GET` /medicamentos/{id}: retorna um medicamento
- `GET` /medicamentos/{cpf}: retorna os medicamentos relacionados a um paciente
- `POST` /medicamentos: cadastra um medicamentos
- `PUT` /medicamentos/{id}: atualizar os dados do estoque 
- `DELETE` /medicamentos/{id}: apaga um medicamentos

#### Exemplo de JSON
```js
{
    "id": 1,
    "nome": "Neosaldina"
}
```

### Especialidades

Descrição dos endpoints de especialidades

- `GET` /especialidades: lista todas as especialidades cadastradas no sistema
- `GET` /especialidades/{id}: retorna uma especialidades
- `POST` /especialidades: cadastra uma especialidades
- `PUT` /especialidades/{id}: atualizar os dados de uma especialidades 
- `DELETE` /especialidades/{id}: apaga uma especialidades

#### Exemplo de JSON
```js
{
    "id": 1,
    "nome": "Clinico Geral"
}
```

### Médicos

Descrição dos endpoints de médicos

- `GET` /medicos: lista todos os medicos cadastrados no sistema
- `GET` /medicos/{id}: retorna um medicos por id
- `GET` /medicos/{crm}: retorna um medicos por crm
- `GET` /medicos/login?email={email}&senha={senha}: retorna um medicos pelo seu email e senha
- `POST` /medicos: cadastra um medico
- `PUT` /medicos/{id}: atualizar os dados do medicos 
- `DELETE` /medicos/{id}: apaga um medico

#### Exemplo de JSON
```js
{
	"id": 2,
	"nome": "Eduardo",
	"email": "eduardo@email.com.br",
	"crm": "67890",
	"senha": "senha123",
	"especialidade": {
		"id": 1,
		"nome": "Clinico Geral"
	}
}
```

### Pacientes

Descrição dos endpoints de pacientes

- `GET` /pacientes: lista todos os pacientes cadastrados no sistema
- `GET` /pacientes/{id}: retorna um paciente por id
- `GET` /pacientes/{cpf}: retorna um paciente por cpf

#### Exemplo de JSON
```js
{
        "id": 3,
        "nome": "Arthur",
        "email": "arthur@email.com.br",
        "cpf": "123456789",
        "dataNascimento": "2003-12-21"
    }
```

### Item Receita

Descrição dos endpoints de item receita que relaciona um medicamento à uma quantidade, dosagem, período... específicos

- `GET` /itensreceitas: lista todos os itens receita cadastrados no sistema
- `GET` /itensreceitas/{id}: retorna um item receita por id
- `POST` /itensreceitas: cadastra um item receita
- `PUT` /itensreceitas/{id}: atualizar os dados de um item receita
- `DELETE` /itensreceitas/{id}: apaga um item receita

#### Exemplo de JSON
```js
{
    "id": 1,
    "descricao": "para dores de cabeça",
    "periodo": "3 dias",
    "dosagem": "500mg",
    "medicamento": {
        "id": 1,
        "nome": "Neosaldina"
    }
}
```

### Receita

Descrição dos endpoints de receita

- `GET` /receitas: lista todas as receitas cadastradas no sistema
- `GET` /receitas/{id}: retorna uma receita por id
- `GET` /receitas/paciente/{cpf}: retorna todas as receitas associadas a um paciente
- `GET` /receitas/status/{cpf}: retorna todas as receitas associadas a um paciente pelo seu status(ativa ou desativada)
- `POST` /receitas: cadastra uma receita
- `PUT` /receitas/{id}: atualiza os dados de uma receita
- `DELETE` /receitas/{id}: apaga uma receita

#### Exemplo de JSON
```js
{
    "id": 1,
    "data": "2023-11-29",
    "dataValidade": "2023-12-06",
    "status": false,
    "medico": {
        "id": 1,
        "nome": "Maria Clara",
        "email": "mariaclara@email.com.br",
        "crm": "12345",
        "senha": "senha123",
        "especialidade": {
            "id": 2,
            "nome": "Ortopedista"
        }
    },
    "paciente": {
        "id": 3,
        "nome": "Arthur",
        "email": "arthur@email.com.br",
        "cpf": "123456789",
        "dataNascimento": "1998-04-20"
    },
    "medicamentos": [
        {
            "id": 1,
            "descricao": "para dores de cabeça",
            "periodo": "3 dias",
            "dosagem": "500mg",
            "medicamento": {
                "id": 1,
                "nome": "Neosaldina"
            }
        }
    ]
}
```

### Códigos de retorno
|codigo| significado
|-|-
|200 | dados retornados com sucesso
|201 | criado com sucesso
|204 | apagado com sucesso
|400 | dados inválidos | violação de integridade com o banco
|404 | não encontrado






