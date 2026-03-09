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

    public List<RestaurantTable> findAvailableTables(int peopleCount, LocalDateTime time) {
        // 1. Võtame kõik lauad, kuhu inimesed ära mahuvad
        List<RestaurantTable> suitableTables = tableRepository.findAll().stream()
                .filter(table -> table.getPlaces() >= peopleCount)
                .collect(Collectors.toList());

        // 2. Filtreerime välja need, mis on sel ajal juba kinni
        return suitableTables.stream()
                .filter(table -> isTableFree(table, time))
                .collect(Collectors.toList());
    }

    private boolean isTableFree(RestaurantTable table, LocalDateTime time) {
        // Siia tuleb loogika, mis kontrollib ReservationRepository-st,
        // kas sellel laual on broneeringuid, mis kattuvad valitud ajaga.
        return true;
    }
}
