package com.fujiang.weiji.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 初审工作区待审
 * @author jindoulixing
 *
 */
@RestController
@RequestMapping("/firstTrial/pendingTrial")
public class FirstTrialpendingTrialAuditController {
	private static final Logger logger = LoggerFactory.getLogger(FirstTrialpendingTrialAuditController.class);

	@RequestMapping(value = "/baquery/export", method = RequestMethod.GET)
	public void exportBaquery(HttpServletRequest request,HttpServletResponse response/*, BaInfoQueryQo qo*/) {
		/*String fileName = "初审工作区待初审病例列表导出-" + new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		try {
			String token=request.getHeader(AuthConstants.HEADER_TOKEN);
			UserLoginInfo info=(UserLoginInfo) redisOper.getData(AuthConstants.TOKEN_CACHE_PREFIX + token);
			qo.setAuditUser(info.getLoginNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			response.setContentType("multipart/form-data;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            try {
                String formatFileName = URLEncoder.encode(fileName, "UTF-8");
                response.setHeader("Content-Disposition", "attachment;fileName=" + formatFileName + ".xlsx");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
			PageableSupplier<BaInfo, BaInfoQueryQo> supplier = new PageableSupplier<BaInfo, BaInfoQueryQo>();
			supplier.setFunc(service::queryFirstTrialBaInfos);
			supplier.setParam(qo);

			POIExcelUtils.createExcel(BaInfo.class, supplier, null, response.getOutputStream(),"初审工作区待初审病例列表");
			return null;
		} catch (IOException e) {
			logger.error("医保智能审核系统-违规审核审核-初审工作区-待审病例信息导出失败", e);
			return new ApiResult(500, "医保智能审核系统-违规审核审核-初审工作区-待审病例信息导出失败", null);
		}*/
	}
}
