import java.io.Serializable;
class Asset implements Serializable{
    private int assetID;
    private String assetName;
    private double assetPurchasePrice;
    private double assetCurrentValue;
    private String assetType;
    private double amount;

    public Asset(String assetName, double assetPurchasePrice, double assetCurrentValue, String assetType, int assetID, double amount) {
        this.assetName = assetName;
        this.assetPurchasePrice = assetPurchasePrice;
        this.assetCurrentValue = assetCurrentValue;
        this.assetID = assetID;
        this.assetType = assetType;
        this.amount = amount;
    }

    public void getAssetDetails() {
        System.out.println("Asset ID: " + assetID);
        System.out.println("Asset Name: " + assetName);
        System.out.println("Asset Purchase Price: " + assetPurchasePrice);
        System.out.println("Asset Current Value: " + assetCurrentValue);
        System.out.println("Asset Type: " + assetType);
        System.out.println("Asset Amount: " + amount);

    }

    public void getAssetProfit() {
        System.out.println("Asset profit: " + (assetPurchasePrice - assetCurrentValue));
    }

    public double  getAssetCurrentValue() {
        return assetCurrentValue;
    }

    public void editAssetDetails(String assetName, double assetPurchasePrice, double assetCurrentValue, String assetType, double assetAmount) {
        this.assetName = assetName;
        this.assetPurchasePrice = assetPurchasePrice;
        this.assetCurrentValue = assetCurrentValue;
        this.assetType = assetType;
        this.amount = assetAmount;
    }

    public void listAsset() {
        System.out.println("----- Asset Summary -----");
        System.out.println("Asset ID: " + assetID);
        System.out.println("Name: " + assetName);
        System.out.println("Type: " + assetType);
        System.out.println("Purchase Price: " + assetPurchasePrice);
        System.out.println("Current Value: " + assetCurrentValue);
        System.out.println("Asset Amount: " + amount);
        System.out.println("Profit/Loss: " + (assetCurrentValue - assetPurchasePrice));
        System.out.println("--------------------------");
    }

    public String getAssetName() {
        return assetName;
    }
}
