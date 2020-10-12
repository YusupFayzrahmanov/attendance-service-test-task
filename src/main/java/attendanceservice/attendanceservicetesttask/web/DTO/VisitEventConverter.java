package attendanceservice.attendanceservicetesttask.web.DTO;

import attendanceservice.attendanceservicetesttask.domain.WebPage;

import java.util.Map;

public class VisitEventConverter {
    public static UniqueVisitPageDTO convertFromPageMap(Map.Entry<WebPage, Integer> webPage){
        return new UniqueVisitPageDTO(webPage.getKey().getExternalId(), webPage.getValue());
    }
}
