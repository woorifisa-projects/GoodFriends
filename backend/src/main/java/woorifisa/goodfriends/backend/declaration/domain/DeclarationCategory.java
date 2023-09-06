package woorifisa.goodfriends.backend.declaration.domain;

public enum DeclarationCategory {
    PROHIBITED_GOODS, // 판매금지 물품이에요
    USED_TRANSACTION_POST, // 중고 거래 게시글이 아니에요
    PROFESSIONAL_VENDOR, // 전문 판매업자 같아요
    FRAUDULENT_WRITING, // 사기 글이에요
    TRANSACTION_AND_REFUND_DISPUTE, // 거래/환불 분쟁
    OTHER_REASONS, // 기타 사유 선택
}
