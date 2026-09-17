package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	//DataProvider needs to returns something - [], [][], Iterator<> 
	@DataProvider(name="LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);	
	}
	
	@DataProvider(name="LoginAPIJSONDataProvider", parallel = true)
	public static Iterator<UserCredentials> loginAPIJSONDataProvider() {
		return JsonReaderUtil.loadJSON("testData/LoginAPITestData.json", UserCredentials[].class);	
	}
	
	@DataProvider(name="CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean createJobBean;
		CreateJobPayload payload;
		while (createJobBeanIterator.hasNext()) {
			createJobBean = createJobBeanIterator.next();
			payload = CreateJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}
		
		return payloadList.iterator();
	}
	
	@DataProvider(name="CreateJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIFakeDataProvider() {
		String fakerCount = System.getProperty("fakerCount","5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		return FakerDataGenerator.generatefakeCreateJobData(fakerCountInt);
	}
	
	@DataProvider(name="CreateJobAPIJSONDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIJSONDataProvider() {
		return JsonReaderUtil.loadJSON("testData/CreateJobAPIData.json", CreateJobPayload[].class);	
	}
}
