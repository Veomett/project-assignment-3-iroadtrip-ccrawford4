package Source.Tests;
import org.junit.*;
import Source.Classes.IRoadTrip;
public class InvalidDistanceTest {
    private final String[] args = {"borders.txt", "capdist.csv", "state_name.tsv"};
    private final IRoadTrip roadTrip = new IRoadTrip(args);
    @Test
    public void invalidTestOne() {
        int distance = roadTrip.getDistance("Canada", "Mexico");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestTwo() {
        int distance = roadTrip.getDistance("Venezuela", "Argentina");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestThree() {
        int distance = roadTrip.getDistance("Canada", "Mexico");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestFour() {
        int distance = roadTrip.getDistance("Kenya", "Sudan");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestFive() {
        int distance = roadTrip.getDistance("Iran", "Syria");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestSix() {
        int distance = roadTrip.getDistance("Ukraine", "Austria");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestSeven() {
        int distance = roadTrip.getDistance("Canada", "Mexico");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestEight() {
        int distance = roadTrip.getDistance("Myanmar", "Cambodia");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestNine() {
        int distance = roadTrip.getDistance("Burma", "Cambodia");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestTen() {
        int distance = roadTrip.getDistance("Papua New Guinea", "Malaysia");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestEleven() {
        int distance = roadTrip.getDistance("Yemen", "Jordan");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestTwelve() {
        int distance = roadTrip.getDistance("Paraguay", "Peru");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestThirteen() {
        int distance = roadTrip.getDistance("Gabon", "Nigeria");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestFourteen() {
        int distance = roadTrip.getDistance("Nigeria", "Algeria");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestFifteen() {
        int distance = roadTrip.getDistance("Niger", "Morocco");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void invalidTestSixteen() {
        int distance = roadTrip.getDistance("Morocco", "France");
        int expectedDistance = -1;
        Assert.assertEquals(distance, expectedDistance);
    }


}
