package woorifisa.goodfriends.backend.declaration.dto.request;

import lombok.Getter;
import woorifisa.goodfriends.backend.declaration.domain.Content;
import woorifisa.goodfriends.backend.declaration.domain.DeclarationCategory;

import javax.validation.constraints.NotBlank;

@Getter
public class DeclarationSaveRequest {

    private DeclarationCategory declarationCategory;

    private Content content;

    public DeclarationSaveRequest(DeclarationCategory declarationCategory, Content content) {
        this.declarationCategory = declarationCategory;
        this.content = content;
    }
}
