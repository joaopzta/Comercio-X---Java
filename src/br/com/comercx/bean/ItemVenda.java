package br.com.comercx.bean;

public class ItemVenda {
	
	private int id;
	private int idVenda;
	private  String desc;
	private double valUnit;
	private int qtd;
	private double subTot;
	
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getValUnit() {
		return valUnit;
	}
	public void setValUnit(double valUnit) {
		this.valUnit = valUnit;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getSubTot() {
		return subTot;
	}
	public void setSubTot(double subTot) {
		this.subTot = subTot;
	}

}
