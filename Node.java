public class Node {
    private int stateNumber;
    private String stateId;
    private String countryName;

    Node() {
        this.stateNumber = -1;
        this.stateId = null;
        this.countryName = null;
    }

    void setStateNumber(int stateNumber) {
        this.stateNumber = stateNumber;
    }

    void setStateId(String stateId) {
        this.stateId = stateId;
    }

    void setCountryName(String countryName) {
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
