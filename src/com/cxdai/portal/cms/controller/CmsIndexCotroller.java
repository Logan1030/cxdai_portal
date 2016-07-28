package com.cxdai.portal.cms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.common.page.Page;
import com.cxdai.portal.account.vo.SearchInvestCnd;
import com.cxdai.portal.borrow.vo.BorrowVo;
import com.cxdai.portal.cms.constant.CmsConstant;
import com.cxdai.portal.cms.service.CmsArticleService;
import com.cxdai.portal.cms.service.CmsChannelService;
import com.cxdai.portal.cms.service.CmsPediaEntryService;
import com.cxdai.portal.cms.service.CmsTagService;
import com.cxdai.portal.cms.vo.CmsArticle;
import com.cxdai.portal.cms.vo.CmsChannel;
import com.cxdai.portal.cms.vo.CmsPediaEntry;
import com.cxdai.portal.cms.vo.CmsTag;
import com.cxdai.portal.first.service.FirstBorrowService;
import com.cxdai.portal.first.vo.FirstBorrowCnd;
import com.cxdai.portal.first.vo.FirstBorrowVo;
import com.cxdai.portal.invest.service.InvestRecordService;
import com.cxdai.portal.news_notice.service.NewsNoticeService;
import com.cxdai.portal.news_notice.vo.NewsNoticeVo;
import com.cxdai.portal.transfer.service.TransferService;
import com.cxdai.portal.transfer.vo.BTransferVo;
import com.cxdai.portal.transfer.vo.SearchTransferVo;

@Controller
public class CmsIndexCotroller {

	@Autowired
	CmsArticleService articleService;

	@Autowired
	CmsPediaEntryService cmsPediaEntryService;

	@Autowired
	private FirstBorrowService firstBorrowService;

	@Autowired
	NewsNoticeService newsNoticeService;

	@Autowired
	CmsChannelService channelService;

	@Autowired
	CmsTagService cmsTagService;

	@Autowired
	InvestRecordService investRecordService;



	@Autowired
	TransferService transferService;

	@RequestMapping(value = "/zixun")
	public ModelAndView index() throws Exception {

		ModelAndView mav = new ModelAndView("/cms/informationindex");

		CmsChannel cmsChannel = null;
		CmsChannel loanCmsChannel = null;
		List<CmsTag> cmsTags = null;
		List<CmsTag> loanCmsTags = null;
		List<CmsArticle> moneyManagementCmsArticles = null;
		List<CmsArticle> loanCmsArticles = null;

		// 查询头部动态显示的栏目
		List<CmsChannel> showChannels = channelService.queryShowChannels();

		// 查询首页 平台公告，行业新闻，媒体报道， 置顶的文章
		int channelTopArticles[] = new int[] { CmsConstant.TRADENEWS, CmsConstant.PLATFORMNOTICE, CmsConstant.MEDIAREPORT };
		List<CmsArticle> topsCmsArticles = articleService.queryTopArticlesByChannels(channelTopArticles, 0, 5);

		// 两篇热门文章
		List<CmsArticle> hotsCmsArticles = articleService.findHotArticlesByLimit(0, 11);
		// 知识百科
		List<CmsPediaEntry> cmsPediaEntrys = cmsPediaEntryService.findPediaEntryByLimit(0, 25);
		// 直通车专区
		FirstBorrowCnd firstBorrowCnd = new FirstBorrowCnd();
		// firstBorrowCnd.setType(String.valueOf(2));// 查询开通未满的
		Page page = new Page(0, 7);
		List<FirstBorrowVo> firstBorrowVos = firstBorrowService.queryFirstBorrowByFirstBorrowCnd(firstBorrowCnd, page);
		// 根据板块查询最新第一个文章
		/*
		 * int channels[] = new int[] { CmsConstant.INVESTMENTSTRATEGY,
		 * CmsConstant.PERSONALFINANCE, CmsConstant.LOANTOBUYCAR,
		 * CmsConstant.CONSUMPTIONLOAN, CmsConstant.KNOW }; List<CmsArticleVo>
		 * cmsArticleVos = articleService.queryNewArticleByChannel(channels);
		 */
		// 国诚最新公告
		List<NewsNoticeVo> newsNoticeVos = newsNoticeService.queryNewNewsNoticeList(0, 0, 3);
		// 查询理财 文章 以及标签
		List<CmsChannel> cmsChannels = channelService.queryCmsChannelListByParentId(CmsConstant.MONEYMANAGEMENT);
		moneyManagementCmsArticles = articleService.queryCmsArticleListByParentChannelId(CmsConstant.MONEYMANAGEMENT, 0, 12);
		cmsTags = cmsTagService.queryCmsTagListByParentChannelId(CmsConstant.MONEYMANAGEMENT, 0, 21);

		// 查询借款类文章 以及标签
		List<CmsChannel> loanCmsChannels = channelService.queryCmsChannelListByParentId(CmsConstant.LOAN);
		loanCmsArticles = articleService.queryCmsArticleListByParentChannelId(CmsConstant.LOAN, 0, 12);
		loanCmsTags = cmsTagService.queryCmsTagListByParentChannelId(CmsConstant.LOAN, 0, 21);

		// 投资攻略
		List<CmsArticle> InvestmentStrategyCmsArticles = articleService.queryCmsArticleList(CmsConstant.INVESTMENTSTRATEGY, 0, 10);
		// 政策法规
		List<CmsArticle> regulationsCmsArticles = articleService.queryCmsArticleList(CmsConstant.POLICIESREGULATIONS, 0, 10);
		// 常见问题
		List<CmsArticle> faqCmsArticles = articleService.queryCmsArticleList(CmsConstant.FAQ, 0, 14);
		Date now = new Date();
		mav.addObject("indexCmsChannels", cmsChannels);
		mav.addObject("cmsChannel", cmsChannel);
		mav.addObject("loanCmsChannel", loanCmsChannel);
		mav.addObject("topsCmsArticles", topsCmsArticles);
		mav.addObject("showChannels", showChannels);
		return mav.addObject("hotsCmsArticles", hotsCmsArticles).addObject("cmsPediaEntrys", cmsPediaEntrys).addObject("firstBorrowVos", firstBorrowVos)
				.addObject("newsNoticeVos", newsNoticeVos).addObject("moneyManagementCmsArticles", moneyManagementCmsArticles).addObject("loanCmsChannels", loanCmsChannels)
				.addObject("cmsTags", cmsTags).addObject("loanCmsChannel", loanCmsChannel).addObject("loanCmsArticles", loanCmsArticles).addObject("loanCmsTags", loanCmsTags)
				.addObject("InvestmentStrategyCmsArticles", InvestmentStrategyCmsArticles).addObject("regulationsCmsArticles", regulationsCmsArticles).addObject("faqCmsArticles", faqCmsArticles)
				.addObject("nowTime", now).addObject("isHome", 1);
	}

