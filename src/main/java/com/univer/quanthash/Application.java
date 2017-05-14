package com.univer.quanthash;

import com.univer.quanthash.dao.DeltaRepository;
import com.univer.quanthash.models.DeltaModel;
import com.univer.quanthash.random.RandomAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;


/**
 * Created by Vladislav on 14-Apr-17.
 */
@SpringBootApplication
public class Application {



    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(DeltaRepository repository) {
        return (args) -> {

            DeltaFunction.q = 16;

            RandomAlgorithm randomAlgorithm = new RandomAlgorithm(100);
            Set<DeltaModel> deltaModelsRand = randomAlgorithm.randomDelta(16, 8);

            DeltaModel minDeltaModel = deltaModelsRand.stream().min((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta())).get();
            //DeltaModel deltaModel1 = deltaModelsFull.stream().min((o1, o2) -> Double.compare(o1.getDelta(), o2.getDelta())).get();

            repository.save(deltaModelsRand);


            repository.findAll().forEach(System.out::println);
           // System.out.println("minFull: " + deltaModel1);

            System.out.println("minRand: " + minDeltaModel);
        };
    }


}
