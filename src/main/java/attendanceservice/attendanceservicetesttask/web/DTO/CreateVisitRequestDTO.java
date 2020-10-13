package attendanceservice.attendanceservicetesttask.web.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateVisitRequestDTO {
    @NotNull(message = "External user id is null")
    @Min(value = 0, message = "External user id less than 0")
    private Long externalUserId;
    @NotNull(message = "External page id is null")
    @Min(value = 0, message = "External page id less than 0")
    private Long externalPageId;

    public Long getExternalUserId() { return externalUserId; }

    public void setExternalUserId(Long externalUserId) { this.externalUserId = externalUserId; }

    public Long getExternalPageId() { return externalPageId; }

    public void setExternalPageId(Long externalPageId) { this.externalPageId = externalPageId; }
}
