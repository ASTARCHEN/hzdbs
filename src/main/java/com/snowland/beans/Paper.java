package com.snowland.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Paper {
	public Paper() {
		questionList = new ArrayList<Question>();
	}
	private List<Question> questionList;

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	
	public void createPaper(int number) {
		QuestionStore store = new QuestionStore();
		List<Question> list = store.getStore();
		List<Question> ready = new ArrayList<Question>();
		int[] index = randperm(list.size(), number);
		for (int i : index) {
			ready.add(list.get(i));
		}
		questionList.clear();
		questionList.addAll(ready);
	}

	public int[] randperm(int n, int num) {
		double[] a = new double[n];
		for (int i = 0; i < n; ++i) {
			a[i] = Math.random();
		}

		int[] b = psort(a);
		return Arrays.copyOf(b, num);
	}

	private int[] psort(double a[]) {
		int n = a.length;
		int[] b = new int[n];
		int i, j;
		double temp;
		for (j = 0; j < n - 1; j++) {
			b[j] = j;
			for (i = j + 1; i < n; i++) {
				if (a[j] > a[i]) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
					b[j] = i;
				}
			}
		}
		return b;
	}
}
