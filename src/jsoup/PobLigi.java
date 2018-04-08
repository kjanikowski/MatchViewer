package jsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import model.Liga;

public class PobLigi {

	Document dokument; 
	String adres="http://www.pomorskifutbol.pl/";
	ArrayList<Liga> lista = new ArrayList<Liga>();
	
	public PobLigi(){
		load();
		listaLig();
	}
	
	
	
	
	
	public ArrayList<Liga> getListaLig() {
		return lista;
	}
	
	
	private void load(){
		try {
		    this.dokument = Jsoup.connect(adres).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	
	private ArrayList<Liga> listaLig() {
		
		Elements spisLig = dokument.select("a");
		
		for(int i = 0; i<spisLig.size(); i++) {
		
		String absHref = spisLig.get(i).attr("abs:href"); // "http://jsoup.org/"
		
		if(absHref.contains("liga.php")) {
			
			Liga nowa = new Liga(spisLig.get(i).text(), absHref);
			lista.add(nowa);
			
			//System.out.println(absHref);
			//System.out.print(spisLig.get(i).text());
		}
		
		}
		
		return lista;
		
	}
	
	
	
}
