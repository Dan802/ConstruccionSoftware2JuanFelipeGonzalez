# ConstruccionSoftware2JuanFelipeGonzalez

# Postman statements 

## Menu principal
1. Iniciar sesion	POST /login 
2. Salir

### **GET** - `/api/users`
Return all the users registered

### **POST** - `/api/login`

```ts
{
  username: string,
  password: string
}
```

```ts
{
    "userName": "alfonso",
    "password": "alfonso1234"
}
```

## Menu del administrador
 1. Crear vendedor.		POST /createPerson 
 2. Crear veterinario.	POST /createPerson 
 3. Salir.

### **POST** - `/api/createPerson`
 
 ```ts
{
  document: long,
  name: String,
  age: int,
  role: String,
  userName: String,
  password: String,
  userNameAdmin: String,
  passwordAdmin  : String
}
```

```ts
{
     "document": 13223232224,
     "name": "Pantera",
     "age": 23,
     "role": "VETERINARIO",
     "userName": "pantera",
     "password": "pantera1234",
     "userNameAdmin": "mariano",
     "passwordAdmin"  : "mariano1234"
    }
```

>[!NOTE]
> El rol debe ser: Administrador, vendedor, veterinario, o dueño


## Menu del Veterinario
 1. Crear dueño 												POST /createPetOwner 
 2. Crear mascota 												POST /createPet 
 3. Crear historia clínica 									POST /createMedicalRecord
 4. Consultar historia clínica (ingresando los milisegundos)	GET /getMedicalRecord 
 5. Editar historia clínica (ingresando los milisegundos)		Put /updateMedicalRecord 
 6. Crear orden 												POST /createOrder 		
 7. Consultar orden (ingresando el id de la orden)				GET /getOrder NO
 8. Anular orden 												Patch /cancelOrder NO
 9. Cerrar sesión



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
- Sudor y lagrimas (90%)
- Imaginación y suerte (10%)