package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.create;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import jsoup.PobLigi;
import model.Mecz;

public class MainController implements Initializable {

	
	@FXML TreeView<String> treeView;
	@FXML TableView<Mecz> table; 
	@FXML TableColumn<Mecz,String> dr1;
	@FXML TableColumn<Mecz,Integer> wyn1;
	@FXML TableColumn<Mecz,Integer> wyn2;
	@FXML TableColumn<Mecz,String> dr2;
    int tab[] = {0,0,0,0,0,0,0,0,0,0,0};
	
	PobLigi l = new PobLigi();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		l.getListaLig().get(1).wczytajMecze();

		TreeItem<String> Listalig = new TreeItem<String>("2018");
		

		Listalig.setExpanded(true);
			
		for(int i = 0; i < l.getListaLig().size(); i++) {
			TreeItem<String> xd = new TreeItem<String>(l.getListaLig().get(i).getNazwa());
			Listalig.getChildren().add(xd);
			
		}
	
		treeView.setRoot(Listalig);
		table.setVisible(false);
		
		treeView.setCellFactory(tree -> {
	            TreeCell<String> cell = new TreeCell<String>() {
	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty) ;
	                    if (empty) {
	                        setText(null);
	                    } else {
	                        setText(item);
	                    }
	                }
	            };
	            cell.setOnMouseClicked(event -> {
	                if (! cell.isEmpty()) {
	                    TreeItem<String> Item = cell.getTreeItem();

	                    int i = 0;
	                    while(Item.getValue()!= l.getListaLig().get(i).getNazwa()) {
	                    	i++;
	                    	
	                    }
	                    
	                    ArrayList<Mecz> mecz;

	                    ObservableList<Mecz> lista = FXCollections.observableArrayList(
	                			mecz=l.getListaLig().get(i).wczytajMecze()
	                			);

	                    
	                    if(tab[i]==0) {
	                    create cr = new create(l.getListaLig().get(i).getNazwa(), mecz);
	                    tab[i]=1;
	                }
	                    
               
	                    table.setItems(lista);
	                    table.setVisible(true);
	                    

	                }
	            });
	            return cell ;
	        });
		 

		 dr1.setCellValueFactory(new PropertyValueFactory<Mecz, String>("dr1"));
		 dr2.setCellValueFactory(new PropertyValueFactory<Mecz, String>("dr2"));
		 wyn1.setCellValueFactory(new PropertyValueFactory<Mecz, Integer>("wynik1"));
		 wyn2.setCellValueFactory(new PropertyValueFactory<Mecz, Integer>("wynik2"));
		 
		 
		
	}
	
	
	

}
