package ch.zhaw.iwi.iwitask.service;

import javax.servlet.http.HttpServletResponse;

import spark.Response;

public final class ResponseUtility {

	public static void createReportResponse(Response response, byte[] data) {
		response.header("Content-Description", "File Transfer");
		response.header("Content-Type", "application/force-download");
		response.header("Content-Type", "application/download");
		response.header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.header("Content-Disposition", "attachment;filename=report" + System.currentTimeMillis() + ".xlsx");
		try {
			HttpServletResponse raw = response.raw();
			raw.getOutputStream().write(data);
			raw.getOutputStream().flush();
			raw.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
