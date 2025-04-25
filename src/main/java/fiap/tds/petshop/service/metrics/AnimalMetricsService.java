package fiap.tds.petshop.service.metrics;

import fiap.tds.petshop.repository.AnimalRepository;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AnimalMetricsService {

    private final AnimalRepository animalRepository;
    private final MeterRegistry meterRegistry;
    private final AtomicReference<Double> pesoMedioRef = new AtomicReference<>(0.0);

    @Autowired
    public AnimalMetricsService(AnimalRepository animalRepository, MeterRegistry meterRegistry) {
        this.animalRepository = animalRepository;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        animalMetrics();
    }

    public void animalMetrics() {
        double pesoMedio = animalRepository.pesoMedioAnimais();

        if (pesoMedio != 0.0) {
            System.out.println("Peso médio para métrica: " + pesoMedio);
            pesoMedioRef.set(pesoMedio);
        } else {
            System.out.println("Nenhum dado disponível para calcular o peso médio.");
            pesoMedioRef.set(0.0);
        }

        meterRegistry.gauge("peso_medio_animais", pesoMedioRef, AtomicReference::get);
    }
}