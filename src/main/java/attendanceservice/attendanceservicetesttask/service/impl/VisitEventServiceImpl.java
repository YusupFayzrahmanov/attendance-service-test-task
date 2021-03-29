package attendanceservice.attendanceservicetesttask.service.impl;

import attendanceservice.attendanceservicetesttask.dao.VisitEventDAO;
import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import attendanceservice.attendanceservicetesttask.service.VisitEventService;
import attendanceservice.attendanceservicetesttask.service.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitEventServiceImpl implements VisitEventService {
    private final VisitEventDAO visitEventDAO;

    @Autowired
    public VisitEventServiceImpl(VisitEventDAO visitEventDAO) {
        this.visitEventDAO = visitEventDAO;
    }


    @Override
    public VisitEvent createVisitEvent(User user, WebPage webPage, Date eventDate) {
        VisitEvent visitEvent = new VisitEvent(webPage, user, eventDate);
        return visitEventDAO.save(visitEvent);
    }

    @Override
    @Async
    public CompletableFuture<VisitEvent> createVisitEventAsync(User user, WebPage webPage, Date eventDate) {
        //Моделирование сложной и долгой операции для демонстрации асинхронности
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        VisitEvent visitEvent = createVisitEvent(user, webPage, eventDate);
        return CompletableFuture.completedFuture(visitEvent);
    }
}
