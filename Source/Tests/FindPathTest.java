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
        List<String> path = roadTrip.findPath("South Africa", "Russia");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("South Africa");
        expectedPath.add("Mozambique");
        expectedPath.add("Tanzania/Tanganyika");
        expectedPath.add("Kenya");
        expectedPath.add("Ethiopia");
        expectedPath.add("Sudan");
        expectedPath.add("Egypt");
        expectedPath.add("Israel");
        expectedPath.add("Syria");
        expectedPath.add("Turkey (Ottoman Empire)");
        expectedPath.add("Georgia");
        expectedPath.add("Russia (Soviet Union)");
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







}
