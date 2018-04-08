package model;

import java.util.ArrayList;

import jsoup.PobMecz;

public class Liga {
		
	String nazwa;
	String link;
	ArrayList<Mecz> listaMeczow = new ArrayList<Mecz>();
	
	public Liga(String nazwa, ArrayList<Mecz> listaMeczow){
		this.nazwa=nazwa;
		this.listaMeczow=listaMeczow;
	}
	
	public Liga(String nazwa, String link) {
		this.nazwa=nazwa;
		this.link=link;
		this.listaMeczow=null;
	}
	
	public ArrayList<Mecz> wczytajMecze(){
		PobMecz pobMecze = new PobMecz(this.link);
		return pobMecze.getListaMeczow();
	}

	public ArrayList<Mecz> getLigaa(){
		return listaMeczow;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
}
