package Source.Classes;
import java.util.HashMap;
import java.util.Map;

/**
 * @class EdgeCases - Custom class for handling edge cases in the data
 */
public class EdgeCases {
    private Map<String, String> aliases;
    public boolean containsKey(String alias) {
        return aliases.containsKey(alias);
    }
    public String getFormal(String alias) {
        return aliases.get(alias);
    }
    EdgeCases() {
        this.aliases = new HashMap<>();
        // lowercase for easier/more accurate comparisons
        aliases.put("united states", "United States of America");
        aliases.put("us", "United States of America");
        aliases.put("america", "united States of America");
        aliases.put("the bahamas", "Bahamas");
        aliases.put("bahamas", "Bahamas");
        aliases.put("trinidad and tobago", "Trinidad and Tobago");
        aliases.put("trinidad", "Trinidad and Tobago");
        aliases.put("suriname", "Surinam");
        aliases.put("uk", "United Kingdom");
        aliases.put("spain (ceuta)", "Spain");
        aliases.put("germany", "German Federal Republic");
        aliases.put("czechia", "Czech Republic");
        aliases.put("italy", "Italy/Sardinia");
        aliases.put("macedonia", "Macedonia (Former Yugoslav Republic of)");
        aliases.put("north macedonia", "Macedonia (Former Yugoslav Republic of)");
        aliases.put("bosnia", "Bosnia-Herzegovina");
        aliases.put("romania", "Rumania");
        aliases.put("russia", "Russia (Soviet Union)");
        aliases.put("soviet union", "Russia (Soviet Union)");
        aliases.put("russia (kaliningrad oblast)", "Russia (Soviet Union)");
        aliases.put("lithuania (kaliningrad oblast)", "Lithuania");
        aliases.put("belarus", "Belarus (Byelorussia)");
        aliases.put("denmark (greenland)", "Denmark");
        aliases.put("the gambia", "Gambia");
        aliases.put("burkina faso", "Burkina Faso (Upper Volta)");
        aliases.put("republic of the congo", "Congo");
        aliases.put("democratic republic of the (zaire) congo", "Democratic Republic of (Zaire) Congo");
        aliases.put("democratic republic of the congo", "Democratic Republic of (Zaire) Congo");
        aliases.put("tanzania", "Tanzania/Tanganyika");
        aliases.put("zimbabwe", "Zimbabwe (Rhodesia)");
        aliases.put("iran", "Iran (Persia)");
        aliases.put("turkey", "Turkey (Ottoman Empire)");
        aliases.put("yemen", "Yemen (Arab Republic of Yemen)");
        aliases.put("emirates", "United Arab Emirates");
        aliases.put("north korea", "People's Republic of Korea");
        aliases.put("korea", "Republic of Korea");
        aliases.put("south korea", "Republic of Korea");
        aliases.put("myanmar", "Myanmar (Burma)");
        aliases.put("burma", "Myanmar (Burma)");
        aliases.put("sri lanka", "Sri Lanka (Ceylon)");
        aliases.put("cambodia", "Cambodia (Kampuchea)");
        aliases.put("vietnam", "Democratic Republic of Vietnam");
    }
}
