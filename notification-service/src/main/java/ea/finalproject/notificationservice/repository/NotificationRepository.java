package ea.finalproject.notificationservice.repository;

import ea.finalproject.notificationservice.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByDateOfExpiry(LocalDate localDate);
}
