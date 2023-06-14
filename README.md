# security-starter-backend
Spring Boot starter project to work with joko-security
# Stack tecnológico
El proyecto utiliza el siguiente stack tecnológico:
- Spring Boot 2
- Java 11
- PostgreSQL 15 
- Kotlin Gradle
# Configuración
## Base de Datos
En la carpeta **/src/main/resources/** se encuentra el dump (joko-db.sql) de la base de datos que contiene los scripts para la creación de las tablas que utiliza el proyecto. Además en esta carpeta encontrarás el archivo application.properties donde podrás configurar la base de datos a utilizar junto con sus propiedades.
## Dependencias
El proyecto utiliza como base la librería [security](https://github.com/jokoframework/security). Dicha librería se descarga de un artifactory privado que no está versionado, para obtener el enlace contáctanos.
## Secret
El sistema necesita un secreto para firmar los tokens. Este secreto puede ser
guradado en dos lugares:
* BD: Si se guarda en la Base de datos va a la tabla joko_security.keychain
* FILE: Si va al filesystem se debe configurar la propiedad joko.secret.file

ATENCIÓN: Es MUY importante que este secreto no sea accedido por terceras
personas. La recomendacion para esto es:
* BD: En este caso asigne permisos a la tabla con solo lectura y solamente
  para el usuario que se utiliza en la aplicacion.
* FILE: Asigne permisos de lecutra y solo para el usuario que se utiliza al
  momento de levantar la aplicacion.

Obs.:En modo BD puede dejarse sin crear un archivo y el sistema va a crear
un secreto la primera vez que se levanta.
# Ejecución
- Clona este repositorio en tu máquina local.
- Navega hasta el directorio del proyecto: cd security-starter-backend
- Abre una ventana de terminal o línea de comandos en la raíz de tu proyecto.
- Ejecuta el siguiente comando para compilar y construir el proyecto con el siguiente comando: 

`gradle build`

Esto descargará todas las dependencias, compilará tu código y generará un archivo JAR ejecutable.

- Una vez que la compilación haya finalizado con éxito, puedes ejecutar el proyecto con el siguiente comando: 

`java -jar build/libs/nombre-del-archivo-generado.jar`
# APIs proveídas
* /api/status
* /api/login
* /api/secure/user/signup
* /api/secure/user/create
* /api/secure/user/update
* /api/secure/user/list
* /api/secure/user/get
* /api/secure/user/delete

Obs: En la carpeta **/src/main/resources/** encontrarás un archivo insomnia.json con la documentación de cada endpoint.