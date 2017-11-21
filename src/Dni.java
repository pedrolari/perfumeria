
public class Dni {

	private boolean enc=false;
	
	/**
	 * 
	 * @param dni recogido del textfield en modo String.
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
	
	
	public void recogerdniconletra()
	{
		
	}
	
	
	
}
