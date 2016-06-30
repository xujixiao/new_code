package com.test.xujixiao.xjx.util;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownLoader {
	public static final int START_DOWNLOAD = 29;
	public static final int UPDATE_DOWNLOAD_PROGRESS = 28;
	public static final int CLOSE_PROGRESSDIALOG = 27;
	public static final int DOWN_FILE_SUCCESS = 26;
	public static final int DOWN_FILE_ERROR = 25;
    private static String fileName;


    public static boolean openSDCard() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	public static int downloadFile(Handler handler, String urlPath) {
		try {
			int downLength = 0, totalLength = 0;

			handler.sendMessage(createHandlerMessage(START_DOWNLOAD,
					downLength, totalLength));


			String sdpath = Environment.getExternalStorageDirectory()
			+ "/download";

			File file = new File(sdpath);
			if (!file.exists()) {
				file.mkdirs();
			}

			File saveFile = new File(file, fileName);
			if (saveFile.exists()) {
				saveFile.delete();
			}

			URL url = new URL(urlPath);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setConnectTimeout(5 * 1000);

			InputStream is =  http.getInputStream();
			totalLength = http.getContentLength();
			FileOutputStream fos = new FileOutputStream(saveFile);
			byte[] buf = new byte[1024];
			http.connect();
			if (http.getResponseCode() >= 400) {

			} else {
				while (true) {
					if (is != null) {
						int numRead = is.read(buf);
						if (numRead <= 0) {
							break;
						} else {
							downLength += numRead;
							fos.write(buf, 0, numRead);
							handler.sendMessage(createHandlerMessage(
									UPDATE_DOWNLOAD_PROGRESS,
									downLength, totalLength));
						}

					}

				}
				if (downLength == totalLength) {
					return DOWN_FILE_SUCCESS;
				}
			}

			http.disconnect();
			fos.close();
			is.close();
		} catch (Exception e) {
		}

		return DOWN_FILE_ERROR;
	}

	public static Message createHandlerMessage(int what, int downlength,
											   int totallength) {
		Message msg = new Message();
		msg.what = what;
		msg.arg1 = downlength;
		msg.arg2 = totallength;
		return msg;
	}
}
