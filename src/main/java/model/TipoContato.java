import java.sql.Date;

//package com.ti2cc;

public class TipoContato {
    private int id;
    private String nome;
	
	public Anotacao() {
		this.id = -1;
		this.nome = "";
	}
	
	public Anotacao(
        int id,
        String nome
    ) {
		this.id = id;
		this.nome = nome;
	}

    public int getId() { return this.id; }
    public String getNome() { return this.nome; }

    public void setId(int value) { this.id = value; }
    public void setNome(String value) { this.nome = value; }

	@Override
	public String toString() {
		return "TipoContato("+this.id+", "+this.nome+")";
	}
	
}
