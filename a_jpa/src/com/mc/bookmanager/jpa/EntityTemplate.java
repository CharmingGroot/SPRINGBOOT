package com.mc.bookmanager.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTemplate {

	// EntityManagerFactory �� thread safe �ϱ� ������ static�� �÷����� ����ϰų� singleTon ���� ����ص� ����
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("a_jpa");
	
	
	// EntityManager �� thread safe���� �ʱ� ������ �ݵ�� �������� ����
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	
}
