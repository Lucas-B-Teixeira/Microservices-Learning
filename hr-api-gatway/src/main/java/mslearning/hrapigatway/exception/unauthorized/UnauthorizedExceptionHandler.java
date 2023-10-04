package mslearning.hrapigatway.exception.unauthorized;

import mslearning.hrapigatway.error.BaseBobyError;
import mslearning.hrapigatway.exception.unauthorized.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UnauthorizedExceptionHandler {
    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<BaseBobyError> handlerUnauthorizedException(UnauthorizedException ex){
        BaseBobyError error = BaseBobyError.builder()
                .errorCode("401")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}
