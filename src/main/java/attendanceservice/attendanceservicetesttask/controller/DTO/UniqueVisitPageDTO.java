package attendanceservice.attendanceservicetesttask.controller.DTO;

import attendanceservice.attendanceservicetesttask.domain.WebPage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static class Builder {
        private Long externalPageId;
        private int count;

        public Builder setExternalPageId(Long externalPageId) {
            this.externalPageId = externalPageId;
            return this;
        }

        public Builder setCount(int count) {
            this.count = count;
            return this;
        }

        public UniqueVisitPageDTO build() {
            return new UniqueVisitPageDTO(externalPageId, count);
        }

        public List<UniqueVisitPageDTO> buildListFromPageMap(Map<WebPage, Integer> pageMap) {
            return pageMap.entrySet().stream().map(this::buildFromPageMapEntry).collect(Collectors.toList());
        }

        public UniqueVisitPageDTO buildFromPageMapEntry(Map.Entry<WebPage, Integer> webPage){
            this.setExternalPageId(webPage.getKey().getExternalId());
            this.setCount(webPage.getValue());
            return this.build();
        }
    }
}
