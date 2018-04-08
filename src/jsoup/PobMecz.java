package jsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import model.Liga;
import model.Mecz;

public class PobMecz {

	Document dokument;
	//Liga liga = null;
	ArrayList<Mecz> listaMeczow = new ArrayList<Mecz>();
	
	public PobMecz(String adres){

		load(adres);
		//liga = new Liga(nazwaL(), pobLigi());
		listaMeczow = pobLigi();
			
	}
	
//	public Liga getLiga() {
//		return liga;		
//	}
	
	public ArrayList<Mecz> getListaMeczow(){
		return listaMeczow;
	}
	
	private void load(String adres){
		try {
		    this.dokument = Jsoup.connect(adres).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
//	private String nazwaL() {
//		
//		return dokument.title() + "\n";
//	}
	
	private ArrayList<Mecz> pobLigi() {
	
		Elements druzyna1 = dokument.getElementsByClass("druzyna-1");
		Elements druzyna2 = dokument.getElementsByClass("druzyna-2");
		Elements wynik = dokument.getElementsByClass("wynik");
		
		ArrayList<Mecz> ListaMeczow = new ArrayList<>();
		
		for(int i = 0; i<wynik.size(); i++) {
			
			//String wynPom = wynik.get(i).text();	
			if(wynik.get(i).text().length()>3) {
			
			String[] tabWyn = wynik.get(i).text().split(" - ");
			
			Mecz mecz = new Mecz(druzyna1.get(i).text(),druzyna2.get(i).text(),Integer.parseInt(tabWyn[0]), Integer.parseInt(tabWyn[1]));
			
			ListaMeczow.add(mecz);
			}
			
		}
		
		return ListaMeczow;
		
	}
	
	

	
	
	
}
