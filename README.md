# Sistema de gestión de pacientes

Aplicación web CRUD desarrollada con Java, JSP y MySQL para administrar la información de pacientes. Proyecto académico realizado en SENATI.

## Descripción

El sistema permite registrar, consultar, actualizar y eliminar pacientes. El código está organizado con el patrón **MVC** y el patrón **DAO**, separando la lógica de negocio, el acceso a datos y las vistas.

## Tecnologías

- Java
- JSP
- MySQL
- Patrón MVC
- Patrón DAO
- Servidor [Apache Tomcat]
- IDE [NetBeans]

## Funcionalidades

- Registro de nuevos pacientes
- Consulta y listado de pacientes
- Actualización de datos
- Eliminación de pacientes

## Estructura del proyecto

```
src/
├── modelo/        # Clases de entidad (Paciente)
├── dao/           # Acceso a datos (DAO)
├── controlador/   # Servlets / controladores
└── conexion/      # Conexión a MySQL
web/               # Vistas JSP
```

## Cómo ejecutarlo

1. Clona el repositorio:
   ```
   git clone https://github.com/bernalestradaangelavaleria-sys/gestión_pacientes.git
   ```
2. Crea la base de datos en MySQL e importa el script `hospital_2027.sql`.
3. Configura usuario y contraseña de MySQL en la clase de conexión.
4. Abre el proyecto en [NetBeans] y ejecútalo en Tomcat.

## Autora

**Angela Valeria Bernal Estrada**
Estudiante de Desarrollo de Software en SENATI
