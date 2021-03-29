package attendanceservice.attendanceservicetesttask.dao.impl;

import attendanceservice.attendanceservicetesttask.dao.WebPageDAO;
import attendanceservice.attendanceservicetesttask.dao.repository.WebPageRepository;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class WebPageDAOImpl implements WebPageDAO {
    private final WebPageRepository webPageRepository;

    @Autowired
    public WebPageDAOImpl(WebPageRepository webPageRepository) {
        this.webPageRepository = webPageRepository;
    }

    @Override
    public Optional<WebPage> getByExternalId(Long externalId) {
        return webPageRepository.getFirstByExternalIdEquals(externalId);
    }

    @Override
    public WebPage save(WebPage webPage) {
        return webPageRepository.save(webPage);
    }
}
