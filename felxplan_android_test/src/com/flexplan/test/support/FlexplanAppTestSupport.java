package com.flexplan.test.support;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import com.flexplan.FlexplanApplication;
import com.flexplan.persistence.FlextimeDB;

public class FlexplanAppTestSupport {

	public static FlexplanApplication createMockApp() {
		FlexplanApplication app = mock(FlexplanApplication.class);
		FlextimeDB db = FlextimeDBTestSupport.createFlextimeDB();
		
		when(app.getDbHelper()).thenReturn(db);
		
		return app;
	}

}
