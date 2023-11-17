package cs3343g30;

import org.junit.Test;

import static org.junit.Assert.*;


public class T6CompatibilityCalculatorTest {

    @Test
    public void getCompatibilityScore() {
    }

    @Test
    public void getPredictionT6X2Score() {
        T6CompatibilityCalculator calculator = new T6CompatibilityCalculator("Ricky", true, 1997, "Mary", false, true, true, 5, 17);
        int score = calculator.getCompatibilityScore();
        assertEquals(score, 90);
    }

    @Test
    public void getPredictionT6X2String() {
        T6CompatibilityCalculator calculator = new T6CompatibilityCalculator("Ricky", true, 1997, "Mary", false, true, true, 5, 17);
        String prediction = calculator.getPrediction();
        assertEquals(prediction, "Hi Ricky, you are 90% compatible with Mary.\n" +
                "A Mary is very unique, you are lucky to find one.\n" +
                "As the older person, it means you are more mature than her, but it also means you have to be more compassionate towards her.\n" +
                "You and Mary are a Ox and Taurus match, with Ox being loyal, honest, but less-talkative and conservative, and Taurus being patient, reliable, but jealous and possessive, you two should learn to look for nonverbal cues from each other in communications.");
    }

    @Test
    public void getPredictionT6X1Score() {
        T6CompatibilityCalculator calculator = new T6CompatibilityCalculator("Tom", true, 2001, "Mary", false, true, false);
        int score = calculator.getCompatibilityScore();
        assertEquals(score, 0);
    }

    @Test
    public void getPredictionT6X1String() {
        T6CompatibilityCalculator calculator = new T6CompatibilityCalculator("Tom", true, 2001, "Mary", false, true, false);
        String prediction = calculator.getPrediction();
        assertEquals(prediction, "Unfortunately, you and Mary does not share the same length of name, therefore it would be hard for you two to work together, you can try the other algorithm to see if it gives a better result.");
    }

    @Test
    public void getText() {
        String result = T6CompatibilityCalculator.getText("Rat+Capricorn");
        assertEquals(result, "find a hobby that you both enjoy and have fun together");
    }

    @Test
    public void getScore() {
        int result = T6CompatibilityCalculator.getScore("Sheep+Aquarius");
        assertEquals(result, 3);
    }

    @Test
    public void constellationTest() {
        // Test if there are error generating constellations
        String result = "";
        for (int month = 1; month <= 12; month++) {
            result = T6CompatibilityCalculator.constellation(month, 3);
        }
        assertEquals(result, "Sagittarius");
    }
}