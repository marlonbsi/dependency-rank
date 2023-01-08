package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReqNlpDao {
	
	Connection con = null;
	
	public void insereProjeto(String titulo){
		try {
			con = new ConnectionFactory().getConnection();
			String sql = "insert into tb_projeto" +
                    " (titulo_projeto)" +
                    " values (?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, titulo);
            
            stmt.execute();
            stmt.close();

            System.out.println("Gravado!");

            con.close();
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Projeto> listAll(){
		List<Projeto> projetos = new ArrayList<Projeto>();
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from tb_projeto");

		    // executa um select
		    ResultSet rs = stmt.executeQuery();

		    // itera no ResultSet
		    while (rs.next()) {
		    	Projeto p = new Projeto();
		    	Integer id = rs.getInt("id_projeto");
		        String titulo = rs.getString("titulo_projeto");
		        p.setIdProjeto(id);
		        p.setTituloProjeto(titulo);
		        projetos.add(p);
		    }

		    stmt.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projetos;
	}
	
	public Projeto returnProjeto(Integer id){
		Projeto p = new Projeto();
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from tb_projeto where id_projeto = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			// itera no ResultSet
		    while (rs.next()) {
		    	Integer idProjeto = rs.getInt("id_projeto");
		        String titulo = rs.getString("titulo_projeto");
		        p.setIdProjeto(id);
		        p.setTituloProjeto(titulo);
		    }

		    stmt.close();
		    con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

}
