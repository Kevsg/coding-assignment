package job.ata.ata_exam.exception;

public class ValidationFailException extends RuntimeException {
    public ValidationFailException(String message) {
        super(message);
    }
}
