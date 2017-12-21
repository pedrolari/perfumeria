
public class Ticket {
	
	private int id;
	private int idTic;
	private String empNom;
	private int idArt;
	private String nomArt;
	private int artPre;
	private int cantArt;
	private int preTotal;
	private int total;
	
	public Ticket() {
		// TODO Auto-generated constructor stub
	}
	
	/*public Ticket(int id,int idTic,String empNom, int idArt, String nomArt, int artPre, int cantArt, int total) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.idTic = idTic;
		this.empNom = empNom;
		this.idArt = idArt;
		this.nomArt = nomArt;
		this.artPre =artPre;
		this.cantArt = cantArt;
		this.total = total;
	}*/
	
	public Ticket(String empNom,int idTic, int idArt, String nomArt, int artPre, int cantArt, int preTotal, int total) {
		// TODO Auto-generated constructor stub
		
		this.empNom = empNom;
		this.idTic = idTic;
		this.idArt = idArt;
		this.nomArt = nomArt;
		this.artPre =artPre;
		this.cantArt = cantArt;
		this.preTotal = preTotal;
		this.total = total;
		
	}

	
	
	public int getPreTotal() {
		return preTotal;
	}

	public void setPreTotal(int preTotal) {
		this.preTotal = preTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTic() {
		return idTic;
	}

	public void setIdTic(int idTic) {
		this.idTic = idTic;
	}

	public String getEmpNom() {
		return empNom;
	}

	public void setEmpNom(String empNom) {
		this.empNom = empNom;
	}

	public int getIdArt() {
		return idArt;
	}

	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}

	public String getNomArt() {
		return nomArt;
	}

	public void setNomArt(String nomArt) {
		this.nomArt = nomArt;
	}

	public int getArtPre() {
		return artPre;
	}

	public void setArtPre(int artPre) {
		this.artPre = artPre;
	}

	public int getCantArt() {
		return cantArt;
	}

	public void setCantArt(int cantArt) {
		this.cantArt = cantArt;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	

}
