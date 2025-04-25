package fiap.tds.petshop.service.metrics;


import fiap.tds.petshop.repository.TutorRepository;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorMetricsService {

    private final TutorRepository tutorRepository;
    private final MeterRegistry meterRegistry;

    @Autowired
    public TutorMetricsService(TutorRepository tutorRepository, MeterRegistry meterRegistry) {
        this.tutorRepository = tutorRepository;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        tutorMetrics();
    }

    public void tutorMetrics() {
        long numeroTutores = tutorRepository.count();

        System.out.println("Número de tutores cadastrados: " + numeroTutores);

        meterRegistry.gauge("numero_tutores_cadastrados", numeroTutores);
    }
}
