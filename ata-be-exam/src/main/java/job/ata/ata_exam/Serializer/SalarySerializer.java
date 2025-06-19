package job.ata.ata_exam.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import job.ata.ata_exam.model.Salary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class SalarySerializer {

    public Salary[] getSalaryData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/static/salary_survey-3.json"), Salary[].class);
    }
}
