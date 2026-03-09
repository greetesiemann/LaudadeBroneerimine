package ee.siemann.laudadebroneerimine.service;

import ee.siemann.laudadebroneerimine.model.RestaurantTable;
import ee.siemann.laudadebroneerimine.repository.TableRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class DataInitializer implements CommandLineRunner {

    private final TableRepository tableRepository;

    public DataInitializer(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Loome mõned testlauad
        tableRepository.save(new RestaurantTable(null, 1, 2, List.of("aken", "vaikne"), 1, 1, false));
        tableRepository.save(new RestaurantTable(null, 2, 4, List.of("mängunurk"), 1, 2 , false));
        tableRepository.save(new RestaurantTable(null, 3, 2, List.of("privaatne"), 2, 1,  false));
        tableRepository.save(new RestaurantTable(null, 4, 8, List.of("aken"), 2, 2,  false));

        System.out.println("Testandmed on lisatud!");
    }
}
