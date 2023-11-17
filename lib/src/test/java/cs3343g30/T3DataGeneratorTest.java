package cs3343g30;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.junit.Test;
import static org.junit.Assert.*;

public class T3DataGeneratorTest {

    @Test
    public void testGenerateSummaryWithData() {
        T3DataGenerator dataGenerator = new T3DataGenerator(1900, 2000, "Mary", false);
        String generatedString = dataGenerator.generateSummary();
        String expectedString = "The year when the name Mary was most popular is 1921 at rank 1. In that year, the number of occurrence is 73985, which represents 3.169% of total female births in 1921.";
        assertEquals(generatedString, expectedString);
    }

    @Test
    public void testGenerateSummaryNoData() {
        T3DataGenerator dataGenerator = new T3DataGenerator(1900, 2000, "Nameless", true);
        dataGenerator.mostPopularYear = -1;
        String generatedString = dataGenerator.generateSummary();
        String expectedString = "This name was not recorded in the selected years";
        assertEquals(generatedString, expectedString);
    }

    @Test
    public void testGenerateDataTableDataNoData() {
        T3DataGenerator dataGenerator = new T3DataGenerator(1900, 2000, "Nameless", true);
        ObservableList<T3YearRecord> generatedList = dataGenerator.generateDataTableData();
        ObservableList<T3YearRecord> expectedList = FXCollections.observableArrayList();
        assertEquals(generatedList, expectedList);
    }

    @Test
    public void testGenerateDataTableDataWithData() {
        T3DataGenerator dataGenerator = new T3DataGenerator(2000, 2000, "Tom", true);
        ObservableList<T3YearRecord> generatedList = dataGenerator.generateDataTableData();
        assertEquals(generatedList.get(0).getOccurrences(), "99");
    }

    @Test
    public void testGenerateBarChartSeries() {
        T3DataGenerator dataGenerator = new T3DataGenerator(2000, 2000, "Tom", true);
        XYChart.Series<String, Number> generatedSeries = dataGenerator.generateBarChartSeries();
        assertEquals(generatedSeries.getData().get(0).getXValue(), "2000");
        assertEquals(generatedSeries.getData().get(0).getYValue(), 99);
    }

    @Test
    public void testGenerateLineChartSeries() {
        T3DataGenerator dataGenerator = new T3DataGenerator(2000, 2000, "Tom", true);
        XYChart.Series<Number, Number> generatedSeries = dataGenerator.generateLineChartSeries();
        assertEquals(generatedSeries.getData().get(0).getXValue(), 2000);
        assertEquals(generatedSeries.getData().get(0).getYValue(), 99);
    }
}