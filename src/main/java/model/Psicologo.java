import java.sql.Date;

//package com.ti2cc;

public class Psicologo {
    private int id;
    private int valor;
    private String cpf;
    private String apelido;
    private String nome;
    private String sobenome;
    private String email;
    private String sobre;
    private String trabalho;
    private Date criacao;
    private Date nascimento;
	
	public Psicologo() {
		this.id = -1;
		this.valor = -1;
		this.cpf = "";
		this.apelido = "";
		this.nome = "";
		this.sobenome = "";
		this.email = "";
		this.sobre = "";
		this.trabalho = "";
		this.criacao = new Date();
		this.nascimento = new Date();
	}
	
	public Psicologo(
        int id,
        int valor,
        String cpf,
        String apelido,
        String nome,
        String sobenome,
        String email,
        String sobre,
        String trabalho,
        Date criacao,
        Date nascimento
    ) {
		this.id = id;
		this.valor = valor;
		this.cpf = cpf;
		this.apelido = apelido;
		this.nome = nome;
		this.sobenome = sobenome;
		this.email = email;
		this.sobre = sobre;
		this.trabalho = trabalho;
		this.criacao = criacao;
		this.nascimento = nascimento;
	}

    public int getId() { return this.id; }
    public int getValor() { return this.valor; }
    public String getCpf() { return this.cpf; }
    public String getApelido() { return this.apelido; }
    public String getNome() { return this.nome; }
    public String getSobenome() { return this.sobenome; }
    public String getEmail() { return this.email; }
    public String getSobre() { return this.sobre; }
    public String getTrabalho() { return this.trabalho; }
    public Date getCriacao() { return this.criacao; }
    public Date getNascimento() { return this.nascimento; }

    public void setId(int value) { this.id = value; }
    public void setValor(int value) { this.valor = value; }
    public void setCpf(String value) { this.cpf = value; }
    public void getApelido(String value) { this.apelido = value; }
    public void getNome(String value) { this.nome = value; }
    public void getSobenome(String value) { this.sobenome = value; }
    public void getEmail(String value) { this.email = value; }
    public void getSobre(String value) { this.sobre = value; }
    public void getTrabalho(String value) { this.trabalho = value; }
    public void getCriacao(Date value) { this.criacao = value; }
    public void getNascimento(Date value) { this.nascimento = value; }

	@Override
	public String toString() {
		return "Psicologo("+this.id+", "+this.valor+", "+this.cpf+", "+
            this.apelido+", "+this.nome+", "+this.sobenome+", "+this.email+", "+
            this.sobre+", "+this.trabalho+", "+this.criacao+", "+this.nascimento+")";
	}
	
}
