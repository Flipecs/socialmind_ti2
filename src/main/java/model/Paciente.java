import java.sql.Date;

//package com.ti2cc;

public class Paciente {
    private int id;
    private int anonimo;
    private String cpf;
    private String apelido;
    private String nome;
    private String sobenome;
    private String email;
    private String sobre;
    private Date criacao;
    private Date nascimento;
	
	public Paciente() {
		this.id = -1;
		this.anonimo = -1;
		this.cpf = "";
		this.apelido = "";
		this.nome = "";
		this.sobenome = "";
		this.email = "";
		this.sobre = "";
		this.criacao = new Date();
		this.nascimento = new Date();
	}
	
	public Paciente(
        int id,
        int anonimo,
        String cpf,
        String apelido,
        String nome,
        String sobenome,
        String email,
        String sobre,
        Date criacao,
        Date nascimento
    ) {
		this.id = id;
		this.anonimo = anonimo;
		this.cpf = cpf;
		this.apelido = apelido;
		this.nome = nome;
		this.sobenome = sobenome;
		this.email = email;
		this.sobre = sobre;
		this.criacao = criacao;
		this.nascimento = nascimento;
	}

    public int getId() { return this.id; }
    public int getAnonimo() { return this.anonimo; }
    public String getCpf() { return this.cpf; }
    public String getApelido() { return this.apelido; }
    public String getNome() { return this.nome; }
    public String getSobenome() { return this.sobenome; }
    public String getEmail() { return this.email; }
    public String getSobre() { return this.sobre; }
    public Date getCriacao() { return this.criacao; }
    public Date getNascimento() { return this.nascimento; }

    public void setId(int value) { this.id = value; }
    public void setAnonimo(int value) { this.anonimo = value; }
    public void setCpf(String value) { this.cpf = value; }
    public void getApelido(String value) { this.apelido = value; }
    public void getNome(String value) { this.nome = value; }
    public void getSobenome(String value) { this.sobenome = value; }
    public void getEmail(String value) { this.email = value; }
    public void getSobre(String value) { this.sobre = value; }
    public void getCriacao(Date value) { this.criacao = value; }
    public void getNascimento(Date value) { this.nascimento = value; }

	@Override
	public String toString() {
		return "Paciente("+this.id+", "+this.anonimo+", "+this.cpf+", "+
            this.apelido+", "+this.nome+", "+this.sobenome+", "+this.email+", "+
            this.sobre+", "+this.criacao+", "+this.nascimento+")";
	}
	
}
