package ea.finalproject.uiservice.model;


public class PaymentWrapper {
    private String serviceProvider;
    private String planName;
    private String time;
    private Double price;

    public PaymentWrapper() {
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PaymentWrapper(String serviceProvider, String planName, String time, Double price) {
        this.serviceProvider = serviceProvider;
        this.planName = planName;
        this.time = time;
        this.price = price;
    }


}
