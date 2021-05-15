import java.sql.Date;

//package com.ti2cc;

public class PacienteContato {
    private int id;
    private int tipo_contato_id;
    private String dado;
	
	public Anotacao() {
		this.id = -1;
		this.tipo_contato_id = -1;
		this.dado = "";
	}
	
	public Anotacao(
        int id,
        int tipo_contato_id,
        String dado
    ) {
		this.id = id;
		this.tipo_contato_id = tipo_contato_id;
		this.dado = dado;
	}

    public int getPacienteId() { return this.id; }
    public int getTipoContatoId() { return this.tipo_contato_id; }
    public String getDado() { return this.dado; }

    public void setPacienteId(int value) { this.id = value; }
    public void setTipoContatoId(int value) { this.tipo_contato_id = value; }
    public void setDado(String value) { this.dado = value; }

	@Override
	public String toString() {
		return "PacienteContato("+this.id+", "+this.tipo_contato_id+", "+this.dado+")";
	}
	
}
