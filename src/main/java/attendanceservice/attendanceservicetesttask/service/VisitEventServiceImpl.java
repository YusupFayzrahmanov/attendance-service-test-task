package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import attendanceservice.attendanceservicetesttask.repository.VisitEventRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitEventServiceImpl implements VisitEventService {
    private VisitEventRepository visitEventRepository;
    private UserService userService;
    private WebPageService webPageService;

    public VisitEventServiceImpl(VisitEventRepository visitEventRepository,
                                 UserService userService, WebPageService webPageService) {
        this.visitEventRepository = visitEventRepository;
        this.userService = userService;
        this.webPageService = webPageService;
    }
    @Override
    @Transactional
    public VisitEvent createVisitEvent(User user, WebPage webPage, Date eventDate) {
        VisitEvent visitEvent = new VisitEvent(webPage, user, eventDate);
        return visitEventRepository.save(visitEvent);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<VisitEvent> createVisitEvent(User user, Long externalPageId, Date eventDate)
            throws InterruptedException {
        //Моделирование сложной и долгой операции для демонстрации асинхронности
        Thread.sleep(10000);
        WebPage webPage = webPageService.getOneByExternalIdOrCreateIfNotExists(externalPageId);
        return CompletableFuture.completedFuture(createVisitEvent(user, webPage, eventDate));
    }

    @Override
    public Map<WebPage, Integer> getUserUniqueVisitEventCountForEveryPage(User user) {
        List<VisitEvent> userVisitEvents = visitEventRepository.findAllByUserEquals(user);
        return userVisitEvents.stream().collect(Collectors.groupingBy(VisitEvent::getPage)).entrySet().stream()
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue().size()));
    }

    @Override
    public List<VisitEvent> getVisitEventsForPeriod(Date startDate, Date endDate) {
        return visitEventRepository.findAllByEventDateBetween(startDate, endDate);
    }

    @Override
    public List<User> getUniqueUsersForVisitEvents(List<VisitEvent> visitEvents) {
        Map<User, List<VisitEvent>> userVisits = visitEvents.stream().collect(Collectors.groupingBy(VisitEvent::getUser));
        return userVisits.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public List<User> getUsersForVisitEventsCountForUniquePage(List<VisitEvent> visitEvents, int count) {
        Map<User, Map<WebPage, List<VisitEvent>>> userVisitEventMap = visitEvents.stream()
                .collect(Collectors.groupingBy(VisitEvent::getUser, Collectors.groupingBy(VisitEvent::getPage)));
        return userVisitEventMap.entrySet().stream().filter(entry -> entry.getValue().keySet().size() >= count)
                .map(user -> user.getKey()).collect(Collectors.toList());
    }
}
