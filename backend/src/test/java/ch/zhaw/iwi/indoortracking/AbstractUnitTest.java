package ch.zhaw.iwi.indoortracking;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

public class AbstractUnitTest {

	private static final Injector injector = Guice.createInjector(new JpaPersistModule("AssessmentTool"));

	public static Injector getInjector() {
		return injector;
	}
	
}
