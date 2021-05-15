import java.sql.Date;

//package com.ti2cc;

public class Anotacao {
    private int psicologo_id;
    private int paciente_id;
    private String titulo;
    private String descricao;
    private Date criacao;
	
	public Anotacao() {
		this.psicologo_id = -1;
		this.paciente_id = -1;
		this.titulo = "";
		this.descricao = "";
		this.criacao = new Date();
	}
	
	public Anotacao(
        int psicologo_id,
        int paciente_id,
        String titulo,
        String descricao,
        Date criacao
    ) {
		this.psicologo_id = psicologo_id;
		this.paciente_id = paciente_id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.criacao = criacao;
	}

    public int getPsicologoId() { return this.psicologo_id; }
    public int getPacienteId() { return this.paciente_id; }
    public String getTitulo() { return this.titulo; }
    public String getDescricao() { return this.descricao; }
    public Date getCriacao() { return this.criacao; }

    public void setPsicologoId(int value) { this.psicologo_id = value; }
    public void setPacienteId(int value) { this.paciente_id = value; }
    public void setTitulo(String value) { this.titulo = value; }
    public void setDescricao(String value) { this.descricao = value; }
    public void setCriacao(Date value) { this.criacao = value; }

	@Override
	public String toString() {
		return "Anotacao("+this.psicologo_id+", "+this.paciente_id+", "+this.titulo+", "+this.descricao+", "+this.criacao+")";
	}
	
}
