package attendanceservice.attendanceservicetesttask.service.impl;

import attendanceservice.attendanceservicetesttask.dao.WebPageDAO;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import attendanceservice.attendanceservicetesttask.service.WebPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class WebPageServiceImpl implements WebPageService {
    private static final Logger logger = LoggerFactory.getLogger(WebPageServiceImpl.class);
    private final WebPageDAO webPageDAO;

    @Autowired
    public WebPageServiceImpl(WebPageDAO webPageDAO) {
        this.webPageDAO = webPageDAO;
    }

    @Override
    public WebPage getByExternalId(Long externalId) {
        WebPage webPage = webPageDAO.getByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("Web page not fount with external id = " + externalId));
        return webPage;
    }

    @Override
    public WebPage createWebPage(WebPage webPage) {
        WebPage createdWebPage = webPageDAO.save(webPage);
        return createdWebPage;
    }

    @Override
    public WebPage getByExternalIdOrCreateIfNotExists(Long externalId) {
        WebPage webPage;
        try {
            webPage = getByExternalId(externalId);
        }
        catch (EntityNotFoundException e) {
            logger.error(e.getMessage(), e);
            WebPage newWebPage = new WebPage.Builer()
                    .setExternalId(externalId)
                    .setName(externalId.toString())
                    .build();
            webPage = createWebPage(newWebPage);
        }
        return webPage;
    }
}
