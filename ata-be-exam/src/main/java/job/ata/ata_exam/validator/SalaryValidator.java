package job.ata.ata_exam.validator;

import job.ata.ata_exam.exception.ValidationFailException;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SalaryValidator {

    public Triple<ArrayList<String>, ArrayList<String>, ArrayList<String>> validate(Map<String, String> parameters) {

        ArrayList<String> selectOperation = new ArrayList<>();
        ArrayList<String> filterOperation = new ArrayList<>();
        ArrayList<String> sortOperation = new ArrayList<>();

        // Handle request params
        for (Map.Entry<String, String> entry : parameters.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            // Select Case
            if (Objects.equals(key, "select")) {
                selectOperation = new ArrayList<>(Arrays.asList(value.split("\\|")));
                this.validateSelectParams(selectOperation);
            }

            // Filter Case
            else if (Objects.equals(key, "where")) {
                String[] x = value.split("\\|");
                if (x.length % 3 != 0) {
                    throw new ValidationFailException("where parameter is invalid");
                }
                // For Loop validate and add
                for (int i = 0; i < x.length; i += 3) {
                    validateFilterParams(x[i], x[i + 1], x[i + 2]);
                    filterOperation.add(x[i]);
                    filterOperation.add(x[i + 1]);
                    filterOperation.add(x[i + 2]);
                }
            }


            // Order by case
            else if (Objects.equals(key, "sort")) {
                String[] x = value.split("\\|");
                if (x.length % 2 != 0) {
                    throw new ValidationFailException("sort parameter is invalid");
                }
                // For Loop validate and add
                for (int i = 0; i < x.length; i += 2) {
                    validateSortParams(x[i], x[i + 1]);
                    sortOperation.add(x[i]);
                    sortOperation.add(x[i + 1]);
                }
            }
        }

        return new Triple<>(selectOperation, filterOperation, sortOperation);
    }

    private void validateSelectParams(ArrayList<String> fields) {
        List<String> colList = Arrays.asList("timestamp", "employer", "location", "jobTitle", "yearsAtEmployer", "yearsOfExperience", "salary", "signBonus", "annualBonus", "annualStockValue", "gender", "comments");

        for (String field : fields) {
            boolean colInvalid = colList.stream().noneMatch(s -> s.equalsIgnoreCase(field));
            if (colInvalid) {
                throw new ValidationFailException(String.format("Field %s is not allowed", field));
            }
        }
    }

    private void validateFilterParams(String col, String operator, String value) {

        // Col Check
        List<String> colList = Arrays.asList("timestamp", "employer", "location", "jobTitle", "yearsAtEmployer", "yearsOfExperience", "salary", "signBonus", "annualBonus", "annualStockValue", "gender", "comments");
        boolean colInValid = colList.stream().noneMatch(s -> s.equalsIgnoreCase(col));
        if (colInValid) {
            throw new ValidationFailException(String.format("Column %s is not allowed", col));
        }

        // Operator Check
        List<String> operatorList = Arrays.asList("eq", "gte", "gt", "lt", "lte", "neq");
        boolean operatorInValid = operatorList.stream().noneMatch(s -> s.equalsIgnoreCase(operator));
        if (operatorInValid) {
            throw new ValidationFailException(String.format("Operator %s is not allowed", operator));
        }


        // Composite Check
        List<String> numberColList = Arrays.asList("yearsAtEmployer", "yearsOfExperience", "salary", "signBonus", "annualBonus", "annualStockValue");
        boolean isNumberCol = numberColList.stream().anyMatch(s -> s.equalsIgnoreCase(col));
        boolean valueContainAlphabet = containsAlphabet(value);

        if (isNumberCol && valueContainAlphabet) {
            throw new ValidationFailException(String.format("Value %s is not allowed for this operation", value));
        }


        List<String> valueColList = Arrays.asList("timestamp", "employer", "location", "jobTitle", "gender", "comments");
        boolean isValueCol = valueColList.stream().anyMatch(s -> s.equalsIgnoreCase(col));
        boolean compatibleOperator = (operator.equals("eq") || operator.equals("neq"));

        if (isValueCol && !compatibleOperator) {
            throw new ValidationFailException(String.format("Operator %s is not allowed for this operation", operator));
        }
    }

    private void validateSortParams(String col, String orderByType) {
        List<String> orderableCol = Arrays.asList("timestamp", "employer", "location", "jobTitle", "yearsAtEmployer", "yearsOfExperience", "salary", "signBonus", "annualBonus", "annualStockValue", "gender", "comments");
        List<String> orderByTypeList = Arrays.asList("ASC", "DESC");

        boolean colInvalid = orderableCol.stream().noneMatch(s -> s.equalsIgnoreCase(col));
        if (colInvalid) {
            throw new ValidationFailException(String.format("Fields %s is not allowed", col));
        }

        boolean orderByInvalid = orderByTypeList.stream().noneMatch(s -> s.equalsIgnoreCase(orderByType));
        if (orderByInvalid) {
            throw new ValidationFailException(String.format("Order By %s is not allowed", orderByType));
        }
    }

    // Helper function
    public boolean containsAlphabet(String str) {
        if (str == null) return false;
        Pattern pattern = Pattern.compile(".*[a-zA-Z,<>]+.*");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
