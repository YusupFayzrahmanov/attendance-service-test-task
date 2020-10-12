package attendanceservice.attendanceservicetesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"attendanceservice.attendanceservicetesttask.domain"})
public class AttendanceServiceTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceServiceTestTaskApplication.class, args);
	}

}
