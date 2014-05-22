package br.com.encontroFacil;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import br.com.encontroFacil.view.base.BasePage;
import br.com.encontroFacil.wicket.WicketApplication;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(BasePage.class);

		//assert rendered page class
		tester.assertRenderedPage(BasePage.class);
	}
}
