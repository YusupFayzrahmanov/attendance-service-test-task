package attendanceservice.attendanceservicetesttask.web.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateVisitRequestDTO {
    @NotNull
    @Min(0)
    private Long externalUserId;
    @NotNull
    @Min(0)
    private Long externalPageId;

    public Long getExternalUserId() { return externalUserId; }

    public void setExternalUserId(Long externalUserId) { this.externalUserId = externalUserId; }

    public Long getExternalPageId() { return externalPageId; }

    public void setExternalPageId(Long externalPageId) { this.externalPageId = externalPageId; }
}
