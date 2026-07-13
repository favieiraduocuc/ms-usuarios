# ms-usuarios

Microservicio encargado de la gestión de usuarios, autenticación interna y administración de roles del proyecto **Lumora Envíos**.

Este servicio forma parte de la arquitectura de microservicios utilizada en el proyecto de título de Ingeniería en Informática de Duoc UC.

---

# Descripción

El microservicio permite administrar la información de los usuarios registrados en la plataforma, incluyendo:

- Registro de usuarios
- Consulta de usuarios
- Actualización de información
- Administración de roles
- Integración con Azure AD B2C
- Comunicación con el BFF Lumora

---

# Arquitectura

Arquitectura Hexagonal (Ports & Adapters)

```
Cliente
      │
      ▼
BFF Lumora
      │
      ▼
REST Controller
      │
Application Service
      │
Domain
      │
Persistence Adapter
      │
MySQL
```

---

# Tecnologías

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Web
- Spring Validation
- MySQL 8
- Maven
- Docker
- Swagger OpenAPI
- JUnit 5
- Mockito
- JaCoCo

---

# Funcionalidades

- Crear usuarios
- Buscar usuario por ID
- Buscar usuario por correo
- Actualizar usuario
- Eliminar usuario
- Obtener listado de usuarios
- Obtener listado de roles

---

# Endpoints principales

## Usuarios

| Método | Endpoint |
|---------|----------|
| GET | /usuarios |
| GET | /usuarios/{id} |
| GET | /usuarios/correo/{correo} |
| POST | /usuarios |
| PUT | /usuarios/{id} |
| DELETE | /usuarios/{id} |

---

## Roles

| Método | Endpoint |
|---------|----------|
| GET | /roles |

---

# Swagger

Disponible una vez iniciado el servicio:

```
http://localhost:8081/swagger-ui/index.html
```

---

# Base de Datos

Motor:

```
MySQL 8
```

Base de datos:

```
lumora_envios
```

Tablas utilizadas:

- usuario
- rol

---

# Docker

Construcción

```bash
mvn clean package -DskipTests

docker build -t lumora-ms-usuarios .
```

Ejecución

```bash
docker run -p 8081:8081 lumora-ms-usuarios
```

---

# Pruebas Unitarias

El proyecto incorpora pruebas unitarias utilizando:

- JUnit 5
- Mockito
- Spring Boot Test

Cobertura analizada mediante:

- JaCoCo

Ejecutar pruebas

```bash
mvn clean test
```

Generar reporte

```bash
mvn jacoco:report
```

Reporte generado en

```
target/site/jacoco/index.html
```

---

# Integración

Este microservicio es consumido por:

- BFF Lumora

A su vez interactúa con:

- MySQL
- Azure AD B2C (información del usuario autenticado)

---

# Estructura del proyecto

```
src
├── main
│   ├── application
│   ├── domain
│   ├── infrastructure
│   └── resources
└── test
    ├── application
    ├── infrastructure
    └── domain
```

---

# Requisitos

- Java 17
- Maven 3.9+
- Docker
- MySQL 8

---

# Autores

Proyecto desarrollado para el Taller Aplicado de Software.

**Equipo**

- Fabián Vieira Villarroel
- Rodrigo Ignacio Díaz Vallejos

Duoc UC
Ingeniería en Informática
2026
