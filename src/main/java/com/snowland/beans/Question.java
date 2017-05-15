package com.snowland.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.snowland.util.FileReaderHelper;

public class Question {
	private String type;//试题类型 ：填空题，选择题
	private String unit;// 所在章节
	private List<String> ansaw;
	private String stem;
	
	public Question() {
		// TODO Auto-generated constructor stub
		ansaw = new ArrayList<String>();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public List<String> getAnsaw() {
		return ansaw;
	}
	public void setAnsaw(List<String> ansaw) {
		this.ansaw = ansaw;
	}
	public String getStem() {
		return stem;
	}
	public void setStem(String stem) {
		this.stem = stem;
	}
	
	public void initByFile(String dir)throws IOException{
		String[] splitPath = dir.split("[\\|/]");
		int endIndex = splitPath.length - 1;
		Question question = new Question();
		this.setType("1");
		this.setUnit(splitPath[endIndex-1]);
		OutputStream outputStream = null;
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;

			String mystem = FileReaderHelper.readFileByLines(dir);
	// TODO
			String [] splitMyStem = mystem.split("【|】");
			StringBuffer stemBuffer = new StringBuffer();
			if((splitMyStem.length&1) == 1){
				for (int i = 0; i < splitMyStem.length - 1; i+=2){
					stemBuffer.append(splitMyStem[i]);
					ansaw.add(splitMyStem[i+1]);
					stemBuffer.append("(\t)");
				}
				this.stem = stemBuffer.toString();
			} else {
				for (int i = 0; i < splitMyStem.length; i+=2){
					stemBuffer.append("(\t)");
					ansaw.add(splitMyStem[i+1]);
					stemBuffer.append(splitMyStem[i]);		
				}
				this.stem = stemBuffer.toString();
			}
	
	}
	
	@Override
	public String toString(){
		Map <String,String> map= new HashMap<String,String>();
		map.put("type", type);
		map.put("stem", stem);
		map.put("ansaw", ansaw.toString());
		map.put("unit", unit);
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);
		return jsonObject.toString();
	}
	public void initByFile(File file)throws Exception{
		try{
		initByFile(file.getAbsolutePath());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
