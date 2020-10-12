package attendanceservice.attendanceservicetesttask.web.DTO;

public class UniqueVisitPageDTO {
    private Long externalPageId;
    private int count;

    public UniqueVisitPageDTO(){}

    public UniqueVisitPageDTO(Long externalPageId, int count) {
        this.externalPageId = externalPageId;
        this.count = count;
    }

    public Long getExternalPageId() { return externalPageId; }

    public void setExternalPageId(Long externalPageId) { this.externalPageId = externalPageId; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }
}
