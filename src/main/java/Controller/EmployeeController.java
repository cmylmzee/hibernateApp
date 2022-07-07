package Controller;

import entity.DepartmentEntity;
import entity.EmployeeEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController {

    static EmployeeEntity employeeEntity;

    private EntityManagerFactory getEntityManagerFactory(String password) {
        return Persistence.createEntityManagerFactory("default",
                getProperties(password));
    }

    private Map getProperties(String password) {
        Map result = new HashMap();

        result.put("javax.persistence.jdbc.password", password);

        return result;
    }

    public void addEmployee(String name, String lastName, int departmentId,String password) {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            employeeEntity = new EmployeeEntity();
            employeeEntity.setFirstName(name);
            employeeEntity.setLastName(lastName);
            Query query = entityManager.createNativeQuery("SELECT * FROM department  where id ="+ departmentId, DepartmentEntity.class);

            System.out.println(query.getResultList());
            employeeEntity.setDept((DepartmentEntity) query.getSingleResult());
            entityManager.persist(employeeEntity);
            System.out.println("Employee with specified values have been added. The id of employee is: " + employeeEntity.getId());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    /*
    -> getDept select * from Dept where dept id = 1
    return Departmen tipinde bir obje döncek
    * */

    public void showTheEmployee(String password) {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT * FROM employee", EmployeeEntity.class);
            List list = query.getResultList();
            if (list.isEmpty()) {
                System.out.println("There are not any employees to list");
            } else {
                for (Object e : list) {
                    System.out.println(e);

                }

            }


            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}
