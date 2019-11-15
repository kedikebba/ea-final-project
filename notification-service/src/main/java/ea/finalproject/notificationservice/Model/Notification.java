package ea.finalproject.notificationservice.Model;

import java.time.LocalDateTime;

public class Notification {
    private String userId;
    private String firstName;
    private String email;
    private String packageSubscribedFor;
    private LocalDateTime dateOfSubscription;
    private LocalDateTime dateOfExpiry;

    public Notification() {
    }

    public Notification(String userId, String firstName, String email, String packageSubscribedFor, LocalDateTime dateOfSubscription, LocalDateTime dateOfExpiry) {
        this.userId = userId;
        this.firstName = firstName;
        this.email = email;
        this.packageSubscribedFor = packageSubscribedFor;
        this.dateOfSubscription = dateOfSubscription;
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPackageSubscribedFor() {
        return packageSubscribedFor;
    }

    public void setPackageSubscribedFor(String packageSubscribedFor) {
        this.packageSubscribedFor = packageSubscribedFor;
    }

    public LocalDateTime getDateOfSubscription() {
        return dateOfSubscription;
    }

    public void setDateOfSubscription(LocalDateTime dateOfSubscription) {
        this.dateOfSubscription = dateOfSubscription;
    }

    public LocalDateTime getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDateTime dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
