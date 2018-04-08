package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mecz {
	
	private SimpleStringProperty dr1;
	private SimpleStringProperty dr2;
	
	private SimpleIntegerProperty wynik1;
	private SimpleIntegerProperty wynik2;
	
	public Mecz(String dr1, String dr2, int wynik1, int wynik2){
		this.dr1= new SimpleStringProperty(dr1);
		this.dr2=new SimpleStringProperty(dr2);
		this.wynik1= new SimpleIntegerProperty(wynik1);
		this.wynik2=new SimpleIntegerProperty(wynik2);
	}
	
	public String getDr1(){
		return dr1.get();
	}
	
	public String getDr2(){
		return dr2.get();
	}
	
	public int getWynik1() {
		return wynik1.get();
	}
	
	public int getWynik2() {
		return wynik2.get();
	}

}
