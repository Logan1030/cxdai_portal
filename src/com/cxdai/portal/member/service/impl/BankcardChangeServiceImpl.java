package com.cxdai.portal.member.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cxdai.base.email.vo.EmailSendVo;
import com.cxdai.base.entity.Bank;
import com.cxdai.base.entity.BankinfoLog;
import com.cxdai.portal.account.mapper.AccountMapper;
import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.freemarker.service.BaseFreemarkerService;
import com.cxdai.portal.lianlian.service.LianlianpayWapService;
import com.cxdai.portal.lianlian.vo.LlWapBankcardInfo;
import com.cxdai.portal.lianlian.vo.LlWapBankcardUnbindRequest;
import com.cxdai.portal.member.mapper.BankInfoMapper;
import com.cxdai.portal.member.mapper.BankcardChangeMapper;
import com.cxdai.portal.member.mapper.BankcardClickMapper;
import com.cxdai.portal.member.mapper.BankcardPicMapper;
import com.cxdai.portal.member.mapper.BankcardTimesMapper;
import com.cxdai.portal.member.mapper.BankinfoLogMapper;
import com.cxdai.portal.member.mapper.MemberMapper;
import com.cxdai.portal.member.mapper.MobileApproMapper;
import com.cxdai.portal.member.mapper.RealNameApproMapper;
import com.cxdai.portal.member.service.BankInfoService;
import com.cxdai.portal.member.service.BankcardChangeService;
import com.cxdai.portal.member.service.EmailSendForUserService;
import com.cxdai.portal.member.vo.BankInfoVo;
import com.cxdai.portal.member.vo.BankcardChange;
import com.cxdai.portal.member.vo.BankcardClick;
import com.cxdai.portal.member.vo.BankcardPic;
import com.cxdai.portal.member.vo.BankcardTimes;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.member.vo.MobileApproVo;
import com.cxdai.portal.member.vo.RealNameApproVo;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.portal.statics.NewLoactionConstants;
import com.cxdai.portal.util.service.PhoneService;
import com.cxdai.security.ShiroUser;
import com.cxdai.utils.DateUtils;
import com.cxdai.utils.file.FileUtil;

@Service
public class BankcardChangeServiceImpl implements BankcardChangeService {

	Logger logger = LoggerFactory.getLogger(BankcardChangeServiceImpl.class);

	@Autowired
	RealNameApproMapper realNameMapper;

	@Autowired
	MobileApproMapper mobileMapper;

	@Autowired
	BankInfoMapper bankInfoMapper;

	@Autowired
	BankinfoLogMapper bankLogMapper;

	@Autowired
	BankcardClickMapper clickMapper;

	@Autowired
	BankcardChangeMapper changeMapper;

	@Autowired
	BankcardTimesMapper timesMapper;

	@Autowired
	AccountMapper accountMapper;

	@Autowired
	BankcardPicMapper picMapper;

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	LianlianpayWapService llwappayService;

	@Autowired
	EmailSendForUserService emailService;

	@Autowired
	BaseFreemarkerService baseFreemarkerService;

	@Autowired
	PhoneService phoneService;
	@Autowired
	private BankInfoService bankInfoService;



	@Override
	public String bankinfoCheck(int userId) throws Exception {
		BankcardTimes times = timesMapper.getByUserId(userId);
		if (times != null && times.getChangeTimes() >= 10) {
			return "换卡次数已达上限10次";
		}
		BankInfoVo bankInfoVo = bankInfoMapper.getUserCurrentCard(userId);
		if (bankInfoVo == null) {
			return "请先添加银行卡";
		}
		if (bankInfoVo.getStatus() == 1) {
			return "银行卡更换审核中";
		}
		if (bankInfoVo.getStatus() == 2) {
			return "银行卡更换冻结中";
		}
		return null;
	}

