package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class UserValidator extends SimpleValidator {

		public String userNameValidator(String value) throws Exception{
			return stringValidator(value, " Nombre de usuario ");
		}
		public String passwordValidator(String value) throws Exception{
			return stringValidator(value, " Contraseña de usuario ");
		}
}
