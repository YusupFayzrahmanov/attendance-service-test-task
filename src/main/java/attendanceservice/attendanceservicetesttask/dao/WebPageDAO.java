package attendanceservice.attendanceservicetesttask.dao;

import attendanceservice.attendanceservicetesttask.domain.WebPage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface WebPageDAO {
    Optional<WebPage> getByExternalId(Long externalId);

    WebPage save(WebPage webPage);
}
