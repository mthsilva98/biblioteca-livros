module org.example.bibliotecalivros {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Abre o pacote `oi.github.mthsilva98.model` para o módulo `javafx.base`
    opens oi.github.mthsilva98.model to javafx.base;
    opens oi.github.mthsilva98 to javafx.fxml;
    opens oi.github.mthsilva98.controller to javafx.fxml;

    exports oi.github.mthsilva98;
    exports oi.github.mthsilva98.controller;
}
