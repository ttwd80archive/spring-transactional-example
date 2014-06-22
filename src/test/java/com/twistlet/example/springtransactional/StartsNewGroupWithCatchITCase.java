package com.twistlet.example.springtransactional;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public class StartsNewGroupWithCatchITCase extends
		AbstractJUnit4SpringContextTests {

}
