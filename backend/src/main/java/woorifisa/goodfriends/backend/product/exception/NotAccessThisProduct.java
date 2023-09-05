package woorifisa.goodfriends.backend.product.exception;

public class NotAccessThisProduct extends RuntimeException {

    public NotAccessThisProduct(String message) {
        super(message);
    }

    public NotAccessThisProduct() {
        this("본인이 작성한 판매글만 수정/삭제 가능합니다.");
    }
}
