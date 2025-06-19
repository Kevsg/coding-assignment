package job.ata.ata_exam.controller;

import job.ata.ata_exam.exception.ValidationFailException;
import job.ata.ata_exam.service.SalaryService;
import job.ata.ata_exam.validator.SalaryValidator;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private SalaryValidator salaryValidator;

    @GetMapping("/job-data")
    public Object queryJobData(
            @RequestParam Map<String, String> allParams
    ) throws IOException, NoSuchFieldException, IllegalAccessException {
        Triple<ArrayList<String>,ArrayList<String>,ArrayList<String>> operations = salaryValidator.validate(allParams);
        return salaryService.queryJobData(operations);
    }


    @ExceptionHandler(ValidationFailException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(ValidationFailException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
