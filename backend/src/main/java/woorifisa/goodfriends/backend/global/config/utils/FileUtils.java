package woorifisa.goodfriends.backend.global.config.utils;

import woorifisa.goodfriends.backend.product.exception.NoSuchImageFileException;

import java.util.UUID;

public class FileUtils {

    public static String generateUniqueFileName(String originalFileName) {
        // 이미지 파일 이름 중복 방지를 위해 고유한 파일명 생성(확장자(extension)는 유지)
        if(originalFileName == null) {
            throw new NoSuchImageFileException();
        }
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return UUID.randomUUID() + extension;
    }
}