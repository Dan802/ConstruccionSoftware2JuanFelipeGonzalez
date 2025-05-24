package app.adapters.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
    
    @GetMapping("/")
	public String itsAlive() {
		return "i'm alive";
	}
	
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
}

/*

"Ingrese la opción que desea:
1. Iniciar sesion	POST /login 
2. Salir"

"Menu del administrador, ingrese la opción:
 1. Crear vendedor.		POST /createPerson 
 2. Crear veterinario.	POST /createPerson 
 3. Salir."

"Menu del Veterinario, ingrese la opción:
 1. Crear dueño" 												POST /createPetOwner 
 2. Crear mascota" 												POST /createPet 
 3. Crear historia clínica" 									POST /createMedicalRecord
 4. Consultar historia clínica (ingresando los milisegundos)	GET /getMedicalRecord NO
 5. Editar historia clínica (ingresando los milisegundos)		Put /updateMedicalRecord WIP
 6. Crear orden" 												POST /createOrder NO		
 7. Consultar orden (ingresando el id de la orden)				GET /getOrder NO
 8. Anular orden" 												Patch /cancelOrder NO
 9. Cerrar sesión"

*/