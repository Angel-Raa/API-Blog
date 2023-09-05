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

## Recursos Adicionales

Aquí hay algunos recursos adicionales que podrían ser útiles:

- [Documentación de Java JWT](https://github.com/jwtk/jjwt)
- [Documentacion de open jdk 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Documentacion de Spring boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Documentacion de Maven](https://maven.apache.org/guides/getting-started/)
- [Documentacion de Docker](https://docs.docker.com/)
- [Documentacion de Spring Security](https://docs.spring.io/spring-security/reference/index.html)
