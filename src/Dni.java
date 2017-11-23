
public class Dni {

	private boolean enc=false;
	
	/**
	 * 
	 * @param dni recogido del textfield en modo String(solo el número, sin letra).
	 * @return Este metodo devuelve true si son 8 números.
	 */
	public boolean validardnisinletra(String dni)
	{
		String numero="";
		String midni="";
		String[] unoNueve={"0","1","2","3","4","5","6","7","8","9"}; 
		
		for(int i=0;i<dni.length()-1;i++)
		{	
			numero=dni.substring(i, i+1);
			
			for(int j=0;j<unoNueve.length;j++)
			{
				if(numero.equals(unoNueve[j]))
				{
					midni+=unoNueve[j];
				}
			}
		}
		
		if(midni.length()==8)
		{
			enc=true;
		}
		
		
		return enc;
	}
	
	/**
	 * 
	 * @param dni Se pasa el dni como String(Solo el número, sin letra).
	 * @return Este metodo devuelve un String, que devuelve el dni con su letra correcta.
	 */
	
	public String recogerdniconletra(String dni)
	{
		String dnicompleto;
		String caracteres="TRWAGMYFPDXBNJZSQVHLCKE";
		int pos=Integer.parseInt(dni)%23;
		char letra=caracteres.charAt(pos);
		dnicompleto=dni+""+letra;
		
		return dnicompleto;
	}
	
	
	
}
