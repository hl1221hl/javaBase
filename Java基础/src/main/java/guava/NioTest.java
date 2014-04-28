package guava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {
	public static void main(String[] args) {
		try {
			FileInputStream in = new FileInputStream(new File("d:/starttime-1393833517363.txt"));
			FileChannel channel = in.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(1024);
			int size = -1;
			while ((size = channel.read(bb))!=-1) {
				bb.flip();
				byte[] bs = new byte[bb.remaining()];
				bb.get(bs);
				System.out.println(new String(bs));
				bb.clear();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
