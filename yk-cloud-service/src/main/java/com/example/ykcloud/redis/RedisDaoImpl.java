package com.example.ykcloud.redis;

import com.example.ykcloud.constant.Common;
import com.example.ykcloud.vo.EmpVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisDaoImpl implements RedisDao {
    private static final String HASH_REFERENCE = "emp";

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public List<EmpVo> findEmpList() {

        ObjectMapper objectMapper = new ObjectMapper();
        String objectList = redisTemplate.opsForValue().get(HASH_REFERENCE);
        if (StringUtils.isNotEmpty(objectList) && objectList.trim().length() > 0) {
            List<EmpVo> list = null;
            try {
                list = objectMapper.readValue(objectList, new TypeReference<List<EmpVo>>() {
                });
                if (ObjectUtils.isEmpty(list)) {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        return null;
    }

    @Override
    public void save(List<EmpVo> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String objectJson = objectMapper.writeValueAsString(list);
            redisTemplate.opsForValue().set(HASH_REFERENCE,objectJson, Common.DEFAULT_EXPIRATION_TIME, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        redisTemplate.opsForList().rightPushAll(HASH_REFERENCE, list);
    }
}
