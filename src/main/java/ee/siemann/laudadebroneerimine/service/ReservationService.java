package ee.siemann.laudadebroneerimine.service;

import ee.siemann.laudadebroneerimine.model.RestaurantTable;
import ee.siemann.laudadebroneerimine.repository.ReservationRepository;
import ee.siemann.laudadebroneerimine.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    /**
     * This method finds the tables that are suitable
     * @param peopleCount how many people there are coming
     * @param features what kind of preferences they have
     * @param time what time they want to make the reservation
     * @return List of suitable tables for the costumer
     */
    public List<RestaurantTable> findAvailableTables(int peopleCount, List<String> features, LocalDateTime time) {
        List<RestaurantTable> suitableTables = tableRepository.findAll().stream()
                .filter(table -> table.getPlaces() >= peopleCount)
                .collect(Collectors.toList());
        return suitableTables.stream()
                .filter(table -> isTableFree(table, time))
                .collect(Collectors.toList());
    }

    /**
     * This method checks if a table is free
     * @param table the table we are looking at
     * @param time time when the reservation should start
     * @return true if the table is free, false if not
     */
    private boolean isTableFree(RestaurantTable table, LocalDateTime time) {
        LocalDateTime requestedEnd = time.plusHours(2); // Let's assume one reservation last for 2 hours
        return reservationRepository.findByRestaurantTable(table).stream()
                .noneMatch(res ->
                        time.isBefore(res.getEndTime()) &&
                                requestedEnd.isAfter(res.getStartTime())
                );
    }
}
