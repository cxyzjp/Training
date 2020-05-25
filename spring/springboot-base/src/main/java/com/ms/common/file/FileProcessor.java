package com.ms.common.file;

public interface FileProcessor {
	boolean processByLine(String filePath, LineProcessor processor);
}
