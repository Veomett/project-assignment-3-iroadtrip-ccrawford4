package Source.Tests;
import org.junit.*;
import Source.Classes.IRoadTrip;
import java.util.ArrayList;
import java.util.List;

public class FindPathTest {
    private final String[] args = {"borders.txt", "capdist.csv", "state_name.tsv"};
    private final IRoadTrip roadTrip = new IRoadTrip(args);
    @Test
    public void testOne() {
        List<String> path = roadTrip.findPath("CS245", "Yemen");
        List<String> expectedPath = new ArrayList<>();
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testTwo() {
        List<String> path = roadTrip.findPath("Yemen", "Jordan");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Yemen (Arab Republic of Yemen)");
        expectedPath.add("Saudi Arabia");
        expectedPath.add("Jordan");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testThree() {
        List<String> path = roadTrip.findPath("Paraguay", "Colombia");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Paraguay");
        expectedPath.add("Bolivia");
        expectedPath.add("Peru");
        expectedPath.add("Colombia");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testFour() {
        List<String> path = roadTrip.findPath("Gabon", "France");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Gabon");
        expectedPath.add("Cameroon");
        expectedPath.add("Nigeria");
        expectedPath.add("Niger");
        expectedPath.add("Algeria");
        expectedPath.add("Morocco");
        expectedPath.add("Spain");
        expectedPath.add("France");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testFive() {
        List<String> path = roadTrip.findPath("United States", "USA");
        List<String> expectedPath =new ArrayList<>(List.of("United States of America"));
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testSix() {
        List<String> path = roadTrip.findPath("Japan", "Australia");
        List<String> expectedPath = new ArrayList<>();
        Assert.assertEquals(expectedPath, path);
    }

    @Test
    public void testSeven() {
        List<String> path = roadTrip.findPath("Brazil", "Argentina");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Brazil");
        expectedPath.add("Argentina");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testEight() {
        List<String> path = roadTrip.findPath("trinidad", "Trinidad and Tobago");
        List<String> expectedPath = new ArrayList<>(List.of("Trinidad and Tobago"));
        Assert.assertEquals(expectedPath, path);
    }

    @Test
    public void testNine() {
        List<String> path = roadTrip.findPath("Italy", "Greece");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Italy/Sardinia");
        expectedPath.add("Slovenia");
        expectedPath.add("Croatia");
        expectedPath.add("Montenegro");
        expectedPath.add("Albania");
        expectedPath.add("Greece");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testTen() {
        List<String> path = roadTrip.findPath("German Federal Republic", "Poland");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("German Federal Republic");
        expectedPath.add("Poland");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testEleven() {
        List<String> path = roadTrip.findPath("Germany", "poland");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("German Federal Republic");
        expectedPath.add("Poland");
        Assert.assertEquals(expectedPath, path);
    }

    @Test
    public void testTwelve() {
        List<String> path = roadTrip.findPath("Canada", "Denmark");
        List<String> expectedPath = new ArrayList<>();
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testThirteen() {
        List<String> path = roadTrip.findPath("Canada", "Canada");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Canada");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testFourteen() {
        List<String> path = roadTrip.findPath("Russia (Kaliningrad Oblast)", "lithuania");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Russia (Soviet Union)");
        expectedPath.add("Lithuania");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testFifteen() {
        List<String> path = roadTrip.findPath("Iran", "United Arab Emirates");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Iran (Persia)");
        expectedPath.add("Iraq");
        expectedPath.add("Saudi Arabia");
        expectedPath.add("United Arab Emirates");
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testSixteen() {
        List<String> path = roadTrip.findPath("Italy", "Italy/Sardinia");
        List<String> expectedPath = new ArrayList<>(List.of("Italy/Sardinia"));
        Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testSeventeen() {
         List<String> path = roadTrip.findPath("Democratic Republic of the Congo", "Congo");
         List<String> expectedPath = new ArrayList<>();
         expectedPath.add("Democratic Republic of (Zaire) Congo");
         expectedPath.add("Congo");
         Assert.assertEquals(expectedPath, path);
    }
    @Test
    public void testEighteen() {
        List<String> path = roadTrip.findPath("north korea", "South Korea");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("People's Republic of Korea");
        expectedPath.add("Republic of Korea");
        Assert.assertEquals(expectedPath, path);
    }







}
