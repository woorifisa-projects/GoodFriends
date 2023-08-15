package woorifisa.goodfriends.backend.admin.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.admin.domain.Admin;
import woorifisa.goodfriends.backend.admin.domain.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String login(String adminId, String password){
        // adminId 없음 구현해야함


        // password 틀림 구현해야함


        // 앞에서 Exception 안났으면 토큰 발행 구현해야함


        return "token"; // 우선 임의의 값 출력
    }
}
