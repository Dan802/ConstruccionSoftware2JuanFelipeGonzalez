package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class PersonValidator extends SimpleValidator {
	
	public String nameValidator(String value) throws Exception {
		return stringValidator(value, "\"Nombre de la persona\" ");
	}
	
	public long documentValidator(String value)throws Exception {
		return longValidator(value, "\"Número de documento\" ");
	}

	public Integer ageValidator(String value) throws Exception{
		return intValidator(value, "\"Edad\" ");
	}
}
