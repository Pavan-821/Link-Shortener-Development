import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PersistentURLManager extends URLManager {
    private static final String FILE_NAME = "urlMappings.ser";

    public PersistentURLManager() {
        super();
        loadMappings();
    }

    @Override
    public String shortenURL(String longURL) {
        String shortURL = super.shortenURL(longURL);
        saveMappings();
        return shortURL;
    }

    @Override
    public String expandURL(String shortURL) {
        return super.expandURL(shortURL);
    }

    private void loadMappings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Map<String, String> loadedLongToShortMap = (Map<String, String>) ois.readObject();
            Map<String, String> loadedShortToLongMap = (Map<String, String>) ois.readObject();
            this.longToShortMap.putAll(loadedLongToShortMap);
            this.shortToLongMap.putAll(loadedShortToLongMap);
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveMappings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(longToShortMap);
            oos.writeObject(shortToLongMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
