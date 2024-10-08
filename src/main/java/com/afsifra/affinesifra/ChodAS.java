package com.afsifra.affinesifra;

public class ChodAS {

    private int [] poleC = new int[26];
    private char [] poleP = new char[26];
    public int a, b;
    public String povodnyText;
// naplnenie polí
    public ChodAS() {
        int i = 0;
        for (char znak = 'A'; znak <= 'Z'; znak++) {
            poleC[i] = i;
            poleP[i] = znak;
            i++;
        }
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void setPovodnyText(String povodnyText) {
        this.povodnyText = povodnyText;
    }
// vrati cislo na základe písmena
    public int getCislo(char pismeno) {
        int vysledok = 0;
        for (int i = 0; i < 26; i++) {
            if (poleP[i] == pismeno) vysledok = i;
        }
        return vysledok;
    }
// ošetrenie šifry o makšene a dĺžne
    public void upravaZ(){
        povodnyText = povodnyText.toLowerCase();
        povodnyText = povodnyText.replaceAll("á","A");
        povodnyText = povodnyText.replaceAll("ď","D");
        povodnyText = povodnyText.replaceAll("č","C");
        povodnyText = povodnyText.replaceAll("é","E");
        povodnyText = povodnyText.replaceAll("ě","E");
        povodnyText = povodnyText.replaceAll("í","I");
        povodnyText = povodnyText.replaceAll("ň","N");
        povodnyText = povodnyText.replaceAll("ó","O");
        povodnyText = povodnyText.replaceAll("ř","R");
        povodnyText = povodnyText.replaceAll("š","S");
        povodnyText = povodnyText.replaceAll("ť","T");
        povodnyText = povodnyText.replaceAll("ú","U");
        povodnyText = povodnyText.replaceAll("ů","U");
        povodnyText = povodnyText.replaceAll("ý","Y");
        povodnyText = povodnyText.replaceAll("ž","Z");
        povodnyText = povodnyText.toUpperCase();
        povodnyText = povodnyText.replaceAll("\\p{Punct}", "");
    }
// ošetrenie medzery a čísla
    public void opravaMedzeraACisla(){
        povodnyText = povodnyText.replaceAll(" ","XMEZERAX");
        povodnyText = povodnyText.replaceAll("1","XONEX");
        povodnyText = povodnyText.replaceAll("2","XTWOX");
        povodnyText = povodnyText.replaceAll("3","XTHREEX");
        povodnyText = povodnyText.replaceAll("4","XFOURX");
        povodnyText = povodnyText.replaceAll("5","XFIVEX");
        povodnyText = povodnyText.replaceAll("6","XSIXX");
        povodnyText = povodnyText.replaceAll("7","XSEVENX");
        povodnyText = povodnyText.replaceAll("8","XEIGHTX");
        povodnyText = povodnyText.replaceAll("9","XNINEX");
        povodnyText = povodnyText.replaceAll("0","XZEROX");
    }
// šifrovnie už upraveného textu
    public String sifrovanie(String apt){
        String resultBezM = "";
        String resultSM = "";
        for (int i = 0; i < apt.length(); i++) {
            if (povodnyText.charAt(i) >= 'A' && povodnyText.charAt(i) <= 'Z') {
                resultBezM = resultBezM + poleP[((a * getCislo(apt.charAt(i)) + b) % 26)];
            }
        }
        for (int j = 0; j < resultBezM.length(); j++) {
            if (j % 5 == 0 && (j != 0)) {
                resultSM = resultSM + " ";
                resultSM = resultSM + resultBezM.charAt(j);
            } else {
                resultSM = resultSM + resultBezM.charAt(j);
            }
        }
        return resultSM;
    }
//dekódovanie textu sposobom prechodu po texte
    public String decodovanie(String input) {
        String result = "";
        input = input.replaceAll(" ","");

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                int a = ((((input.charAt(i) - 'A' - this.b) * inModulo(this.a)) % 26) + 26) % 26;
                result = result + poleP[a];
            }
        }

        result = result.replaceAll("XMEZERAX", " ");
        result = result.replaceAll("XONEX", "1");
        result = result.replaceAll("XTWOX", "2");
        result = result.replaceAll("XTHREEX", "3");
        result = result.replaceAll("XFOURX", "4");
        result = result.replaceAll("XFIVEX", "5");
        result = result.replaceAll("XSIXX", "6");
        result = result.replaceAll("XSEVENX", "7");
        result = result.replaceAll("XEIGHTX", "8");
        result = result.replaceAll("XNINEX", "9");
        result = result.replaceAll("XZEROX", "0");
        return result;
    }
// inverzné modulo vyuzitie pre desifrovanie
    public int inModulo(int keyA) {
        keyA = keyA % 26;
        for (int i = 0; i < 26; i++) {
            if ((keyA * i) % 26 == 1) return i;
        }
        return -1;
    }
}
