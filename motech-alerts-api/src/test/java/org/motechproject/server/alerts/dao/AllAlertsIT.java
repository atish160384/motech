package org.motechproject.server.alerts.dao;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.server.alerts.domain.Alert;
import org.motechproject.server.alerts.domain.AlertStatus;
import org.motechproject.server.alerts.domain.AlertType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContextAlert.xml"})
public class AllAlertsIT {
	
	@Autowired
	private AllAlerts allAlerts;
	
	private Alert alert1 = new Alert();
	private Alert alert2 = new Alert();
	private Alert alert3 = new Alert();

    private static int MAX_RECORDS = 10;

	@Before
	public void setUp() {
        alert1.setId("1");
		alert1.setName("Test Alert 1");
		alert1.setDescription("Test Alert1 Description");
		alert1.setDateTime(new DateTime());
		alert1.setExternalId("111");
		alert1.setPriority(2);
		alert1.setStatus(AlertStatus.NEW);
		alert1.setAlertType(AlertType.HIGH);

        alert2.setId("2");
        alert2.setName("Test Alert 2");
        alert2.setDescription("Test Alert2 Description");
        alert2.setDateTime(new DateTime());
		alert2.setExternalId("222");
		alert2.setPriority(1);
		alert2.setStatus(AlertStatus.CLOSED);
		alert2.setAlertType(AlertType.CRITICAL);
        
        alert3.setId("3");
		alert3.setName("Test Alert 3");
		alert3.setDescription("Test alert3 Description");
		alert3.setDateTime(new DateTime());
		alert3.setExternalId("111");
		alert3.setPriority(1);
		alert3.setStatus(AlertStatus.NEW);
		alert3.setAlertType(AlertType.CRITICAL);

		allAlerts.add(alert1);
        allAlerts.add(alert2);
	}
	
	@After
	public void tearDown() {
		allAlerts.remove(alert1);
		allAlerts.remove(alert2);
        if (allAlerts.contains("3")) allAlerts.remove(alert3);
	}

	@Test
	public void shouldReturnAllAlertsWithoutAnyFilter() {
		List<Alert> listAlerts = allAlerts.listAlerts(null, null, null, null, MAX_RECORDS);
        assertEquals(2, listAlerts.size());
	}

    @Test
    public void shouldReturnMaxNumberOfAlertsWithoutAnyFilter() {
        List<Alert> listAlerts = allAlerts.listAlerts(null, null, null, null, 1);
        assertEquals(1, listAlerts.size());
    }

    @Test
    public void shouldFilterAlertsBasedOnExternalId() {
        List<Alert> listAlerts = allAlerts.listAlerts("222", null, null, null, MAX_RECORDS);
        assertEquals(1, listAlerts.size());
        assertEquals("2", listAlerts.get(0).getId());
    }

    @Test
    public void shouldFilterAlertsBasedOnAlertType() {
        List<Alert> listAlerts = allAlerts.listAlerts(null, AlertType.CRITICAL, null, null, MAX_RECORDS);
        assertEquals(1, listAlerts.size());
        assertEquals("2", listAlerts.get(0).getId());
    }

    @Test
    public void shouldFilterAlertsBasedOnAlertStatus() {
        List<Alert> listAlerts = allAlerts.listAlerts(null, null, AlertStatus.NEW, null, MAX_RECORDS);
        assertEquals(1, listAlerts.size());
        assertEquals("1", listAlerts.get(0).getId());
    }

    @Test
    public void shouldFilterAlertsBasedOnAlertPriority() {
        List<Alert> listAlerts = allAlerts.listAlerts(null, null, null, 1, MAX_RECORDS);
        assertEquals(1, listAlerts.size());
        assertEquals("2", listAlerts.get(0).getId());
    }

    @Test
    public void shouldFindAlertsWithOneOrMoreMatchingFilterCriteria() {
        allAlerts.add(alert3);
        
        List<Alert> listAlerts = allAlerts.listAlerts("111", AlertType.CRITICAL, null, null, MAX_RECORDS);
        assertEquals(1, listAlerts.size());
        assertEquals("3", listAlerts.get(0).getId());
    }

    @Test
    public void shouldFindAlertsWithoutAnyMatchingFilterCriteria() {
        allAlerts.add(alert3);

        List<Alert> listAlerts = allAlerts.listAlerts("333", AlertType.CRITICAL, null, null, MAX_RECORDS);
        assertEquals(0, listAlerts.size());
    }

    @Test
    public void shouldSortAlertsWhenNoFiltersAreSprecified() {
        List<Alert> listAlerts = allAlerts.listAlerts(null, null, null, null, MAX_RECORDS);
        assertEquals(2, listAlerts.size());
        assertEquals("2", listAlerts.get(0).getId());
        assertEquals("1", listAlerts.get(1).getId());
    }

    @Test
    public void shouldSortAlertsWhenFiltersAreSprecified() {
        allAlerts.add(alert3);
        
        List<Alert> listAlerts = allAlerts.listAlerts("111", null, AlertStatus.NEW, null, MAX_RECORDS);
        assertEquals(2, listAlerts.size());
        assertEquals("3", listAlerts.get(0).getId());
        assertEquals("1", listAlerts.get(1).getId());
    }

}
