import java.util.Date;
import java.text.SimpleDateFormat;

//package com.ti2cc;

public class Psicologo {
    private int id;
    private int anonimo;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
    private String sobre;
    private String sobre_trabalho;
    private Date criacao;
    private Date nascimento;
    private String sexo;
    private String telefone;
    private String cep;
    private String valor;
    private String senha;
    private String senha2;
    private String CRP;
	
	public Psicologo() {
        SimpleDateFormat br_format = new SimpleDateFormat("dd/MM/yyyy");
        Date setar_data = br_format.parse("01/01/0001");

		this.id = -1;
		this.anonimo = -1;
		this.cpf = "";
		this.nome = "";
		this.sobrenome = "";
		this.email = "";
		this.sobre = "";
        this.sexo = "";
        this.sobre_trabalho = "";
        this.telefone = "";
        this.cep = "";
        this.valor = "";
        this.senha = "";
        this.senha2 = "";
		this.criacao = setar_data;
		this.nascimento = setar_data;
        this.CRP = "";
	}
	
	public Psicologo(
        int id,
        String nome,
        String sobrenome,
        int anonimo,
        String Sexo,
        String telefone,
        String cep,
        String valor,
        String senha,
        String senha2,
        String cpf,
        String email,
        String sobre,
        String sobre_trabalho,
        Date criacao,
        Date nascimento,
        String CRP
    ) {
		this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
		this.anonimo = anonimo;
        this.sexo = Sexo;
        this.telefone = telefone;
        this.cep = cep;
        this.valor = valor;
        this.senha = senha;
        this.senha2 = senha2;
		this.cpf = cpf;
		this.email = email;
		this.sobre = sobre;
        this.sobre_trabalho = sobre_trabalho;
		this.criacao = criacao;
		this.nascimento = nascimento;
        this.CRP = CRP;
	}

    public int getId() { return this.id; }
    public int getAnonimo() { return this.anonimo; }
    public String getCpf() { return this.cpf; }
    public String getNome() { return this.nome; }
    public String getSobrenome() { return this.sobrenome; }
    public String getEmail() { return this.email; }
    public String getSobre() { return this.sobre; }
    public String getSobre_trabalho() { return this.sobre_trabalho; }
    public Date getCriacao() { return this.criacao; }
    public Date getNascimento() { return this.nascimento; }
    public String getSexo() {return this.sexo;}
    public String getTelefone() {return this.telefone;}
    public String getCEP() {return this.cep;}
    public String getSenha() {return this.senha;}
    public String getSenha2() {return this.senha2;}
    public String getValor() {return this.valor;}
    public String getCRP() {return this.CRP;}

    public void setId(int value) { this.id = value; }
    public void setAnonimo(int value) { this.anonimo = value; }
    public void setCpf(String value) { this.cpf = value; }
    public void setNome(String value) { this.nome = value; }
    public void setSobenome(String value) { this.sobrenome = value; }
    public void setEmail(String value) { this.email = value; }
    public void setSobre(String value) { this.sobre = value; }
    public void setSobre_trabalho(String value) { this.sobre_trabalho = value; }
    public void setCriacao(Date value) { this.criacao = value; }
    public void setNascimento(Date value) { this.nascimento = value; }
    public char setSexo(String value) {this.sexo = value;}
    public int setTelefone(String value) {this.telefone = value;}
    public int setCEP(String value) {this.cep = value;}
    public String setSenha(String value) {this.senha = value;}
    public String setSenha2(String value) {this.senha2 = value;}
    public String setValor(String value) {this.valor = value;}
    public String setCRP(String value) {this.CRP = value;}

	@Override
	public String toString() {
		return "Psicologo("+this.id+", "+this.anonimo+", "+this.cpf+", "+
            this.nome+", "+this.sobrenome+", "+this.email+", "+
            this.sobre+", "+this.criacao+", "+this.nascimento+")";
	}
	
}
