package com.example.passwordgenerator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class HelloController {
    @FXML
    private Text generatedPassword;

    @FXML
    private Button generateButton;

    @FXML
    private CheckBox checkBoxUppercase;

    @FXML
    private CheckBox checkBoxLowercase;

    @FXML
    private CheckBox checkBoxSymbols;

    @FXML
    private CheckBox checkBoxNumbers;

    @FXML
    private Slider slider;

    @FXML
    private Text sliderValue;

    @FXML
    private ProgressBar passwordStrengthBar;

    // Password parameters

    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String NUMERIC_CHARACTERS = "0123456789";

    private static final String SYMBOL_CHARACTERS = "!@#$%^&*_=+-/";

    public void initialize() {
        sliderValue.setText("15");
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                sliderValue.setText(String.format("%.0f", newValue));
            }
        });
    }

    public void onGenerateButtonClick() {
        if (!checkBoxLowercase.isSelected() && !checkBoxUppercase.isSelected() && !checkBoxNumbers.isSelected() && !checkBoxSymbols.isSelected()) {
            generatedPassword.setText("Select one chekbox at least");
        } else {
            generatedPassword.setText(generatePassword(parseInt(sliderValue.getText()), checkBoxLowercase.isSelected(), checkBoxUppercase.isSelected(), checkBoxNumbers.isSelected(), checkBoxSymbols.isSelected()));
        }
    }

    public static String generatePassword(int length, boolean includeLowercase, boolean includeUppercase, boolean includeNumeric, boolean includeSymbols) {
        StringBuilder characterSet = new StringBuilder();
        if (includeLowercase) {
            characterSet.append(LOWERCASE_CHARACTERS);
        }
        if (includeUppercase) {
            characterSet.append(UPPERCASE_CHARACTERS);
        }
        if (includeNumeric) {
            characterSet.append(NUMERIC_CHARACTERS);
        }
        if (includeSymbols) {
            characterSet.append(SYMBOL_CHARACTERS);
        }
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = characterSet.charAt(new Random().nextInt(characterSet.length()));
        }
        return new String(password);
    }

    public void onCopyButtonClick() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(generatedPassword.getText());
        clipboard.setContent(content);
    }

}