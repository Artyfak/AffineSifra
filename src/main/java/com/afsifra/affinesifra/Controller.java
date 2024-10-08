package com.afsifra.affinesifra;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    ChodAS as = new ChodAS();

    @FXML
    private TextArea TextD;

    @FXML
    private TextArea TextZT;

    @FXML
    private TextArea TextZaT;

    @FXML
    private TextField kA;

    @FXML
    private TextField kB;

    @FXML
    private TextField pText;

    @FXML
    private TextField sText;

    @FXML
    private TextField sifalphabet;

    @FXML
    private TextField alphabet;
    // výpočet pre najvačší spoločný delitel
    int gcd(int paa , int pab) {
        while (pab != 0) {
            int medzi = pab;
            pab = paa % pab;
            paa = medzi;
        }
        return paa;
    }

    String filteredText() {
        as.upravaZ();
        as.opravaMedzeraACisla();

        return as.povodnyText;
    }
    // buttony + kontrola validity klúčov A a B
    @FXML
    boolean handle_btn1() {
        //Kontrola ci je vstup INT
        boolean isInt = true;
        try {
            as.setA(Integer.parseInt(kA.getText()));
        } catch (NumberFormatException e) {
            System.out.println("KeyA is not INT..");
            kA.setText("");
            isInt = false;
        }

        //Kontrola validity kluca
        if (isInt) {
            if (as.getA() > 0 && gcd(as.getA(), 26) == 1 && as.getA() <= 26) {
                System.out.println("KeyA is valid..");
            } else {
                kA.setText("");
                isInt = false;
            }
        }

        return isInt;
    }

    @FXML
    boolean handle_btn2() {
        //Kontrola ci je vstup INT
        boolean isInt = true;
        try {
            as.setB(Integer.parseInt(kB.getText()));
        } catch (NumberFormatException e) {
            System.out.println("KeyB is not INT..");
            kB.setText("");
            isInt = false;
        }

        //Kontrola validity kluca
        if (isInt) {
            if (as.getB() > 0 && as.getB() <= 26) {
                System.out.println("KeyB is valid..");
            } else {
                kB.setText("");
                isInt = false;
            }
        }

        return isInt;
    }
    // vypis Sifrovanje abecedy do textfieldu
    void setSifAlphabet() {
        String input = alphabet.getText();
        as.setPovodnyText(input);
        sifalphabet.setText(as.sifrovanie(input).replaceAll(" ", ""));
    }
    //kontrola oboch buttonov validity hodnot v textfieldoch pre kluče
    boolean checkKeys() {
        if (handle_btn1() && handle_btn2()) {
            setSifAlphabet();
            return true;
        }
        return false;
    }

    @FXML
    void handle_pText() {
        //Zapise text z GUI
        as.setPovodnyText(pText.getText());

        String sif = "";
        String desif = "";

        //Vyfiltruje text a zobrazi v GUI
        String input = filteredText();

        //Sif a desif
        if (checkKeys()) {
            sif = as.sifrovanie(input);
            desif = as.decodovanie(sif);
        } else {
            input = "";
            pText.setText("");
        }

        //Zobrazi v GUI sif a desif
        TextZaT.setText(sif);
        TextD.setText(desif);
        TextZT.setText(input);
    }

    @FXML
    void handle_sText() {
        as.setPovodnyText(sText.getText());

        String input = sText.getText().toUpperCase();
        String desif = "";

        if (checkKeys()) {
            desif = as.decodovanie(input);
        } else {
            sText.setText("");
        }

        TextD.setText(desif);
    }

}
