package woorifisa.goodfriends.backend.declaration.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.declaration.application.DeclarationService;
import woorifisa.goodfriends.backend.declaration.dto.request.DeclarationSaveRequest;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/api/report")
@RestController
public class DeclarationController {

    private final DeclarationService declarationService;

    public DeclarationController(DeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @PostMapping("/{productId}/new")
    public ResponseEntity<Void> saveReport(@AuthenticationPrincipal LoginUser loginUser,
                                           @PathVariable Long productId,
                                           @Valid @RequestBody DeclarationSaveRequest request) {
        Long declarationId = declarationService.saveReport(loginUser, productId, request);
        return ResponseEntity.created(URI.create("/report/" + declarationId)).build();
    }
}
