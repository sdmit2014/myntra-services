package com.wecodee.myntra.report.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wecodee.myntra.common.utils.Helper;
import com.wecodee.myntra.model.Register;
import com.wecodee.myntra.report.dto.ReportDTO;

@Service
public class ExcelGenerateService {

	final Logger log = LoggerFactory.getLogger(this.getClass());

	public ByteArrayInputStream registeredDataExcel(List<Register> registeredList, ReportDTO reportDTO) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			// create blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet spreadsheet = workbook.createSheet("Registered Records");

			XSSFRow row;

			int rowid = 0;
			int cellid = 0;

			spreadsheet = setExcelReportTile(workbook, spreadsheet, "Registered Records");
			row = spreadsheet.createRow(3);
			row.createCell(0).setCellValue("From Date");
			row.createCell(1).setCellValue(Helper.formatDateToString(reportDTO.getFromDate()));

			row.createCell(3).setCellValue("Report Date");
			row.createCell(4).setCellValue(Helper.formatDateToString(new Date()));

			row = spreadsheet.createRow(4);
			row.createCell(0).setCellValue("To Date");
			row.createCell(1).setCellValue((Helper.formatDateToString(reportDTO.getToDate())));

			String[] colList = { "Id", "User Name", "password", "PhoneNumber", "Date Of Birth" };

			rowid = 6;
			cellid = 0;

			row = spreadsheet.createRow(rowid);

			// header style
			XSSFCellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setBorderBottom(BorderStyle.MEDIUM);
			headerStyle.setBorderLeft(BorderStyle.MEDIUM);
			headerStyle.setBorderRight(BorderStyle.MEDIUM);
			headerStyle.setBorderTop(BorderStyle.MEDIUM);
			headerStyle.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
			XSSFFont font = workbook.createFont();
			font.setBold(true);
			headerStyle.setFont(font);

			for (int i = 0; i < colList.length; i++) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue(colList[i]);
				cell.setCellStyle(headerStyle);
			}
			for (int i = 0; i < registeredList.size(); i++) {
				row = spreadsheet.createRow(++rowid);
				Register register = registeredList.get(i);
				System.out.println(register.toString());
				row.createCell(0).setCellValue(register.getId());
				row.createCell(1).setCellValue(register.getUserName());
				row.createCell(2).setCellValue(register.getPassword());
				row.createCell(3).setCellValue(register.getMobileNumber());
				row.createCell(4).setCellValue(register.getDateOfBirth());
			}
			for (int j = 0; j <= 4; j++)
				spreadsheet.autoSizeColumn(j);
			workbook.write(byteArrayOutputStream);
			workbook.close();
			log.info(".xlsx written successfully");
			return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		} catch (FileNotFoundException e) {
			log.error("In exception ->" + e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("In exception ->" + e);
			e.printStackTrace();
		}
		return null;
	}

	public XSSFSheet setExcelReportTile(XSSFWorkbook workbook, XSSFSheet spreadsheet, String excelTileData) {
		try {
			XSSFRow row;
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0, 9);
			spreadsheet.addMergedRegion(cellRangeAddress);
			row = spreadsheet.createRow(0);
			Cell reportTitleCell = row.createCell(0);
			reportTitleCell.setCellValue(excelTileData);
			CellStyle reportTitleCellStyle = workbook.createCellStyle();
			XSSFFont reportTitleFont = workbook.createFont();
			reportTitleFont.setBold(true);
			reportTitleFont.setColor(Short.valueOf("0"));
			reportTitleFont.setFontHeightInPoints(Short.valueOf("16"));
			reportTitleCellStyle.setFont(reportTitleFont);
			reportTitleCell.setCellStyle(reportTitleCellStyle);
			CellUtil.setAlignment(reportTitleCell, HorizontalAlignment.CENTER);
			CellUtil.setVerticalAlignment(reportTitleCell, VerticalAlignment.CENTER);
		} catch (Exception e) {
			log.error("In exception ->" + e);
			e.printStackTrace();
			return null;
		}
		return spreadsheet;
	}

}
