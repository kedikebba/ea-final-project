package ea.finalproject.uiservice.model;


public class Plan {
    private String id;
    private String planCode;
    private String planName;
    private double price;
    private String time;
    private String category;

    private ServiceProvider provider;

    public Plan(String planCode, String planName, double price, String time, String category) {
        this.planCode = planCode;
        this.planName = planName;
        this.price = price;
        this.time = time;
        this.category = category;
    }

    public Plan() {
    }

    public Plan(String id, String planCode, String planName, double price, String time, String category, ServiceProvider provider) {
        this.id = id;
        this.planCode = planCode;
        this.planName = planName;
        this.price = price;
        this.time = time;
        this.category = category;
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ServiceProvider getProvider() {
        return provider;
    }

    public void setProvider(ServiceProvider provider) {
        this.provider = provider;
    }
}

