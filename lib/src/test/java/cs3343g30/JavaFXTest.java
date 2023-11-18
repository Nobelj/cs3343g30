
package cs3343g30;

import static org.junit.Assert.*;

import javafx.scene.input.KeyCode;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;



public class JavaFXTest extends ApplicationTest {

	private FXMLLoader loader;
	private Scene s;
	private TextArea t;
	private TextField t1YearOfInterest;
	private TextField t1NumOfNames;
	private TextField t4DadName;
	private TextField t4MumName;
	private TextField t4DadYOB;
	private TextField t4MumYOB;
	private ChoiceBox<String> t4AlgoChoice;

	private TextField tf;
	private ChoiceBox scb;

	@Override
	public void start(Stage stage) throws Exception {
    	loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Popular Names");
   		stage.show();
   		s = scene;
		t = (TextArea)s.lookup("#textAreaConsole");
		t1YearOfInterest = (TextField)s.lookup("#t1YearOfInterest");
		t1NumOfNames = (TextField)s.lookup("#t1NumOfNames");
		t4DadName = (TextField)s.lookup("#t4DadName");
		t4MumName = (TextField)s.lookup("#t4MumName");
		t4DadYOB = (TextField)s.lookup("#t4DadYOB");
		t4MumYOB = (TextField)s.lookup("#t4MumYOB");
		t4AlgoChoice = (ChoiceBox<String>)s.lookup("#t4AlgoChoice");
	}

    
	@Test
	public void testButtonRankTrue() {	
		//clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankM");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	
	@Test
	public void testButtonRankFalse() {	
		//clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankF");
		//sleep(1000);
		String s2 = t.getText();
		assertFalse(s1.equals(s2));
	}
	

	@Test
	public void testTextAreaConsole() {	
		t.setText("David");
		String s = t.getText();
		assertTrue(s.equals("David"));
	}
	
	@Test
	public void testButtonT0Summary() {
		clickOn("#buttonSummary");
		assertEquals(t.getText(), "Summary (Year of 2019):\n" +
                "Total Births = 3,445,321\n" +
                "***Baby Girls = 1,665,373\n" +
                "***Baby Boys = 1,779,948\n" +
                "Total Number of Unique Names = 31,954\n" +
                "***Unique Names (baby girls) = 17,905\n" +
                "***Unique Names (baby boys) = 14,049\n");
	}
	
	@Test
	public void testButtonT0TestRankMale() {
		clickOn("#buttonRankM");
		assertEquals(t.getText(), "Rank of David (male) in year 2019 is #27.\n");
	}
	
	@Test
	public void testButtonT0TestRankMaleInvalid() {
		TextField textfieldNameM = (TextField)s.lookup("#textfieldNameM");
		textfieldNameM.setText("X");
		clickOn("#buttonRankM");
		assertEquals(t.getText(), "The name X (male) has not been ranked in the year 2019.\n");
	}
	
	@Test
	public void testButtonT0TestRankFemale() {
		clickOn("#buttonRankF");
		assertEquals(t.getText(), "Rank of Desire (female) in year 2019 is #2192.\n");
	}
	
	@Test
	public void testButtonT0TestRankFemaleInvalid() {
		TextField textfieldNameF = (TextField)s.lookup("#textfieldNameF");
		textfieldNameF.setText("X");
		clickOn("#buttonRankF");
		assertEquals(t.getText(), "The name X (female) has not been ranked in the year 2019.\n");
	}
	
	@Test
	public void testButtonT0TopFiveMale() {
		clickOn("#buttonTopM");
		assertEquals(t.getText(), "Top 5 most popular names (male) in the year 2019:\n"
				+ "#1: Liam\n"
				+ "#2: Noah\n"
				+ "#3: Oliver\n"
				+ "#4: William\n"
				+ "#5: Elijah\n");
	}
	
	@Test
	public void testButtonT0TopFiveFemale() {
		clickOn("#buttonTopF");
		assertEquals(t.getText(), "Top 5 most popular names (female) in the year 2019:\n"
				+ "#1: Olivia\n"
				+ "#2: Emma\n"
				+ "#3: Ava\n"
				+ "#4: Sophia\n"
				+ "#5: Isabella\n");
	}

	@Test
	public void testButtonT1GenerateReportValid() {
		t1YearOfInterest.setText("1880");
		t1NumOfNames.setText("1");
		clickOn("#tabReport1");
		clickOn("#t1GenerateButton");
		TextArea t = (TextArea)s.lookup("#t1Summary");
		assertEquals(t.getText(), "John is the most popular name with the number of occurrences of 9655, which "
				+ "represents 8.74% of total male births in 1880.\n"
				+ "Mary is the most popular name with the number of occurrences of 7065, which "
				+ "represents 7.76% of total female births in 1880.");
	}

	@Test
	public void testButtonT1GenerateReportNumNamesInvalid() {
		TextArea t = (TextArea)s.lookup("#t1Summary");
		
		clickOn("#tabReport1");
		assertEquals(t.getText(), "");
		
		t1YearOfInterest.setText("1880");
		t1NumOfNames.setText("0");
		clickOn("#t1GenerateButton");
		assertEquals(t.getText(), "");
		
		t1YearOfInterest.setText("2020");
		t1NumOfNames.setText("1");
		clickOn("#t1GenerateButton");
		assertEquals(t.getText(), "");
		
		t1YearOfInterest.setText("");
		t1NumOfNames.setText("1");
		clickOn("#t1GenerateButton");
		assertEquals(t.getText(), "");
		
		t1YearOfInterest.setText("a");
		t1NumOfNames.setText("b");
		clickOn("#t1GenerateButton");
		assertEquals(t.getText(), "");
	}

	@Test
	public void testButtonT4GenerateRecommendationValid() {
		clickOn("#tabApp1");
		t4DadName.setText("Joshua");
		t4MumName.setText("Ruby");
		t4DadYOB.setText("1999");
		t4MumYOB.setText("2001");
		clickOn(t4AlgoChoice);
		clickOn("T4X1");
		clickOn("#t4Button");
		Label boyName = (Label)s.lookup("#t4BoyName");
		Label girlName = (Label)s.lookup("#t4GirlName");
		assertEquals(boyName.getText(), "Jacob");
		assertEquals(girlName.getText(), "Emily");
		
		clickOn(t4AlgoChoice);
		clickOn("T4X2");
		clickOn("#t4Button");
		assertEquals(boyName.getText(), "Dominic");
		assertEquals(girlName.getText(), "Caitlyn");
	}
	
	@Test
	public void testButtonT4GenerateRecommendationInvalid() {
		Label boyName = (Label)s.lookup("#t4BoyName");
		Label girlName = (Label)s.lookup("#t4GirlName");
		
		clickOn("#tabApp1");
		assertEquals(boyName.getText(), "");
		assertEquals(girlName.getText(), "");
		
		clickOn("#t4Button");
		assertEquals(boyName.getText(), "");
		assertEquals(girlName.getText(), "");
		
		t4DadYOB.setText("1770");
		t4MumYOB.setText("2020");
		clickOn("#t4Button");
		assertEquals(boyName.getText(), "");
		assertEquals(girlName.getText(), "");
		
		
		t4DadName.setText("Joshuu");
		t4MumName.setText("Rubb");
		t4DadYOB.setText("1999");
		t4MumYOB.setText("2001");
		clickOn("#t4Button");
		assertEquals(boyName.getText(), "");
		assertEquals(girlName.getText(), "");
		
	}



	
	@Test
	public void testT2ReportingNoInput() {
		clickOn("#tabReport2");
		clickOn("#t2_generate");
		t = (TextArea)s.lookup("#t2_summary");
		String s1 = t.getText();
		assertTrue(s1.equals(""));
	}
	
	@Test
	public void testT2ReportingValidInput() {
		clickOn("#tabReport2");
		tf = (TextField)s.lookup("#t2_start_year");
		tf.setText("2000");
		tf = (TextField)s.lookup("#t2_end_year");
		tf.setText("2010");
		tf = (TextField)s.lookup("#t2_k");
		tf.setText("5");
		clickOn("#t2_gender");
		type(KeyCode.ENTER);
		clickOn("#t2_generate");
		t = (TextArea)s.lookup("#t2_summary");
		String s1 = t.getText();
		assertTrue(s1.equals("Ethan has hold the 5-th rank most often for a total of 3 times among names registered \nfor baby boys born in the period from 2000 to 2010. The total number of occurrences of \nEthan is 65611, which represents 28.49% of total male births at the 5-th rank in the period from 2000 to 2010."));
	}
	
	@Test
	public void testT5Alg1() {
		clickOn("#tabApp2");
		tf = (TextField) s.lookup("#t5_iName");
		tf.setText("Timothy");
		clickOn("#t5_iGender");
		type(KeyCode.ENTER);
		tf = (TextField) s.lookup("#t5_iYOB");
		tf.setText("2001");
		clickOn("#t5_iGenderMate");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		clickOn("#t5_iPreference");
		type(KeyCode.ENTER);
		clickOn("#t5_algorithm");
		type(KeyCode.ENTER);
		clickOn("#t5_predict_button");
		t = (TextArea)s.lookup("#t5_reason");
		String s1 = t.getText();
		assertTrue(s1.equals("Emily is the most popular female baby name in 2001!\n"
				+ "You will have the highest chance of having a\n"
				+ "soulmate with the name Emily"));
	}
	
	@Test
	public void testT5Alg2() {
		clickOn("#tabApp2");
		tf = (TextField) s.lookup("#t5_iName");
		tf.setText("Timothy");
		clickOn("#t5_iGender");
		type(KeyCode.ENTER);
		tf = (TextField) s.lookup("#t5_iYOB");
		tf.setText("2001");
		clickOn("#t5_iGenderMate");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		clickOn("#t5_iPreference");
		type(KeyCode.ENTER);
		clickOn("#t5_algorithm");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		clickOn("#t5_predict_button");
		t = (TextArea)s.lookup("#t5_reason");
		String s1 = t.getText();
		assertTrue(s1.equals("The rank of your name, Timothy, is 67 in 2001,\n"
				+ "and according to our calculations,\n"
				+ "Kaitlyn has matched your rank\n"
				+ "in the year 2009,\n"
				+ "and also occurs the most between\n"
				+ "2001 and 2010, which means\n"
				+ "they will most likely become your soulmate in the future!"));
	}


	@Test
	public void testT3ReportingNoInput() {
		clickOn("#tabReport3");
		clickOn("#t3GenerateButton");
		t = (TextArea)s.lookup("#t3SummaryTextArea");
		String s1 = t.getText();
		assertTrue(s1.equals(""));
	}

	@Test
	public void testT3ReportingValidInput() {
		clickOn("#tabReport3");
		tf = (TextField)s.lookup("#t3StartYearField");
		tf.setText("1890");
		tf = (TextField)s.lookup("#t3EndYearField");
		tf.setText("1891");
		tf = (TextField)s.lookup("#t3NameField");
		tf.setText("Mary");
		clickOn("#t3GenderChoice");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		clickOn("#t3GenerateButton");
		t = (TextArea)s.lookup("#t3SummaryTextArea");
		String s1 = t.getText();
		assertTrue(s1.equals("The year when the name Mary was most popular is 1890 at rank 1. In that year, the number of occurrence is 12078, which represents 4.007% of total female births in 1890."));
	}

	@Test
	public void testT6AlgorithmT6X1() {
		clickOn("#tabApp3");
		tf = (TextField) s.lookup("#t6OwnNameField");
		tf.setText("Tommy");
		clickOn("#t6OwnGenderChoice");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		tf = (TextField) s.lookup("#t6OwnYOB");
		tf.setText("2001");
		tf = (TextField) s.lookup("#t6TargetName");
		tf.setText("Mary");
		clickOn("#t6TargetGenderChoice");
		type(KeyCode.DOWN);
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		clickOn("#t6PreferenceChoice");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		clickOn("#t6X1Button");
		clickOn("#t6RunButton");
		t = (TextArea) s.lookup("#t6TextArea");
		String s = t.getText();
		assertEquals(s, "Unfortunately, you and Mary does not share the same length of name, therefore it would be hard for you two to work together, you can try the other algorithm to see if it gives a better result.");
	}

	@Test
	public void testT6AlgorithmT6X2() {
		clickOn("#tabApp3");
		tf = (TextField) s.lookup("#t6OwnNameField");
		tf.setText("Tommy");
		clickOn("#t6OwnGenderChoice");
		type(KeyCode.ENTER);
		tf = (TextField) s.lookup("#t6OwnYOB");
		tf.setText("2001");
		tf = (TextField) s.lookup("#t6TargetName");
		tf.setText("Mary");
		clickOn("#t6TargetGenderChoice");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		clickOn("#t6PreferenceChoice");
		type(KeyCode.ENTER);
		clickOn("#t6X2Button");
		tf = (TextField) s.lookup("#t6TargetBirthMonth");
		tf.setText("1");
		tf = (TextField) s.lookup("#t6TargetBirthDay");
		tf.setText("1");
		clickOn("#t6RunButton");
		t = (TextArea) s.lookup("#t6TextArea");
		String s = t.getText();
		assertEquals(s, "Hi Tommy, you are 73% compatible with Mary.\n" +
				"A Mary is very unique, you are lucky to find one.\n" +
				"As the older person, it means you are more mature than her, but it also means you have to be more compassionate towards her.\n" +
				"You and Mary are a Snake and Capricorn match, with Snake being calm, talented, but indifferent and skeptical, and Capricorn being practical, prudent, but pessimistic and fatalistic, you two should keep your lives balanced, don't overlook other aspects of life.");
	}



}
