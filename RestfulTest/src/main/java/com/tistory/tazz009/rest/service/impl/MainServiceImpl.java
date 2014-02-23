package com.tistory.tazz009.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tistory.tazz009.rest.service.ApiResult;
import com.tistory.tazz009.rest.service.GridVO;
import com.tistory.tazz009.rest.service.MainService;
import com.tistory.tazz009.rest.service.RowVO;
import com.tistory.tazz009.rest.service.UserDataVO;

@Service
public class MainServiceImpl implements MainService {

	public ApiResult getList(Integer pageNo) {
		ApiResult result = new ApiResult();
		result.setStatus("SUCCESS");
		result.setData("<li>°á°ú</li>");
		return result;
	}

	public GridVO getJqtGridList(Integer pageNo) {
		GridVO grid = new GridVO();
		grid.setPage("1");
		grid.setTotal("2");
		grid.setRecords("13");
		List<Object> rows = new ArrayList<Object>();
		
		RowVO row = new RowVO();
		row.setId("13");
		ArrayList<String> cell = new ArrayList<String>();
		cell.add("13");
		cell.add("2007-10-06");
		cell.add("Client 3");
		cell.add("1000.00");
		cell.add("0.00");
		cell.add("1000.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("12");
		cell = new ArrayList<String>();
		cell.add("12");
		cell.add("2007-10-06");
		cell.add("Client 2");
		cell.add("700.00");
		cell.add("140.00");
		cell.add("840.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("11");
		cell = new ArrayList<String>();
		cell.add("11");
		cell.add("2007-10-06");
		cell.add("Client 1");
		cell.add("600.00");
		cell.add("120.00");
		cell.add("720.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("10");
		cell = new ArrayList<String>();
		cell.add("10");
		cell.add("2007-10-06");
		cell.add("Client 2");
		cell.add("100.00");
		cell.add("20.00");
		cell.add("120.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("9");
		cell = new ArrayList<String>();
		cell.add("9");
		cell.add("2007-10-06");
		cell.add("Client 1");
		cell.add("200.00");
		cell.add("40.00");
		cell.add("240.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("8");
		cell = new ArrayList<String>();
		cell.add("8");
		cell.add("2007-10-06");
		cell.add("Client 3");
		cell.add("200.00");
		cell.add("0.00");
		cell.add("200.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("7");
		cell = new ArrayList<String>();
		cell.add("7");
		cell.add("2007-10-05");
		cell.add("Client 2");
		cell.add("120.00");
		cell.add("12.00");
		cell.add("134.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("6");
		cell = new ArrayList<String>();
		cell.add("6");
		cell.add("2007-10-05");
		cell.add("Client 1");
		cell.add("50.00");
		cell.add("10.00");
		cell.add("60.00");
		cell.add("null");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("5");
		cell = new ArrayList<String>();
		cell.add("5");
		cell.add("2007-10-05");
		cell.add("Client 3");
		cell.add("100.00");
		cell.add("0.00");
		cell.add("100.00");
		cell.add("no tax at all");
		row.setCell(cell);
		rows.add(row);
		
		row = new RowVO();
		row.setId("4");
		cell = new ArrayList<String>();
		cell.add("4");
		cell.add("2007-10-05");
		cell.add("Client 3");
		cell.add("150.00");
		cell.add("0.00");
		cell.add("150.00");
		cell.add("no tax");
		row.setCell(cell);
		rows.add(row);
		
		grid.setRows(rows);

		UserDataVO userData = new UserDataVO();
		userData.setAmount("3220");
		userData.setTax("342");
		userData.setTotal("3564");
		userData.setName("Totals:");
		return grid;
	}

}
