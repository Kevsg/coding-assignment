package job.ata.ata_exam.util;

public class SalaryCleaner {
    public static float parseStrToFloat(String s) {
        if(s.isEmpty()) {
            return Float.parseFloat("0");
        }
        String result = s.replaceAll("[^0-9]+", "").trim();
        if(result.isEmpty()) {
            return Float.parseFloat("0");
        }
        return Float.parseFloat(result);
    }
}
