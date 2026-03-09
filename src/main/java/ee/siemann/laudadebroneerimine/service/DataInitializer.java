package ee.siemann.laudadebroneerimine.service;

import ee.siemann.laudadebroneerimine.model.Reservation;
import ee.siemann.laudadebroneerimine.model.RestaurantTable;
import ee.siemann.laudadebroneerimine.repository.ReservationRepository;
import ee.siemann.laudadebroneerimine.repository.TableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;
    private final Random random = new Random();

    public DataInitializer(TableRepository tableRepository, ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        tableRepository.save(new RestaurantTable(null, 1, 2, List.of("aken", "vaikne"), 1, 1, false));
        tableRepository.save(new RestaurantTable(null, 2, 4, List.of("mängunurk"), 1, 2 , false));
        tableRepository.save(new RestaurantTable(null, 3, 2, List.of("privaatne"), 2, 1,  false));
        tableRepository.save(new RestaurantTable(null, 4, 8, List.of("aken"), 2, 2,  false));
        tableRepository.save(new RestaurantTable(null, 5, 4, List.of("terrass"), 3, 1, false));
        System.out.println("Tables are added");
        List<RestaurantTable> tables = tableRepository.findAll();
        for (RestaurantTable table : tables) {
            if (random.nextBoolean()) {
                LocalDateTime startTime = LocalDateTime.now()
                        .withHour(17 + random.nextInt(4)) // random time 17:00 - 21:00
                        .withMinute(0).withSecond(0).withNano(0);

                Reservation res = Reservation.builder()
                        .restaurantTable(table)
                        .startTime(startTime)
                        .endTime(startTime.plusHours(2))
                        .personCount(random.nextInt(table.getPlaces()) + 1)
                        .customerName("Random client")
                        .build();

                reservationRepository.save(res);
            }
        }

    }
}
