package guava;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Throwables;

public class ThrowablesTest {
	@Test
	public void throwablesTest() throws Exception {
		String s = null;
		try {
			s.getBytes();
		} catch (Exception e) {
			List<Throwable> list = Throwables.getCausalChain(e);
			Throwable root = Throwables.getRootCause(e);
			System.out.println(Throwables.getStackTraceAsString(e));
			Throwables.propagateIfInstanceOf(e, IllegalArgumentException.class);
			Throwables.propagate(e);
		}
	}
}
