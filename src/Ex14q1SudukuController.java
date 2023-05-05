//import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.Color;
import java.awt.Insets;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.scene.layout.Border;
//import javafx.scene.layout.GridPane;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
import javafx.scene.layout.CornerRadii;

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
    private int[][]cells_int;
    
    @FXML
    private boolean DEBUG_MODE = false;
    
    
    @FXML
    public void initialize(){
        assert UpPane != null : "fx:id=\"UpPane\" was not injected: check your FXML file 'Ex14q1Suduku.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'Ex14q1Suduku.fxml'.";
        assert setBtn != null : "fx:id=\"setBtn\" was not injected: check your FXML file 'Ex14q1Suduku.fxml'.";
        
    	cells_text = new TextField[(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)][(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)];
    	cells_int = new int[(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)][(BLOCK_DIM*BLOCK_DIM)*(BLOCK_DIM*BLOCK_DIM)];
    	
        
        System.out.println(DEBUG_MODE ? "Initialize":"");
		for (int i = 0; i < BLOCK_DIM*BLOCKS_AMOUNT; i++) {
			for (int j = 0; j < BLOCK_DIM*BLOCKS_AMOUNT; j++) {
				
				cells_text[i][j] = new TextField();
				cells_text[i][j].setPrefSize(gridPane.getPrefWidth() / 9, gridPane.getPrefHeight() / 9);
				cells_text[i][j].setOnAction(new EventHandler<ActionEvent>(){@Override public void handle(ActionEvent e) {handleEnterPressed(e);}});
				gridPane.add(cells_text[i][j], i, j);

			}
		}
    	
    }
    
	

    @FXML
    void onActionClearBtn(ActionEvent event) {
		for (int i = 0; i < BLOCK_DIM*BLOCKS_AMOUNT; i++) {
			for (int j = 0; j < BLOCK_DIM*BLOCKS_AMOUNT; j++) {
				cells_text[i][j].clear();
			}
		}
    }

    @FXML
    void onActionSetBtn(ActionEvent event) {

    }
    
   private static boolean isLegalValue(String val) throws NumberFormatException {
	   try {
		   if(Integer.parseInt(val) <= 0 || Integer.parseInt(val) > 9) {return false;}
	   		else {return true;}
	   }
	   catch(NumberFormatException ex) {
		   return false;
	   }
	   
   }
   
   private void handleEnterPressed(ActionEvent e){
		for (int i = 0; i < BLOCK_DIM*BLOCKS_AMOUNT; i++) {
			for (int j = 0; j < BLOCK_DIM*BLOCKS_AMOUNT; j++) {
				if(cells_text[i][j].getText() != null && cells_text[i][j].getText() != "") {
						if(isLegalValue(cells_text[i][j].getText())) {
							//System.out.println(cells_text[i][j].getText());
							cells_int[i][j] = Integer.parseInt(cells_text[i][j].getText());
							}
					}
				}
			}
		isLegalPositions();
	}
	   
   @FXML
   private boolean isLegalPositions() {
	   
	   int[] bucket = new int[BLOCK_DIM*BLOCKS_AMOUNT + 1]; // Assisting structure
	   int[] bucket_c = new int[BLOCK_DIM*BLOCKS_AMOUNT + 1]; // Assisting structure
	   for (int cell: bucket) cell = 0; // Initializing bucket
	   for (int cell: bucket_c) cell = 0; // Initializing bucket
	   
	   for(int col_lim=0;col_lim< BLOCK_DIM*BLOCKS_AMOUNT;col_lim+=3) {
		   for(int row_lim = 0; row_lim<BLOCK_DIM*BLOCKS_AMOUNT;row_lim+=3) {
		   
			   //Columns
			   System.out.println(DEBUG_MODE? "Columns" : "");
			   for (int cell = 1; cell < BLOCK_DIM*BLOCKS_AMOUNT + 1; cell ++) {bucket[cell] = 0;} //Initializing bucket
			   for (int i = col_lim; i < col_lim+3; i++) {
				   for (int cell = 1; cell < BLOCK_DIM*BLOCKS_AMOUNT + 1; cell ++) {bucket[cell] = 0;} //Initializing bucket
				   for (int j = row_lim; j < row_lim+3; j++) {	
					   bucket[cells_int[i][j]] ++;
				   }
				   if (!isValideBucket(bucket)) {return false;}
				   
			   }
			   
			   //Rows
			   System.out.println(DEBUG_MODE? "Rows" : "");
			   for (int cell = 1; cell < BLOCK_DIM*BLOCKS_AMOUNT + 1; cell ++) {bucket[cell] = 0;} //Initializing bucket
			   for (int i = col_lim; i < col_lim+3; i++) {
				   for (int cell = 1; cell < col_lim+3 + 1; cell ++) {bucket[cell] = 0;} //Initializing bucket
				   for (int j = row_lim; j < row_lim+3; j++) {	
					   bucket[cells_int[j][i]] ++;
				   }
				   if(!isValideBucket(bucket)) {return false;}
				   
			   }

		   }
	   	}
	   	return true;
}
		
	@FXML
	private boolean isValideBucket(int[] bucket) {
		for(int cell = 1; cell < BLOCK_DIM*BLOCKS_AMOUNT + 1; cell ++) {
			if (bucket[cell] > 1) {
				System.out.println(DEBUG_MODE? "Ilegal cell="+cell+" of value="+bucket[cell]: "");
				JOptionPane.showConfirmDialog(null, "Ilegal numbers combination!","Error",JOptionPane.CLOSED_OPTION);
				return false;
				}
			}
		
			
		return true;
	}
	  
			   
	   
  }
		

