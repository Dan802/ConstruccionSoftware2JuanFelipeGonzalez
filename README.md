# ConstruccionSoftware2JuanFelipeGonzalez

# Postman statements 

## Menu principal
1. Iniciar sesion	POST /login 
2. Salir

### **GET** - `/api/users`
JSON Response:
```ts
{
  loginId : long,   
  personId: {
    document: long,
    name: string,
    age: int,
    role: string,
  }, 
  userName : string,
  password : string
}
```

### **POST** - `/api/login`

JSON request body:
```ts
{
  username: string,
  password: string
}
```

JSON request example:
```ts
{
    "userName": "mariano",
    "password": "mariano1234"
}
```

## Menu del administrador
 1. Crear vendedor.		POST /createPerson 
 2. Crear veterinario.	POST /createPerson 
 3. Salir.

### **POST** - `/api/createPerson`
 
 JSON Request Body:
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
JSON Request Example:
```ts
{
     "document": 123435679,
     "name": "Alfonso Rodriguez",
     "age": 24,
     "role": "VETERINARIO",
     "userName": "alfonsos",
     "password": "alfonsos1234",
     "userNameAdmin": "mariano",
     "passwordAdmin": "mariano1234"
}
```

>[!NOTE]
> The rol have to be: ADMINISTRADOR, VENDEDOR, VETERINARIO, or DUEÑO


## Menu del Veterinario
 1. Crear dueño 												POST /createPetOwner 
 2. Crear mascota 												POST /createPet 
 3. Crear historia clínica 									POST /createMedicalRecord
 4. Consultar historia clínica (ingresando los milisegundos)	GET /getMedicalRecord 
 5. Editar historia clínica (ingresando los milisegundos)		Put /updateMedicalRecord 
 6. Crear orden 												POST /createOrder 		
 7. Consultar orden (ingresando el id de la orden)				GET /getOrder
 8. Anular orden 												Patch /cancelOrder 
 9. Cerrar sesión

### **POST** - `/api/petOwner`
JSON Request Body:
```ts
{
  document: long,
  name: string,
  age: int,
  userName: string,
  password: string,
  userNameVet: string,
  passwordVet: string
}
```

JSON Request Example:
```ts
{
  "document": 102,
  "name": "Don Gediondo",
  "age": 54,
  "userName": "gediondo",
  "password": "gediondo1234",
  "userNameVet": "alfonso",
  "passwordVet": "alfonso1234"
}
```

### **POST** - `/api/pet`
JSON Request Body:
```ts
{
  name: string,
  species: string,
  breed: string,
  age: int,
  documentOwner: long,
  userNameVet: string,
  passwordVet: string
}
```

JSON Request Example:
```ts
{
  "name": "Poppins",
  "description": "bola de pelos hermosa",
  "specie": "gata",
  "breed": "criolla",
  "age": 4,
  "weight": 30,
  "documentOwner": 101,
  "userNameVet": "alfonso",
  "passwordVet": "alfonso1234"
}
```

### **POST** - `/api/medicalRecord`
JSON Request Body:
```ts
{
  vetDocument: long,
  petId: long,
  reason: string,
  symptoms: string,
  diagnosis: string,
  procedures: string,
  medicine: string,
  doseMedication: string,
  vaccinationHistory: string,
  allergyMedications: string,
  procedureDetail: string,
  userNameVet: string,
  passwordVet: string
}
```

JSON Request Example:
```ts
{
  "vetDocument" : 12345679,
  "petId" : 1,
  "reason" : "Fiebre y deshidratación",
  "symptoms" : "Ta malito",
  "diagnosis" : "Enfermedad general",
  "procedures" : "NA",
  "medicine" : "NA",
  "doseMedication" : "NA",
  "vaccinationHistory" : "Actualizada",
  "allergyMedications" : "No",
  "procedureDetail" : "NA",
  "userNameVet": "alfonso",
  "passwordVet": "alfonso1234"
}
```

### **GET** - `/api/medicalRecord/:ms`
Query Parameters:
```ts
{
  ms : long  // Unix timestamp in milliseconds
}
```

JSON Response:
```ts
{
  date: long,
  vetDocument: {
    document: long,
    name: string,
    age: int,
    role: string
  },
  petId: {
    petId: long,
    name: string,
    documentOwner: {
      document: long,
      name: string,
      age: int,
      role: string
    },
    age: int,
    specie: string,
    breed: string,
    description: string,
    weight: double
  },
  reason: string,
  symptoms: string,
  diagnosis: string,
  procedures: string,
  medicine: string,
  doseMedication: string,
  vaccinationHistory: string,
  allergyMedications: string,
  procedureDetail: string,
  orderCancellation: boolean
}
```

### **PUT** - `/api/updateMedicalRecord`
JSON Request Body:
```ts
{
  ms: long,
  vetDocument?: long,
  petId?: long,
  reason?: string,
  symptoms?: string,
  diagnosis?: string,
  procedures?: string,
  medicine?: string,
  doseMedication?: string,
  vaccinationHistory?: string,
  allergyMedications?: string,
  procedureDetail?: string,
  userNameVet: string,
  passwordVet: string
}
```

>[!NOTE]
> ? Fields are optional

JSON Request Example:
```ts
{
    "ms": 1748649028633,
    "petId": 5,
    "reason": "Mami, mi patita, me duele...",
    "userNameVet": "alfonso",
    "passwordVet": "alfonso1234"
}
```

### **POST** - `/api/order`
JSON Request Body:
```ts
{
    orderId : long,
    petId : long,
    documentOwner : long,
    documentVet : long,
    medicine : long,
    userNameVet: string,
    passwordVet: string
}
```

JSON Request Example:
```ts
{
  "orderId" : "1",
  "petId" : "1",
  "documentOwner" : "101",
  "documentVet" : "12345679",
  "medicine" : "1748649028633",
  "userNameVet": "alfonso",
  "passwordVet": "alfonso1234"
}
```

### **GET** - `/api/order/:orderId`
Query Parameters:
```ts
{
  orderId: long
}
```

JSON Response:
```ts
{
  orderId: long,
  petId: {
    petId: long,
    name: string,
    documentOwner: {
      document: long,
      name: string,
      age: int,
      role: string
    },
    age: int,
    specie: string,
    breed: string | null,
    description: string,
    weight: double
  },
  documentOwner: {
    document: long,
    name: string,
    age: int,
    role: string
  },
  documentVet: {
    document: long,
    name: string,
    age: int,
    role: string
  },
  medicine: {
    date: long,
    vetDocument: {
      document: long,
      name: string,
      age: int,
      role: string
    },
    petId: {
      petId: long,
      name: string,
      documentOwner: {
        document: long,
        name: string,
        age: int,
        role: string
      },
      age: int,
      specie: string,
      breed: string | null,
      description: string,
      weight: double
    },
    reason: string,
    symptoms: string,
    diagnosis: string,
    procedures: string,
    medicine: string,
    doseMedication: string,
    vaccinationHistory: string,
    allergyMedications: string,
    procedureDetail: string,
    orderCancellation: boolean
  },
  createdDate: long
}
```

### **PATCH** - `/api/cancelOrder`
JSON Request Body:
```ts
{
  medicine: long,
  userNameVet: string,
  passwordVet: string
}
```

JSON Request Example:
```ts
{
  "medicine": 1748625524763,
  "userNameVet": "alfonso",
  "passwordVet": "alfonso1234"
}
```

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
