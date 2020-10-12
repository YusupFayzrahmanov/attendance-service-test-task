package attendanceservice.attendanceservicetesttask.web.DTO;

public class StatisticVisitEventDTO {
    private int commonCountOfVisits;
    private int countOfUniqueUsers;
    private int countOfRegularUsers;

    public int getCommonCountOfVisits() { return commonCountOfVisits; }

    public void setCommonCountOfVisits(int commonCountOfVisits) { this.commonCountOfVisits = commonCountOfVisits; }

    public int getCountOfUniqueUsers() { return countOfUniqueUsers; }

    public void setCountOfUniqueUsers(int countOfUniqueUsers) { this.countOfUniqueUsers = countOfUniqueUsers; }

    public int getCountOfRegularUsers() { return countOfRegularUsers; }

    public void setCountOfRegularUsers(int countOfRegularUsers) { this.countOfRegularUsers = countOfRegularUsers; }
}
