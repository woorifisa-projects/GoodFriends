package woorifisa.goodfriends.backend.admin.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.admin.domain.Admin;
import woorifisa.goodfriends.backend.admin.domain.AdminRepository;
import woorifisa.goodfriends.backend.admin.exception.InvalidAdminException;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String login(String adminId, String password){
        // adminId가 틀린 경우
        Admin selectedAdmin = adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> new InvalidAdminException(adminId + "와 일치하는 아이디가 없습니다."));

        // password가 틀린 경우
        if(!selectedAdmin.getPassword().equals(password)) {
            throw new InvalidAdminException("잘못된 비밀번호입니다.");
        }

        // 앞에서 Exception 안났으면 토큰 발행 구현해야함


        return "token"; // 우선 임의의 값 출력
    }
}
