# Restful CRUD Blog API usando Spring Boot 3, Spring Security 6, JWT, PostgreSQL, JPA 

Este proyecto representa un caso de uso de una aplicación Restful API, desarrollada con  Java 17, Spring Boot 3, Spring Security 6,  JWT (JSON Web Tokens),  PostgreSQL y  JPA (Java Persistence API).

## Tecnologías Utilizadas

- JDK 17
- Spring Boot 3
- Spring security 6
- PostgreSQL 15
- Java JWT
- Docker

## Configuración del Entorno  y  Prerrequisitos

Asegúrate de tener instalados los siguientes componentes en tu entorno de desarrollo antes de comenzar:

1. [Docker](https://docs.docker.com/compose/install/)

## Instrucciones de Uso

Sigue estos pasos para ejecutar la aplicación en tu entorno local:


#### Clonar el repositorio en tu máquina local
```
git clone https://github.com/Angel-Raa/Restful-CRUD-Blog-API-Spring-Boot-3.git
```
#### Navegar al directorio del proyecto
```
cd CRestful-CRUD-Blog-API-Spring-Boot-3
```

#### Construir y ejecutar los contenedores de Docker Compose
```
docker compose up -d
```
### Documentación de los Endpoints

### Base URL

- [http://localhost:2020](http://localhost:2020)
  
- Se requiere autenticación para acceder a los endpoints de esta API. Puedes obtener un token JWT mediante los siguientes endpoints:

| Método   | Endpoint                               | Descripción                                |
|----------|----------------------------------------|--------------------------------------------|
| POST     | `/authentication/login`                | Iniciar sesión de usuario                  |
| POST     | `/authentication/signup`               | Registro de usuario                        |
| GET      | `/authentication/all`                  | Obtener todos los usuarios autenticados    |


## Autenticación

- **Iniciar sesión de usuario:** `POST /authentication/login`
    - Cuerpo de la solicitud:
        ```json
        {
            "username": "alba",
            "password": "alba"
        }
        ```
    - Respuesta exitosa:
        ```json
        {
             "message":"login successfully",
             "jwt": "TOKEN_JWT",
             "status":"OK"
        }
        ```
        
- **Registro de usuario:** `POST /authentication/sign`
    - Cuerpo de la solicitud:
        ```json
        {
            "username": "angel",
            "password": "admin"
            "email":"alba@gmail.com"
        }
        ```
    - Respuesta exitosa:
        ```json
        {
            "message":"register successfully",
            "jwt": "TOKEN_JWT",
            "status":"CREATED"
        }
- **Obtener todos los usuario:** `Endpoint: GET /authentication/all`
    - Respuesta Exitosa:
      ```json
      {

        [
          {
            "id": 1,
            "username": "angel",
            "email": "angel@gmail.com",
            "createAt": "2023-09-05T01:49:15.016076",
            "updateAt": "2023-09-05T01:49:15.016335"
          },
         {
            "id": 2,
            "username": "admin",
            "email": "admin@gmail.com",
            "createAt": "2023-09-05T01:52:34.307614",
            "updateAt": "2023-09-05T01:52:34.307684"
          },
          {
            "id": 3,
            "username": "alba",
            "email": "alba@gmail.com",
            "createAt": "2023-09-05T02:24:01.017702",
            "updateAt": "2023-09-05T02:24:01.017821"
          }
        ]    
      
      }
      ```
## Endpoints de Post
- **Obtener Todos los Post:** `Endpoint: POST /post/all`
  - Respuesta Exitosa:
      ```json
      {

        [
          {
              "id": 1,
              "username": "angel",
              "title": "para que sirve en java",
              "content": "Java es un lenguaje de programación que se compila en bytecode y se ejecuta en una máquina virtual, lo que permite que el código se ejecute en cualquier plataforma con                  una  JVM instalada, y ofrece un gran conjunto de bibliotecas estándar para facilitar el desarrollo de aplicaciones.",
              "createAt": "2023-09-05T01:49:41.90261",
              "updateAt": "2023-09-05T01:49:41.902698"
            },
            {
              "id": 2,
              "username": "angel",
              "title": "que son JSON Web Token",
              "content": "JSON Web Token (abreviado JWT) es un estándar abierto basado en JSON propuesto por IETF (RFC 7519) para la creación de tokens de acceso que permiten la propagación de                   identidad y privilegios o claims en inglés.",
              "createAt": "2023-09-05T01:50:17.367953",
              "updateAt": "2023-09-05T01:50:17.36798"
            },
            {
              "id": 3,
              "username": "angel",
              "title": "que en git",
              "content": "GIT es el SCV (sistema de control de versiones) de código abierto más utilizado que te permite rastrear los cambios realizados en los archivos. Las empresas y los                       programadores suelen utilizar el GIT para colaborar en el desarrollo de software y aplicaciones.",
              "createAt": "2023-09-05T01:51:51.602443",
              "updateAt": "2023-09-05T01:51:51.602491"
            },
            {
              "id": 5,
              "username": "alba",
              "title": "La Programación Orientada a Objetos (POO)",
              "content": "La Programación Orientada a Objetos (POO) es un paradigma de programación, esto es, un modelo o un estilo de programación que proporciona unas guías acerca de cómo                      trabajar con él y que está basado en el concepto de clasey objetos.",
              "createAt": "2023-09-05T02:58:23.848193",
              " updateAt": "2023-09-05T02:58:23.848241"
            }
          ]
      }
        ```
## Recursos Adicionales

Aquí hay algunos recursos adicionales que podrían ser útiles:

- [Documentación de Java JWT](https://github.com/jwtk/jjwt)
- [Documentacion de open jdk 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Documentacion de Spring boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Documentacion de Maven](https://maven.apache.org/guides/getting-started/)
- [Documentacion de Docker](https://docs.docker.com/)
- [Documentacion de Spring Security](https://docs.spring.io/spring-security/reference/index.html)
