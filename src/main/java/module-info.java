module org.example.bibliotecalivros {
    requires javafx.controls;
    requires javafx.fxml;


    opens oi.github.mthsilva98 to javafx.fxml;
    exports oi.github.mthsilva98;
    exports oi.github.mthsilva98.controller;
    opens oi.github.mthsilva98.controller to javafx.fxml;
}