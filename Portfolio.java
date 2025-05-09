import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Portfolio implements Serializable {

    private Map<Integer, Asset> assets = new HashMap<>();
    private int nextAssetID = 0;
    private double riskScore;
    private double totalROI;
    private double totalProfit;

    public void addAsset(String assetName, double purchasePrice, double currentValue, String type) {
        int assetID = nextAssetID;
        while (assets.containsKey(assetID)) {
            assetID++;
        }
        assets.put(assetID, new Asset(assetName, purchasePrice, currentValue, type, assetID));
    }

    public void editAsset(String assetName, double purchasePrice, double currentValue, String type, int assetID) {
        assets.replace(assetID, new Asset(assetName, purchasePrice, currentValue, type, assetID));
    }

    public void removeAsset(int assetID) {
        assets.remove(assetID);
    }

    public double calculateNetWorth() {
        return assets.values().stream().mapToDouble(Asset::getAssetCurrentValue).sum();
    }

    public double calculateZakat() {
        return .025 * calculateNetWorth();
    }

    public void listAssets() {
        if(assets.isEmpty()) {
            System.out.println("No assets to display\n");
            return;
        }
        System.out.println();
        for (Map.Entry<Integer, Asset> entry : assets.entrySet()) {
            Asset asset = entry.getValue();
            asset.listAsset();
            System.out.println();
        }
    }

    public boolean assetExists(int assetID) {
        return assets.containsKey(assetID);
    }



}
