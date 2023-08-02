# Resolución Desafio Java Concrete

Desafio técnico para evaluar el conocimiento de los aspirantes a unirse al equipo de Backend.
Enunciado del desafío: [Enlace al desafío](https://github.com/concretesolutions/java-recruiting-hsa#el-desafio)

## Contenido
- [Instrucciones de Configuración](#instrucciones-de-configuración)
  - [Requisitos Previos](#requisitos-previos)
  - [Configuración](#configuración)
- [Compilación y Ejecución](#compilación-y-ejecución)
- [Acceso a la Aplicación](#acceso-a-la-aplicación)
- [Endpoints de la API](#endpoints-de-la-api)
- [Documentación de la API](#documentación-de-la-api)
- [Dener Docker](#detener-el-contenedor-docker)

## Instrucciones de Configuración

### Requisitos Previos
- Java JDK 17 instalado
- Maven instalado
- Docker instalado

### Configuración

1. Clona este repositorio en tu máquina local:
```sh
git clone https://github.com/ChristopherMontiel/concrete-java-challenge.git
```
ó
```sh
git clone git@github.com:ChristopherMontiel/concrete-java-challenge.git
```
2. Navega al directorio del proyecto:
```sh
cd concrete-java-challenge
```
## Compilación y Ejecución
#### 1. Compila el proyecto usando Maven:
```sh
mvn clean install
```
#### 2. Crea la imagen Docker:
```sh
docker build -t javachallenge:1.0 .
```
#### 3. Ejecuta el contenedor Docker:
```sh
docker run --name java-challenge -p 8080:8080 javachallenge:1.0
```

## Acceso a la Aplicación

Accede a la aplicación en tu navegador o herramienta de cliente API en:
http://localhost:8080

## Endpoints de la API
- Todas las Categorias:  http://localhost:8080/api/v1/categories
- Todos los Cupones:  http://localhost:8080/api/v1/coupons
### Features del desafío:
- Carrusel con Top 5 categorias:
  http://localhost:8080/api/v1/categories/top

- Carrusel con cupones que no han expirado:
  http://localhost:8080/api/v1/coupons/valid

- Tabla (Grid) con las categorias restantes:
  http://localhost:8080/api/v1/categories/remaining

## Documentación de la API

Acceda a la documentación de la API en:
(http://localhost:8080/swagger-ui/index.html)

## Detener el Contenedor Docker:
Si deseas detener y eliminar el contenedor Docker:
```sh
docker stop java-challenge
docker rm java-challenge
```