public class Node {
    private final int stateNumber;
    private final String stateId;
    private final String countryName;
    Node(int stateNumber, String stateId, String countryName) {
        this.stateNumber = stateNumber;
        this.stateId = stateId;
        this.countryName = countryName;
    }
    int getStateNumber() {
        return stateNumber;
    }

    String getStateId() {
        return stateId;
    }

    String getCountryName() {
        return countryName;
    }
}
