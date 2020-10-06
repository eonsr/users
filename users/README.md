# **Poyecto: Ejercicio Pago Diario**

* Proyecto **SpringBoot**.

* Arquetipo **Maven**.

* El proyecto genera un **JAR** (Tomcat embebido).

* Los módulos usan arquitectura **ortogonal (ligera)**.

## **Capas Ortogonales**

* **Controlador Front:** Capa encargada de consumir servicios (**FrontController**).

* **Servicio:** Capa encargada de exponer los servicios, consume los métodos expuestos en la capa de Negocio (**Service**).

* **Negocio:** Capa encargada de almacenar las clases con la lógica de negocio (el objeto Assembler “limpiaría” el proceso que no describa las reglas de negocio) (**BO & Assembler**).

* **Acceso a Datos:** Capa encargada de manejar las consultas a Base de Datos (**DAO**).

## **Tecnología**

* **Lenguaje:** Java versión 1.8

* **Framework:** SpringBoot (Chasis microservicios) versión 2.3.4.RELEASE.

* **Base de Datos:** PostgreSQL versión 10.

## **Instalación**

### **Java**

* Descargar el JDK v1.8+ e instalar (seguir instrucciones en wizard).

* Crear Variable de entorno **JAVA_HOME** y agregarla al **Path**.

## **Ejecución**

* Crear jar del proyecto desde IDE o Terminal (mvn clean install).

* Ejecutar java -jar users-0.0.1-SNAPSHOT.jar

### **PostgreSQL**

* Descargar el archivo versión 10+ e instalar (seguir instrucciones en wizard).

## **Uso**

### **Swagger**

Se hace uso de Swagger para generar automáticamente una página la cual muestre mecanismos para probar el API Rest de la aplicación.

### **Página para llamar a swagger**

Se puede usar un HTML (**index.html**) para la generación de la página, incluso se puede llamar directamente haciendo uso del contexto de la aplicación (entrando a **swagger-ui.html**). Para ingresar en las paginas se entra en **http://localhost:8080/**
