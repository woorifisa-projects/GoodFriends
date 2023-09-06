package woorifisa.goodfriends.backend.declaration.exception;

public class DeclarationException extends RuntimeException {

    public DeclarationException(String message) {
        super(message);
    }
    public static class ContentLengthException extends DeclarationException {
        public ContentLengthException(final int allowedLength, final String inputValue) {
            super(String.format(
                    "신고 내용의 길이가 최대 길이를 초과했습니다. - request info { allowed length : %d, input_value_length : %d }"
                    , allowedLength
                    , inputValue.length())
            );
        }
    }
}
