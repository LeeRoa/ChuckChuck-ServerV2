package cc.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 비밀번호 해싱 처리
     * @param rawPassword 평문 비밀번호
     * @return 해시된 비밀번호
     */
    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 비밀번호 비교
     * @param rawPassword 사용자가 입력한 평문 비밀번호
     * @param encodedPassword DB에 저장된 해시값
     * @return 일치 여부
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
