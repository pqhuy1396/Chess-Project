/**
 * The main module of the chess application.
 */
module chess {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    exports chess.controller;
    opens chess.controller;
}


