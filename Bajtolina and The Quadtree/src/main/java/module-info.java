module com.example.unnamed_rouglike_game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jdk.accessibility;

    opens com.example.unnamed_roguelike_game to javafx.fxml;
    exports com.example.unnamed_roguelike_game;
}