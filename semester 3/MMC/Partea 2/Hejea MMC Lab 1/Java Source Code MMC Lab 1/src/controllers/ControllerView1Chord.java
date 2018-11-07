package controllers;

import app.components.Methods;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.text.DecimalFormat;

public class ControllerView1Chord {
    
    @FXML
    public TextField idFieldA;
    @FXML
    public TextField idFieldB;
    @FXML
    public TextField idFieldEps;
    @FXML
    public TextField idFieldMaxItr;
    @FXML
    public TextField idFieldMaxItr1;
    @FXML
    public Button    idBtnCalculeaza;
    @FXML
    public Button    idBtnCalculeaza1;
    @FXML
    public VBox      idContainer0;
    @FXML
    public VBox      idContainer1;
    @FXML
    public VBox      idOuterContainer;
    @FXML
    public VBox      idOuterContainer1;
    
    public Methods methods;
    
    @FXML
    public void initialize() {
        System.out.println("Tab2 starts");
        
        ControllerMain.outerContainer = idOuterContainer;
        ControllerMain.outerContainer1 = idOuterContainer1;
        
        idFieldA.setText(String.valueOf(0.1));
        idFieldB.setText(String.valueOf(1));
        idFieldEps.setText(String.valueOf(1.0E-10));
        idFieldMaxItr.setText(String.valueOf(100));
        idFieldMaxItr1.setText(String.valueOf(100));
        
        methods = new Methods();
        
        callChord();
        callSecant();
    }
    
    public void setData() {
        
        try {
            methods.a = Double.valueOf(idFieldA.getText());
            methods.b = Double.valueOf(idFieldB.getText());
            methods.eps = Double.valueOf(idFieldEps.getText());
            methods.nrItrMaxChord = Integer.valueOf(idFieldMaxItr.getText());
            methods.nrItrMaxSecant = Integer.valueOf(idFieldMaxItr1.getText());
        } catch (NumberFormatException e) {System.out.println(e.toString());}
    }
    
    public void syncData(VBox where, int i, double[] valueOf_x, double[] valueOf_Fx) {
        DecimalFormat df = new DecimalFormat(stringPatern());
        
        where.getChildren().clear();
        
        TextField iteration;
        TextField valueX;
        TextField valueXPrecision;
        TextField valueFx;
        HBox      box;
        String    s;
        
        s = String.valueOf(df.format(valueOf_x[i]));
        //s = s.substring(0, s.length() - 1);
        
        iteration = new TextField(String.valueOf(i + 1));
        valueX = new TextField(String.format("%.20f", valueOf_x[i]));
        valueXPrecision = new TextField(s);
        valueFx = new TextField(String.format("%.20f", valueOf_Fx[i]));
        
        
        Font font = Font.font("System", FontWeight.MEDIUM, 14);
        
        int s1 = 150, s2 = 200;
        iteration.setPrefWidth(75);
        valueX.setPrefWidth(s2);
        valueXPrecision.setPrefWidth(s1);
        valueFx.setPrefWidth(s2);
        
        iteration.setFont(font);
        valueX.setFont(font);
        valueXPrecision.setFont(font);
        valueFx.setFont(font);
        
        box = new HBox();
        box.getChildren().addAll(iteration, valueX, valueXPrecision, valueFx);
        where.getChildren().add(box);
    }
    
    public void callChord() {
        setData();
        methods.runChord();
        syncData(idContainer0, methods.nrItrC, methods.xC, methods.fxC);
    }
    
    public void callSecant() {
        setData();
        methods.runSecant();
        syncData(idContainer1, methods.nrItrS, methods.xS, methods.fxS);
    }
    
    private int countEpsDigits() {
        int    i = 0;
        double a = methods.eps;
        while (a < 1) {
            i++;
            a *= 10;
        }
        return i;
    }
    
    
    private String stringPatern() {
        String patern = "#.";
        int    n      = countEpsDigits();
        for (int i = 0; i < n + 1; i++) {
            patern = patern + "#";
        }
        return patern;
    }
    
}