	@RequestMapping(value = "/searchArticle/{type}/{channelId}")
	public ModelAndView index(@PathVariable("type") Integer type, @PathVariable("channelId") Integer channelId) throws Exception {
		ModelAndView mav = new ModelAndView("/cms/content");
		CmsChannel cmsChannel = null;
		List<CmsTag> cmsTags = null;
		List<CmsArticle> cmsArticles = null;
		List<CmsChannel> cmsChannels = channelService.queryCmsChannelListByParentId(type);
		if (!cmsChannels.isEmpty()) {
			cmsArticles = articleService.queryCmsArticleList(channelId, 0, 12);
			cmsChannel = channelService.getCmsChannelById(channelId);
			cmsTags = cmsTagService.queryCmsTagList(channelId, 0, 27);
		}
		mav.addObject("cmsChannels", cmsChannels);
		mav.addObject("channel", cmsChannel);
		mav.addObject("type", type);
		return mav.addObject("cmsArticles", cmsArticles).addObject("cmsTags", cmsTags);
	}

	@RequestMapping(value = "/searchTab/{type}")
	public ModelAndView index(@PathVariable("type") Integer type) throws Exception {
		ModelAndView mav = new ModelAndView("/cms/content");
		if (type.intValue() == 1) {
			// 直通车专区
			FirstBorrowCnd firstBorrowCnd = new FirstBorrowCnd();
			Page page = new Page(0, 7);
			List<FirstBorrowVo> firstBorrowVos = firstBorrowService.queryFirstBorrowByFirstBorrowCnd(firstBorrowCnd, page);
			mav.setViewName("/cms/zhitongchecontent");
			mav.addObject("firstBorrowVos", firstBorrowVos).addObject("nowTime", new Date());
		} else if (type.intValue() == 2) {
			// 投标
			SearchInvestCnd seach = new SearchInvestCnd();
			seach.setLimitTime("isTendering");
			seach.setOrderType("asc");
			List<BorrowVo> investList = investRecordService.queryInvestRecordList(seach, new Page(0, 7));
			mav.setViewName("/cms/invertcontent");
			mav.addObject("investList", investList);
		} else if (type.intValue() == 3) {
			// 转让
			SearchTransferVo seach = new SearchTransferVo();
			seach.setOrderBy("asc");
			seach.setTransferStatus("2");
			List<BTransferVo> transferlist = transferService.querytransferRecordList(seach, new Page(0, 7));
			mav.setViewName("/cms/transfercontent");
			mav.addObject("transferlist", transferlist);
		}

		return mav;
	}


}
