package cn.withstars.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-10
 * Time: 21:50
 * Mail: withstars@126.com
 */
public class PasswordUtil {

    // 密码加密函数
    public static String encodePasswd(String salt1, String salt2, String password){
        String algorithmName="md5";
        int hashIterations = 2;
        SimpleHash hash=new SimpleHash(algorithmName,password,salt1+salt2,hashIterations);
        String encodePassword = hash.toHex();
        return encodePassword;
    }
}
