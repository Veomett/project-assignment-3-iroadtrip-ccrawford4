package Source.Tests;
import org.junit.*;
import Source.Classes.IRoadTrip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindPathTest {
    private final String[] args = {"borders.txt", "capdist.csv", "state_name.tsv"};
    private final IRoadTrip roadTrip = new IRoadTrip(args);
    @Test
    public void testOne() {
        List<String> path = roadTrip.findPath("CS245", "Yemen");
        List<String> expectedPath = new ArrayList<>();
        Assert.assertEquals(path, expectedPath);
    }
    @Test
    public void testTwo() {
        List<String> path = roadTrip.findPath("Yemen", "Jordan");
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Yemen");
        expectedPath.add("Saudi Arabia");
        expectedPath.add("Jordan");
    }

}
