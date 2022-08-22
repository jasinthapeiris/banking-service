package lk.example.bankingservice.exception;

import java.util.Date;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

		// handling Resource NotFoundException exception
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.NOT_FOUND,"404");
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		// handling httpMessageNot Readable  Eexception
		@ExceptionHandler(HttpMessageNotReadableException.class)
		public ResponseEntity<?> httpMessageNotReadableExceptionHandling(HttpMessageNotReadableException exception, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.BAD_REQUEST,"500");
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

		}
				
		// handling Data Integrity Violation Eexception
		@ExceptionHandler(DataIntegrityViolationException.class)
		public ResponseEntity<?> dataIntegrityViolationHandling(DataIntegrityViolationException exception, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR,"500");
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// handling Already Exists Exception
		@ExceptionHandler(AlreadyExistsException.class)
		public ResponseEntity<?> alreadyExistsException(AlreadyExistsException exception, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),HttpStatus.CONFLICT,"409");
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
		
		
		 // handling Validation fail Exception
		  @ExceptionHandler(javax.validation.ConstraintViolationException.class) 
		  public ResponseEntity<?> constraintViolationException(javax.validation.ConstraintViolationException exception, WebRequest request){ ErrorDetails errorDetails = new
			  ErrorDetails(new Date(), exception.getMessage(),request.getDescription(false),HttpStatus.UNPROCESSABLE_ENTITY,"422"); 
			  return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY); }
		 
		// handling global exception
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
			ErrorDetails errorDetails = 
					new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,"500");
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
