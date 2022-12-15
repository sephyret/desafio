# desafio

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Docker](https://spring.io/projects/spring-boot), [Git](https://git-scm.com), [Spring Boot](https://spring.io/projects/spring-boot). 
Além disto é bom ter um editor para trabalhar com o código como [Eclipse](https://www.eclipse.org/downloads/)

### 🎲 Rodando o Back End (servidor)

```bash
# Clone este repositório
$ git clone <https://github.com/sephyret/desafio>

# Vá para a pasta do projeto
$ cd desafio

# Execute a aplicação em modo de desenvolvimento
$ docker-compose up

# O servidor inciará na porta:8080 - acesse <http://localhost:8080/>

# Acesse o servidor Rabbitmq - <http://localhost:15672/>
  - user = guest
  - pass = guest
  
# Crie uma fila resultado, exchange = amq.direct e routingkey = routing
