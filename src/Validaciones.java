import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

	/**
	 * 
	 * @param camp Se le pasa el String que queremos comprobar si esta vacío.
	 * @return Si esta vacío devuelve true.
	 */
	public boolean campovacio(String camp)
	{
		if(camp.length()==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * 
	 * @param num Se le pasa el número en formato String.
	 * @return  Devuelve true si el teléfono NO es valido.
	 */
	public boolean validartelefono(String num)
	{
		if(this.isNumeric(num)==true)
		{
		if(Integer.parseInt(num)<111111111||Integer.parseInt(num)>999999999)
		{
			return true;
		}
		else 
		{
			return false;
		}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @param email Se introduce en formato String.
	 * @return Devuelve true si el email es valido.
	 */
	public boolean validaremail(String email)
	{
		 Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	        Matcher mather = pattern.matcher(email);
	 
	        if (mather.find() == true) 
	        {
	           return true;
	        } 
	        else 
	        {
	            return false;
	        }
	}
	
	/**
	 * 
	 * @param nif Pasamos el nif en modo string
	 * @return Este metodo devuelve true si es correcto el nif.
	 */
	
	public  boolean validarNIF(String nif) {

	    boolean correcto = false;

	    Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

	    Matcher matcher = pattern.matcher(nif);

	    if (matcher.matches()) {

	        String letra = matcher.group(2);

	        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

	        int index = Integer.parseInt(matcher.group(1));

	        index = index % 23;

	        String reference = letras.substring(index, index + 1);



	        if (reference.equalsIgnoreCase(letra)) {

	            correcto = true;

	        } else {

	            correcto = false;

	        }

	    } else {

	        correcto = false;

	    }

	    return correcto;

	}
	
}
