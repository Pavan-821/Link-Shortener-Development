import java.util.HashMap;
import java.util.Map;

public class URLManager {
    private static final String BASE_URL = "http://short.url/";
    private final Map<String, String> longToShortMap;
    private final Map<String, String> shortToLongMap;

    public URLManager() {
        this.longToShortMap = new HashMap<>();
        this.shortToLongMap = new HashMap<>();
    }

    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }
        String shortURL = generateShortURL(longURL);
        longToShortMap.put(longURL, shortURL);
        shortToLongMap.put(shortURL, longURL);
        return shortURL;
    }

    public String expandURL(String shortURL) {
        return shortToLongMap.getOrDefault(shortURL, null);
    }

    private String generateShortURL(String longURL) {
        return BASE_URL + Integer.toHexString(longURL.hashCode());
    }
}
