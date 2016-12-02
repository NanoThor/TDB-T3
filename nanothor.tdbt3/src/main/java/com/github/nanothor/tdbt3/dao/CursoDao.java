package com.github.nanothor.tdbt3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.github.nanothor.tdbt3.entity.Curso;

public class CursoDao {
	private Connection connection;

	public CursoDao(Connection connection) {
		this.connection = connection;
	}

	public List<Curso> listar() throws Exception {
		List<Curso> res = new ArrayList<>();
		Curso curso;

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM cursos");
		while (rs.next()) {
			curso = new Curso();

			int cod = rs.getInt("codigo");
			int chr = rs.getInt("carga_horaria");
			String desc = rs.getString("descricao");

			curso.setCodigo(cod);
			curso.setCargaHoraria(chr);
			curso.setDescricao(desc);

			res.add(curso);
		}

		return res;
	}

	public void salvar(Curso curso) throws Exception {
		String sql = "INSERT INTO cursos (codigo, descricao, carga_horaria) VALUES (?, ?, ?)";
		
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, curso.getCodigo());
		pst.setString(2, curso.getDescricao());
		pst.setInt(3, curso.getCargaHoraria());
		pst.execute();
		
		pst.close();
	}
}
