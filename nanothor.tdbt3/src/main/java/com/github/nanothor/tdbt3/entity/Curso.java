package com.github.nanothor.tdbt3.entity;

public class Curso {
	private int codigo;
	private String descricao;
	private int cargaHoraria;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Curso [codigo=");
		builder.append(codigo);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", cargaHoraria=");
		builder.append(cargaHoraria);
		builder.append("]");
		return builder.toString();
	}

}
