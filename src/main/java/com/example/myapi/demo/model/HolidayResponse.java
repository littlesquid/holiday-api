package com.example.myapi.demo.model;

import java.util.ArrayList;
import java.util.List;

public class HolidayResponse {
private String status = "";
private List<Holiday> holidays = null;

HolidayResponse() {
	holidays = new ArrayList<Holiday>();
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}



public List<Holiday> getHolidays() {
	return holidays;
}

public void setHolidays(List<Holiday> holidays) {
	this.holidays = holidays;
}



}
