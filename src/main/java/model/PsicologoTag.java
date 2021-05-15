import java.sql.Date;

//package com.ti2cc;

public class PsicologoTag {
    private int id;
    private int tag_id;
    private int count;
	
	public Anotacao() {
		this.id = -1;
		this.tag_id = -1;
		this.count = "";
	}
	
	public Anotacao(
        int id,
        int tag_id,
        int count
    ) {
		this.id = id;
		this.tag_id = tag_id;
		this.count = count;
	}

    public int getPsicologoId() { return this.id; }
    public int getTipoTagId() { return this.tag_id; }
    public int getCount() { return this.count; }

    public void setPsicologoId(int value) { this.id = value; }
    public void setTipoTagId(int value) { this.tag_id = value; }
    public void setCount(int value) { this.count = value; }

	@Override
	public String toString() {
		return "PsicologoTag("+this.id+", "+this.tag_id+", "+this.count+")";
	}
	
}
