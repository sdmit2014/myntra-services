package com.wecodee.myntra.report.dto;

import java.util.Date;

public class ReportDTO {

	private Date fromDate;

	private Date toDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "ReportDTO [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

}
