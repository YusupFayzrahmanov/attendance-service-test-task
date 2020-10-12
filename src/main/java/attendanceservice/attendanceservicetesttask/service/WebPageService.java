package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.WebPage;

import java.util.List;

public interface WebPageService {
    WebPage getOne(Long id);
    WebPage create(WebPage webPage);
    WebPage getOneByExternalIdOrCreateIfNotExists(Long id);
}
