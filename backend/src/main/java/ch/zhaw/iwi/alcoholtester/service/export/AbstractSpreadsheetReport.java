package ch.zhaw.iwi.alcoholtester.service.export;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class AbstractSpreadsheetReport {

	private Workbook workbook;
	private Sheet sheet;
	private CellStyle boldStyle;
	private CellStyle titleStyle;
	private CellStyle dateStyle;

	protected void init() {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();

		boldStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldStyle.setFont(font);

		titleStyle = workbook.createCellStyle();
		Font fontTitle = workbook.createFont();
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontTitle.setFontHeightInPoints((short) 24);
		titleStyle.setFont(fontTitle);

		dateStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yyy h:mm:ss.000"));
		dateStyle.setAlignment(CellStyle.ALIGN_LEFT);
	}

	protected int createTitle(Sheet sheet, int row, String title) {
		Row titleRow = sheet.createRow(row++);
		Cell cellTitle = titleRow.createCell(0);
		cellTitle.setCellValue(title);
		cellTitle.setCellStyle(getTitleStyle());

		Row dateRow = sheet.createRow(row++);
		Cell cellDate = dateRow.createCell(0);
		cellDate.setCellValue(new Date());
		cellDate.setCellStyle(getDateStyle());
		return row;
	}

	protected byte[] createDocument(int columns) throws IOException {
		for (int s = 0; s < getWorkbook().getNumberOfSheets(); s++) {
			for (int k = 0; k < columns; k++) {
				getWorkbook().getSheetAt(s).autoSizeColumn(k);
			}
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			getWorkbook().write(bos);
			byte[] bytes = bos.toByteArray();
			return bytes;
		} finally {
			bos.close();
			getWorkbook().close();
		}
	}

	protected Workbook getWorkbook() {
		return workbook;
	}

	protected Sheet getSheet() {
		return sheet;
	}

	protected CellStyle getBoldStyle() {
		return boldStyle;
	}

	protected CellStyle getDateStyle() {
		return dateStyle;
	}

	protected CellStyle getTitleStyle() {
		return titleStyle;
	}

	protected CellStyle getNumberStyle(int digits) {
		String format = "0";
		if (digits > 0) {
			format += ".";
		}
		for (int k = 0; k < digits; k++) {
			format += "0";
		}

		CellStyle numberStyle = workbook.createCellStyle();
		DataFormat dataFormat = workbook.createDataFormat();
		numberStyle.setDataFormat(dataFormat.getFormat(format));
		numberStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		return numberStyle;
	}

}
