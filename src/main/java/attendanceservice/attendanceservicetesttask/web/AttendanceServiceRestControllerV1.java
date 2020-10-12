package attendanceservice.attendanceservicetesttask.web;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import attendanceservice.attendanceservicetesttask.service.UserService;
import attendanceservice.attendanceservicetesttask.service.VisitEventService;
import attendanceservice.attendanceservicetesttask.web.DTO.CreateVisitRequestDTO;
import attendanceservice.attendanceservicetesttask.web.DTO.StatisticVisitEventDTO;
import attendanceservice.attendanceservicetesttask.web.DTO.UniqueVisitPageDTO;
import attendanceservice.attendanceservicetesttask.web.DTO.VisitEventConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/attendance-service/visit-events")
public class AttendanceServiceRestControllerV1 {
    private VisitEventService visitEventService;
    private UserService userService;

    public AttendanceServiceRestControllerV1(VisitEventService visitEventService, UserService userService) {
        this.visitEventService = visitEventService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<List<UniqueVisitPageDTO>> createVisitEvent(@RequestBody @Valid CreateVisitRequestDTO requestBody) {
        User requestUser = userService.getOneByExternalIdOrCreateIfNotExist(requestBody.getExternalUserId());
        VisitEvent visitEvent = visitEventService
                .createVisitEvent(requestUser.getId(), requestBody.getExternalPageId(), new Date());
        Map<WebPage, Integer> userWebPages = visitEventService
                .getUserUniqueVisitEventCountForEveryPage(requestUser);
        return ResponseEntity.ok(userWebPages.entrySet().stream()
                .map(VisitEventConverter::convertFromPageMap).collect(Collectors.toList()));
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticVisitEventDTO> getVisitEventStatistics(@RequestParam(name ="start_date") @NotNull Date startDate,
                                                                          @RequestParam(name = "end_date") @NotNull Date endDate,
                                                                          @RequestParam(name = "user_id") Long externalId) {
        StatisticVisitEventDTO statisticVisitEventDTO = new StatisticVisitEventDTO();
        List<VisitEvent> visitEventsForPeriod = visitEventService.getVisitEventsForPeriod(startDate, endDate);
        statisticVisitEventDTO.setCommonCountOfVisits(visitEventsForPeriod.size());
        if(externalId == null){
            User user = userService.getOneByExternalIdOrCreateIfNotExist(externalId);
            statisticVisitEventDTO.setCountOfUniqueUsers(visitEventService
                    .getUserUniqueVisitEventCountForEveryPage(user).size());
        }
        else
            statisticVisitEventDTO.setCountOfUniqueUsers(1);
        statisticVisitEventDTO.setCountOfRegularUsers(visitEventService
                .getUsersForVisitEventsCountForUniquePage(visitEventsForPeriod, 10).size());
        return ResponseEntity.ok(statisticVisitEventDTO);
    }

}
