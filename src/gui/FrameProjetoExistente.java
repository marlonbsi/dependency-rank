package gui;

import jdbc.Projeto;
import jdbc.ReqNlpDao;

public class FrameProjetoExistente {
	
	Projeto projeto;
	
	public FrameProjetoExistente(Integer idProjeto){
		ReqNlpDao dao = new ReqNlpDao();
		projeto = dao.returnProjeto(idProjeto);
		System.out.println(projeto.toString());
	}

}