	/**
	 * 验证提交数据
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月1日
	 * @param change
	 * @param userId
	 * @return
	 * @throws Exception String
	 */
	private String autoCheck(BankcardChange change, int userId) throws Exception {
		if (change.getRealName() == null || change.getIdCard() == null || change.getPhone() == null || change.getOldBankcard() == null || change.getNewBankcard() == null
				|| change.getUpdateReason() == null || change.getActiveCode() == null || change.getNewBankCode() == null || (change.getRemark() != null && change.getRemark().length() > 200)) {
			return "非法提交数据错误";
		}

		// if (changeMapper.getLastFailApprove(userId) <= 7) {
		// return "银行卡冻结状态";
		// }

		BankcardTimes times = timesMapper.getByUserId(userId);
		if (times != null && times.getChangeTimes() >= 10) {
			return "换卡次数已达上限10次";
		}

		RealNameApproVo realNameVo = realNameMapper.getByUserId(userId);
		if (!change.getRealName().equals(realNameVo.getRealName())) {
			return "真实姓名错误";
		}
		if (!change.getIdCard().equals(realNameVo.getIdCardNo())) {
			return "证件号码错误";
		}

		BankInfoVo bankInfoVo = bankInfoMapper.getUserCurrentCard(userId);
		if (bankInfoVo.getStatus() == 1) {
			return "银行卡更换审核中,请勿重复申请.";
		}
		if (bankInfoVo.getStatus() == 2) {
			return "银行卡更换冻结中,请勿重复申请.";
		}
		if (!change.getOldBankcard().equals(bankInfoVo.getCardNum())) {
			return "原银行卡错误";
		}

		MobileApproVo mobileVo = mobileMapper.getByUserId(userId);
		if (!change.getPhone().equals(mobileVo.getMobileNum())) {
			return "手机号码错误";
		}
		return null;
	}

	@Override
	public MessageBox addCheckErrorLog(BankcardChange change, ShiroUser user, String addIp) throws Exception {
		int userId = user.getUserId();

		String errorMsg = this.autoCheck(change, userId);
		if (errorMsg == null && user.getIsFinancialUser() == 1) {
			// 连连接口验证银行卡有效否
			// String result =
			// llwappayService.querybankcard(change.getNewBankcard());
			// if (!result.equals(BusinessConstants.SUCCESS)) {
			// errorMsg = "新银行卡错误：" + result;
			// }

			LlWapBankcardInfo llWapBankcardInfo = llwappayService.queryBankcardInfo(change.getNewBankcard());
			if (!llWapBankcardInfo.getResultMsg().equals(BusinessConstants.SUCCESS)) {
				errorMsg = "新银行卡错误：" + llWapBankcardInfo.getResultMsg();
			}
			if (llWapBankcardInfo.getBank_code() == null) {
				errorMsg = "新银行卡错误：开户行不存在！";
			}
			if (!change.getNewBankCode().equals(llWapBankcardInfo.getBank_code())) {
				errorMsg = "新银行卡错误：所选开户行与卡号所在开户行不一致！";
			}
			change.setNewBankCode(llWapBankcardInfo.getBank_code());
			change.setNewBank(llWapBankcardInfo.getBank_name());
		}

		BankcardClick click = new BankcardClick();
		click.setUserId(userId);
		click.setAddIp(addIp);
		click.setPlatform(user.getPlatform());

		// 查询是否有换卡统计信息,没有则新增
		if (timesMapper.getByUserId(userId) == null) {
			timesMapper.insert(userId);
		}

		if (errorMsg != null) {
			// 1.记录一条错误click
			click.setErrorMsg(errorMsg);
			click.setType(-1);
			clickMapper.add(click);

			// 2.点击申请次数加1
			timesMapper.addTimes(userId, 0, 0, 1);

			return MessageBox.build("0", errorMsg);
		}
		String valiateResult = phoneService.compareSmsValidate(change.getPhone(), change.getActiveCode().trim(), String.valueOf(BusinessConstants.BANK_INFO_MODIFY_FUNCTION));
		if (!"success".equals(valiateResult)) {
			return MessageBox.build("0", "手机验证码错误");
		}
		return null;
	}

