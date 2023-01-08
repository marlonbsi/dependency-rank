package jdbc;

public class Projeto {
	
	private Integer idProjeto;
	private String tituloProjeto;
	
	public Integer getIdProjeto() {
		return idProjeto;
	}
	
	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
	}
	
	public String getTituloProjeto() {
		return tituloProjeto;
	}
	
	public void setTituloProjeto(String tituloProjeto) {
		if(tituloProjeto.length() > 60 || tituloProjeto.length() < 3){
			System.out.println("String fora dos limites do campo -título do projeto-");
		} else{
			this.tituloProjeto = tituloProjeto;
		}
	}
	
	public String toString(){
		return "Id: " + this.getIdProjeto() + " - " + this.getTituloProjeto();
	}
	
}
