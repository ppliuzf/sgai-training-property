package com.sgai.property.commonService.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.UserInfo;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.commonService.client.RestClient;
import com.sgai.property.commonService.constants.Constants;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.vo.RedisBean;
import com.sgai.property.commonService.vo.organ.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.*;

/**
 * description:对接组织平台相关接口
 * Created by llh on 2017/4/18.
 */
@Service
public class OrganServiceImpl {
    //@Value("${orgAppAuth.orgApiUrl}")
    private String orgApiUrl;
    //@Value("${accessToken.accessId}")
    private String accessId;
    //@Value("${accessToken.accessSecret}")
    private String accessSecret;
    //@Autowired
    private RedisClient redisClient;
    //@Autowired
    CommonEmpInfoServiceImpl empInfoService;
    private static final Long expire = 600L;

    public List<OrganUnitVo> getDeptDetail(String companyId, Long deptId) {
        String key = MessageFormat.format(Constants.REDIS_KEY_ORGAN_KEY, companyId) + "getDeptDetail:" + deptId;
        String deptDetailJson = (String) redisClient.get(key);
        if (StringUtils.isNotEmpty(deptDetailJson) && deptDetailJson.length() > 0) {
            return JSON.parseObject(deptDetailJson, new TypeReference<List<OrganUnitVo>>() {
            });
        } else {
            try {
                String organToken = getAccessToken(companyId + "");
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("accesstoken", organToken);
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
                HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
                ResponseEntity<String> response = restTemplate.postForEntity(orgApiUrl + "/uum/get_organ_unit_detail?ids=" + deptId, requestEntity, String.class);
                JSONObject jsonObject = paseJsonObject(response.getBody());
                List<OrganUnitVo> result = JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<OrganUnitVo>>() {
                });
                redisClient.set(key, JSON.toJSONString(result), expire);
                return result;
            } catch (RestClientException e) {
                throw new BusinessException(ReturnType.Error, "调用第三方系统错误");
            }
        }

    }

    /**
     * 根据多个部门ID查询部门详细信息
     *
     * @param deptIds 入参是多个部门id列表，多个用逗号隔开
     * @return
     */
    public List<OrganUnitVo> getDeptDetailByIds(String companyId, String deptIds) {
        String key = MessageFormat.format(Constants.REDIS_KEY_ORGAN_KEY, companyId) + "getDeptDetailByIds:" + deptIds;
        String deptDetailJson = (String) redisClient.get(key);
        if (StringUtils.isNotEmpty(deptDetailJson) && deptDetailJson.length() > 0) {
            return JSON.parseObject(deptDetailJson, new TypeReference<List<OrganUnitVo>>() {
            });
        } else {
            try {
                String organToken = getAccessToken(companyId + "");
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("accesstoken", organToken);
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
                HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
                ResponseEntity<String> response = null;//restTemplate.postForEntity(orgApiUrl + "/uum/get_organ_unit_detail?ids=" + deptIds, requestEntity, String.class);
                JSONObject jsonObject = paseJsonObject(response.getBody());
                List<OrganUnitVo> result = JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<OrganUnitVo>>() {
                });
                redisClient.set(key, JSON.toJSONString(result), expire);
                return result;
            } catch (RestClientException e) {
                throw new BusinessException(ReturnType.Error, "调用第三方系统错误");
            }
        }
    }


    /**
     * 根据部门ID查询子部门ID集合,如果为空，则该部门下没有子部门
     *
     * @param companyId
     * @param deptId
     * @param cascade   是否级联查询出子部门
     * @return
     */
    public List<OrganUnitVo> getDeptList(String companyId, Long deptId, boolean cascade) {
        String key = MessageFormat.format(Constants.REDIS_KEY_ORGAN_KEY, companyId) + "getDeptList:" + deptId + ":" + cascade;
        String deptDetailJson = (String) redisClient.get(key);
        String resJSON = null;
        if (StringUtils.isNotEmpty(deptDetailJson) && deptDetailJson.length() > 0) {
            return JSON.parseObject(deptDetailJson, new TypeReference<List<OrganUnitVo>>() {
            });
        } else {
            try {
                if (cascade) {
                    List<OrganUnitVo> organUnitVoList = getAllDeptList(companyId);
                    List<OrganUnitVo> returnList = getChildDeptInfoList(organUnitVoList, deptId);
                    redisClient.set(key, JSON.toJSONString(returnList), expire);
                    return returnList;
                } else {
                    String organToken = getAccessToken(companyId + "");
                    RestTemplate restTemplate = new RestTemplate();
                    Map<String, String> param = Maps.newHashMap();
                    param.put("id", deptId + "");
                    resJSON = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_organ_unit", param, organToken, restTemplate);
                    JSONObject jsonObject = paseJsonObject(resJSON);
                    List<OrganUnitVo> result = JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<OrganUnitVo>>() {
                    });
                    redisClient.set(key, JSON.toJSONString(result), expire);
                    return result;
                }
            } catch (Exception e) {
                throw new BusinessException(ReturnType.Error, "调用第三方系统错误," + e.getMessage());
            }
        }
    }

    /**
     * 根据部门ID获取子部门集合
     *
     * @param organUnitVoList
     * @param deptId          指定部门ID
     * @return
     */
    public List<OrganUnitVo> getChildDeptInfoList(List<OrganUnitVo> organUnitVoList, Long deptId) {
        List<OrganUnitVo> deptIds = Lists.newArrayList();
        for (OrganUnitVo organUnitVo : organUnitVoList) {
            if (organUnitVo.getParentId().longValue() == deptId.longValue()) {
                deptIds.add(organUnitVo);
                deptIds.addAll(getChildDeptInfoList(organUnitVoList, organUnitVo.getId()));
            }
        }
        return deptIds;
    }

    /**
     * 根据部门ID或者子级部门ID集合，获取部门的结果集
     *
     * @param deptIds
     * @return
     */
    public List<Long> getChildDeptList(String companyId, List<Long> deptIds) {
        List<OrganUnitVo> unitVoList = getAllDeptList(companyId);
        Set<Long> deptIdSet = new HashSet<>(); //去重部门
        List<Long> deptIdList = Lists.newArrayList();
        for (Long deptId : deptIds) {
            deptIdSet.addAll(getChildDeptList(unitVoList, deptId));
            deptIdSet.add(deptId);
        }
        deptIdList.addAll(deptIdSet);
        return deptIdList;
    }

    /**
     * 根据部门ID获取子部门集合
     *
     * @param organUnitVoList
     * @param deptId          指定部门ID
     * @return
     */
    public List<Long> getChildDeptList(List<OrganUnitVo> organUnitVoList, Long deptId) {
        List<Long> deptIds = Lists.newArrayList();
        for (OrganUnitVo organUnitVo : organUnitVoList) {
            if (organUnitVo.getParentId().longValue() == deptId.longValue()) {
                deptIds.add(organUnitVo.getId());
                deptIds.addAll(getChildDeptList(organUnitVoList, organUnitVo.getId()));
            }
        }
        return deptIds;
    }


    /**
     * 根据部门id模糊匹配查询子部门信息，如果是整个部门，则传0
     *
     * @param deptName
     * @return
     */
    public List<OrganUnitVo> searchDept(String companyId, Long deptId, String deptName) {
        String key = MessageFormat.format(Constants.REDIS_KEY_ORGAN_KEY, companyId) + "searchDept:" + deptId;
        String deptDetailJson = (String) redisClient.get(key);
        if (StringUtils.isNotEmpty(deptDetailJson) && deptDetailJson.length() > 0) {
            return JSON.parseObject(deptDetailJson, new TypeReference<List<OrganUnitVo>>() {
            });
        } else {
            List<OrganUnitVo> unitVoList = Lists.newArrayList();
            if (deptId != null && deptId.longValue() == 0) {
                unitVoList = getAllDeptList(companyId);
            } else {
                unitVoList = getDeptList(companyId, deptId, true);
            }
            for (Iterator<OrganUnitVo> iterator = unitVoList.iterator(); iterator.hasNext(); ) {
                OrganUnitVo organ = iterator.next();
                if (!organ.getOrganUnitName().contains(deptName)) {
                    iterator.remove();
                }
            }
            redisClient.set(key, JSON.toJSONString(unitVoList), expire);
            return unitVoList;
        }
    }


    //获取部门人数信息
    public UserQueryResultVo getDeptNumInfo(Long deptId, String accessToken, boolean cascade) {
        Map<String, String> param = Maps.newHashMap();
        RestTemplate restTemplate = new RestTemplate();
        param.put("id", deptId.toString());
        if (cascade) {
            param.put("sub", "3");
        } else {
            param.put("sub", "2");
        }
        param.put("curPage", "1");
        param.put("pageSize", "1");
        String resJSON2 = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_users_by_organunit_page", param, accessToken, restTemplate);
        JSONObject jsonObject2 = JSON.parseObject(resJSON2);
        UserQueryResultVo userQueryResultVo = JSON.parseObject(jsonObject2.getString("data"), new TypeReference<UserQueryResultVo>() {
        });
        if (userQueryResultVo != null) {
            return userQueryResultVo;
        }
        return null;
    }

    //获取部门人数
    public int getDeptNum(Long deptId, String accessToken, boolean cascade) {
        Map<String, String> param = Maps.newHashMap();
        RestTemplate restTemplate = new RestTemplate();
        param.put("id", deptId.toString());
        if (cascade) {
            param.put("sub", "3");
        } else {
            param.put("sub", "2");
        }
        param.put("curPage", "1");
        param.put("pageSize", "1");
        String resJSON2 = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_users_by_organunit_page", param, accessToken, restTemplate);
        JSONObject jsonObject2 = JSON.parseObject(resJSON2);
        UserQueryResultVo userQueryResultVo = JSON.parseObject(jsonObject2.getString("data"), new TypeReference<UserQueryResultVo>() {
        });
        if (userQueryResultVo != null) {
            return userQueryResultVo.getTotalRecords();
        }
        return 0;
    }


    //根据根部门ID查询所有子部门
    public List<OrganUnitVo> getAllDeptList(String companyId) {
        String key = MessageFormat.format(Constants.REDIS_KEY_ORGAN_KEY, companyId) + "getAllDeptList:" + companyId;
        String deptDetailJson = (String) redisClient.get(key);
        if (StringUtils.isNotEmpty(deptDetailJson) && deptDetailJson.length() > 0) {
            return JSON.parseObject(deptDetailJson, new TypeReference<List<OrganUnitVo>>() {
            });
        } else {
            String organToken = getAccessToken(companyId + "");
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> param = Maps.newHashMap();
            param.put("id", -1L + "");
            String resJSON = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_organ_unit", param, organToken, restTemplate);
            JSONObject jsonObject = paseJsonObject(resJSON);
            List<OrganUnitVo> deptList = JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<OrganUnitVo>>() {
            });
            redisClient.set(key, JSON.toJSONString(deptList), expire);
            return deptList;
        }
    }

    //查询根部门信息
    public OrganUnitVo getRootDeptList(String companyId) {
        List<OrganUnitVo> voList = getDeptList(companyId, -99L, false);
        if (voList.size() > 0) {
            return voList.get(0);
        }
        return null;
    }

    /**
     * 级联调用
     *
     * @param companyId
     * @param deptId
     * @return
     */
    private List<OrganUnitVo> cascadeDept(String companyId, Long deptId, String organToken) {
        List<OrganUnitVo> allList = Lists.newArrayList();
        List<OrganUnitVo> unitVoList = getcascadeDeptList(companyId, deptId, organToken);
        if (unitVoList != null && unitVoList.size() > 0) {
            allList.addAll(unitVoList);
            for (OrganUnitVo organUnitVo : unitVoList) {
                allList.addAll(cascadeDept(companyId, organUnitVo.getId(), organToken));
            }
        }
        return allList;
    }

    /**
     * 根据部门ID获取父部门集合
     *
     * @param organUnitVoList
     * @param deptId          指定部门ID
     * @return
     */
    public List<Long> getParentDeptList(List<OrganUnitVo> organUnitVoList, Long deptId) {
        List<Long> deptIds = Lists.newArrayList();
        for (OrganUnitVo organUnitVo : organUnitVoList) {
            if (organUnitVo.getId() != null && deptId != null) {
                if (organUnitVo.getId().longValue() == deptId.longValue()) {
                    deptIds.add(organUnitVo.getId());
                    deptIds.addAll(getParentDeptList(organUnitVoList, organUnitVo.getParentId()));
                }
            }
        }
        return deptIds;
    }


    //提供级联调用
    private List<OrganUnitVo> getcascadeDeptList(String companyId, Long deptId, String organToken) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> param = Maps.newHashMap();
        param.put("id", deptId + "");
        String resJSON = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_organ_unit", param, organToken, restTemplate);
        JSONObject jsonObject = JSON.parseObject(resJSON);
        return JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<OrganUnitVo>>() {
        });
    }

    private List<OrganRoleVo> getRoleList(String organToken, Long deptId, RestTemplate restTemplate) {
        Map<String, String> reqParam = Maps.newHashMap();
        reqParam.put("id", deptId + "");
        String resJSON = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_role_simplelist", reqParam, organToken, restTemplate);
        JSONObject jsonObject = JSON.parseObject(resJSON);
        return JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<OrganRoleVo>>() {
        });
    }


    //查询部门人员信息
    public List<OrganUserVo> getUserByDeptId(String companyId, Long deptId, boolean cascade) {
        String key = MessageFormat.format(Constants.REDIS_KEY_ORGAN_KEY, companyId) + "getUserByDeptId:" + deptId + ":" + cascade;
        String deptDetailJson = (String) redisClient.get(key);
        if (StringUtils.isNotEmpty(deptDetailJson) && deptDetailJson.length() > 0) {
            return JSON.parseObject(deptDetailJson, new TypeReference<List<OrganUserVo>>() {
            });
        } else {
            String organToken = getAccessToken(companyId);
            RestTemplate restTemplate = new RestTemplate();
            //判断是否是根部门,如果是，则返回所有人员信息
//            if (cascade) {
//                List<OrganUnitVo> unitVos = getDeptDetail(companyId, deptId);
//                if (unitVos != null & unitVos.size() > 0) {
//                    OrganUnitVo organUnitVo = unitVos.get(0);
//                    //-99为根部门列表
//                    if (organUnitVo.getParentId().toString().equals("-99")) {
//                        //如果是父部门，则查询所有人员
//                        return getAllUsers(companyId);
//                    }
//                }
//            }
//                List<OrganUserVo> userList = Lists.newArrayList();
            //有岗位人员信息
            Map<String, String> param = Maps.newHashMap();
            String sub = "2"; //默认不级联
            if (cascade) {
                sub = "3";
            }
            param.put("id", deptId.toString());
            param.put("sub", sub);
            String resJSON = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_users_by_organunit",
                    param, organToken, restTemplate);
            JSONObject userObject = JSON.parseObject(resJSON);
            List<OrganUserVo> users = JSON.parseObject(userObject.getString("data"), new TypeReference<List<OrganUserVo>>() {
            });
            redisClient.set(key, JSON.toJSONString(users), expire);
            return users;
        }
    }

    /**
     * 根据公司ID和人员ID查询用户归属部门信息
     *
     * @param eiIds 入参是用户id列表，多个用逗号隔开
     * @return
     */
    public List<OrganUnitAndUserVo> getDeptInfoByEiIds(String companyId, String eiIds) {
        String key = MessageFormat.format(Constants.REDIS_KEY_ORGAN_KEY, companyId) + "getUserByDeptId:" + eiIds;
        String deptDetailJson = (String) redisClient.get(key);
        if (StringUtils.isNotEmpty(deptDetailJson) && deptDetailJson.length() > 0) {
            return JSON.parseObject(deptDetailJson, new TypeReference<List<OrganUnitAndUserVo>>() {
            });
        } else {
            String organToken = getAccessToken(companyId);
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> param = Maps.newHashMap();
            String resJSON = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_user_detail?ids=" + eiIds, param, organToken, restTemplate);
            JSONObject jsonObject = JSON.parseObject(resJSON);
            List<OrganUserVo> userVoList = JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<OrganUserVo>>() {
            });
            List<OrganUnitAndUserVo> organUnitVos = Lists.newArrayList();
            OrganUnitAndUserVo organUnitAndUserVo = null;
            for (OrganUserVo organUserVo : userVoList) {
                organUnitAndUserVo = new OrganUnitAndUserVo();
                if (organUserVo.getRoles().size() > 0) {
                    StringBuilder ids = new StringBuilder();
                    for (OrganUnitVo organUnitVo : organUserVo.getRoles()) {
                        if ("0".equals(organUnitVo.getRelationType()) || StringUtils.isEmpty(organUnitVo.getRelationType())) {  //只查询主岗位信息
                            ids.append(organUnitVo.getId()).append(",");
                        }
                    }
                    ids.deleteCharAt(ids.length() - 1);
                    String userJSON = RestClient.getInstance().getOrganMapRequest(orgApiUrl + "/uum/get_role_detail?ids=" + ids, param, organToken, restTemplate);
                    JSONObject orgObject = JSON.parseObject(userJSON);
                    List<OrganRoleVo> unitVoList = JSON.parseObject(orgObject.getString("data"), new TypeReference<List<OrganRoleVo>>() {
                    });
                    for (OrganRoleVo roleVo : unitVoList) {
                        organUnitAndUserVo.setOrganRoleVo(roleVo);
                        for (OrganUnitVo organUnitVo : roleVo.getDepts()) {
                            organUnitAndUserVo.setOrganVoList(getDeptDetail(companyId, organUnitVo.getId()));
                        }
                    }
                    organUnitAndUserVo.setOrganUserVo(organUserVo);
                    organUnitVos.add(organUnitAndUserVo);
                }
            }
            redisClient.set(key, JSON.toJSONString(organUnitVos), expire);
            return organUnitVos;
        }
    }



    /**
     * 获取组织下所有人的信息
     *
     * @param orgId     组织id
     * @param appId     appId
     */
    public List<EmpInfo> getOrgEveryOne(String orgId,String appId,String accessId,String accessSecret){
        String accessToken = getAccessToken1(orgId,accessId,accessSecret);
        if(null != accessToken){
            String url = orgApiUrl+"/uum/get_all_users_simple";
            JSONObject jsonObject = new JSONObject();
            //jsonObject.put("appId", appId);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            headers.add("accesstoken", accessToken);
            headers.add("orgId", orgId);
            headers.add("appId", appId);
            HttpEntity<String> requestEntity = new HttpEntity(JSON.toJSONString(jsonObject), headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class);
            JSONObject data = JSON.parseObject(response.getBody());
            if (data.getJSONObject("meta").getString("code").equals("0")) {
                List<UserInfo> userInfoList = JSON.parseObject(data.getString("data"), new TypeReference<List<UserInfo>>() {});
                List<String> eiIds= new LinkedList<>();
                userInfoList.stream().filter(userInfo -> null != userInfo.getId()).forEach(userInfo -> eiIds.add(userInfo.getId().toString()));
                List<EmpInfo> empInfoList = empInfoService.getByListId(eiIds);
                return empInfoList;
            }
        }
        return null;
    }

    public String getAccessToken(String companyId) {
        String key = MessageFormat.format(Constants.ORGAN_TOKEN_KEY_PREFIX, companyId);
    	String organToken = (String) redisClient.get(key);
        if (null == organToken) {
            String url = orgApiUrl + "/uua/get_accesstoken";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accessId", accessId);
            jsonObject.put("accessSecret", accessSecret);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> requestEntity = new HttpEntity(JSON.toJSONString(jsonObject), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            String result = response.getBody();
            JSONObject data = JSON.parseObject(result);
            if (data.getJSONObject("meta").getString("code").equals("0")) {
                String accessToken = data.getJSONObject("data").getString("access_token");
                redisClient.set(MessageFormat.format(Constants.ORGAN_TOKEN_KEY_PREFIX, companyId), accessToken, Constants.ORGAN_TOKEN_EXPIRE_IN);
                return accessToken;
            }
        } else {
            return organToken;
        }
        return null;
    }


    public String getAccessToken1(String companyId,String accessId,String accessSecret) {
        String key = MessageFormat.format(Constants.ORGAN_TOKEN_KEY_PREFIX, companyId);
        String organToken = getAccessTokenByRedis(key);
        if (null == organToken) {
            String url = orgApiUrl + "/uua/get_accesstoken";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accessId", accessId);
            jsonObject.put("accessSecret", accessSecret);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity(JSON.toJSONString(jsonObject), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            String result = response.getBody();
            JSONObject data = JSON.parseObject(result);
            if (data.getJSONObject("meta").getString("code").equals("0")) {
                String accessToken = data.getJSONObject("data").getString("access_token");
                this.setByFixedTime(key,accessToken);
                return accessToken;
            }
        } else {
            return organToken;
        }
        return null;
    }

    /**
     * 放置access_token
     * @auth hou
     * @param key
     * @param token
     * @return
     */
    private Boolean setByFixedTime(String key,String token){
        RedisBean redisBean = new RedisBean();
        redisBean.setKey(key);
        redisBean.setObj(token);
        redisBean.setTime(System.currentTimeMillis());
        return redisClient.set(redisBean.getKey(),JSONObject.toJSON(redisBean), Constants.ORGAN_ACCESS_TOKEN_EXPIRE_IN);
    }


    /**
     * 获取accessToken从redis
     * @auth hou
     * @param key
     * @return
     */
    private String getAccessTokenByRedis(String key){
        String organToken = redisClient.get(key) + "";
        if(!"null".equals(organToken)) {
            if (StringUtils.isNotEmpty(organToken)) {
                RedisBean redisBean = JSONObject.parseObject(organToken, RedisBean.class);
                Long nowTime = System.currentTimeMillis();
                if (nowTime - redisBean.getTime() < Constants.ORGAN_ACCESS_TOKEN_EXPIRE_IN) {
                    return redisBean.getObj() + "";
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    //校验格式
    private JSONObject paseJsonObject(String response) {
        JSONObject jsonObject = JSON.parseObject(response);
        JSONObject mateObject = jsonObject.getJSONObject("meta");
        if (mateObject.getString("code") == null || !(mateObject.getString("code").equals("0"))) {
            throw new BusinessException(mateObject.getString("code"), ReturnType.ServiceError.getType()
                    , mateObject.getString("message"));
        }
        return jsonObject;
    }

//    public Long getUserByFeedId(String companyId,String feedIds){
//        String organToken=getAccessToken(companyId+"");
//        Map<String,Object> reqOrgMap=Maps.newHashMap();
//        RestTemplate rtTemplate=new RestTemplate();
//        reqOrgMap.put("feedIds",feedIds);
//        String data = RestClient.getInstance().postMapRequest(orgApiUrl + "/cardstrategy/getStaffCardList", reqOrgMap,organToken,rtTemplate);
//        JSONObject jsonObject=JSON.parseObject(data);
//        if(jsonObject.getString("code").equals("0")) {
//            JSONObject dataObject=JSON.parseObject(jsonObject.getString("data"));
//            return  dataObject.getInteger("userId").longValue();
//        }
//        return 0L;
//    }

    public JSONObject getUserInfo(String code, String appToken) {
        String url = orgApiUrl + "/uua/get_userinfo_by_code";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appToken", appToken);
        jsonObject.put("code", code);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity(JSON.toJSONString(jsonObject), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String result = response.getBody();
        JSONObject data = JSON.parseObject(result).getJSONObject("data");
        return data;
    }


    public JSONObject getPCToken(String authCode, String appToken) {
        String url = orgApiUrl + "/uua/validate_auth_code";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appToken", appToken);
        jsonObject.put("authCode", authCode);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity(JSON.toJSONString(jsonObject), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String result = response.getBody();
        JSONObject data = JSON.parseObject(result).getJSONObject("data").getJSONObject("loginInfo").getJSONObject("user");
        return data;
    }


    public String getAToken(String companyId,String accessId,String accessSecret) {
        String url = orgApiUrl + "/uua/get_app_token";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appKey", accessId);
        jsonObject.put("appSecret", accessSecret);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity(JSON.toJSONString(jsonObject), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String result = response.getBody();
        JSONObject data = JSON.parseObject(result);
        if (data.getJSONObject("meta").getString("code").equals("0")) {
            String accessToken = data.getJSONObject("data").getString("appAToken");
            return accessToken;
        }
        return null;
    }



    public String getToonCode(Object toonCode, String accessToken) {
        String url = orgApiUrl + "/uua/generateCypherText?plainText={plainText}";
        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("accesstoken", accessToken);
//        jsonObject.put("plainText", toonCode);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("accesstoken", accessToken);
        HttpEntity<String> requestEntity = new HttpEntity(JSON.toJSONString(jsonObject), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class, toonCode);
        String result = response.getBody();
        String data = JSON.parseObject(result).getString("data");
        return data;
    }
}
