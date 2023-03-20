module com.homework.multilayerprog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.homework.multilayerprog to javafx.fxml;
    exports com.homework.multilayerprog;
}