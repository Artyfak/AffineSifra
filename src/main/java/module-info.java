module com.afsifra.affinesifra {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.afsifra.affinesifra to javafx.fxml;
    exports com.afsifra.affinesifra;
}