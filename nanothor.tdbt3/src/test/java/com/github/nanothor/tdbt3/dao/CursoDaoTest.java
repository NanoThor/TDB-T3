package com.github.nanothor.tdbt3.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.Test;

import com.github.nanothor.tdbt3.entity.Curso;

public class CursoDaoTest {

	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testListar() throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/mem:tbd", "root", "root");
		CursoDao cd = new CursoDao(conn);
		System.out.println(cd.listar());

	}

	@Test
	public void testSalvar() throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/mem:tbd", "root", "root");
		CursoDao cd = new CursoDao(conn);
		Curso curso = new Curso();
		curso.setCargaHoraria(450);
		curso.setCodigo(20);
		curso.setDescricao("Pedagogia");
		cd.salvar(curso);
		System.out.println(cd.listar());
	}

}
