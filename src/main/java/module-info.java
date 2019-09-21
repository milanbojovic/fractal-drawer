module tk.milanbojovic {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    opens tk.milanbojovic to javafx.fxml;
    exports tk.milanbojovic;
}