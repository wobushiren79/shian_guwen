package cn.ifavor.cycleviewpager.cache.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class StreamUtils {
	public static String InputStreamToString(InputStream is, Charset charset)
			throws IOException {

		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;

			while ((len = is.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, len);
			}


			byte[] newBuffer = baos.toByteArray();

			String temp = new String(newBuffer, 0, newBuffer.length, charset);

			return temp;
		} finally {

			if (baos != null)
				baos.close();

			if (is != null)
				is.close();
		}
	}
}
