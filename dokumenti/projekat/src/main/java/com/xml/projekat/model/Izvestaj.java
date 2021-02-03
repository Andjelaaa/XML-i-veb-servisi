package com.xml.projekat.model;

import com.xml.projekat.dto.IzvestajDTO;

public class Izvestaj {
	private String godina;
	private String brPodnetihZahteva;
	private String brOdbijenihZahteva;
	private String brZalbi;
	
	
	
	
	public Izvestaj(IzvestajDTO izvestaj) {
		super();
		this.godina = izvestaj.getGodina();
		this.brOdbijenihZahteva = izvestaj.getBrOdbijenihZahteva();
		this.brPodnetihZahteva = izvestaj.getBrPodnetihZahteva();
		this.brZalbi = izvestaj.getBrZalbi();
	}
	
	public Izvestaj() {
		super();
	}
	public Izvestaj(String godina, String brPodnetihZahteva, String brOdbijenihZahteva, String brZalbi) {
		super();
		this.godina = godina;
		this.brPodnetihZahteva = brPodnetihZahteva;
		this.brOdbijenihZahteva = brOdbijenihZahteva;
		this.brZalbi = brZalbi;
	}
	public String getGodina() {
		return godina;
	}
	public void setGodina(String godina) {
		this.godina = godina;
	}
	public String getBrPodnetihZahteva() {
		return brPodnetihZahteva;
	}
	public void setBrPodnetihZahteva(String brPodnetihZahteva) {
		this.brPodnetihZahteva = brPodnetihZahteva;
	}
	public String getBrOdbijenihZahteva() {
		return brOdbijenihZahteva;
	}
	public void setBrOdbijenihZahteva(String brOdbijenihZahteva) {
		this.brOdbijenihZahteva = brOdbijenihZahteva;
	}
	public String getBrZalbi() {
		return brZalbi;
	}
	public void setBrZalbi(String brZalbi) {
		this.brZalbi = brZalbi;
	}
	
	
}
