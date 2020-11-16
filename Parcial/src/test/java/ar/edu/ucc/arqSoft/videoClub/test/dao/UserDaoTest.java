package ar.edu.ucc.arqSoft.videoClub.test.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:spring/applicationContext.xml" })
@Transactional
public class UserDaoTest {
	private static final Logger logger = LogManager.getLogger(UserDaoTest.class);
	
	@Autowired
	private UserDao userDao;

	@Test
	public void testFindById() {
		Long uno = (long) 1;
		logger.info("Test de busqueda de Alquiler por ID");
		User user = userDao.load((long) 1);
		
		Assert.assertEquals(uno, user.getId());
		return;
	}

}
