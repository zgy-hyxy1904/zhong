package com.oracle.cmp.service;

import java.util.List;
import java.util.Map;


import com.oracle.cmp.dao.PartsRepertoryDao;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.PartsRepBill;
import com.oracle.cmp.entity.PartsRepertory;

public class PartsRepertoryService {
	public List<PartsRepertory> query(Map<String,Object> map,int pageNo,int pageSize) {
		PartsRepertoryDao dao = new PartsRepertoryDao();
		return dao.selectForPage(map,pageNo,pageSize);
	}
	public List<PartsRepertory> query(Map<String,Object> map) {
		PartsRepertoryDao dao = new PartsRepertoryDao();
		return dao.select(map);
	}
	
	public PartsRepertory queryId(int PartsRepId) {
		PartsRepertoryDao dao = new PartsRepertoryDao();
		return dao.selectById(PartsRepId);
	}
	
	public int count(Map<String,Object> map) {
		PartsRepertoryDao dao = new PartsRepertoryDao();
		return dao.count(map);
	}
	public void update(PartsRepertory partsRepertory) {
		PartsRepertoryDao dao = new PartsRepertoryDao();
		dao.update(partsRepertory);
	}
	public boolean repertory(PartsRepBill prb){
		if("O".equals(prb.getBillflag())) {
			PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
			PartsRepertory partsRepertory = partsRepertoryService.queryId(prb.getParts().getPartsId());
			int count = partsRepertory.getPartsRepCount();
			if(count<prb.getBillcount()) {
				return false;
			}else if(count>=prb.getBillcount()) {
				PartsRepBillService partsRepBillService = new PartsRepBillService();
				partsRepBillService.insert(prb);
				PartsRepertoryService partsRepertoryService1 = new PartsRepertoryService();
				PartsRepertory partsRepertory1 = new PartsRepertory();
				int Billcount = prb.getBillcount(); 
				Billcount = count-prb.getBillcount();
				partsRepertory1.setPartsRepCount(Billcount);
				Parts parts1 = new Parts();
				parts1.setPartsId(prb.getParts().getPartsId());
				partsRepertory1.setParts(parts1);
				partsRepertoryService1.update(partsRepertory1);
			}
		}else if("I".equals(prb.getBillflag())){
			PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
			PartsRepertory partsRepertory = partsRepertoryService.queryId(prb.getParts().getPartsId());
			int count = partsRepertory.getPartsRepCount();
			PartsRepBillService partsRepBillService = new PartsRepBillService();
			partsRepBillService.insert(prb);
			PartsRepertoryService partsRepertoryService1 = new PartsRepertoryService();
			PartsRepertory partsRepertory1 = new PartsRepertory();
			int Billcount = prb.getBillcount(); 
			Billcount = count+prb.getBillcount();
			partsRepertory1.setPartsRepCount(Billcount);
			Parts parts1 = new Parts();
			parts1.setPartsId(prb.getParts().getPartsId());
			partsRepertory1.setParts(parts1);
			partsRepertoryService1.update(partsRepertory1);
			
		}
		return true;
	}
}
