package app.adapters.inputs.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Utils {
  private static Scanner reader = new Scanner(System.in);
	
  public static Scanner getReader() {
		return reader;
	}

  public static String mstoDate (Long ms) {
    // Creating date format
    DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm");

    // Creating date from milliseconds
    // using Date() constructor 
    Date result = new Date(ms);
    
    return simple.format(result);
  }
}
