package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.CustGroupMap;
import com.vetc.manage.entity.Customer;
import com.vetc.manage.entity.CustomerGroup;
import com.vetc.manage.entity.VerifyRuleConfig;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.CustomizeException;
import com.vetc.manage.exception.ValidationException;
import com.vetc.manage.repository.CustGroupMapRepository;
import com.vetc.manage.repository.CustomerGroupRepository;
import com.vetc.manage.repository.CustomerRepository;
import com.vetc.manage.repository.VerifyRuleConfigRepository;
import com.vetc.manage.service.VerifyRuleConfigService;
import com.vetc.manage.service.common.ServiceBase;
import com.vetc.manage.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerifyRuleConfigServiceImpl
    extends ServiceBase<VerifyRuleConfig, Long, VerifyRuleConfigRepository>
    implements VerifyRuleConfigService {

  @Autowired
  private CustGroupMapRepository custGroupMapRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerGroupRepository customerGroupRepository;

  @Autowired
  private VerifyRuleConfigRepository verifyRuleConfigRepository;

  @Autowired
  private EntityManager entityManager;

  @Override
  public ListResult<VerifyRuleConfig> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public VerifyRuleConfig get(Long id) {
    return super.get(id);
  }


  @Override
  public VerifyRuleConfig save(VerifyRuleConfig object) throws Exception {
    VerifyRuleConfig verifyRuleConfig = verifyRuleConfigRepository.findByCustId(
        object.getCustId(), object.getVerifyMethod());
    if (Objects.isNull(verifyRuleConfig)) {
      verifyRuleConfig = object;
    } else {
      verifyRuleConfig.setMaxAmount(object.getMaxAmount());
      verifyRuleConfig.setMinAmount(object.getMinAmount());
    }
    return super.save(verifyRuleConfig);
  }

  @Override
  public VerifyRuleConfig update(VerifyRuleConfig object, Long id) throws Exception {
    object.setId(id);
    return super.update(object, id);
  }

  @Override
  public VerifyRuleConfig delete(Long id) throws Exception {
    VerifyRuleConfig verifyRuleConfig = super.get(id);
    if (StringUtil.isNullOrEmpty(verifyRuleConfig.getCustType())
        && StringUtil.isNullOrEmpty(verifyRuleConfig.getCustGroupCode())) {
      return super.delete(id);
    } else {
      throw new CustomizeException(ExceptionEnum.DATA_INVALID.getCode(),
          "Đây là cấu hình mặc định theo Group hoặc Loại khách hàng", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public List<VerifyRuleConfig> getAll() {
    return super.getAll();
  }

  @Override
  public List<VerifyRuleConfig> getVerifyRuleConfigByCust(Long custId) {

    Customer customer = customerRepository.findById(custId).orElse(null);
    if (customer == null) {
      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          ExceptionEnum.RECORD_NOT_FOUND.getMessage());
    }
    List<CustGroupMap> lsCustGroupMap = custGroupMapRepository.findByCustId(custId);
    List<Long> lsGroupId = lsCustGroupMap.stream().filter(item -> item.getGroupId() != null)
        .map(item -> item.getGroupId()).collect(Collectors.toList());

    List<CustomerGroup> lsGroupOfCust = customerGroupRepository.findAllByIdIn(lsGroupId);
    List<String> method = new ArrayList<>();
    method.add("VERIFY_PASSWD");
    method.add("OPT_SMS");
    method.add("OTP_EMAIL");
    List<VerifyRuleConfig> verifyRuleConfigs = new ArrayList<>();
    for (int i = 0; i < method.size(); i++) {
      String verify = "";
      String codeGroup = "null";
      String custType = "null";
      if (!lsGroupOfCust.isEmpty()) {
        codeGroup = lsGroupOfCust.get(0).getCode();
      }

      if (!StringUtil.isNullOrEmpty(customer.getCusType())) {
        custType = customer.getCusType();
      }
      verify = getRuleConfig(customer.getId(), custType,
          codeGroup, method.get(i));

      switch (verify) {
        case "OTP_CUSTID":
          VerifyRuleConfig verifyRuleConfig1 = verifyRuleConfigRepository.findByCustId(custId,
              method.get(i));
          verifyRuleConfigs.add(verifyRuleConfig1);
          break;
        case "OTP_TYPE":
          VerifyRuleConfig verifyRuleConfig2 = verifyRuleConfigRepository.findByType(
              customer.getCusType(), method.get(i));
          verifyRuleConfigs.add(verifyRuleConfig2);
          break;
        case "OTP_GROUP":
          VerifyRuleConfig verifyRuleConfig3 = verifyRuleConfigRepository.findByGroup(
              lsGroupOfCust.get(0).getCode(), method.get(i));
          verifyRuleConfigs.add(verifyRuleConfig3);
          break;
        case "default":
          log.warn("Not found config method " + method);
      }

    }
    if (verifyRuleConfigs.isEmpty()) {
      throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          "Không có cấu hình nào cho user này !");
    }
    return verifyRuleConfigs;
  }

  private String getRuleConfig(Long id, String custType, String custGroup, String method) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("select case");
    stringBuilder.append(
        " when (select case when exists(select vrc.CUST_ID from VERIFY_RULE_CONFIG vrc) then 'true' end from VERIFY_RULE_CONFIG vrc");
    stringBuilder.append(" where vrc.CUST_ID = " + id);
    stringBuilder.append(" and vrc.VERIFY_METHOD = '" + method + "') = 'true' then 'OTP_CUSTID'");
    stringBuilder.append(
        " when (select case when exists(select vrc.CUST_TYPE from VERIFY_RULE_CONFIG vrc) then 'true' end from VERIFY_RULE_CONFIG vrc");
    stringBuilder.append(" where vrc.CUST_TYPE = '" + custType + "'");
    stringBuilder.append(" and vrc.VERIFY_METHOD = '" + method + "') = 'true' then 'OTP_TYPE'");
    stringBuilder.append(
        " when (select case when exists(select vrc.CUST_GROUP_CODE from VERIFY_RULE_CONFIG vrc) then 'true' end from VERIFY_RULE_CONFIG vrc");
    stringBuilder.append(" where vrc.CUST_GROUP_CODE = '" + custGroup + "'");
    stringBuilder.append(
        " and vrc.VERIFY_METHOD = '" + method + "') = 'true' then 'OTP_GROUP' end");
    stringBuilder.append(" from VERIFY_RULE_CONFIG vrc where vrc.VERIFY_METHOD = '" + method + "'");

    Query q = entityManager.createNativeQuery(stringBuilder.toString());
    String result = "";
    try {
      result = (String) q.getResultList().get(0);
    } catch (Exception ex) {
      log.error(">>>Method " + method + " null");
      log.error(ExceptionUtils.getMessage(ex));
      return "default";
    }
    if (StringUtil.isNullOrEmpty(result)) {
      return "default";
    }
    return result;
  }
}
