package utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //密钥 -- 根据实际项目，这里可以做成配置
    public static final String KEY = "022bdc63c3c5a45879ee6581508b9d03adfec4a4658c0ab3d722e50c91a351c42c231cf43bb8f86998202bd301ec52239a74fc0c9a9aeccce604743367c9646b";

    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_AUTH = "authorization";
    public static final String HEADER_USERID = "id";

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(KEY.getBytes());
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return key;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @return
     */
    public static String generateToken(String id) {
        //Token 超时时间，30分钟后
        Date date = DateUtils.addTime(Calendar.MINUTE, 30);

        //todo 优化token的生层规则
        HashMap<String, Object> data = new HashMap<>();
        data.put(HEADER_USERID, id);

        String jwt = Jwts.builder()     // 这里其实就是new一个JwtBuilder，设置jwt的body
                //.setClaims(claims)      // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //.setIssuedAt(now)     // iat: jwt的签发时间
                //.setIssuer(issuer)    // issuer：jwt签发人
                .setSubject(JSON.toJSONString(data))  // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(SignatureAlgorithm.HS512, generalKey())     // 设置签名使用的签名算法和签名使用的秘钥
                .setExpiration(date)    // 设置签名使用的签名算法和签名使用的秘钥
                .compact();
        return TOKEN_PREFIX + " " + jwt;
    }

    /**
     * 验证jwt
     *
     * @param token
     * @return
     */
    public static Map<String, String> validateTokenAndUser(String token, String userName) {
        Map<String, String> tokenResultMap = new HashMap<>();
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(userName)) {
            return tokenResultMap;
        }
        tokenResultMap = validateToken(token);
        //判断传入的userid和token是否匹配
        String userIdOri = tokenResultMap.get(HEADER_USERID);
        if (!userName.equals(userIdOri)) {
            return new HashMap<String, String>();
        }
        return tokenResultMap;
    }

    /**
     * 验证jwt
     *
     * @param token
     * @return
     */
    public static Map<String, String> validateToken(String token) {
        HashMap<String, String> tokenMap = new HashMap<String, String>();
        if (StringUtils.isEmpty(token)) {
            return tokenMap;
        }
        try {
            Map<String, Object> tokenBody = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String id = String.valueOf(tokenBody.get(HEADER_USERID));
            tokenMap.put(HEADER_USERID, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenMap;
    }
}
