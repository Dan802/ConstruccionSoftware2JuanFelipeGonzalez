# ConstruccionSoftware2JuanFelipeGonzalez

# Materia: Construccion de software 2

## Integrantes del Equipo 
- [Juan Felipe González](https://github.com/Dan802)

## Tecnología en que se Desarrolló 
- SpringBoot
- SQL

# Nombre del Proyecto: Vet 
Aplicativo para administrar una veterinaria.

## Cómo Correr el proyecto:
1. Clonar el repositorio:
```bash 
git clone https://github.com/Dan802/ConstruccionSoftware2JuanFelipeGonzalez.git
```

2. Crear la base de datos con el comando:
```bash 
CREATE DATABASE cs2vet;
```

3. Configurar la conexión a la base de datos en:

*vet/src/main/resources/application.properties*

Configuración de ejemplo:

```bash 
spring.application.name=app
spring.datasource.url=jdbc:mysql://localhost:3306/cs2vet
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
server.port=8081
spring.jpa.hibernate.ddl-auto=update 
```

4. Correr el proyecto 🤙

## Herramientas utilizadas 
- Sudor y lagrimas (97%)
- Github del profe (1%)
- Stack Overflow (1%)
- Imaginación (0,5%)
- Lógica (0,5%)