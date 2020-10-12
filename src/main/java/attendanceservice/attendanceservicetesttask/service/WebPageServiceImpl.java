package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.WebPage;
import attendanceservice.attendanceservicetesttask.repository.WebPageRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class WebPageServiceImpl implements WebPageService {
    private WebPageRepository webPageRepository;

    public WebPageServiceImpl(WebPageRepository webPageRepository) {
        this.webPageRepository = webPageRepository;
    }

    @Override
    public WebPage getOne(Long id) {
        WebPage webPage = webPageRepository.getOne(id);
        if(webPage == null)
            throw new EntityNotFoundException("Web page not found");
        return webPage;
    }

    @Override
    @Transactional
    public WebPage create(WebPage webPage) {
        return webPageRepository.save(webPage);
    }

    @Override
    @Transactional
    public WebPage getOneByExternalIdOrCreateIfNotExists(Long externalId) {
        WebPage webPage = webPageRepository.getFirstByExternalIdEquals(externalId)
                .orElseGet(() -> webPageRepository.save(WebPage.createFromExternal(externalId)));
        return webPage;
    }
}