	/**
	 * 提交银行卡更换申请
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月1日
	 * @param change
	 * @param user
	 * @param addIp
	 * @return
	 * @throws Exception MessageBox
	 */
	@Override
	public MessageBox add(BankcardChange change, List<BankcardPic> pics, ShiroUser user, String addIp) throws Exception {
		if (pics == null || pics.size() == 0 || pics.size() > 10) {
			return MessageBox.build("0", "认证资料上传最少1张最多10张");
		}

		// 用于判断重复提交
		MemberVo memberVo = memberMapper.queryMemberByIdForUpdate(user.getUserId());
		Integer userId = memberVo.getId();

		if (bankInfoMapper.getUserCurrentCard(userId) == null) {
			return MessageBox.build("0", "请先添加银行卡");
		}

		String errorMsg = this.autoCheck(change, userId);
		if (errorMsg == null && user.getIsFinancialUser() == 1) {
			LlWapBankcardInfo c = llwappayService.queryBankcardInfo(change.getNewBankcard());
			if (c.getResultMsg().equals(BusinessConstants.SUCCESS)) {
				change.setNewBank(c.getBank_name());
				change.setNewBankCode(c.getBank_code());
			} else {
				errorMsg = "新银行卡错误：" + c.getResultMsg();
			}
		} else {
			List<Bank> bankList = bankInfoService.queryBankList(null);
			for (Bank vo : bankList) {
				if (change.getNewBankCode().equals(vo.getBankCode())) {
					change.setNewBank(vo.getBankName());
				}
			}

		}

		BankcardClick click = new BankcardClick();
		click.setUserId(userId);
		click.setAddIp(addIp);
		click.setPlatform(user.getPlatform());

		// 查询是否有换卡统计信息,没有则新增
		if (timesMapper.getByUserId(userId) == null) {
			timesMapper.insert(userId);
		}

		// 填写信息有误
		if (errorMsg != null) {
			return MessageBox.build("0", "申请提交失败：" + errorMsg);
		}

		// 填写信息无误，记录一条正确click
		click.setType(1);
		clickMapper.add(click);

		// 插入换卡提交日志
		createApplyLogForChangeBankCard(change, userId);

		// 总资产小于100元，自动审核通过
		String returnStr = "申请提交成功";
		change.setUserId(userId);
		BigDecimal total = accountMapper.queryByUserId(userId).getTotal();
		if (total.doubleValue() < 100) {
			/* 自动初审信息 */
			change.setApproveIp(addIp);
			change.setApproveRemark("用户当前资产总额为：" + total + "元，系统自动审核通过。");
			change.setApproveTime(new Date());
			change.setApproveUserId(0);
			change.setApproveUserName("系统");

			/* 自动复审信息 */
			change.setRecheckRemark("用户当前资产总额为：" + total + "元，系统自动复审通过。");
			change.setRecheckTime(new Date());
			change.setRecheckUserId(0);
			change.setRecheckIp(addIp);
			change.setRecheckUserName("系统");

			/* 复审通过 */
			change.setApproveStatus(2);

			// 更新用户银行卡信息，换卡&申请&点击申请次数加1
			changeMapper.updateBankinfo(change);
			timesMapper.addTimes(userId, 1, 1, 1);

			// 添加银行卡操作日志--系统自动审核换卡通过
			createApproveLogForBankCardChange(change, userId);

			// 添加银行卡操作日志--系统自动复审换卡通过
			createRecheckLogForBankCardChange(change, userId);

			// 调连连支付接口解绑银行卡
			String noAgree = bankInfoMapper.getUserCurrentCard(userId).getNoAgree();
			if (noAgree != null && !"".equals(noAgree) && user.getIsFinancialUser() == 1) {
				LlWapBankcardUnbindRequest llWapBankcardUnbindRequest = new LlWapBankcardUnbindRequest();
				llWapBankcardUnbindRequest.setUser_id(String.valueOf(user.getUserId()));
				String s = llwappayService.saveBankcardUnbind(llWapBankcardUnbindRequest);
				if (!s.equals(BusinessConstants.SUCCESS)) {
					return MessageBox.build("0", "换卡失败：" + s);
				}
			}
			returnStr = "系统自动审核通过,您已换卡成功.";
		} else {
			// 银行卡状态为审核中，申请&点击申请次数加1
			changeMapper.updateBankinfoStatus(userId, 1);
			timesMapper.addTimes(userId, 0, 1, 1);
			change.setApproveStatus(0);// 待审核状态

			// 发送邮件给客服
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("applyUser", user.getUserName());
			templateMap.put("applyTime", DateUtils.format(new Date(), DateUtils.YMD_HMS));
			String content = baseFreemarkerService.generateEmailContentByFreeMarker("protocol/applychangebank.ftl", templateMap);
			String to = changeMapper.kefuEmailList();
			try {
				EmailSendVo emailSendVo = new EmailSendVo();
				emailSendVo.setContent(content);
				emailSendVo.setSubject("国诚金融-客户更换银行卡审批提醒");
				if (to != null) {
					emailSendVo.setToUsers(to.split(","));
					emailService.send(emailSendVo);
				}
			} catch (Exception e) {
				System.out.println("国诚金融-客户更换银行卡审批提醒-邮件发送失败" + e);
			}
		}

		// 保存申请信息
		change.setPlatform(user.getPlatform());
		change.setUserName(user.getUserName());
		change.setUserId(userId);
		change.setAddIp(addIp);
		change.setIdCardType(realNameMapper.getByUserId(userId).getCardType());// 证件类型
		change.setLastAddTime(changeMapper.getLastApplyTime(userId));// 上次提交申请时间

		changeMapper.addChange(change);

		for (BankcardPic p : pics) {
			p.setBcId(change.getId());
			p.setAddIp(addIp);
			p.setUserId(userId);
			picMapper.add(p);
		}
		return MessageBox.build("1", returnStr);
	}

	
	/**
	 * 
	 */
	
