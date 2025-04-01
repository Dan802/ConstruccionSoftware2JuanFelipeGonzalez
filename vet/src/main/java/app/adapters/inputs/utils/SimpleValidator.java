package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public  class SimpleValidator {
	
	public String stringValidator(String value, String element)throws Exception {
		if (value== null||value.equals("")) {
			throw new Exception(element + " no tiene un valor valido");
		}
		return value;
	}
	
	public Long longValidator(String value, String element)throws Exception{
		try {
			return Long.parseLong(stringValidator(value, element));
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico");
		}
	}

	public Integer intValidator(String value, String element) throws Exception {
		try {
			return Integer.parseInt(stringValidator(value, element));
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico");
		}
	}

	public Double doubleValidator(String value, String element) throws Exception {
		try {
			return Double.parseDouble(stringValidator(value, element));
		}catch(Exception e) {
			throw new Exception(element + " debe ser un valor numerico");
		}
	}

	public String isNull(String dato) {
    if(dato == null || dato == "" || dato.length() == 0) {
      return "No Aplica";
    } else {
      return dato;
    }
  }
}
