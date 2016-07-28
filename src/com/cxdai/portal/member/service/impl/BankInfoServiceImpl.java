package com.cxdai.portal.member.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.base.entity.Bank;
import com.cxdai.base.entity.BankInfo;
import com.cxdai.base.entity.BankinfoLog;
import com.cxdai.base.entity.SmsRecord;
import com.cxdai.base.mapper.BaseBankInfoMapper;
import com.cxdai.common.statics.Constants;
import com.cxdai.portal.member.mapper.BankInfoMapper;
import com.cxdai.portal.member.mapper.BankinfoLogMapper;
import com.cxdai.portal.member.mapper.MemberMapper;
import com.cxdai.portal.member.service.BankInfoService;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.service.MobileApproService;
import com.cxdai.portal.member.service.RealNameApproService;
import com.cxdai.portal.member.vo.BankInfoCnd;
import com.cxdai.portal.member.vo.BankInfoVo;
import com.cxdai.portal.member.vo.MemberCnd;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.member.vo.MobileApproVo;
import com.cxdai.portal.member.vo.RealNameApproVo;
import com.cxdai.portal.sms.service.SmsSendService;
import com.cxdai.portal.sms.service.SmsTemplateService;
import com.cxdai.portal.sms.vo.SmsTemplateVo;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.portal.util.service.PhoneService;
import com.cxdai.security.ShiroUser;
import com.cxdai.utils.DateUtils;
import com.cxdai.utils.HttpTookit;
import com.cxdai.utils.MD5;
import com.cxdai.utils.SmsUtil;

