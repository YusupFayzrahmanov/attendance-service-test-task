package attendanceservice.attendanceservicetesttask.controller.DTO;

public class StatisticVisitEventDTO {
    private long commonCountOfVisits;
    private long countOfUniqueUsers;
    private long countOfRegularUsers;

    public StatisticVisitEventDTO(long commonCountOfVisits, long countOfUniqueUsers, long countOfRegularUsers) {
        this.commonCountOfVisits = commonCountOfVisits;
        this.countOfUniqueUsers = countOfUniqueUsers;
        this.countOfRegularUsers = countOfRegularUsers;
    }

    public long getCommonCountOfVisits() { return commonCountOfVisits; }

    public void setCommonCountOfVisits(long commonCountOfVisits) { this.commonCountOfVisits = commonCountOfVisits; }

    public long getCountOfUniqueUsers() { return countOfUniqueUsers; }

    public void setCountOfUniqueUsers(long countOfUniqueUsers) { this.countOfUniqueUsers = countOfUniqueUsers; }

    public long getCountOfRegularUsers() { return countOfRegularUsers; }

    public void setCountOfRegularUsers(long countOfRegularUsers) { this.countOfRegularUsers = countOfRegularUsers; }

    public static class Builder {
        private long commonCountOfVisits;
        private long countOfUniqueUsers;
        private long countOfRegularUsers;

        public Builder setCommonCountOfVisits(long  commonCountOfVisits) {
            this.commonCountOfVisits = commonCountOfVisits;
            return this;
        }

        public Builder setCountOfUniqueUsers(long countOfUniqueUsers) {
            this.countOfUniqueUsers = countOfUniqueUsers;
            return this;
        }

        public Builder setCountOfRegularUsers(long countOfRegularUsers) {
            this.countOfRegularUsers = countOfRegularUsers;
            return this;
        }

        public StatisticVisitEventDTO build() {
            return new StatisticVisitEventDTO(commonCountOfVisits, countOfUniqueUsers, countOfRegularUsers);
        }
    }
}
