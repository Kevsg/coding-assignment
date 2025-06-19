package job.ata.ata_exam.service;

import job.ata.ata_exam.Serializer.SalarySerializer;
import job.ata.ata_exam.model.Salary;
import job.ata.ata_exam.util.SalaryCleaner;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class SalaryService {

    @Autowired
    SalarySerializer salarySerializer;

    public Object queryJobData(
            Triple<ArrayList<String>, ArrayList<String>, ArrayList<String>> operations
    ) throws IOException, NoSuchFieldException, IllegalAccessException {

        ArrayList<String> selectOperation = operations.a;
        ArrayList<String> filterOperation = operations.b;
        ArrayList<String> sortOperation = operations.c;

        Salary[] salaries = salarySerializer.getSalaryData();


        // Filter Operator
        ArrayList<Salary> output = new ArrayList<>();
        for (Salary item : salaries) {
            if (isPassFilter(filterOperation, item)) {
                output.add(item);
            }
        }

        // Sort Operator
        output.sort((a, b) -> {
            try {
                return compareTo(a,b,sortOperation);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        // Select Operator
        ArrayList<HashMap<String, String>> filteredOutput = new ArrayList<>();
        List<String> colList = selectOperation;
        if (selectOperation.isEmpty()) {
            colList = Arrays.asList("timestamp", "employer", "location", "jobTitle", "yearsAtEmployer", "yearsOfExperience", "salary", "signBonus", "annualBonus", "annualStockValue", "gender", "comments");
        }
        for (Salary s : output) {
            HashMap<String, String> map = new HashMap<>();
            for (String column : colList) {
                Field field = s.getClass().getDeclaredField(column);
                field.setAccessible(true);
                Object value = field.get(s);
                map.put(column, value.toString());
            }
            filteredOutput.add(map);
        }

        return filteredOutput;
    }

    private Boolean isPassFilter(ArrayList<String> filterOperation, Salary salary) throws NoSuchFieldException, IllegalAccessException {
        boolean isPass = true;
        for (int i = 0; i < filterOperation.toArray().length; i += 3) {
            String validKey = filterOperation.get(i);
            String validOperator = filterOperation.get(i + 1);
            String validValue = filterOperation.get(i + 2);
            Field field = salary.getClass().getDeclaredField(validKey);
            field.setAccessible(true);
            Object value = field.get(salary);

            if (validOperator.equals("eq") && !value.equals(validValue)) {
                isPass = false;
            } else if (validOperator.equals("gte") && (SalaryCleaner.parseStrToFloat(value.toString()) < SalaryCleaner.parseStrToFloat(validValue))) {
                isPass = false;
            } else if (validOperator.equals("gt") && (SalaryCleaner.parseStrToFloat(value.toString()) <= SalaryCleaner.parseStrToFloat(validValue))) {
                isPass = false;
            } else if (validOperator.equals("lt") && (SalaryCleaner.parseStrToFloat(value.toString()) >= SalaryCleaner.parseStrToFloat(validValue))) {
                isPass = false;
            } else if (validOperator.equals("lte") && (SalaryCleaner.parseStrToFloat(value.toString()) > SalaryCleaner.parseStrToFloat(validValue))) {
                isPass = false;
            } else if (validOperator.equals("neq") && value.equals(validValue)) {
                isPass = false;
            }
        }
        return isPass;
    }

    public int compareTo(Salary salary1, Salary salary2, ArrayList<String> sortOperation) throws NoSuchFieldException, IllegalAccessException {

        int result = 0;
        for (int i = 0; i < sortOperation.size(); i += 2) {
            String col = sortOperation.get(i);
            String orderType = sortOperation.get(i + 1);

            Field field1 = salary1.getClass().getDeclaredField(col);
            field1.setAccessible(true);
            Object salary1Value = field1.get(salary1);

            Field field2 = salary2.getClass().getDeclaredField(col);
            field2.setAccessible(true);
            Object salary2Value = field2.get(salary2);


            result = compareCol(salary1Value.toString(), salary2Value.toString(), col, orderType);
            if (result != 0) {
                return result;
            }
        }
        return result;
    }


    public int compareCol(String val1, String val2, String col, String orderType) {

        // If float col
        List<String> numberColList = Arrays.asList("yearsAtEmployer", "yearsOfExperience", "salary", "signBonus", "annualBonus", "annualStockValue");
        boolean isNumberCol = numberColList.stream().anyMatch(s -> s.equalsIgnoreCase(col));
        if (isNumberCol) {
            float v1 = SalaryCleaner.parseStrToFloat(val1);
            float v2 = SalaryCleaner.parseStrToFloat(val2);
            if (orderType.equals("ASC")) {
                return Float.compare(v1, v2);

            } else
                return Float.compare(v2, v1);

        }

        // Value Col -> String Compare
        else {
            if (orderType.equals("ASC")) {
                return val1.compareTo(val2);
            } else
                return val2.compareTo(val1);
        }
    }


}