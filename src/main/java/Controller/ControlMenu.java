package Controller;

import entity.EmployeeEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlMenu {
    static EmployeeEntity employeeEntity;
    private EntityManagerFactory getEntityManagerFactory(String password) {
        return Persistence.createEntityManagerFactory( "default",
                getProperties(password) );
    }

    private Map getProperties(String password) {
        Map result = new HashMap();

        result.put( "javax.persistence.jdbc.password", password );

        return result;
    }

    public void addEmployee(String name, String lastName,String password){
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            employeeEntity = new EmployeeEntity();
            employeeEntity.setFirstName(name);
            employeeEntity.setLastName(lastName);
            entityManager.persist(employeeEntity);
            System.out.println("Employee with specified values have been added. The id of employee is: " + employeeEntity.getId());
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void showTheEmployee(String password){
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT * FROM employee",EmployeeEntity.class);
            List list = query.getResultList();
            if (list.isEmpty()){
                System.out.println("There are not any employees to list");
            }
            else{
                for (Object e: list) {
                    System.out.println(e);

                }

            }


            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}
