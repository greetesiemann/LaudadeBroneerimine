package ee.siemann.laudadebroneerimine.repository;

import ee.siemann.laudadebroneerimine.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
