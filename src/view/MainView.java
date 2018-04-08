package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainView extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader load = new FXMLLoader();
			load.setLocation(this.getClass().getResource("/controller/Okno.fxml"));
			AnchorPane stackPane = load.load();
			
			primaryStage.setTitle("PomorskiFutbol");
			
			//Image applicationIcon = new Image("file:soccer.png");
			//primaryStage.getIcons().add(applicationIcon);
			
			Scene scene = new Scene(stackPane);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