	@Override
	public MessageBox addModify(BankcardChange change, List<BankcardPic> pics, ShiroUser user, String addIp) throws Exception {
		if (pics != null && pics.size() >0  ) {
		 
		
		for (BankcardPic p : pics) {
			if (p.getId() != null) {
				continue;
			}
			p.setBcId(change.getId());
			p.setAddIp(addIp);
			p.setUserId(user.getUserId());
			picMapper.add(p);
		}
	
		}
		
		
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("applyUser",  user.getUserName());
			templateMap.put("applyTime", DateUtils.format(new Date(), DateUtils.YMD_HMS));
			String content = baseFreemarkerService.generateEmailContentByFreeMarker("protocol/editbankcarddata.ftl", templateMap);
			String to = changeMapper.kefuEmailList();
			try {
				EmailSendVo emailSendVo = new EmailSendVo();
				emailSendVo.setContent(content);
				emailSendVo.setSubject("国诚金融-客户重新修改银行卡资料审批提醒");
				if (to != null) {
					emailSendVo.setToUsers(to.split(","));
					emailService.send(emailSendVo);
				}
			} catch (Exception e) {
				logger.error("editPics---国诚金融-客户更换银行卡重新上传资料审批提醒-邮件发送失败" + e);
			}
		return MessageBox.build("1", "修改上传资料成功");
	}
	
	/**
	 * 上传图片
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月1日
	 * @param files
	 * @param change
	 * @param picType
	 * @param request
	 * @return
	 * @throws Exception MessageBox
	 */
	@Override
	public MessageBox addPics(MultipartFile[] files, BankcardChange change, String picType, HttpServletRequest request, Integer userId) throws Exception {

		if (null == files || files.length <= 0) {
			return MessageBox.build("0", "请选择文件");
		}

		if (files.length > 5) {
			return MessageBox.build("0", "每次最多选择5个文件");
		}

		List<BankcardPic> tempChangePics = (List<BankcardPic>) request.getSession().getAttribute("tempChangePics");
		if (tempChangePics != null && tempChangePics.size() >= 10) {
			return MessageBox.build("0", "最多上传10张");
		}
		if (tempChangePics != null && tempChangePics.size() + files.length > 10) {
			return MessageBox.build("0", "最多上传10张");
		}

		// 循环获取file数组中得文件
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];

