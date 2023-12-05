package Source.Tests;

import Source.Classes.IRoadTrip;
import org.junit.*;

// *ADD CASES FOR WHEN THE COUNTRIES ARE THE SAME --> SHOULD RETURN 0
public class ValidDistanceTest {
    private final String[] args = {"borders.txt", "capdist.csv", "state_name.tsv"};
    private final IRoadTrip roadTrip = new IRoadTrip(args);
    @Test
    public void validDistanceOne() {
        int distance = roadTrip.getDistance("USA", "Mexico");
        int expectedDistance = 3024;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceTwo() {
        int distance = roadTrip.getDistance("United States of America", "Canada");
        int expectedDistance = 731;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceThree() {
        int distance = roadTrip.getDistance("United States", "Mexico");
        int expectedDistance = 3024;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceFour() {
        int distance = roadTrip.getDistance("Dominican Republic", "Haiti");
        int expectedDistance = 246;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceFive() {
        int distance = roadTrip.getDistance("Colombia", "Panama");
        int expectedDistance = 760;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceSix() {
        int distance = roadTrip.getDistance("South Sudan", "Ethiopia");
        int expectedDistance = 936;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceSeven() {
        int distance = roadTrip.getDistance("Congo", "Gabon");
        int expectedDistance = 790;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceEight() {
        int distance = roadTrip.getDistance("Democratic Republic of Congo", "Congo");
        int expectedDistance = 0;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceNine() {
        int distance = roadTrip.getDistance("Ukraine", "Soviet Union");
        int expectedDistance = 739;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceTen() {
        int distance = roadTrip.getDistance("Bhutan", "China");
        int expectedDistance = 2806;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceEleven() {
        int distance = roadTrip.getDistance("Yemen", "Saudi Arabia");
        int expectedDistance = 1040;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceTwelve() {
        int distance = roadTrip.getDistance("Saudi Arabia", "Jordan");
        int expectedDistance = 1323;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceThirteen() {
        int distance = roadTrip.getDistance("Paraguay", "Bolivia");
        int expectedDistance = 1480;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceFourteen() {
        int distance = roadTrip.getDistance("Bolivia", "Peru");
        int expectedDistance = 1069;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceFifteen() {
        int distance = roadTrip.getDistance("Peru", "Colombia");
        int expectedDistance = 1880;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceSixteen() {
        int distance = roadTrip.getDistance("Gabon", "Cameroon");
        int expectedDistance = 405;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceSeventeen() {
        int distance = roadTrip.getDistance("Cameroon", "Nigeria");
        int expectedDistance = 963;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceEighteen() {
        int distance = roadTrip.getDistance("Nigeria", "Niger");
        int expectedDistance = 788;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceNineteen() {
        int distance = roadTrip.getDistance("Niger", "Algeria");
        int expectedDistance = 2561;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceTwenty() {
        int distance = roadTrip.getDistance("Algeria", "Morocco");
        int expectedDistance = 958;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceTwentyOne() {
        int distance = roadTrip.getDistance("Morocco", "Spain");
        int expectedDistance = 822;
        Assert.assertEquals(distance, expectedDistance);
    }
    @Test
    public void validDistanceTwentyTwo() {
        int distance = roadTrip.getDistance("Spain", "France");
        int expectedDistance = 1012;
        Assert.assertEquals(distance, expectedDistance);
    }


}
