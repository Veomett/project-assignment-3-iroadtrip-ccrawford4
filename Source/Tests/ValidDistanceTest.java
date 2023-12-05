package Source.Tests;

import Source.Classes.IRoadTrip;
import org.junit.*;
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
}
