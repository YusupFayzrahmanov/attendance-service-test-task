package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.WebPage;

import java.util.List;

public interface WebPageService {
    WebPage getOne(String id);
    WebPage create(WebPage webPage);
}
