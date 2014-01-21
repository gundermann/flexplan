package com.flexplan.test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import com.flexplan.FlexplanApplication;
import com.flexplan.persistence.FlextimeDB;

public class FlexplanAppTestHelper {

	public static FlexplanApplication createMockApp() {
		FlexplanApplication app = mock(FlexplanApplication.class);
		FlextimeDB db = FlextimeDBTestHelper.createFlextimeDB();
		
		when(app.getDbHelper()).thenReturn(db);
		
		return app;
	}

}
