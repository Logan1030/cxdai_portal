<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 
 ${param.isHome!=null?'<h2><a href="#">认证资料</a></h2>':'<h1><a href="#">认证资料</a></h1>' }
         <div class="help-title" onclick="list(12)"><a href="javascript:void(0);" id="help_title">认证资料<span></span></a></div>
         <div class="help-content" id="list12">
             <p class="blues">诚薪贷</p>
             <p>身份认证、个人信用报告、劳动合同或在职证明、近3个月工资卡银行流水等。</p>
             <p class="blues">诚商贷</p>
             <p>房产认证、购车认证、结婚认证、学历认证、技术职称认证、手机实名认证、居住地证明等。</p>
             <p class="up" onclick="list(12)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(13)"><a href="javascript:void(0);" id="help_title">身份认证<span></span></a></div>
         <div class="help-content" id="list13">
             <p>1， 本人身份证原件的正、反两面照片。</p>
             <p>2，本人手持身份证正面头部照（确保身份证上的信息没有被遮挡，避免证件与头部重叠）。</p>
             <p>3，本人户口本。</p>
             <p>认证有效期：1-5年。</p>
             <p class="up" onclick="list(13)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(14)"><a href="javascript:void(0);" id="help_title">信用报告<span></span></a></div>
         <div class="help-content" id="list14">
             <p>个人信用报告是由中国人民银行出具，全面记录个人信用活动，反映个人信用基本状况的文件。本报告是国诚金融了解您信用状况的一个重要参考资料。</p> 
             <p>认证说明：个人信用报告需15日内开具。</p> 
             <p>认证条件：信用记录良好。</p>
             <p>认证有效期：6个月。</p>
             <p>如何办理：可去当地人民银行打印，部分地区可登录个人信用信息服务平台。全国各地征信中心联系方式查询个人信用信息服务平台，关于个人查询本人信用报告实施收费的公告。</p>
             <p class="up" onclick="list(14)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(15)"><a href="javascript:void(0);" id="help_title">（诚薪贷）工作认证<span></span></a></div>
         <div class="help-content" id="list15">
             <p>您的工作状况是国诚金融评估您信用状况的主要依据之一。</p> 
             <p>请提供下面任意一项资料：</p> 
             <p>1，加盖单位公章（或劳动合同专用章）的劳动合同。</p>
             <p>2，最近1个月内开具的加盖单位公章（或人力章、财务章）的机打（手写无效）在职证明。</p>
             <p>3，带有姓名、照片、工作单位名称的工作证。</p>
             <p>认证条件：本人需在现单位工作满3个月。</p>
             <p>认证有效期：6个月。</p>
             <p class="up" onclick="list(15)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(16)"><a href="javascript:void(0);" id="help_title">（诚薪贷）收入认证<span></span></a></div>
         <div class="help-content" id="list16">
             <p>您的银行流水单是国诚金融评估您收入状况的主要依据之一。</p> 
             <p>请提供下面任意一项资料：</p> 
             <p>1，可体现工资项的最近3个月的工资卡银行流水单。</p>
             <p>2，可体现工资项的最近3个月的网银电脑截屏。</p>
             <p>认证条件：本人名下近3个月的月收入均在2000以上。</p>
             <p>认证有效期：6个月。</p>
             <p class="up" onclick="list(16)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(17)"><a href="javascript:void(0);" id="help_title">房产认证<span></span></a></div>
         <div class="help-content" id="list17">
             <p>房产认证是证明借入者资产及还款能力的重要凭证之一。</p> 
             <p>请提供下面任意一项资料：</p> 
             <p>1，房屋产权证明。</p>
             <p>2，购房合同以及近3个月的还贷流水。</p>
             <p>3，购房发票以及近3个月的还贷流水。</p>
             <p>4，按揭合同以及近3个月的还贷流水。</p>
             <p>认证条件：必须是商品房，且房产是本人名下所有或共有的。</p>
             <p>认证有效期：永久。</p>
             <p class="up" onclick="list(17)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div> 
         <div class="help-title" onclick="list(18)"><a href="javascript:void(0);" id="help_title">购车认证<span></span></a></div>
         <div class="help-content" id="list18">
             <p>购车认证是证明借入者资产及还款能力的重要凭证之一。</p> 
             <p>请同时提供下面两项资料：</p> 
             <p>1，车辆行驶证的原件照片。</p>
             <p>2，本人和车辆的合影（照片需露出车牌号码）。</p>
             <p>认证条件：车辆必须是本人名下所有。</p>
             <p>认证有效期：永久。</p>
             <p class="up" onclick="list(18)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(19)"><a href="javascript:void(0);" id="help_title">结婚认证<span></span></a></div>
         <div class="help-content" id="list19">
             <p>结婚认证是证明借入者婚姻状况的重要凭证之一。</p> 
             <p>请同时提供下面两项资料：</p> 
             <p>1，本人正反面身份证照片。</p>
             <p>2，配偶正反面身份证照片。</p>
             <p>3，结婚证原件照片。</p>
             <p>4，当地公安机关出具的婚姻状况证明。</p>
             <p class="up" onclick="list(19)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(20)"><a href="javascript:void(0);" id="help_title">学历认证<span></span></a></div>
         <div class="help-content" id="list20">
             <p>借出者在选择借款申请投标时，借入者的学历也是一个重要的参考因素。为了让借出者更好、更快地相信您的学历是真实的，建议您提供学历证书由公司进行审核认证。</p> 
             <p class="up" onclick="list(20)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(21)"><a href="javascript:void(0);" id="help_title">技术职称认证<span></span></a></div>
         <div class="help-content" id="list21">
             <p>技术职称是经专家评审、反映一个人专业技术水平并作为聘任专业技术职务依据的一种资格。不与工资挂钩，是国诚金融考核借款人信用的评估因素之一。</p> 
             <p>认证说明：技术职称证书原件照片。</p> 
             <p>认证条件：国家承认的二级及以上等级证书。例如律师证、会计证、工程师证等。</p> 
             <p>认证有效期：永久。</p> 
             <p class="up" onclick="list(21)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(22)"><a href="javascript:void(0);" id="help_title">手机实名认证<span></span></a></div>
         <div class="help-content" id="list22">
             <p>手机实名认证需要用户上传手机流水单。手机流水单是用户最近一段时间内的详细通话记录，是国诚金融用以验证借入者真实性的重要凭证之一。</p> 
             <p>认证说明：绑定的手机号码最近3个月的手机详单原件照片。</p> 
             <p>必须提供手机运营商出具的，可以证明机主身份与手机号码关系的文件(办理业务的回执单)。</p> 
             <p>认证有效期：永久。</p> 
             <p class="up" onclick="list(22)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(23)"><a href="javascript:void(0);" id="help_title">居住地证明<span></span></a></div>
         <div class="help-content" id="list23">
             <p>居住地的稳定性，是国诚金融考核借款人的主要评估因素之一。</p> 
             <p>请提供下面任意一项资料：</p> 
             <p>1，用本人姓名登记的水、电、气最近3个月缴费单。</p> 
             <p>2，用本人姓名登记的固定电话最近3个月缴费单。</p>
             <p>3，本人的信用卡最近2个月的月结单。</p>
             <p>4，本人的自有房产证明。</p>
             <p>5，本人父母的房产证明，及证明本人和父母关系的证明材料。</p>
             <p>认证有效期：6个月。</p>
             <p class="up" onclick="list(23)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(24)"><a href="javascript:void(0);" id="help_title">（诚薪贷）工作单位的注册地要求<span></span></a></div>
         <div class="help-content" id="list24">
             <p>国诚金融网站支持的用户是中国大陆地区公民，且工作单位或经营场所注册地必须在中国大陆，港澳台地区以及国外目前不支持。</p> 
             <p class="up" onclick="list(24)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(25)"><a href="javascript:void(0);" id="help_title">（诚薪贷）银行流水<span></span></a></div>
         <div class="help-content" id="list25">
             <p>如果您无法提供工资卡流水，也可提供能体现您收支情况的常用银行卡流水。</p> 
              <p>要求银行流水有连续月份，内容包括交易日期、交易金额、余额、交易地点、交易摘要等。</p> 
             <p class="up" onclick="list(25)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(26)"><a href="javascript:void(0);" id="help_title">（诚商贷）经营地认证<span></span></a></div>
         <div class="help-content" id="list26">
             <p>经营地证明中的经营场地租赁合同是您必须要提供的，90天内的租金发票或水电单据您可选择其中一项提供。</p> 
             <p class="up" onclick="list(26)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(27)"><a href="javascript:void(0);" id="help_title">（诚商贷）借款人资质<span></span></a></div>
         <div class="help-content" id="list27">
             <p>诚商贷产品要求申请人是企业法人、合伙人以及工商个体户。</p> 
             <p class="up" onclick="list(27)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
             
         <div class="help-title" onclick="list(30)"><a href="javascript:void(0);" id="help_title">资料上传<span></span></a></div>
         <div class="help-content" id="list30">
             <p>登录国诚金融网站，打开“我要融资”>>“诚薪贷”（以诚薪贷为例），点击“立即申请”，按照网站流程进行操作，并上传资料。</p> 
            <p class="up" onclick="list(30)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>     

