package app.gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Interfejs służy do wprowadzania do wyznaczonego pola tekstowego tylko wartości liczbowe od 0 do 9.
 * Każdy inny znak jest usuwany.
 */
public class IntegerOnlyInputTextFieldListener implements ChangeListener
{
    private TextField textField;
    private int maxLength = -1;

    /**
     * Obiekt klasy implementujący listenera.
     * @param textField Pole tekstowe na którym jest dodany listener.
     */
    public IntegerOnlyInputTextFieldListener(TextField textField)
    {
        this.textField = textField;
    }

    /**
     * Obiekt klasy implementujący listenera.
     * @param textField Pole tekstowe na którym jest dodany listener.
     * @param maxLength Maksymalna długość znaków w polu tekstowym.
     */
    public IntegerOnlyInputTextFieldListener(TextField textField, int maxLength)
    {
        this.textField = textField;
        this.maxLength = maxLength;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

        if(newValue.toString().length() > 0)
        {
            //Określona długość danych wejściowych.
            if(maxLength != -1)
            {
                if(newValue.toString().length() > maxLength)
                    textField.setText(oldValue.toString());
                else
                {
                    char c = newValue.toString().charAt(newValue.toString().length()-1);
                    if((int) c < 48 || (int) c > 57)
                        textField.setText(oldValue.toString());
                }
            }
            //Dowolna długość danych wejściowych.
            else
            {
                char c = newValue.toString().charAt(newValue.toString().length()-1);
                if((int) c < 48 || (int) c > 57)
                    textField.setText(oldValue.toString());
            }
        }
    }
}


