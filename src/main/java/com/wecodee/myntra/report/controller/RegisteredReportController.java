package com.wecodee.myntra.report.controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wecodee.myntra.admin.dto.SearchFilterDTO;
import com.wecodee.myntra.report.dto.ReportDTO;
import com.wecodee.myntra.report.service.RegisteredReportSerive;
import com.wecodee.myntra.responseDTO.ApiResponse;
import org.springframework.http.HttpHeaders;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/report")
public class RegisteredReportController {

	@Autowired
	private RegisteredReportSerive registeredReportSerive;

	@PostMapping("registeredaccounts")
	public ApiResponse<JSONObject> searchRegisterRecords(@RequestBody SearchFilterDTO<ReportDTO> registerFilterDTO) {
		return registeredReportSerive.searchRegisterRecords(registerFilterDTO);
	}

	@PostMapping("/registeredaccountsexcel")
	public ResponseEntity<InputStreamResource> searchRegisterRecordsExcel(@RequestBody ReportDTO reportDTO)
			throws FileNotFoundException {
		ByteArrayInputStream in = registeredReportSerive.registeredAccountsExcel(reportDTO);
		String fileName = "Transactions.xlsx";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + fileName);
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

}
