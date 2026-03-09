package ee.siemann.laudadebroneerimine.repository;

import ee.siemann.laudadebroneerimine.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
}
