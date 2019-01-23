package br.com.comercx.bean;

import java.util.Date;

public class Venda {
	
	private int num;
	private double total;
	private String situac;
	private int idCli;
	private Date data;
	private String cliNome;
 
	public String getCliNome() {
		return cliNome;
	}
	public void setCliNome(String cliNome) {
		this.cliNome = cliNome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getSituac() {
		return situac;
	}
	public void setSituac(String situac) {
		this.situac = situac;
	}
	public int getIdCli() {
		return idCli;
	}
	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}

}
