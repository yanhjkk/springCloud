package com.example.ykcloud.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class TokenUtils {
    /**
     * 签名秘钥
     */
    private static final String SECRET = "yk12345678";
    /**
     * 签发人
     */
    private static final String issuer="yangkun";
    /**
     * token过期时间，单位:min
     */
    private static final Integer expireTime=5;

    /**
     * 从header中取token
     * @return
     */
    public static String getToken(){
        HttpServletRequest request =WebUtils.getRequest();
        String re=request.getHeader("token");
        return re;
    }

    /**
     * 创建token
     * @param json 需要放入token的参数，多个参数可以封装成json或者map
     * @return token
     */
    public static String createToken(JSONObject json) {
        try {
            // 加密方式
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withSubject(json.toString())
                    .withIssuer(issuer)
                    // 设置过期时间为60分钟后
                    .withExpiresAt(DateUtil.offsetMinute(new Date(), expireTime))
                    .withClaim("customString", "zhiyi")
                    .withArrayClaim("customArray", new Integer[]{1, 2, 3})
                    .sign(algorithm);
        } catch (Exception exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            System.out.println(exception.getMessage());
            return null;
        }
    }

    /**
     * 校验token合法性
     *
     * @param token to verify.
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // 验证签发人是否相同
                    .withIssuer(issuer)
                    .build();
            /*
             * 校验：
             * 格式校验：header.payload.signature
             * 加密方式校验 Header中的alg
             * 签名信息校验，防篡改
             * 载体Payload 中公有声明字段校验
             */
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            //Invalid signature/claims
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 解析token
     *
     * @param token to decode.
     */
    public static void decodeToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim customStringClaim = claims.get("customString");
            Claim customArrayClaim = claims.get("customArray");

            String issuer = jwt.getIssuer();
            String subject = jwt.getSubject();

            System.out.println(customStringClaim.asString());
            System.out.println(Arrays.toString(customArrayClaim.asArray(Integer.class)));
            System.out.println(issuer);
            System.out.println(JSONUtil.parseObj(subject).get("userId"));

        } catch (JWTDecodeException exception) {
            //Invalid token
            exception.printStackTrace();
        }
    }

    public static String getUserIdByToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        String subject = jwt.getSubject();
        String re=JSONUtil.parseObj(subject).get("userId").toString();
        return re;
    }


    public static void main(String[] args) {
        JSONObject subjectJson = new JSONObject();
        subjectJson.put("userId", 1);
        subjectJson.put("name", "ylc");
        String token = createToken(subjectJson);
        System.out.println("token:" + token);
        System.out.println("==============================================");
//
//        System.out.println("1 min exp,now verify result:" + verifyToke(token));
//        System.out.println("==============================================");
//
//        System.out.println("decode info:");
//        decodeToken(token);
//        System.out.println("================================================");
//        System.out.println("1 min later,verify result:"+verifyToke("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjoxLFwibmFtZVwiOlwieWxjXCJ9IiwiaXNzIjoiemhpeWkiLCJleHAiOjE2MDIzMzI2OTMsImN1c3RvbUFycmF5IjpbMSwyLDNdLCJjdXN0b21TdHJpbmciOiJ6aGl5aSJ9.v6AQ2mba8gGsHFpJ52EY4fwoN03gEDsCUUSafpPscyQ"));
//        System.out.println("================================================");
        System.out.println("userId:"+getUserIdByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjoxLFwibmFtZVwiOlwieWxjXCJ9IiwiaXNzIjoiemhpeWkiLCJleHAiOjE2MDIzMzM1MjEsImN1c3RvbUFycmF5IjpbMSwyLDNdLCJjdXN0b21TdHJpbmciOiJ6aGl5aSJ9.BKU-X6Bre9kCbsCtQgwDO6UE7znqbM84xy8dH6R7AiY"));
    }


}



