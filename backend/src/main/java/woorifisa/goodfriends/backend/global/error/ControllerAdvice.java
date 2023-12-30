package woorifisa.goodfriends.backend.global.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import woorifisa.goodfriends.backend.auth.exception.*;
import woorifisa.goodfriends.backend.global.error.dto.ErrorReportRequest;
import woorifisa.goodfriends.backend.global.error.dto.ErrorResponse;
import woorifisa.goodfriends.backend.infrastructure.oauth.exception.OAuthException;
import woorifisa.goodfriends.backend.order.exception.AlreadyOrderedException;
import woorifisa.goodfriends.backend.order.exception.NotFoundOrderException;
import woorifisa.goodfriends.backend.order.exception.InvalidProductOrderAccessException;
import woorifisa.goodfriends.backend.order.exception.ProductOwnerNotRegisterOrderException;
import woorifisa.goodfriends.backend.product.exception.*;
import woorifisa.goodfriends.backend.profile.exception.AlreadyExitPhoneProfileException;
import woorifisa.goodfriends.backend.profile.exception.NotFoundProfileException;
import woorifisa.goodfriends.backend.report.exception.AlreadyReportedException;
import woorifisa.goodfriends.backend.report.exception.ReportException;
import woorifisa.goodfriends.backend.user.exception.InvalidNicknameException;
import woorifisa.goodfriends.backend.user.exception.InvalidUserException;
import woorifisa.goodfriends.backend.user.exception.NotFoundUserException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)  // 클라이언트 에러: 400
    public ResponseEntity<ErrorResponse> handleMethodArgumentException(BindingResult bindingResult) {
        String errorMessage = bindingResult.getFieldErrors()
                .get(0)
                .getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({ // 클라이언트 에러: 400
            InvalidNicknameException.class,
            InvalidUserException.class,
            InvalidDescriptionException.class,
            ReportException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidData(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({ // 클라이언트 에러: 401
            EmptyAuthorizationHeaderException.class,
            InvalidTokenException.class,
    })
    public ResponseEntity<ErrorResponse> handleInvalidAuthorization(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler({ // 클라이언테 에러: 403
            NotFoundProfileException.class,
            InvalidProductAccessPermissionException.class,
            AuthorizationException.class,
            InactiveUserAccessException.class,
            AlreadyExitPhoneProfileException.class,
            ProductOwnerNotRegisterOrderException.class,
            InvalidProductOrderAccessException.class
    })
    public ResponseEntity<ErrorResponse> handleForbidden(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler({ // 클라이언트 에러: 404
            NotFoundOAuthTokenException.class,
            NotFoundTokenException.class,
            NotFoundProductException.class,
            NotFoundImageFileException.class,
            NotFoundUserException.class,
            NotFoundOrderException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundData(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class) //  클라이언트 에러: 405
    public ResponseEntity<ErrorResponse> handleNotSupportedMethod() {
        ErrorResponse errorResponse = new ErrorResponse("지원하지 않는 HTTP 메서드 요청입니다.");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    @ExceptionHandler({ // 클라이언트 에러: 406
            AlreadyOrderedException.class,
            AlreadyReportedException.class
    })
    public ResponseEntity<ErrorResponse> handleAlreadyOrderException(final RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
    }

    @ExceptionHandler(OAuthException.class) // 서버 에러: 500
    public ResponseEntity<ErrorResponse> handleOAuthException(final RuntimeException e) {
        log.error(e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(Exception.class) // 서버 에러: 500
    public ResponseEntity<ErrorResponse> handleUnexpectedException(final Exception e,
                                                                   final HttpServletRequest request) {
        ErrorReportRequest errorReportRequest = new ErrorReportRequest(request, e);
        log.error(errorReportRequest.getLogMessage(), e);

        ErrorResponse errorResponse = new ErrorResponse("서버에서 예상치 못한 에러가 발생했습니다.");
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