/**
 * <p>
 * Description:积分等级接口的实现类<br />
 * </p>
 * 
 * @title IntegralServiceImpl.java
 * @package com.cxdai.integral.service.impl
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
@Service
public class BankInfoServiceImpl implements BankInfoService {

	@Autowired
	private BankInfoMapper bankInfoMapper;
	@Autowired
	private BaseBankInfoMapper baseBankInfoMapper;
	@Autowired
	private RealNameApproService realNameApproService;
	@Autowired
	private PhoneService phoneService;
	@Autowired
	private SmsTemplateService smsTemplateService;
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private MobileApproService mobileApproService;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BankinfoLogMapper bankinfoLogMapper;

	@Override
	public BankInfoVo queryBankInfoByUserIdPrivateKey(Integer memberId, Integer id) throws Exception {
		BankInfoCnd bankInfoCnd = new BankInfoCnd();
		bankInfoCnd.setUserId(memberId);
		bankInfoCnd.setId(id);
		List<BankInfoVo> list = bankInfoMapper.queryBankInfoList(bankInfoCnd);
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BankInfoVo> queryBankInfosByUserId(Integer memberId) throws Exception {
		BankInfoCnd bankInfoCnd = new BankInfoCnd();
		bankInfoCnd.setUserId(memberId);
		return bankInfoMapper.queryBankInfoList(bankInfoCnd);
	}

	@Override
	public String saveBankcard(BankInfo bankInfo, String payPassword, String activeCode) throws Exception {
		// Bank bank =
		// bankInfoMapper.queryBankByCnapsCodeAndBranch(bankInfo.getCnapsCode(),
		// bankInfo.getBranch());
		// if (bank == null) {
		// return "开户行不存在！";
		// }

		if (bankInfoMapper.countCardByCardNum(bankInfo.getCardNum()) > 0) {
			return "卡号已存在！";
		}

		// 验证银行页面提交的数据是否正确
		// result = validatePageBankData(bankInfo);
		// if (result != "success") {
		// return result;
		// }

		if (memberMapper.queryPayPasswordIsCorrect(bankInfo.getUserId(), MD5.toMD5(payPassword)) == 0) {
			return "交易密码输入错误！";
		}
		// if (!MD5.toMD5(payPassword).equals(memberVo.getPaypassword())) {
		// return "-支付密码输入错误！ \n";
		// }

		// 检查实名认证
		RealNameApproVo rna = realNameApproService.queryRealNameApproByUserId(bankInfo.getUserId());
		if (null == rna || rna.getIsPassed() != Constants.REALNAME_APPR_ISPASSED_PASSED) {
			return "您还未通过实名认证，无法设置银行卡信息。";
		}

		MemberVo memberVo = new MemberVo();
		ShiroUser shiroUser = currentUser();
		MemberCnd memberCnd = new MemberCnd();
		memberCnd.setId(shiroUser.getUserId());
		memberVo = memberService.queryMemberByCnd(memberCnd);
		MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(shiroUser.getUserId());
		if (mobileApproVo == null || mobileApproVo.getPassed() == null || mobileApproVo.getPassed() != 1) {
			return "手机未认证";
		}
		String result = mobileApproService.verifyMobileCodeBeforeAddBankCard(memberVo, mobileApproVo.getMobileNum(), activeCode, null, BusinessConstants.BANK_INFO_MODIFY_FUNCTION,
				BusinessConstants.SMS_TEMPLATE_TYPE_ADD_BANKCARD_SUCCESS);
		if (!BusinessConstants.SUCCESS.equals(result)) {
			return "手机认证码校验失败";
		}
		// 查询银行卡信息是否重复
		// BankInfoCnd bankInfoCnd = new BankInfoCnd();
		// bankInfoCnd.setCardNum(bankInfo.getCardNum());
		// Integer repeatCardNum = bankInfoMapper
		// .queryRepeatBankInfoCount(bankInfoCnd);
		// if (null != repeatCardNum && repeatCardNum > 0) {
		// return "已存在此银行卡号,请重新输入！";
		// }

		Date currentTime = new Date();
		// 已有的银行卡数量，
		//int userCardNum = bankInfoMapper.queryBankCardCountByUserId(shiroUser.getUserId());

		// 查询银行卡操作日志中的锁定记录(type=0的记录)
		//int cardLock = bankinfoLogMapper.querytBankCardLock(shiroUser.getUserId());

		// 银行卡数量为3，或银行卡已锁定的，前台中不可添加银行卡；
		// if(userCardNum>=3 || cardLock>0){
		// return "银行卡添加功能已锁定，不能添加！";
		// }

		// bankInfo.setBankName(bank.getBankName());
		// bankInfo.setBranch(bank.getBranchName());
		bankInfo.setRealName(rna.getRealName());
		bankInfo.setIdCardNo(rna.getIdCardNo());
		bankInfo.setVerifyStatus(Constants.BANK_INFO_VERIFY_STATUS_YES);
		baseBankInfoMapper.insertEntity(bankInfo);

		// 添加银行卡信息日志表
		BankinfoLog bankinfoLog = new BankinfoLog();

		// 第1,2张卡添加；
		// if (userCardNum < 2) {
		bankinfoLog.setUserId(bankInfo.getUserId());
		bankinfoLog.setCardNum(bankInfo.getCardNum());
		bankinfoLog.setType(1);// 1：添加
		bankinfoLog.setStatus(0);// 0：有效
		bankinfoLog.setAddBy(shiroUser.getUserId());
		bankinfoLog.setAddTime(currentTime);
		bankinfoLog.setRemark("前台新增银行卡");

		bankinfoLogMapper.insertOld(bankinfoLog);
		// }

		// 有2张卡时； | 先加一条日志记录；然后再添加一条锁定记录；
		/*
		 * if (userCardNum == 2) { bankinfoLog.setUserId(bankInfo.getUserId());
		 * bankinfoLog.setCardNum(bankInfo.getCardNum());
		 * bankinfoLog.setType(1);// 1：添加 bankinfoLog.setStatus(0);// 0：有效
		 * bankinfoLog.setAddBy(shiroUser.getUserId());
		 * bankinfoLog.setAddTime(currentTime);
		 * bankinfoLog.setRemark("前台新增银行卡");
		 * 
		 * bankinfoLogMapper.insert(bankinfoLog);
		 * 
		 * BankinfoLog bankinfoLog2 = new BankinfoLog();
		 * bankinfoLog2.setUserId(bankInfo.getUserId());
		 * bankinfoLog2.setCardNum("0"); bankinfoLog2.setType(0);// 0：锁定
		 * bankinfoLog2.setStatus(0);// 0：有效
		 * bankinfoLog2.setAddBy(shiroUser.getUserId());
		 * bankinfoLog2.setAddTime(currentTime); bankinfoLog2.setRemark("系统锁定");
		 * 
		 * bankinfoLogMapper.insert(bankinfoLog2); }
		 */

		return "success";
	}

	private ShiroUser currentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	@Override
	// 锁定银行卡新增功能
	public String insertBankCardLock(Integer memberId) throws Exception {

		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		// 查询银行卡操作日志中的锁定记录(type=0的记录)
		int cardLock = bankinfoLogMapper.querytBankCardLock(shiroUser.getUserId());

		if (cardLock > 0) {
			return "银行卡已锁定，不能再次锁定！";
		}

		Date currentTime = new Date();

		// 添加银行卡信息日志表-锁定记录
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(memberId);
		bankinfoLog.setCardNum("0");
		bankinfoLog.setType(0);// 0：锁定
		bankinfoLog.setStatus(0);// 0：有效
		bankinfoLog.setAddBy(memberId);
		bankinfoLog.setAddTime(currentTime);
		bankinfoLog.setRemark("用户锁定");

		bankinfoLogMapper.insert(bankinfoLog);

		return "success";
	}

	@Override
	// 已有的银行卡数量
	public Integer querytBankCardNum(Integer memberId) throws Exception {

		int userCardNum = bankInfoMapper.queryBankCardCountByUserId(memberId);

		return userCardNum;
	}

	@Override
	// 查询银行卡信息日志表-用户锁定的记录
	public BankinfoLog querytBankCardLogLock(Integer memberId) throws Exception {

		BankinfoLog bankinfoLog = bankinfoLogMapper.queryBankCardLogLockByUserId(memberId);

		return bankinfoLog;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String sendBankInfoValidate(String mobile, HttpServletRequest request, MemberVo memberVo, String modelName) throws Exception {
		String result = "success";
		// 获得验证码并存入缓存
		String randCode = phoneService.querySmsValidate(mobile, modelName);
		System.err.println(randCode);
		// 获取短信模板
		SmsTemplateVo smsTempale = smsTemplateService.querySmsTemplateByType(BusinessConstants.SMS_TEMPLATE_TYPE_MODIFY_BANKNO_REQUEST);

		SmsRecord smsRecord = new SmsRecord();
		smsRecord.setAddip(HttpTookit.getRealIpAddr(request));
		smsRecord.setAddtime(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", memberVo.getUsername());
		map.put("time", DateUtils.format(new Date(), DateUtils.YMD_HMS));
		map.put("randCode", randCode);

		String content = SmsUtil.generateParamContent(smsTempale.getContent(), map);
		// 发送短信
		smsRecord.setContent(content);
		smsRecord.setMobile(mobile);
		smsRecord.setSmsType(BusinessConstants.SMS_TEMPLATE_TYPE_MODIFY_MOBILE_REQUEST);
		smsRecord.setSendStatus(BusinessConstants.SMS_SEND_STATUS_WAIT);
		smsRecord.setLastmodifytime(new Date());
		smsRecord.setSmsTemplateType(BusinessConstants.SMS_TEMPLATE_TYPE_MODIFY_BANKNO_REQUEST);
		result = smsSendService.saveSmsByZucp(smsRecord);
		return result;
	}

	/**
	 * <p>
	 * Description:验证更新银行贴的数据是否正确<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月24日
	 * @param memberVo
	 * @param bankInfo
	 * @param payPassword
	 * @param activeCode
	 * @param modelName
	 * @param request
	 * @return
	 * @throws Exception String
	 */
	private String validateUpdateBankInfoData(MemberVo memberVo, BankInfo bankInfo, String payPassword, String activeCode, String modelName, HttpServletRequest request) throws Exception {
		String result = "success";
		// 验证银行页面提交的数据是否正确
		result = validatePageBankData(bankInfo);
		if (result != "success") {
			return result;
		}
		if (!MD5.toMD5(payPassword).equals(memberVo.getPaypassword())) {
			return "-支付密码输入错误！ \n";
		}
		RealNameApproVo rna = realNameApproService.queryRealNameApproByUserId(memberVo.getId());
		if (null == rna || rna.getIsPassed() != Constants.REALNAME_APPR_ISPASSED_PASSED) {
			return "您还未通过实名认证，无法设置银行卡信息。";
		}
		// 验证验证码是否正确
		MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(memberVo.getId());
		String valiateResult = phoneService.compareSmsValidate(mobileApproVo.getMobileNum(), activeCode, modelName);
		if (valiateResult != "success") {
			return valiateResult;
		}
		// 查询银行卡信息是否重复
		if (bankInfoMapper.countCardByCardNum(bankInfo.getCardNum()) > 0) {
			return "已存在此银行卡号,请重新输入！";
		}
		return result;
	}

	/**
	 * <p>
	 * Description:验证银行页面提交的数据是否正确<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月24日
	 * @param bankInfo
	 * @return String
	 */
	private String validatePageBankData(BankInfo bankInfo) {
		String result = "success";
		if (null == bankInfo.getBankName() || "".equals(bankInfo.getBankName().trim())) {
			return "开户行名称不能为空！";
		}
		if (null == bankInfo.getCardNum() || "".equals(bankInfo.getCardNum().trim())) {
			return "银行卡号不能为空！";
		}
		if (null == bankInfo.getBranch() || "".equals(bankInfo.getBranch().trim())) {
			return "开户支行名称不能为空！";
		}
		return result;
	}

	@Override
	public String saveDeleteBankinfoById(Integer userId, Integer id) throws Exception {
		String result = BusinessConstants.SUCCESS;
		BankInfoCnd bankInfoCnd = new BankInfoCnd();
		bankInfoCnd.setId(id);
		bankInfoCnd.setUserId(userId);
		List<BankInfoVo> list = bankInfoMapper.queryBankInfoList(bankInfoCnd);
		if (null == list || list.size() == 0) {
			return "未找到此银行卡信息";
		}
		bankInfoMapper.deleteBankinfoById(id);
		BankInfoVo bankInfo = list.get(0);
		// 记录删除日志
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setCardNum(bankInfo.getCardNum());
		bankinfoLog.setType(2);
		bankinfoLog.setUserId(userId);
		bankinfoLog.setAddBy(userId);
		bankinfoLog.setAddTime(new Date());
		bankinfoLog.setRemark("前台删除银行卡");
		bankinfoLog.setStatus(0);
		bankinfoLogMapper.insertOld(bankinfoLog);
		return result;
	}

	@Override
	public List<Bank> queryProvinceList() {
		return bankInfoMapper.queryProvinceList();
	}

	@Override
	public List<Bank> queryCityList(String province) {
		return bankInfoMapper.queryCityList(province);
	}

	@Override
	public List<Bank> queryDistrictList(String city) {
		return bankInfoMapper.queryDistrictList(city);
	}

	@Override
	public List<Bank> queryBankList(String district) {
		return bankInfoMapper.queryBankList(district);
	}

	@Override
	public List<Bank> queryBranchList(String district, String bankCode) {
		return bankInfoMapper.queryBranchList(district, bankCode);
	}

	@Override
	public List<Bank> queryBranchList(String district, String bankCode, String branchKey) {
		if (branchKey != null) {
			branchKey = branchKey.trim();
		}
		return bankInfoMapper.queryBranchListLike(district, bankCode, branchKey);
	}

	@Override
	public Bank queryBankInfoByCnapsCode(long parseLong) {
		return null;
	}

	@Override
	public List<Bank> queryBankInfosByCnapsCode(long parseLong) {
		return bankInfoMapper.queryBankInfosByCnapsCode(parseLong);
	}

	@Override
	public int querytBankCardLock(Integer userId) {
		int cardLock = bankinfoLogMapper.querytBankCardLock(userId);
		return cardLock;
	}

	@Override
	public List<BankInfoVo> queryBankInfoList(BankInfoCnd bankInfoCnd) throws Exception {
		return bankInfoMapper.queryBankInfoList(bankInfoCnd);
	}

	@Override
	public Integer updateBankInfoStatus(Integer id, Integer verifyStatus) throws Exception {
		return bankInfoMapper.updateBankInfoStatus(id, verifyStatus);
	}

	@Override
	public BankInfoVo getUserCurrentCard(int userId) throws Exception {
		return bankInfoMapper.getUserCurrentCard(userId);
	}
}
