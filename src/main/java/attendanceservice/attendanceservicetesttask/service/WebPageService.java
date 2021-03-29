package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.WebPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WebPageService {
    WebPage getByExternalId(Long externalId);

    WebPage createWebPage(WebPage webPage);

    WebPage getByExternalIdOrCreateIfNotExists(Long externalId);
}
