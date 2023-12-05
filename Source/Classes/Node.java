package Source.Classes;
public class Node {
    private final int stateNumber;
    private final String stateId;
    private final String countryName;
    Node(int stateNumber, String stateId, String countryName) {
        this.stateNumber = stateNumber;
        this.stateId = stateId;
        this.countryName = countryName;
    }
    public int getStateNumber() {
        return stateNumber;
    }

    public String getStateId() {
        return stateId;
    }

    public String getCountryName() {
        return countryName;
    }
}
