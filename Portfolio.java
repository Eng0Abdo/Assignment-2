/**
 * Manages investment assets and performs financial calculations including Zakat.
 * Uses HashMap for O(1) asset lookups by ID.
 *
 * @author Abdelrahman Emad, Mahmoud Mohamed, Peter Gerges
 * @version 1.2
 */

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Portfolio implements Serializable {

    /**
     * Adds a new asset to the portfolio
     *
     * @param assetName Display name (e.g., "Apple Stock")
     * @param purchasePrice Buying price per unit (must be > 0)
     * @param currentValue Current market value
     * @param type Asset category (Stock/RealEstate/Crypto/Gold)
     * @param amount Number of units
     * @throws IllegalArgumentException for negative values
     */

    private Map<Integer, Asset> assets = new HashMap<>();
    private int nextAssetID = 0;
    private double riskScore;
    private double totalROI;
    private double totalProfit;

    public void addAsset(String assetName, double purchasePrice, double currentValue, String type, double amount) {
        int assetID = nextAssetID;
        while (assets.containsKey(assetID)) {
            assetID++;
        }
        assets.put(assetID, new Asset(assetName, purchasePrice, currentValue, type, assetID, amount));
    }

    public void editAsset(String assetName, double purchasePrice, double currentValue, String type, int assetID, double amount) {
        assets.replace(assetID, new Asset(assetName, purchasePrice, currentValue, type, assetID, amount));
    }

    public void removeAsset(int assetID) {
        assets.remove(assetID);
    }

    public double calculateNetWorth() {
        return assets.values().stream().mapToDouble(Asset::getAssetCurrentValue).sum();
    }

    /**
     * Calculates Zakat obligation (2.5% of net worth)
     *
     * @return Zakat amount in local currency
     * @see <a href="https://islamicfinance.com/zakat">Zakat Rules</a>
     */

    public double calculateZakat() {
        return .025 * calculateNetWorth();
    }

    public void listAssets() {
        if (assets.isEmpty()) {
            System.out.println("No assets to display\n");
            return;
        }

        System.out.println();
        double maxValue = assets.values().stream()
                .mapToDouble(asset -> asset.getAssetCurrentValue())
                .max()
                .orElse(1.0);

        for (Map.Entry<Integer, Asset> entry : assets.entrySet()) {
            Asset asset = entry.getValue();
            asset.listAsset();

        }
        System.out.println();
        for (Map.Entry<Integer, Asset> entry : assets.entrySet()) {
            Asset asset = entry.getValue();

            double value = asset.getAssetCurrentValue();
            int barLength = (int) ((value / maxValue) * 50);
            String bar = "█".repeat(barLength);
            System.out.printf("%s %.2f", bar, value);
            System.out.print(" - " + asset.getAssetName() + "\n\n");

        }
    }


    public boolean assetExists(int assetID) {
        return assets.containsKey(assetID);
    }


}
