package com.flexplan.test.support;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import com.flexplan.FlexplanApplication;
import com.flexplan.persistence.FlextimeDBHelper;

public class FlexplanAppTestSupport {

	public static FlexplanApplication createMockApp() {
		FlexplanApplication app = mock(FlexplanApplication.class);
		FlextimeDBHelper db = FlextimeDBTestSupport.createFlextimeDB();
		
		when(app.getFlextimeDB()).thenReturn(db);
		
		return app;
	}

}
