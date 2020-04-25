package backdropv1.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourseNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ExceptionDetails exdetails = new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exdetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception exx, WebRequest request) {
		ExceptionDetails exxdetails = new ExceptionDetails(new Date(), exx.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exxdetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
