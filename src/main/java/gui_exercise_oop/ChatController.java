package gui_exercise_oop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ChatController implements Initializable {
    @FXML
    private Button sendBtn;

    @FXML
    private VBox chatbox;

    @FXML
    private TextField messageText;

    

    public void updateChatBox(ActionEvent event) {
        Label label = new Label(messageText.getText());
        label.setId("client");
        label.setPadding(new Insets(3, 10, 3, 10));
        label.setWrapText(true);
        HBox hBox=new HBox();
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox.setMargin(label, new Insets(5));
        
        label.minWidthProperty().bind(Bindings.createDoubleBinding(
        () -> Math.max(label.prefWidth(-1) / 2 + 10, 50), // Show at least half of content and at least 50px
        label.textProperty(), label.graphicProperty(), label.prefWidthProperty(), label.layoutBoundsProperty()));
        label.maxWidthProperty().bind(Bindings.createDoubleBinding(
        () -> Math.min(label.prefWidth(-1) + 10, 275), // Show content up to 200px
        label.textProperty(), label.graphicProperty(), label.prefWidthProperty(), label.layoutBoundsProperty()));
        
        //label.setAlignment(Pos.BASELINE_RIGHT);
        chatbox.getChildren().add(hBox);
        
        messageText.setText("");

        Label newLabel = new Label("Hello world!");
        newLabel.setId("server");
        newLabel.setPadding(new Insets(3, 10, 3, 10));
        newLabel.minWidthProperty().bind(Bindings.createDoubleBinding(
        () -> Math.max(newLabel.prefWidth(-1) / 2 + 10, 50), // Show at least half of content and at least 50px
        newLabel.textProperty(), newLabel.graphicProperty(), newLabel.prefWidthProperty(), newLabel.layoutBoundsProperty()));
        newLabel.maxWidthProperty().bind(Bindings.createDoubleBinding(
        () -> Math.min(newLabel.prefWidth(-1) + 10, 275), // Show content up to 200px
        newLabel.textProperty(), newLabel.graphicProperty(), newLabel.prefWidthProperty(), newLabel.layoutBoundsProperty()));
        chatbox.getChildren().add(newLabel);
        VBox.setMargin(newLabel, new Insets(5));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendBtn.addEventFilter(ActionEvent.ACTION, event -> {
            String message = messageText.getText();
            if (message == null || message.equals("")) {
                event.consume();
            }
        });
    }
}
