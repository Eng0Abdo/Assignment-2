import java.util.HashMap;
import java.util.Map;
public class Portfolio {

    private Map<Integer, Asset> assets = new HashMap<>();
    private int nextAssetID = 0;
    private double netWorth;
    private double riskScore;
    private double totalROI;
    private double zakatCalculation;
    private double totalProfit;


    public void addAsset(String assetName, double purchasePrice, double currentValue, String type) {
        int assetID = nextAssetID++; // Assign and increment ID
        assets.put(assetID, new Asset(assetName, purchasePrice, currentValue, type, assetID));
    }

    public void editAsset(String assetName, double purchasePrice, double currentValue, String type, int assetID) {
        assets.replace(assetID, new Asset(assetName, purchasePrice, currentValue, type, assetID));
    }

    public void removeAsset(int assetID) {
        assets.remove(assetID);
    }

    public void calculateNetWorth() {
        this.netWorth = assets.values().stream().mapToDouble(Asset::getAssetCurrentValue).sum();
    }

    public void calculateZakat() {
        zakatCalculation = .025 * netWorth;
    }
}
