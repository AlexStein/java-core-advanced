package javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    public TextArea chatArea;
    @FXML
    public TextArea messageField;

    public void sendMessage() {
        chatArea.appendText(messageField.getText() + "\n\n");

        messageField.setText("");
        messageField.requestFocus();
    }

    public void onSendButtonClicked(MouseEvent actionEvent) {
        sendMessage();
    }

    public void onMessageKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }
}
