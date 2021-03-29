package attendanceservice.attendanceservicetesttask.controller;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import attendanceservice.attendanceservicetesttask.service.UserService;
import attendanceservice.attendanceservicetesttask.service.VisitEventService;
import attendanceservice.attendanceservicetesttask.controller.DTO.CreateVisitRequestDTO;
import attendanceservice.attendanceservicetesttask.controller.DTO.StatisticVisitEventDTO;
import attendanceservice.attendanceservicetesttask.controller.DTO.UniqueVisitPageDTO;
import attendanceservice.attendanceservicetesttask.service.VisitEventStatisticsService;
import attendanceservice.attendanceservicetesttask.service.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/attendance-service/visit-events")
public class AttendanceServiceRestControllerV1 {
    private final VisitEventService visitEventService;
    private final UserService userService;
    private final WebPageService webPageService;
    private final VisitEventStatisticsService visitEventStatisticsService;

    @Autowired
    public AttendanceServiceRestControllerV1(VisitEventService visitEventService,
                                             UserService userService, WebPageService webPageService,
                                             VisitEventStatisticsService visitEventStatisticsService) {
        this.visitEventService = visitEventService;
        this.userService = userService;
        this.webPageService = webPageService;
        this.visitEventStatisticsService = visitEventStatisticsService;
    }


    @PostMapping()
    public CompletableFuture<ResponseEntity<List<UniqueVisitPageDTO>>> createVisitEvent(@RequestBody @Valid CreateVisitRequestDTO requestBody) {
        User requestUser = userService.getByExternalIdOrCreateIfNotExists(requestBody.getExternalUserId());
        WebPage webPage = webPageService.getByExternalIdOrCreateIfNotExists(requestBody.getExternalPageId());
        CompletableFuture<VisitEvent> visitEvent = visitEventService.createVisitEventAsync(requestUser, webPage, new Date());
        Map<WebPage, Integer> userWebPages = visitEventStatisticsService.getUserUniqueVisitEventCountForEveryPage(requestUser);
        List<UniqueVisitPageDTO> userUniqueVisits = new UniqueVisitPageDTO.Builder().buildListFromPageMap(userWebPages);
        ResponseEntity<List<UniqueVisitPageDTO>> response = ResponseEntity.status(HttpStatus.CREATED).body(userUniqueVisits);
        return CompletableFuture.completedFuture(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/statistics")
    public ResponseEntity<StatisticVisitEventDTO> getVisitEventStatistics(@RequestParam(name ="start_date", required = true)
                                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                          @RequestParam(name = "end_date", required = true)
                                                                          @DateTimeFormat(pattern = "yyyy-MM-dd")  Date endDate,
                                                                          @RequestParam(name = "user_id", required = false) @Null Long externalUserId) {
        Long countOfVisits = visitEventStatisticsService.getCountOfVisitEventsForPeriod(startDate, endDate);
        Long countOfUniqueUsers = externalUserId == null ?
                visitEventStatisticsService.getCountOfUniqueUsersOfVisitForPeriod(startDate, endDate) : 1L;
        Long countOfRegularUsers = visitEventStatisticsService.getCountOfUsersWhoVisitUniqueWebPageForPeriodMoreThan(10, startDate, endDate);
        StatisticVisitEventDTO statisticVisitEvent = new StatisticVisitEventDTO.Builder()
                .setCommonCountOfVisits(countOfVisits)
                .setCountOfUniqueUsers(countOfUniqueUsers)
                .setCountOfRegularUsers(countOfRegularUsers)
                .build();
        return ResponseEntity.ok(statisticVisitEvent);
    }

}
