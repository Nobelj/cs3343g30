package cs3343g30;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * The class for performing data validation
 */
public class DataValidator {
    /**
     * @param yearStart The starting year
     * @param yearEnd   The ending year
     * @return If the range of the two year is valid
     */
    public static boolean invalidYearRange(int yearStart, int yearEnd) {
        return (yearEnd < yearStart);
    }

    /**
     * @param year The year
     * @return If the range is within the year where we have data
     */
    public static boolean yearOutOfRange(int year) {
        return (year < 1880 || year > 2019);
    }

    /**
     * @param box The choice box
     * @return If the choice box is empty
     */
    public static boolean choiceBoxEmpty(ChoiceBox<String> box) {
        return box.getValue() == null;
    }
    
    /**
     * @param box The char box
     * @return If the char box is empty
     */
    public static boolean charBoxEmpty(ChoiceBox<Character> box) {
        return box.getValue() == null;
    }

    /**
     * @param field The text field
     * @return If the text field is empty
     */
    public static boolean textFieldEmpty(TextField field) {
        return field.getText().equals("");
    }
    
    /**
     * @param k Value of k
     * @return If k is within the range
     */
    public static boolean kOutOfRange(int k) {
    	return (k<1 || k>1000);
    }
}
