package guava;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.base.Preconditions;

public class PreconditionsTest {
	@Test
	public void preconditionstest() throws Exception {
		try {
			Preconditions.checkArgument(1>2);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
		try {
			Preconditions.checkState(2>1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
}
