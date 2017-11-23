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
	
	
	/**
	 * 
	 * @param num Se le pasa el número en formato String.
	 * @return  Devuelve true si el teléfono NO es valido.
	 */
	public boolean validartelefono(String num)
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
	
	
	
}
