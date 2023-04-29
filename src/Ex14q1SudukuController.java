//import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.scene.layout.Border;
//import javafx.scene.layout.GridPane;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;

public class Ex14q1SudukuController {
	
	@FXML
	private final int BLOCK_DIM = 3;
	@FXML
	private final int BLOCKS_AMOUNT = 3;
	
    @FXML
    private ResourceBundle resources;
    
    @FXML
    private AnchorPane UpPane;
    
    @FXML
    private GridPane gridPane;

    @FXML
    private Button clearBtn;

    @FXML
    private Button setBtn;
    
    @FXML
    private TextField[][] cells_text;
    
    @FXML
  	private String[][] cells;    
    
    @FXML
    public void initialize(){
        assert UpPane != null : "fx:id=\"UpPane\" was not injected: check your FXML file 'Ex14q1Suduku.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'Ex14q1Suduku.fxml'.";
        assert setBtn != null : "fx:id=\"setBtn\" was not injected: check your FXML file 'Ex14q1Suduku.fxml'.";
        
    	cells_text = new TextField[(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)][(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)];
        cells = new String[(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)][(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)];
        
        
		for (int i = 0; i < BLOCK_DIM*BLOCKS_AMOUNT; i++) {
			for (int j = 0; j < BLOCK_DIM*BLOCKS_AMOUNT; j++) {
				System.out.println(""+(i+1)+","+(j+1));
				cells_text[i][j] = new TextField(""+(i+1)+","+(j+1));
				cells_text[i][j].setPrefSize(gridPane.getPrefWidth() / 9, gridPane.getPrefHeight() / 9);
				
				//cells_text[i][j].setOnAction(new EventHandler<ActionEvent>() {
			    	//@Override public void handle(ActionEvent e) {
			    		//handleButton(e);
			   // }
			
				gridPane.add(cells_text[i][j], i, j);
				cells_text[i][j].setText("   ");
				cells_text[i][j].centerShapeProperty();
				cells_text[i][j].setCenterShape(true);
				//cells_text[i][j].setBorder(new Border());
			}
		}
    	
    }
    
	@FXML
	void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (d.isLegal(textF.getText()))
				JOptionPane.showConfirmDialog(null, "Legal word", "Legal word", JOptionPane.CLOSED_OPTION);
			else
				JOptionPane.showConfirmDialog(null, "Illegal word", "Illegal word", JOptionPane.CLOSED_OPTION);
		}
		textF.setText("");
	}

    @FXML
    void onActionClearBtn(ActionEvent event) {

    }

    @FXML
    void onActionSetBtn(ActionEvent event) {

    }

}