			// 验证文件格式和大小是否正确
			String s = FileUtil.validateFileInfo(file, BusinessConstants.IMAGE_MAX_SIZE, BusinessConstants.IMAGE_FILE_TYPE_LIST);
			if (!"success".equals(s)) {
				return MessageBox.build("0", s);
			}

			// 改变文件名
			String filename = file.getOriginalFilename();
			String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
			// 上传图片命名规则：年月日时分秒毫秒_用户ID_6位随机数
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US);
			String randomFileName = sf.format(new Date()) + "_" + userId + "_" + String.valueOf(Math.round(Math.random() * 899999 + 100000));
			String lastFileName = randomFileName + extName;

			// 增加水印上传
			FileUtil.uploadAddWatermark(file, request, NewLoactionConstants.CHANGE_BANK_FILE_PATH, lastFileName, null);

			// 保存
			BankcardPic pic = new BankcardPic();
			pic.setAddIp(change.getAddIp());
			pic.setBcId(change.getId());
			pic.setPicType(picType);
			pic.setPicUrl(NewLoactionConstants.CHANGE_BANK_FILE_PATH + lastFileName);
			pic.setUserId(change.getUserId());
			// picMapper.add(pic);

			if (tempChangePics != null)
				tempChangePics.add(pic);
		}

		return MessageBox.build("1", "上传成功");
	}

	// public List<String> addPics(MultipartFile[] files, HttpServletRequest
	// request, Integer userId) throws Exception {
	//
	// List<String> list = new ArrayList<>();
	//
	// // 循环获取file数组中得文件
	// for (int i = 0; i < files.length; i++) {
	// MultipartFile file = files[i];
	//
	// // 验证文件格式和大小是否正确
	// String s = FileUtil.validateFileInfo(file, 3l,
	// BusinessConstants.IMAGE_FILE_TYPE_LIST);
	// if (!"success".equals(s)) {
	// return null;
	// }
	//
	// // 改变文件名
	// String filename = file.getOriginalFilename();
	// String extName =
	// filename.substring(filename.lastIndexOf(".")).toLowerCase();
	// // 上传图片命名规则：年月日时分秒毫秒_用户ID_6位随机数
	// SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS",
	// Locale.US);
	// String randomFileName = sf.format(new Date()) + "_" + userId + "_" +
	// String.valueOf(Math.round(Math.random() * 899999 + 100000));
	// String lastFileName = randomFileName + extName;
	//
	// // 增加水印上传
	// FileUtil.uploadAddWatermark(file, request,
	// NewLoactionConstants.CHANGE_BANK_FILE_PATH, lastFileName, null);
	//
	// list.add(NewLoactionConstants.CHANGE_BANK_FILE_PATH + lastFileName);
	// }
	//
	// return list;
	// }

	/****************************************** begin private method ***********************/

	/**
	 * <p>
	 * Description:添加银行卡操作日志--系统自动审核换卡通过<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年4月20日
	 * @param change
	 * @param userId void
	 */
	private void createApproveLogForBankCardChange(BankcardChange change, Integer userId) {
		BankinfoLog bankinfoLog1 = new BankinfoLog();
		bankinfoLog1.setUserId(userId);
		bankinfoLog1.setCardNum(change.getNewBankcard());
		bankinfoLog1.setType(3);// 3:更换
		bankinfoLog1.setStatus(0);// 0：有效
		bankinfoLog1.setAddBy(change.getApproveUserId());
		bankinfoLog1.setAddTime(new Date());
		bankinfoLog1.setRemark("系统自动初审换卡通过");
		bankLogMapper.insertOld(bankinfoLog1);
	}

	/**
	 * <p>
	 * Description:添加银行卡操作日志--系统自动复审换卡通过<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年4月20日
	 * @param change
	 * @param userId void
	 */
	private void createRecheckLogForBankCardChange(BankcardChange change, Integer userId) {
		BankinfoLog bankinfoLog1 = new BankinfoLog();
		bankinfoLog1.setUserId(userId);
		bankinfoLog1.setCardNum(change.getNewBankcard());
		bankinfoLog1.setType(3);// 3:更换
		bankinfoLog1.setStatus(0);// 0：有效
		bankinfoLog1.setAddBy(change.getApproveUserId());
		bankinfoLog1.setAddTime(new Date());
		bankinfoLog1.setRemark("系统自动复审换卡通过");
		bankLogMapper.insertOld(bankinfoLog1);
	}

	/**
	 * <p>
	 * Description:创建银行卡换卡操作申请日志<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年4月20日
	 * @param change
	 * @param userId void
	 */
	private void createApplyLogForChangeBankCard(BankcardChange change, Integer userId) {
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(userId);
		bankinfoLog.setCardNum(change.getNewBankcard());
		bankinfoLog.setType(3);// 3:更换
		bankinfoLog.setStatus(0);// 0：有效
		bankinfoLog.setAddBy(userId);
		bankinfoLog.setAddTime(new Date());
		bankinfoLog.setRemark("用户换卡申请");
		bankLogMapper.insert(bankinfoLog);
	}
	/****************************************** end private method ***********************/

	@Override
	public List<BankcardPic> queryBankcardPicsByUserId(Integer userId) {
		return picMapper.queryBankcardPicsByUserId(userId);
	}

	@Override
	public MessageBox saveEditPics(List<Integer> waitDeleteIds, BankcardChange change, List<BankcardPic> pics, ShiroUser currentUser, String realIpAddr) {

		if (pics != null && pics.size() == 0) {
			return MessageBox.build("0", "不能上传空资料");
		}

		if (pics != null && pics.size() >= 10) {
			return MessageBox.build("0", "最多上传10张");
		}

		try {


			if (waitDeleteIds != null && !waitDeleteIds.isEmpty()) {
				for (Integer deleteId : waitDeleteIds) {
					removePicById(deleteId);
				}
			}

			for (BankcardPic p : pics) {
				if (p.getId() != null) {
					continue;
				}
				p.setBcId(change.getId());
				p.setAddIp(realIpAddr);
				p.setUserId(currentUser.getUserId());
				picMapper.add(p);
			}
			sendEmailTo(currentUser);
		} catch (Exception e) {
			logger.error("saveEditPics::userID=" + currentUser.getUserId() + "::" + e);
			MessageBox.build("0", "上传失败");
		}
		return MessageBox.build("1", "上传成功");
	}

	private void sendEmailTo(ShiroUser currentUser) throws Exception {
		// 发送邮件给客服
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("applyUser", currentUser.getUserName());
		templateMap.put("applyTime", DateUtils.format(new Date(), DateUtils.YMD_HMS));
		String content = baseFreemarkerService.generateEmailContentByFreeMarker("protocol/editbankcarddata.ftl", templateMap);
		String to = changeMapper.kefuEmailList();
		try {
			EmailSendVo emailSendVo = new EmailSendVo();
			emailSendVo.setContent(content);
			emailSendVo.setSubject("国诚金融-客户重新修改银行卡资料审批提醒");
			if (to != null) {
				emailSendVo.setToUsers(to.split(","));
				emailService.send(emailSendVo);
			}
		} catch (Exception e) {
			logger.error("editPics---国诚金融-客户更换银行卡重新上传资料审批提醒-邮件发送失败" + e);
		}
	}

	@Override
	public void removePicById(int id) {
		picMapper.deletePicById(id);
	}

	@Override
	public String getReasonByUserId(int userId)  {
		// TODO Auto-generated method stub
		return picMapper.getReasonByUserId(userId);
	}
}
