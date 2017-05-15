package com.snowland.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.simple.JSONArray;


public class QuestionStore {
	private String root;
	private List<Question> store;
	
	public QuestionStore() {
		store = new LinkedList<Question>();
		try {
			initRoot();
			initQuestionStore();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Question> getStore() {
		return store;
	}


	public void setStore(List<Question> store) {
		this.store = store;
	}


	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

//	public static void main(String[] args) {
//		
//		try {
//			q.initRoot();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	private void initRoot() throws IOException,DocumentException{
		InputStream in = null;
		SAXReader reader = new SAXReader();
		InputStreamReader isr =null;
		try {
			in =new FileInputStream("src\\main\\java\\settings.xml");
			isr = new InputStreamReader(in, "UTF-8");
			//使用read方法将输入流加载到SAXBuilder中获得xml的Document对象
			Document document = reader.read(isr);
			//获取根节点Location
			Element rootElement = document.getRootElement();
			List<Element>list = rootElement.elements();
			for(Element each:list){
				if (each.getName() == "root"){
					root = (String) each.getData();
					return;
				}
			}
		} catch(IOException ioe){
			ioe.printStackTrace();
			throw ioe;
		} catch (DocumentException doce) {
			doce.printStackTrace();
			throw doce;
		} finally {
			isr.close();
			in.close();
		}
	}
	private void initQuestionStore() throws DocumentException {
		initQuestionStore(root);
	}
	
	public void initQuestionStore(String dir) {
		File file = new File(dir);
		if(file.isDirectory()){
			String[] subFile = file.list();
			for(String eachFile : subFile){
				initQuestionStore(dir+"/"+eachFile);
			}
		} else {
			
			Question questionItem = new Question();
			try {
				questionItem.initByFile(dir);
				store.add(questionItem);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	
	public static void main(String[] args) {
		QuestionStore q = new QuestionStore();
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(q.store);
		System.out.println(jsonArray.toString());
	}
}
