package com.ms.common.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessorImpl implements FileProcessor {

	@Override
	public boolean processByLine(String filePath, LineProcessor processor) {
		String line = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			line = br.readLine();
			processor.doSomeThing(line.trim());
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		boolean isEnd = (line == null ? true : false);
		return isEnd;
	}
}
