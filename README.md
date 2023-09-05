# Restful CRUD Blog API usando Spring Boot 3, Spring Security 6, JWT, PostgreSQL y  Spring Data Jpa

Este proyecto representa un caso de uso de una aplicación Restful API, desarrollada con  Java 17, Spring Boot 3, Spring Security 6, JWT (JSON Web Tokens),  PostgreSQL y Spring Data Jpa.

## Tecnologías Utilizadas

- JDK 17
- Spring Boot 3
- Spring Data Jpa
- Spring security 6
- PostgreSQL 15
- Java JWT
- Docker

## Configuración del Entorno  y  Prerrequisitos

Asegúrate de tener instalados los siguientes componentes en tu entorno de desarrollo antes de comenzar:

1. [Git](https://git-scm.com/downloads)
2. [Docker](https://docs.docker.com/compose/install/)

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

Se requiere autenticación para acceder a los endpoints de esta API. Puedes obtener un token JWT mediante los siguientes endpoints:

## Autenticación

| Método   | Endpoint                               | Descripción                                |
|----------|----------------------------------------|--------------------------------------------|
| POST     | `/authentication/login`                | Iniciar sesión de usuario                  |
| POST     | `/authentication/signup`               | Registro de usuario                        |
| GET      | `/authentication/all`                  | Obtener todos los usuarios autenticados    |

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
            "password": "admin",
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

| Método   | Endpoint                       | Descripción                                      |
|----------|--------------------------------|--------------------------------------------------|
| POST     | `/post/create`                 | Crear un nuevo post                              |
| GET      | `/post/list`                   | Listar todos post                                |
| GET      | `/post/{id}`                   | Obtener un post por ID                           |
| GET      | `/post/find/{title}`           | Buscar post por título                           |
| PUT      | `/post/update/{id}`            | Actualizar un mensaje de post por ID             |
| DELETE   | `/post/delete/{id}`            | Eliminar post por ID                             |
| GET     | `/post/all`                    | Obtener todos los  post                           |


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

- **Obtener un Post por ID:** `Endpoint: GET /post/{id} `
    - Parámetros de la Solicitud:
        - `{id}` (Path Parameter): El ID del mensaje de publicación que se desea obtener.
    - Respuesta Exitosa:
        ```json
        {
          {
            "id": 1,
            "username": "angel",
            "title": "para que sirve en java",
            "content": "Java es un lenguaje de programación que se compila en bytecode y se ejecuta en una máquina virtual, lo que permite que el código se ejecute en cualquier plataforma con una              JVM  instalada, y ofrece un gran conjunto de bibliotecas estándar para facilitar el desarrollo de aplicaciones.",
            "createAt": "2023-09-05T01:49:41.90261",
            "updateAt": "2023-09-05T01:49:41.902698"
          }
        }
         ```
- **Buscar Post por Título** `Endpoint: GET /post/find/{title}`
    - Parámetros de la Solicitud:
      - `{title}` (Path Parameter): El título del POst que se desea buscar.
    - Respuesta Exitosa:
        ```json
        {
          {
            "id": 3,
            "username": "angel",
            "title": "que en git",
            "content": "GIT es el SCV (sistema de control de versiones) de código abierto más utilizado que te permite rastrear los cambios realizados en los archivos. Las empresas y lo             programadores suelen utilizar el GIT para colaborar en el desarrollo de software y aplicaciones.",
            "createAt": "2023-09-05T01:51:51.602443",
            "updateAt": "2023-09-05T01:51:51.602491"
          }
        } 
        ```

- **Crear un Nuevo  POst:** `Endpoint: POST /post/create `
    - Cuerpo de la Solicitud:
        ```json
        {

           {
              "username": "alba",
              "title": "que son tipos de datos java",
              "content": "Los tipos de datos en Java son un formato de almacenamiento de datos que puede contener un tipo específico o rango de valores."
            }
          
        }
        ```

  - Respuesta Exitosa:
      ```json
      {
        {
          "message": "post created successfully",
          "code": 201,
          "http": "CREATED",
          "time": "05-09-2023 03:29:08"
        }
      }
      ```
- **Actualizar Post por ID:** `Endpoint: PUT /post/update/{id}`
    - Parámetros de la Solicitud:
      - `{id}` (Path Parameter): El ID del Post que se desea actualizar.
        
    - Cuerpo de la Solicitud:
        ```json
        {

           {
              "username": "alba",
              "title": "que son tipos de datos java",
              "content": "Los tipos de datos en Java son un formato de almacenamiento de datos que puede contener un tipo específico o rango de valores."
            }
          
        }
        ```
    - Respuesta Exitosa:
         ```json
        {

           {
              "username": "alba",
              "title": "que son tipos de datos java",
              "content": "Los tipos de datos en Java son un formato de almacenamiento de datos que puede contener un tipo específico o rango de valores Cuando una aplicación o un programa informático almacena datos en variables, a cada variable se le debe asignar un tipo de datos específico."
            }
          
        }
        ```
- **Eliminar Post por ID:**  `Endpoint: DELETE /post/delete/{id}`
    - Parámetros de la Solicitud:
       - `{id}` (Path Parameter): El ID del Post eliminar.
     - Cuerpo de la Solicitud:
         ```json
         {
           {
             "username":"alba"
           {
         }
         ```
  - Respuesta Exitosa:
    ```json
    {
       {
          "message": "post deleted successfully",
          "code": 200,
          "http": "OK",
          "time": "05-09-2023 03:44:36"
        }
    }
    ```


- **Listar Todos los Post:** `Endpoint: GET /post/list `
  
Descripción: Este endpoint devuelve una lista de todos Post, incluyendo los comentarios asociados a cada post.

  - Respuesta Exitosa:
    ```json
    {

    [
      {
          "username": "angel",
          "title": "para que sirve en java",
        "content": "Java es un lenguaje de programación que se compila en bytecode y se ejecuta en una máquina virtual, lo que permite que el código se ejecute en cualquier plataforma con una JVM instalada, y ofrece un gran conjunto de bibliotecas estándar para facilitar el desarrollo de aplicaciones.",
          "createAt": "2023-09-05T01:49:41.90261",
          "updateAt": "2023-09-05T01:49:41.902698",
          "commentList": [
            {
                "id": 1,
                "body": "excelente contenido",
                "created": "2023-09-05T01:52:07.086776"
              },
              {
                "id": 4,
                "body": "muy buen artículo",
                "created": "2023-09-05T01:57:26.710532"
              }
            ]
        },
        {
            "username": "angel",
            "title": "que son JSON Web Token",
            "content": "JSON Web Token (abreviado JWT) es un estándar abierto basado en JSON propuesto por IETF (RFC 7519) para la creación de tokens de acceso que permiten la propagación de identidad y privilegios o claims en inglés.",
            "createAt": "2023-09-05T01:50:17.367953",
            "updateAt": "2023-09-05T01:50:17.36798",
            "commentList": []
        }
    ]

    }
    ``` 

## Endpoints de Comment 

| Método   | Endpoint                               | Descripción                                      |
|----------|----------------------------------------|--------------------------------------------------|
| GET      | `/comment/all`                         | Obtener todos los comentarios                    |
| GET      | `/comment/{commentId}`                 | Obtener un comentario por ID                     |
| POST     | `/comment/create/{PostId}`             | Crear un comentario en un publicación            |
| PUT      | `/comment/update/{commentId}`          | Actualizar un comentario por ID                  |
| DELETE   | `/comment/delete/{commentId}`          | Eliminar un comentario por ID                    |


- **Obtener Todos los Comment:** `Endpoint: GET /comment/all`
  
Descripción: Este endpoint permite obtener una lista de todos los Comment.

- Respuesta Exitosa:
  ```json
  {

    [
            {
                "id": 1,
                "username": "angel",
                "body": "excelente contenido",
            "    created": "2023-09-05T01:52:07.086776"
            },
            {
                "id": 4,
                "username": "admin",
                "body": "muy buen articulos",
                "created": "2023-09-05T01:57:26.710532"
            }
        ]
  }
  ```

- **Obtener un Comentario por ID:** `Endpoint: GET /comment/{id}`

Descripción: Este endpoint permite obtener un Comment específico según su ID.

 - Parámetros de la Solicitud:
      `{id}` (Path Parameter): El ID del Commtn que se desea obtener.

 - Respuesta Exitosa:
  ```json
  {

    {
        "id": 4,
        "username": "admin",
        "body": "muy buen articulos",
        "created": "2023-09-05T01:57:26.710532"
    }

  }
  ```


- **Crear un Nuevo Comment en un Post:** `Endpoint: POST /comment/create/{PostId}`
Descripción: Este endpoint permite crear un nuevo Comment en un Post específico, identificado por su PostId.

    - Parámetros de la Solicitud:
      - `{PostId}` (Path Parameter): El ID del Post en el que se desea crear el Comment.

   - Cuerpo de la Solicitud:
     ```json
     {
             {    
                "username": "alba",
                "body": "Cuáles son las ventajas características principales de Git ?"
            }
     }
     ```      
  - Respuesta Exitosa:
    ```json
    {

        {
            "message": "comment created successfully",
            "code": 201,
            "http": "CREATED",
            "time": "05-09-2023 12:51:14"
         }

    }
    ```

- **Actualizar un Comment por ID:** `Endpoint: PUT /comment/update/{commentId}`
Descripción: Este endpoint permite actualizar un Comment existente  mediante su ID

    - Parámetros de la Solicitud:
      `{commentId}` (Path Parameter): El ID del Comment que se desea actualizar.

    - Cuerpo de la Solicitud:
      ```json
      {

        {
            "username": "alba",
            "body": "Cuáles son las ventajas características principales de Git y Quién creó Git?"
        }

      }
      ```
  - Respuesta Exitosa:
    ```json
      {

        {
            "username": "alba",
            "body": "Cuáles son las ventajas características principales de Git y Quién creó Git?"
        }

      }
      ```

- **Eliminar un Comment por ID:** `ndpoint: DELETE /comment/delete/{commentID}`
 
Descripción: Este endpoint permite eliminar un Comment existente  mediante su ID.
    - Parámetros de la Solicitud:
        `{commentID}` (Path Parameter): El ID del comment que se desea eliminar.
        
   - Cuerpo de la Solicitud:
        ```json
        {

            {
                "username": "alba"
            }
        }
        ```
        
- Respuesta Exitosa:
    ```json
    {
        {
            "message": "comment deleted successfully",
            "code": 200,
            "http": "OK",
            "time": "05-09-2023 01:11:32"
        }
    }
    ```  
        
## Recursos Adicionales

Aquí hay algunos recursos adicionales que podrían ser útiles:
- [Documentacion de Spring Data](https://spring.io/projects/spring-data)
- [Documentación de Java JWT](https://github.com/jwtk/jjwt)
- [Documentacion de open jdk 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Documentacion de Spring boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Documentacion de Maven](https://maven.apache.org/guides/getting-started/)
- [Documentacion de Docker](https://docs.docker.com/)
- [Documentacion de Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [Documentacion de Git](https://git-scm.com/doc)
